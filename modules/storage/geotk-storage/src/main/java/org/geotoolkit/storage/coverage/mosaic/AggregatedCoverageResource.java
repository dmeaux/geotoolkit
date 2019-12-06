/*
 *    Geotoolkit.org - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2019, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotoolkit.storage.coverage.mosaic;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.RenderedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.measure.Unit;
import org.apache.sis.coverage.Category;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.DisjointExtentException;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridExtent;
import org.apache.sis.coverage.grid.GridGeometry;
import org.apache.sis.coverage.grid.IncompleteGridGeometryException;
import org.apache.sis.geometry.Envelopes;
import org.apache.sis.geometry.GeneralEnvelope;
import org.apache.sis.image.PixelIterator;
import org.apache.sis.image.WritablePixelIterator;
import org.apache.sis.metadata.iso.DefaultMetadata;
import org.apache.sis.referencing.CRS;
import org.apache.sis.referencing.operation.transform.MathTransforms;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.GridCoverageResource;
import org.apache.sis.storage.NoSuchDataException;
import org.apache.sis.storage.Resource;
import org.apache.sis.storage.WritableAggregate;
import org.apache.sis.storage.event.StoreEvent;
import org.apache.sis.storage.event.StoreListener;
import org.apache.sis.storage.event.StoreListeners;
import org.apache.sis.util.ArgumentChecks;
import org.apache.sis.util.collection.FrequencySortedSet;
import org.geotoolkit.coverage.grid.EstimatedGridGeometry;
import org.geotoolkit.coverage.grid.GridCoverageBuilder;
import org.geotoolkit.coverage.io.DisjointCoverageDomainException;
import org.geotoolkit.geometry.jts.JTSEnvelope2D;
import org.geotoolkit.image.BufferedImages;
import org.geotoolkit.image.interpolation.InterpolationCase;
import org.geotoolkit.internal.coverage.CoverageUtilities;
import org.geotoolkit.storage.event.AggregationEvent;
import org.geotoolkit.storage.event.ContentEvent;
import org.geotoolkit.storage.event.ModelEvent;
import org.locationtech.jts.index.quadtree.Quadtree;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.metadata.Metadata;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.datum.PixelInCell;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;
import org.opengis.util.GenericName;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public final class AggregatedCoverageResource implements WritableAggregate, GridCoverageResource {

    public static enum Mode {
        /**
         * Coverage will be generated by progressively aggregating resource is the user defined order.
         * This mode should be used to merge data by there quality order.
         */
        ORDER,
        /**
         * Resources will be sorted by scale and combined is a smart way by requested resolution at reading.
         * This mode should be used when all data represent the same information but in different
         * scales and areas.
         */
        SCALE
    }

    /**
     * Based on sample dimension information, we can define
     * the most appropriate merge operation.
     */
    private static enum MergingMode {
        COLORED,
        SAMPLED,
        CONVERTED
    }

    private final StoreListeners listeners = new StoreListeners(null, this);
    private final List<VirtualBand> bands = new ArrayList<>();

    //user defined parameters
    private Mode mode = Mode.SCALE;
    private InterpolationCase interpolation = InterpolationCase.BILINEAR;
    private CoordinateReferenceSystem outputCrs = null;

    //computed informations
    private GenericName identifier;
    private Quadtree tree;
    private GridGeometry cachedGridGeometry;
    private boolean forceTransformedValues;
    private double[] noData;
    private MergingMode mergeMode;


    public static GridCoverageResource create(CoordinateReferenceSystem resultCrs, GridCoverageResource ... resources) throws DataStoreException, TransformException {
        return create(resultCrs, Mode.ORDER, resources);
    }

    public static GridCoverageResource create(CoordinateReferenceSystem resultCrs, Mode mode, GridCoverageResource ... resources) throws DataStoreException, TransformException {
        if (resources.length == 0) {
            throw new DataStoreException("No resources to aggregate");
        } else if (resources.length == 1) {
            return resources[0];
        } else {
            return new AggregatedCoverageResource(toMapping(Arrays.asList(resources)), mode, resultCrs);
        }
    }

    public AggregatedCoverageResource(List<VirtualBand> bands, Mode mode, CoordinateReferenceSystem resultCrs) throws DataStoreException, TransformException {
        this.bands.addAll(bands);
        this.mode = mode;
        this.outputCrs = resultCrs;
        initModel();
    }

    public AggregatedCoverageResource() {
    }

    @Override
    public Optional<GenericName> getIdentifier() throws DataStoreException {
        return Optional.ofNullable(identifier);
    }

    public void setIdentifier(GenericName identifier) {
        this.identifier = identifier;
    }

    @Override
    public Metadata getMetadata() throws DataStoreException {
        return new DefaultMetadata();
    }

    @Override
    public synchronized GridGeometry getGridGeometry() throws DataStoreException {
        initModel();
        return cachedGridGeometry;
    }

    @Override
    public synchronized List<SampleDimension> getSampleDimensions() throws DataStoreException {
        initSampleDimensions();
        final List<SampleDimension> dims = new ArrayList<>(bands.size());
        for (VirtualBand vb : bands) {
            dims.add(vb.cachedSampleDimension);
        }
        return dims;
    }

    /**
     * Force sample dimensions.
     *
     * @param sampleDimensions
     */
    public synchronized void setSampleDimensions(List<SampleDimension> sampleDimensions) throws DataStoreException {
        eraseCaches();
        if (bands.isEmpty()) {
            //create virtual bands with no datas
            for (SampleDimension sd : sampleDimensions) {
                final VirtualBand band = new VirtualBand();
                band.userDefinedSampleDimension = sd;
                bands.add(band);
            }
        } else if (bands.size() != sampleDimensions.size()) {
            throw new DataStoreException("Provided dimensions do not match virtual bands size");
        }
        for (int i = 0,n = sampleDimensions.size(); i < n; i++) {
            bands.get(i).userDefinedSampleDimension = sampleDimensions.get(i);
        }
    }

    @Override
    public Optional<Envelope> getEnvelope() throws DataStoreException {
        final GridGeometry grid = getGridGeometry();
        if (grid.isDefined(GridGeometry.ENVELOPE)) {
            return Optional.of(grid.getEnvelope());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Collection<? extends Resource> components() throws DataStoreException {
        final Set<GridCoverageResource> components = new HashSet<>();
        for (VirtualBand band : bands) {
            for (Source source : band.sources) {
                components.add(source.resource);
            }
        }
        return components;
    }

    /**
     * Remove resource from aggregation.
     * This method does not delete any data.
     *
     * @param resource resource to remove
     * @throws DataStoreException
     */
    @Override
    public synchronized void remove(Resource resource) throws DataStoreException {
        remove(resource, true);
    }

    private synchronized void remove(Resource resource, boolean sendEvent) throws DataStoreException {
        ArgumentChecks.ensureNonNull("resource", resource);
        boolean found = false;
        for (VirtualBand band : bands) {
            final ListIterator<Source> ite = band.sources.listIterator();
            while (ite.hasNext()) {
                Source source = ite.next();
                if (source.resource == resource) {
                    found = true;
                    ite.remove();
                }
            }
        }
        if (!found) {
            throw new DataStoreException("Resource not found");
        }
        if (sendEvent) {
            listeners.fire(new AggregationEvent(this, AggregationEvent.TYPE_REMOVE, resource), AggregationEvent.class);
        }
        eraseCaches();
    }

    /**
     * Add a new resource in the aggregation.
     * The resource is not copied.
     *
     * @param resource
     * @return the same resource
     * @throws DataStoreException if new resource is incompatible with other resources.
     */
    @Override
    public synchronized Resource add(Resource resource) throws DataStoreException {
        if (resource instanceof GridCoverageResource) {
            final GridCoverageResource gcr = (GridCoverageResource) resource;
            if (bands.isEmpty()) {
                //first resource in the aggregation
                bands.addAll(toMapping(Arrays.asList(gcr)));
                eraseCaches();
                listeners.fire(new AggregationEvent(this, AggregationEvent.TYPE_ADD, resource), AggregationEvent.class);
                return resource;
            }

            final List<SampleDimension> sampleDimensions = gcr.getSampleDimensions();
            if (sampleDimensions.size() != bands.size()) {
                throw new DataStoreException("Coverage sample dimension size " + sampleDimensions.size() + "do not match bands size " + bands.size());
            }

            for (int i = 0, n = sampleDimensions.size(); i < n; i++) {
                final VirtualBand band = bands.get(i);
                band.sources.add(new Source(gcr, i));
            }

            eraseCaches();
            try {
                initModel();
            } catch (DataStoreException ex) {
                //restore to an usable state
                remove(resource, false);
                throw ex;
            }
            listeners.fire(new AggregationEvent(this, AggregationEvent.TYPE_ADD, resource), AggregationEvent.class);
            return resource;
        } else {
            throw new DataStoreException("Resource must be an instance of GridCoverageResource");
        }
    }

    /**
     * Returns preferred interpolation method when aggregating data.
     * @return interpolation, not null
     */
    public InterpolationCase getInterpolation() {
        return interpolation;
    }

    /**
     * Set preferred interpolation method when aggregating data.
     * @param interpolation not null
     * @return
     */
    public void setInterpolation(InterpolationCase interpolation) {
        ArgumentChecks.ensureNonNull("interpolation", interpolation);
        this.interpolation = interpolation;
    }

    /**
     * Returns aggregation ordering.
     *
     * @return order mode, not null
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * Set aggregation ordering mode.
     *
     * @param mode , not null
     */
    public void setMode(Mode mode) {
        ArgumentChecks.ensureNonNull("mode", mode);
        this.mode = mode;
    }

    /**
     * Returns preferred crs when aggregating data.
     *
     * @return may be null
     */
    public CoordinateReferenceSystem getOutputCrs() {
        return outputCrs;
    }

    /**
     * Set preferred crs when aggregating data.
     *
     * @param outputCrs, may be null
     */
    public synchronized void setOutputCrs(CoordinateReferenceSystem outputCrs) {
        if (this.outputCrs == outputCrs) return;
        this.outputCrs = outputCrs;
        eraseCaches();
    }

    /**
     * Compute grid geometry and sample dimensions.
     */
    private synchronized void initModel() throws DataStoreException {
        initGridGeometry();
        initSampleDimensions();
    }

    private void initGridGeometry() throws DataStoreException {
        if (cachedGridGeometry != null) return;

        tree = new Quadtree();

        final Set<GridCoverageResource> components = new HashSet<>();
        for (VirtualBand band : bands) {
            for (Source source : band.sources) {
                components.add(source.resource);
            }
        }

        if (components.isEmpty()) {
            //no data yet
            this.cachedGridGeometry = new GridGeometry(new GridExtent(1, 1), null);
            return;
        } else if (components.size() == 1) {
            //copy exact definition
            GridCoverageResource resource = components.iterator().next();
            this.cachedGridGeometry = resource.getGridGeometry();
            return;
        }

        if (outputCrs == null) {
            //use most common crs
            //TODO find a better approach to determinate a common crs
            final FrequencySortedSet<CoordinateReferenceSystem> map = new FrequencySortedSet<>();
            for (GridCoverageResource resource : components) {
                map.add(resource.getGridGeometry().getCoordinateReferenceSystem());
            }
            outputCrs = map.last();
        }

        //compute envelope and check sample dimensions
        GeneralEnvelope env = new GeneralEnvelope(outputCrs);
        env.setToNaN();
        int index = 0;
        double[] estimatedResolution = new double[outputCrs.getCoordinateSystem().getDimension()];
        Arrays.fill(estimatedResolution, Double.POSITIVE_INFINITY);
        for (GridCoverageResource resource : components) {
            final GridGeometry componentGrid = resource.getGridGeometry();
            Envelope envelope = componentGrid.getEnvelope();
            try {
                envelope = Envelopes.transform(envelope, outputCrs);
            } catch (TransformException ex) {
                throw new DataStoreException(ex.getMessage(), ex);
            }
            tree.insert(new JTSEnvelope2D(envelope), resource);

            //try to find an estimated resolution
            if (estimatedResolution != null) {
                try {
                    final double[] est = CoverageUtilities.estimateResolution(componentGrid.getEnvelope(), componentGrid.getResolution(true), outputCrs);
                    for (int i=0; i < estimatedResolution.length; i++) {
                        estimatedResolution[i] = Math.min(estimatedResolution[i], est[i]);
                    }
                } catch (FactoryException | MismatchedDimensionException | TransformException | IncompleteGridGeometryException ex) {
                    estimatedResolution = null;
                }
            }

            if (env.isAllNaN()) {
                env.setEnvelope(envelope);
            } else {
                env.add(envelope);
            }
        }

        if (estimatedResolution != null) {
            cachedGridGeometry = new EstimatedGridGeometry(env, estimatedResolution);
        } else {
            cachedGridGeometry = new GridGeometry(null, env);
        }
    }

    private void initSampleDimensions() throws DataStoreException {
        if (bands.isEmpty()) return;
        if (bands.get(0).cachedSampleDimension != null) return;

        for (int i = 0, n = bands.size(); i < n; i++) {
            final VirtualBand band = bands.get(i);
            if (band.userDefinedSampleDimension != null) {
                band.cachedSampleDimension = band.userDefinedSampleDimension;
            } else {
                //compute sampleDimension
                boolean hasUndefinedUnits = false;
                SampleDimension dim = null;
                for (Source source : band.sources) {
                    final SampleDimension sourceBand = source.resource.getSampleDimensions().get(source.bandIndex);
                    if (dim == null) {
                        dim = sourceBand;
                    } else {
                        final Unit<?> baseUnit = dim.getUnits().orElse(null);
                        final Unit<?> unit = sourceBand.getUnits().orElse(null);
                        if (baseUnit == null) {
                            hasUndefinedUnits = true;
                            if (unit != null) {
                                //replace the declared unit, more accurate
                                dim = sourceBand;
                            }
                        } else if (unit == null) {
                            //we assume all datas have the implicite same unit
                            //if there is no conflict
                            hasUndefinedUnits = true;
                        } else {
                            if (!baseUnit.equals(unit)) {
                                throw new DataStoreException("Uncompatible sample dimensions, different units found : "+baseUnit +", "+unit);
                            }
                        }
                    }
                }
                if (dim == null) {
                    throw new DataStoreException("Sample dimension " + i + " is undefined");
                }
                band.cachedSampleDimension = dim;
            }
        }

        /*
         * Add a no-data category
         * the no-data is needed to fill possible gaps between coverages
         * TODO need to improve detection cases to avoid switching to transformed values all the time
         */
        forceTransformedValues = false;
        noData = new double[bands.size()];
        for (int i = 0,n = bands.size(); i < n; i++) {
            VirtualBand band = bands.get(i);
            SampleDimension baseDim = band.cachedSampleDimension;
            Set<Number> noData = baseDim.getNoDataValues();
            Optional<Number> background = baseDim.getBackground();
            if (noData.isEmpty() && !background.isPresent()) {
                baseDim = band.cachedSampleDimension.forConvertedValues(true);
                final SampleDimension.Builder builder = new SampleDimension.Builder();
                final Unit<?> unit = baseDim.getUnits().orElse(null);

                for (Category c : baseDim.getCategories()) {
                    if (c.isQuantitative()) {
                        builder.addQuantitative(c.getName(), c.getSampleRange(), c.getTransferFunction().orElse(null), unit);
                    } else {
                        builder.addQualitative(c.getName(), c.getSampleRange());
                    }
                }
                builder.setBackground(null, Double.NaN);
                baseDim = builder.build();
                noData = baseDim.getNoDataValues();
                background = baseDim.getBackground();
                band.cachedSampleDimension = baseDim;
                forceTransformedValues = true;
            }

            if (background.isPresent()) {
                this.noData[i] = background.get().doubleValue();
            } else {
                this.noData[i] = noData.iterator().next().doubleValue();
            }
        }
    }

    private void eraseCaches() {
        cachedGridGeometry = null;
        for (VirtualBand vb : bands) {
            vb.cachedSampleDimension = null;
        }
        tree = null;
        listeners.fire(new ModelEvent(this), ModelEvent.class);
        listeners.fire(new ContentEvent(this), ContentEvent.class);
    }

    @Override
    public GridCoverage read(GridGeometry domain, int... range) throws DataStoreException {
        initModel();

        final GridGeometry gridGeometry = getGridGeometry();
        if (domain == null) domain = gridGeometry;

        // List the bands requested ////////////////////////////////////////////
        final List<SampleDimension> sampleDimensions;
        final List<VirtualBand> bands;
        if (range.length != 0) {
            bands = new ArrayList<>();
            sampleDimensions = new ArrayList<>();
            for (int i : range) {
                bands.add(this.bands.get(i));
                sampleDimensions.add(this.bands.get(i).cachedSampleDimension.forConvertedValues(forceTransformedValues));
            }
        } else {
            bands = this.bands;
            sampleDimensions = new ArrayList<>();
            for (SampleDimension sd : getSampleDimensions()) {
                sampleDimensions.add(sd.forConvertedValues(forceTransformedValues));
            }
        }

        // Extract resources which intersect request ///////////////////////////
        Envelope envelope = domain.getEnvelope();
        try {
            envelope = Envelopes.transform(envelope, outputCrs);
        } catch (TransformException ex) {
            throw new DataStoreException(ex.getMessage(), ex);
        }
        final List<GridCoverageResource> treeResults = tree.query(new JTSEnvelope2D(envelope));

        if (treeResults == null || treeResults.isEmpty()) {
            throw new DisjointCoverageDomainException();
        }

        // Build expected GridGeometry /////////////////////////////////////////

        final GridGeometry canvas;
        if (gridGeometry.isDefined(GridGeometry.EXTENT)) {
            canvas = gridGeometry.derive().subgrid(domain).build();
        } else if (domain.isDefined(GridGeometry.EXTENT)) {
            canvas = domain;
        } else {
            throw new DataStoreException("Aggregated resource require a grid extent to be defined on the resource or on the requested domain");
        }


        // aggregate tiles /////////////////////////////////////////////////////
        final BufferedImage[] bandImages = new BufferedImage[bands.size()];
        for (int bandIndex = 0, n = bands.size(); bandIndex < n; bandIndex++) {
            final VirtualBand band = bands.get(bandIndex);

            //pick and sort resources as user mode requested
            final List<Source> sorted = new ArrayList<>();
            for (Source source : band.sources) {
                if (treeResults.contains(source.resource)) {
                    sorted.add(source);
                }
            }
            if (sorted.isEmpty()) {
                continue;
            }
            if (Mode.SCALE.equals(mode)) {
                //the most accurate order is to render in order coverages
                //with relative scale going from 1.0 to 0.0 then from 1.0 to +N
                //filling only the gaps at each coverage

                List<Source> ordered = new ArrayList<>();
                final Map<GridCoverageResource,Double> ratios = new HashMap<>();
                for (Source entry : sorted) {
                    try {
                        double ratio = estimateRatio(entry.resource.getGridGeometry(), canvas);
                        double order = ratio;
                        if (ratio <= 1.05) { //little tolerance du to crs deformations and math
                            order = 1.05 - ratio;
                        }
                        ratios.put(entry.resource, order);
                        ordered.add(entry);
                    } catch (DisjointExtentException ex) {
                        continue;
                    } catch (FactoryException | TransformException ex) {
                        continue;
                    }
                }
                ordered.sort((Source o1, Source o2) -> ratios.get(o1.resource).compareTo(ratios.get(o2.resource)));
                sorted.clear();
                sorted.addAll(ordered);
            }
            if (sorted.isEmpty()) {
                continue;
            }

            bandImages[bandIndex] = aggregate(sorted, canvas, bandIndex);
        }

        BufferedImage result = null;
        if (bandImages.length == 1) {
            result = bandImages[0];
        } else {
            result = BufferedImages.createImage(bandImages[0].getWidth(), bandImages[1].getHeight(), bandImages.length, DataBuffer.TYPE_DOUBLE);
            WritablePixelIterator writer = WritablePixelIterator.create(result);
            for (int bandIndex = 0; bandIndex < bandImages.length; bandIndex++) {
                final PixelIterator reader = PixelIterator.create(bandImages[bandIndex]);
                while (reader.next()) {
                    final Point pt = reader.getPosition();
                    writer.moveTo(pt.x, pt.y);
                    writer.setSample(bandIndex, reader.getSampleDouble(0));
                }
            }
        }


        final GridCoverageBuilder gcb = new GridCoverageBuilder();
        gcb.setName("Aggregated");
        gcb.setGridGeometry(canvas);
        gcb.setSampleDimensions(sampleDimensions);
        gcb.setRenderedImage(result);
        return gcb.build();
    }

    private BufferedImage aggregate(List<Source> ordered, final GridGeometry canvas, int bandIndex) throws DataStoreException {

        final double[] noData = new double[]{this.noData[bandIndex]};

        BufferedImage result = null;
        BufferedImage intermediate = null;

        //start by creating a mask of filled datas
        final BitSet2D mask = new BitSet2D((int) canvas.getExtent().getSize(0), (int) canvas.getExtent().getSize(1));

        for (Source source : ordered) {
            try {
                //check the mask if we have finish
                final GridExtent maskExtent = mask.areaCleared().orElse(null);
                if (maskExtent == null) break;
                final GridGeometry maskGrid = new GridGeometry(maskExtent, PixelInCell.CELL_CENTER, canvas.getGridToCRS(PixelInCell.CELL_CENTER), canvas.getCoordinateReferenceSystem());

                //expend grid geometry a little for interpolation
                GridGeometry readGridGeom;
                GridGeometry coverageGridGeometry = source.resource.getGridGeometry();
                if (coverageGridGeometry.isDefined(GridGeometry.EXTENT)) {
                    readGridGeom = coverageGridGeometry.derive()
                            .margin(5,5)
                            .subgrid(maskGrid)
                            .build();
                } else {
                    readGridGeom = maskGrid.derive().margin(5,5).build();
                }

                GridCoverage coverage = source.resource.read(readGridGeom, source.bandIndex).forConvertedValues(forceTransformedValues);
                if (coverage.getSampleDimensions().size() != 1) {
                    throw new DataStoreException(source.resource + " returned a coverage with more then one sample dimension, fix implementation");
                }
                final RenderedImage tileImage = coverage.render(null);

                final BufferedImage workImage;
                if (result == null) {
                    //create result image
                    GridExtent extent = canvas.getExtent();
                    int sizeX = Math.toIntExact(extent.getSize(0));
                    int sizeY = Math.toIntExact(extent.getSize(1));
                    result = BufferedImages.createImage(sizeX, sizeY, 1, DataBuffer.TYPE_DOUBLE);
                    workImage = result;
                    if (!MosaicedCoverageResource.isAllZero(noData)) BufferedImages.setAll(result, noData);
                } else {
                    if (intermediate == null) {
                        intermediate = BufferedImages.createImage(result.getWidth(), result.getHeight(), result);
                    }
                    workImage = intermediate;
                    if (!MosaicedCoverageResource.isAllZero(noData)) BufferedImages.setAll(intermediate, noData);
                }

                MosaicedCoverageResource.resample(coverage, tileImage, canvas, workImage);

               if (workImage != result) {
                    //we need to merge image, replacing only not-NaN values
                    PixelIterator read = PixelIterator.create(workImage);
                    WritablePixelIterator write = WritablePixelIterator.create(result);
                    final double[] pixelr = new double[read.getNumBands()];
                    final double[] pixelw = new double[read.getNumBands()];
                    while (read.next() & write.next()) {
                        read.getPixel(pixelr);
                        if (noData[0] == pixelr[0] || Double.isNaN(pixelr[0])) continue;
                        write.getPixel(pixelw);
                        if (noData[0] == pixelw[0] || Double.isNaN(pixelw[0])) {
                            write.setPixel(pixelr);
                            //fill the mask
                            Point pt = read.getPosition();
                            mask.set2D(pt.x, pt.y);
                        }
                    }
                } else {
                    //first resampled Image, fill the mask
                    PixelIterator read = PixelIterator.create(workImage);
                    final double[] pixelr = new double[read.getNumBands()];
                    while (read.next()) {
                        read.getPixel(pixelr);
                        if (!(noData[0] == pixelr[0] || Double.isNaN(pixelr[0]))) {
                            Point pt = read.getPosition();
                            mask.set2D(pt.x, pt.y);
                        }
                    }
                }

            } catch (NoSuchDataException | DisjointExtentException ex) {
                //may happen, enveloppe is larger then data or mask do not intersect anymore
                //quad tree may also return more results
            } catch (FactoryException ex) {
                throw new DataStoreException(ex.getMessage(), ex);
            } catch (TransformException ex) {
                throw new DataStoreException(ex.getMessage(), ex);
            }
        }

        return result;
    }


    @Override
    public <T extends StoreEvent> void addListener(Class<T> eventType, StoreListener<? super T> listener) {
        listeners.addListener(eventType, listener);
    }

    @Override
    public <T extends StoreEvent> void removeListener(Class<T> eventType, StoreListener<? super T> listener) {
        listeners.removeListener(eventType, listener);
    }

    /**
     * Estimate the ratio of coverage grid geometry intersection in target grid geometry.
     * The ratio is an estimation of the resolution difference from the target grid resolution.
     * A value lower then 1 means the coverage has a higher resolution then the target grid, coverage is more accurate.
     * A value higher then 1 means the coverage has a lower resolution then the target grid, coverage is less accurate.
     *
     * @param coverage
     * @param target
     * @return
     */
    private static double estimateRatio(GridGeometry coverage, GridGeometry target) throws FactoryException, TransformException {
        //intersect grid geometries
        final GridGeometry result = coverage.derive().subgrid(target.getEnvelope()).build();
        //compute transform
        final MathTransform gridToCRS = result.getGridToCRS(PixelInCell.CELL_CENTER);
        final CoordinateOperation crsToCrs = CRS.findOperation(result.getCoordinateReferenceSystem(), target.getCoordinateReferenceSystem(), null);
        final MathTransform trs = MathTransforms.concatenate(gridToCRS, crsToCrs.getMathTransform(), target.getGridToCRS(PixelInCell.CELL_CENTER).inverse());
        //transform a unitary vector at most representative point
        double[] point = result.getExtent().getPointOfInterest();
        double[] vector = Arrays.copyOf(point, 4);
        double diagonal = 1.0 / Math.sqrt(2); //for a vector of length = 1
        vector[2] = point[0] + diagonal;
        vector[3] = point[1] + diagonal;
        trs.transform(vector, 0, vector, 0, 2);
        double x = Math.abs(vector[0] - vector[2]);
        double y = Math.abs(vector[1] - vector[3]);
        return Math.sqrt(x*x + y*y);
    }


    private static List<VirtualBand> toMapping(List<GridCoverageResource> resources) throws DataStoreException {
        final List<VirtualBand> lst = new ArrayList();
        for (GridCoverageResource gcr : resources) {
            final List<SampleDimension> sampleDimensions = gcr.getSampleDimensions();
            for (int i = 0, n = sampleDimensions.size(); i < n; i++) {
                final VirtualBand band;
                if (i < lst.size()) {
                    band = lst.get(i);
                } else {
                    band = new VirtualBand();
                    lst.add(band);
                }
                band.sources.add(new Source(gcr, i));
            }
        }
        return lst;
    }

    public static class VirtualBand {
        private SampleDimension userDefinedSampleDimension;
        SampleDimension cachedSampleDimension;
        private final List<Source> sources = new ArrayList<>();

        public List<Source> getSources() {
            return Collections.unmodifiableList(sources);
        }

        public SampleDimension getSampleDimension() {
            return userDefinedSampleDimension;
        }

        public void setSampleDimension(SampleDimension userDefinedSampleDimension) {
            this.userDefinedSampleDimension = userDefinedSampleDimension;
        }
    }

    public static class Source {
        public final GridCoverageResource resource;
        public final int bandIndex;
        //leave space for possible futur parameters
        //public MapTransform1D sampleTransform;

        public Source(GridCoverageResource resource, int bandIndex) {
            this.resource = resource;
            this.bandIndex = bandIndex;
        }
    }
}
