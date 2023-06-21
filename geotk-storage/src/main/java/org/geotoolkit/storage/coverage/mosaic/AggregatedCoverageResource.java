/*
 *    Geotoolkit.org - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2019-2021, Geomatys
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

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.RenderedImage;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.measure.Quantity;
import javax.measure.Unit;
import org.apache.sis.coverage.Category;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.DisjointExtentException;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridCoverage2D;
import org.apache.sis.coverage.grid.GridCoverageProcessor;
import org.apache.sis.coverage.grid.GridExtent;
import org.apache.sis.coverage.grid.GridGeometry;
import org.apache.sis.coverage.grid.GridOrientation;
import org.apache.sis.coverage.grid.GridRoundingMode;
import org.apache.sis.coverage.grid.IncompleteGridGeometryException;
import org.apache.sis.geometry.Envelopes;
import org.apache.sis.geometry.GeneralEnvelope;
import org.apache.sis.image.ImageCombiner;
import org.apache.sis.image.Interpolation;
import org.apache.sis.image.PixelIterator;
import org.apache.sis.image.WritablePixelIterator;
import org.apache.sis.internal.storage.ResourceOnFileSystem;
import org.apache.sis.measure.Quantities;
import org.apache.sis.metadata.iso.DefaultMetadata;
import org.apache.sis.referencing.CRS;
import org.apache.sis.referencing.operation.matrix.Matrices;
import org.apache.sis.referencing.operation.matrix.MatrixSIS;
import org.apache.sis.referencing.operation.matrix.NoninvertibleMatrixException;
import org.apache.sis.referencing.operation.transform.LinearTransform;
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
import org.apache.sis.util.Utilities;
import org.apache.sis.util.collection.BackingStoreException;
import org.apache.sis.util.collection.FrequencySortedSet;
import org.apache.sis.util.resources.Vocabulary;
import org.geotoolkit.coverage.grid.EstimatedGridGeometry;
import org.geotoolkit.geometry.jts.JTSEnvelope2D;
import org.geotoolkit.image.BufferedImages;
import org.geotoolkit.internal.coverage.CoverageUtilities;
import org.geotoolkit.storage.DataStores;
import org.geotoolkit.storage.InterruptedStoreException;
import org.geotoolkit.storage.event.AggregationEvent;
import org.geotoolkit.storage.event.ContentEvent;
import org.geotoolkit.storage.event.ModelEvent;
import org.geotoolkit.util.NamesExt;
import org.geotoolkit.util.StringUtilities;
import org.geotoolkit.util.TriFunction;
import org.locationtech.jts.index.quadtree.Quadtree;
import org.opengis.coverage.grid.SequenceType;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.metadata.Metadata;
import org.opengis.metadata.spatial.DimensionNameType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.ImageCRS;
import org.opengis.referencing.datum.PixelInCell;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransform1D;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.NoninvertibleTransformException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;
import org.opengis.util.GenericName;

/**
 * View a collection of GridCoverageResource as a single one.
 *
 * @author Johann Sorel (Geomatys)
 */
public final class AggregatedCoverageResource implements WritableAggregate, GridCoverageResource {

    private static final Logger LOGGER = Logger.getLogger("org.geotoolkit.storage.coverage");

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

    /**
     * Compare by scale then by coverage area
     * if scales are equivalent, priorize data source with more overlap.
     */
    private static final Comparator<SourceToSort> SCALEAREA_COMPARATOR = (s1, s2) -> {
            int c = Double.compare(s1.scaleRatio, s2.scaleRatio);
            if (c == 0) c = Double.compare(s2.areaRatio, s1.areaRatio);
            return c;
        };

    private static final Set<String> COLORED_SAMPLE_NAMES = new HashSet<>();
    static {
        COLORED_SAMPLE_NAMES.add(Vocabulary.formatInternational(Vocabulary.Keys.ColorIndex  ).toString(Locale.ENGLISH));
        COLORED_SAMPLE_NAMES.add(Vocabulary.formatInternational(Vocabulary.Keys.Black       ).toString(Locale.ENGLISH));
        COLORED_SAMPLE_NAMES.add(Vocabulary.formatInternational(Vocabulary.Keys.Yellow      ).toString(Locale.ENGLISH));
        COLORED_SAMPLE_NAMES.add(Vocabulary.formatInternational(Vocabulary.Keys.Magenta     ).toString(Locale.ENGLISH));
        COLORED_SAMPLE_NAMES.add(Vocabulary.formatInternational(Vocabulary.Keys.Cyan        ).toString(Locale.ENGLISH));
        COLORED_SAMPLE_NAMES.add(Vocabulary.formatInternational(Vocabulary.Keys.Red         ).toString(Locale.ENGLISH));
        COLORED_SAMPLE_NAMES.add(Vocabulary.formatInternational(Vocabulary.Keys.Green       ).toString(Locale.ENGLISH));
        COLORED_SAMPLE_NAMES.add(Vocabulary.formatInternational(Vocabulary.Keys.Blue        ).toString(Locale.ENGLISH));
        COLORED_SAMPLE_NAMES.add(Vocabulary.formatInternational(Vocabulary.Keys.Gray        ).toString(Locale.ENGLISH));
        COLORED_SAMPLE_NAMES.add(Vocabulary.formatInternational(Vocabulary.Keys.Transparency).toString(Locale.ENGLISH));
    }

    private final StoreListeners listeners = new StoreListeners(null, this);
    private final List<VirtualBand> bands = new ArrayList<>();

    //user defined parameters
    private Mode mode = Mode.SCALE;
    private Interpolation interpolation = Interpolation.BILINEAR;
    private CoordinateReferenceSystem outputCrs = null;

    //computed informations
    private GenericName identifier;
    private Quadtree tree;
    private GridGeometry cachedGridGeometry;
    private boolean forceTransformedValues;
    private double[] noData;
    private int dataType = -1; // Not known / initialized
    private boolean photographic = false;
    /** See {@link #isHomogeneousSources()} */
    private boolean homogeneous = false;
    /**
     * Find a better way to control error recovery in the aggregation process.
     */
    private boolean neverfail = true;

    /*
     * list resources used more then once in the process
     * this list is used in the aggregation process to keep in cache coverage read
     */
    private final Set<Entry<GridCoverageResource,Integer>> reusable = new HashSet<>();


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

    public AggregatedCoverageResource(List<SampleDimension> sampleDimensions) {
        this.bands.addAll(toBands(sampleDimensions));
    }

    @Override
    public Optional<GenericName> getIdentifier() {
        return Optional.ofNullable(identifier);
    }

    public void setIdentifier(GenericName identifier) {
        this.identifier = identifier;
    }

    @Override
    public Metadata getMetadata() {
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
            bands.addAll(toBands(sampleDimensions));
        } else if (bands.size() != sampleDimensions.size()) {
            throw new DataStoreException("Provided dimensions do not match virtual bands size");
        }
        for (int i = 0,n = sampleDimensions.size(); i < n; i++) {
            bands.get(i).userDefinedSampleDimension = sampleDimensions.get(i);
        }
    }

    private static List<VirtualBand> toBands(List<SampleDimension> sampleDimensions) {
        final List<VirtualBand> bands = new ArrayList<>();
        if (sampleDimensions != null) {
            for (SampleDimension sd : sampleDimensions) {
                final VirtualBand band = new VirtualBand();
                band.userDefinedSampleDimension = sd;
                bands.add(band);
            }
        }
        return bands;
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

    public List<VirtualBand> getBands() {
        return new ArrayList<>(bands);
    }

    public void setBands(List<VirtualBand> bands) throws DataStoreException {
        ArgumentChecks.ensureNonNull("bands", bands);
        this.bands.clear();
        this.bands.addAll(bands);
        eraseCaches();
        initModel();
    }

    @Override
    public Set<GridCoverageResource> components() {
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
            listeners.fire(AggregationEvent.class, new AggregationEvent(this, AggregationEvent.TYPE_REMOVE, resource));
        }
        eraseCaches();
    }

    /**
     * Remove all resources from aggregation.
     * This method does not delete any data.
     */
    public synchronized void removeAll() {
        final Set<Resource> removed = new HashSet<>();
        for (VirtualBand band : bands) {
            for (Source source : band.sources) {
                removed.add(source.resource);
            }
            band.sources.clear();
        }
        listeners.fire(AggregationEvent.class, new AggregationEvent(this, AggregationEvent.TYPE_REMOVE, removed.toArray(new Resource[0])));
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
                listeners.fire(AggregationEvent.class, new AggregationEvent(this, AggregationEvent.TYPE_ADD, resource));
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
            listeners.fire(AggregationEvent.class, new AggregationEvent(this, AggregationEvent.TYPE_ADD, resource));
            return resource;
        } else {
            throw new DataStoreException("Resource must be an instance of GridCoverageResource");
        }
    }

    /**
     * Returns preferred interpolation method when aggregating data.
     * @return interpolation, not null
     */
    public Interpolation getInterpolation() {
        return interpolation;
    }

    /**
     * Set preferred interpolation method when aggregating data.
     * @param interpolation not null
     * @return
     */
    public void setInterpolation(Interpolation interpolation) {
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
     * Set preferred image raster data type. If set to -1 or below, we'll try to infer data type on the fly when
     * aggregating images.
     *
     * @param dataType Data type to force on output renderings.
     */
    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    /**
     *
     * @return Value manually set by user to force data type. If < 0, means that data type is unknown and we'll try to
     * infer it from aggregated images.
     */
    public int getDataType() {
        return dataType;
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

        final Map<GridCoverageResource, GridGeometry> components = new HashMap<>();
        for (VirtualBand band : bands) {
            for (Source source : band.sources) {
                try {
                    final GridGeometry gridGeometry = source.resource.getGridGeometry();
                    if (!(gridGeometry.isDefined(GridGeometry.CRS) && gridGeometry.isDefined(GridGeometry.ENVELOPE))) {
                        throw new DataStoreException("Resource has no defined CRS and Envelope.");
                    }
                    final CoordinateReferenceSystem crs = gridGeometry.getCoordinateReferenceSystem();
                    if (crs instanceof ImageCRS) {
                        throw new DataStoreException("CRS " + crs.getClass() + " can not be used in aggregation, resource will be ignored.");
                    } else if (crs != null && crs.getCoordinateSystem().getDimension() != 2) {
                        throw new DataStoreException("CRS " + crs.getName()+ " can not be used in aggregation it is not 2D, resource will be ignored.");
                    }
                    components.put(source.resource, gridGeometry);
                } catch (Exception ex) {
                    if (neverfail) {
                        this.cachedGridGeometry = new GridGeometry(new GridExtent(1, 1), null, GridOrientation.HOMOTHETY);
                        LOGGER.log(Level.INFO, "Failed to extract grid geometry crs or envelope from resource " + resourceName(source.resource) + " : " + ex.getMessage());
                        source.inMetaError = true;
                    } else {
                        throw ex;
                    }
                }
            }
        }

        if (components.isEmpty()) {
            //no data yet
            this.cachedGridGeometry = new GridGeometry(new GridExtent(1, 1), null, GridOrientation.HOMOTHETY);
            return;
        } else if (components.size() == 1) {
            //copy exact definition
            final Map.Entry<GridCoverageResource, GridGeometry> entry = components.entrySet().iterator().next();
            final GridCoverageResource resource = entry.getKey();
            final GridGeometry gridGeometry = entry.getValue();
            if (outputCrs == null || Utilities.equalsIgnoreMetadata(outputCrs, gridGeometry.getCoordinateReferenceSystem())) {
                this.cachedGridGeometry = gridGeometry;
                final JTSEnvelope2D key = new JTSEnvelope2D(gridGeometry.getEnvelope());
                tree.insert(key, new IndexedResource(key, resource));
                return;
            }
        }

        if (outputCrs == null) {
            //use most common crs
            //TODO find a better approach to determinate a common crs
            final FrequencySortedSet<CoordinateReferenceSystem> map = new FrequencySortedSet<>();
            for (Map.Entry<GridCoverageResource, GridGeometry> entry : components.entrySet()) {
                map.add(entry.getValue().getCoordinateReferenceSystem());
            }
            outputCrs = map.last();
        }

        //compute envelope and check sample dimensions
        GeneralEnvelope env = new GeneralEnvelope(outputCrs);
        env.setToNaN();
        int index = 0;
        double[] estimatedResolution = new double[outputCrs.getCoordinateSystem().getDimension()];
        Arrays.fill(estimatedResolution, Double.POSITIVE_INFINITY);

        //if all coverage resources have the same grid geometry we can use it directly
        GridGeometry sharedGrid = components.entrySet().iterator().next().getValue();
        if (!Utilities.equalsIgnoreMetadata(sharedGrid.getCoordinateReferenceSystem(), outputCrs)) {
            sharedGrid = null;
        }

        for (Map.Entry<GridCoverageResource, GridGeometry> entry : components.entrySet()) {
            final GridCoverageResource resource = entry.getKey();
            final GridGeometry componentGrid = entry.getValue();

            //try combining grid geometries
            sharedGrid = CoverageUtilities.tryAggregate(sharedGrid, componentGrid).orElse(null);

            Envelope envelope = componentGrid.getEnvelope();
            try {
                envelope = Envelopes.transform(envelope, outputCrs);
            } catch (TransformException ex) {
                if (neverfail) {
                    LOGGER.log(Level.INFO, "Failed to transform resource envelope" + resourceName(resource) + " : " + ex.getMessage());
                    continue;
                } else {
                    throw new DataStoreException(ex.getMessage(), ex);
                }
            }
            final JTSEnvelope2D key = new JTSEnvelope2D(envelope);
            tree.insert(key, new IndexedResource(key, resource));

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
        if (sharedGrid != null) {
            cachedGridGeometry = sharedGrid;
        } else if (env.isAllNaN()) {
            //could not extract any usefull information from datas
            this.cachedGridGeometry = new GridGeometry(new GridExtent(1, 1), null, GridOrientation.HOMOTHETY);
        } else if (estimatedResolution != null) {
            cachedGridGeometry = new EstimatedGridGeometry(env, estimatedResolution);
        } else {
            cachedGridGeometry = new GridGeometry(null, env, GridOrientation.HOMOTHETY);
        }
    }

    private void initSampleDimensions() throws DataStoreException {
        if (bands.isEmpty()) return;
        if (bands.get(0).cachedSampleDimension != null) return;

        //list resources used more then once in the process
        //this list is used in the aggregation process to keep in cache coverage read
        reusable.clear();
        final Map<Entry<GridCoverageResource,Integer>, AtomicInteger> count = new HashMap<>();
        for (int i = 0, n = bands.size(); i < n; i++) {
            final VirtualBand band = bands.get(i);
            for (Source src : band.sources) {
                final Entry<GridCoverageResource,Integer> key = new AbstractMap.SimpleImmutableEntry<>(src.resource, src.bandIndex);
                AtomicInteger ai = count.get(key);
                if (ai == null) {
                    ai = new AtomicInteger();
                    count.put(key, ai);
                }
                ai.incrementAndGet();
            }
        }
        for (Entry<Entry<GridCoverageResource,Integer>, AtomicInteger> entry : count.entrySet()) {
            if (entry.getValue().get() > 1) {
                reusable.add(entry.getKey());
            }
        }

        final List<SampleDimension> sampleDimensions = new ArrayList<>();
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
            sampleDimensions.add(band.cachedSampleDimension);
        }

        photographic = isPhotographic(sampleDimensions);
        homogeneous = isHomogeneousSources(sampleDimensions);

        if (photographic && homogeneous) {
            forceTransformedValues = false;
            noData = new double[bands.size()];
            return;
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
                builder.setName(baseDim.getName());
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

    private double extractNoData(SampleDimension baseDim) {
        final Optional<Number> background = baseDim.getBackground();
        if (background.isPresent()) return background.get().doubleValue();

        try {
            final Iterator<Number> noData = baseDim.getNoDataValues().iterator();
            if (noData.hasNext()) return noData.next().doubleValue();
        } catch (Exception ex) {
            if (neverfail) {
                LOGGER.log(Level.FINE, "Failed to extract no data values from " + baseDim + " : " + ex.getMessage());
            } else {
                throw ex;
            }
        }

        return Double.NaN;
    }

    /**
     * @return true if :
     * <ul>
     *     <li>All sources are in the same order on all bands.</li>
     *     <li>All bands are used and in their original order.</li>
     *     <li>No transforms are applied on each source.</li>
     *     <li>No user defined sample dimensions.</li>
     * </ul>
     *
     * This is a common case where we just aggregate similar coverages.
     */
    private boolean isHomogeneousSources(List<SampleDimension> sampleDimensions) throws DataStoreException {

        List<GridCoverageResource> orderedResources = null;
        for (int i = 0, n = bands.size(); i < n; i++) {
            final VirtualBand band = bands.get(i);

            //check number and order of resources
            if (orderedResources == null) {
                orderedResources = new ArrayList();
                for (int k = 0, kn = band.sources.size(); k < kn; k++) {
                    final Source source = band.sources.get(k);
                    orderedResources.add(source.resource);
                }
            } else {
                for (int k = 0, kn = band.sources.size(); k < kn; k++) {
                    final Source source = band.sources.get(k);
                    if (source.resource != orderedResources.get(k)) {
                        return false;
                    }
                }
            }

            // check transform and band index
            for (int k = 0, kn = band.sources.size(); k < kn; k++) {
                final Source source = band.sources.get(k);
                if (source.sampleTransform != null && !source.sampleTransform.isIdentity()) {
                    return false;
                }
                if (source.bandIndex != i) {
                    return false;
                }
                try {
                    if (source.resource.getSampleDimensions().size() != sampleDimensions.size()) {
                        return false;
                    }
                } catch (Exception ex) {
                    source.inMetaError = true;
                    if (neverfail) {
                        LOGGER.log(Level.INFO, ex.getMessage());
                        continue;
                    } else {
                        throw ex;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isPhotographic(List<SampleDimension> sampleDimensions) {
        boolean photographic = true;
        for (SampleDimension sd : sampleDimensions) {
            final GenericName name = sd.getName();
            final String enName = name.tip().toInternationalString().toString(Locale.ENGLISH);
            if (COLORED_SAMPLE_NAMES.contains(enName)) {
                //TODO need a better check
            } else {
                photographic = false;
                break;
            }
        }
        return photographic;
    }

    private void eraseCaches() {
        cachedGridGeometry = null;
        for (VirtualBand vb : bands) {
            vb.cachedSampleDimension = null;
        }
        tree = null;
        listeners.fire(ModelEvent.class, new ModelEvent(this));
        listeners.fire(ContentEvent.class, new ContentEvent(this));
        photographic = false;
        homogeneous = false;
    }

    @Override
    public GridCoverage read(GridGeometry domain, int... range) throws DataStoreException {
        initModel();

        final GridGeometry gridGeometry = getGridGeometry();
        if (domain == null) domain = gridGeometry;

        // List the bands requested ////////////////////////////////////////////
        final List<SampleDimension> sampleDimensions;
        final List<VirtualBand> bands;
        if (range != null && range.length != 0) {
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
        final Set<GridCoverageResource> candidates = getCandidates(domain);

        // Build expected GridGeometry /////////////////////////////////////////

        //TODO : we force the canvas to start on zero, there is an issue with the resample
        //when canvas does not start at zero.
        final GridGeometry canvas;
        if (gridGeometry.isDefined(GridGeometry.EXTENT)) {
            canvas = CoverageUtilities.forceLowerToZero(gridGeometry.derive().subgrid(domain).build());
        } else if (domain.isDefined(GridGeometry.EXTENT)) {
            //note the crs may be different from this data
            canvas = CoverageUtilities.forceLowerToZero(domain);
        } else {
            throw new DataStoreException("Aggregated resource require a grid extent to be defined on the resource or on the requested domain");
        }

        // aggregate tiles /////////////////////////////////////////////////////
        if (photographic && homogeneous) {
            final List<Source> sources = new ArrayList<>(bands.get(0).getSources());
            final List<Source> sorted = prepareSources(sources, candidates, canvas);
            if (sorted.isEmpty()) throw new NoSuchDataException("No data on requested domain");

            //canvas accuracy
            final double[] canvasResolution = canvas.getResolution(true);
            final Unit<?> canvasUnit = canvas.getCoordinateReferenceSystem().getCoordinateSystem().getAxis(0).getUnit();
            final Quantity accuracy = Quantities.create(Math.min(canvasResolution[0], canvasResolution[1]) / 2.0, canvasUnit);

            BufferedImage result = null;
            BitSet2D mask = null;
            double[] transparent = null;
            for (Source source : sorted) {
                try {
                    final GridCoverage sourceCoverage = source.resource.read(source.resource.getGridGeometry().derive().margin(3,3).subgrid(canvas).build());
                    final RenderedImage sourceImage = sourceCoverage.render(null);
                    if (result == null) {
                        final int width = Math.toIntExact(canvas.getExtent().getSize(0));
                        final int height = Math.toIntExact(canvas.getExtent().getSize(1));
                        result = BufferedImages.createImage(sourceImage, width, height, null, null);
                        mask = new BitSet2D(width, height);
                        transparent = new double[result.getSampleModel().getNumBands()];
                    }

                    final GridCoverageProcessor processor = new GridCoverageProcessor();
                    processor.setInterpolation(interpolation);
                    processor.setPositionalAccuracyHints(accuracy);
                    //ensure we have expected image correctly aligned
                    final RenderedImage intermediate = processor.resample(sourceCoverage, canvas).render(canvas.getExtent());

                    aggregate(result, intermediate, mask, transparent, transparent);
                } catch (NoSuchDataException ex) {
                    //do nothing
                } catch (TransformException ex) {
                    //do nothing
                } catch (Exception ex) {
                    if (neverfail) {
                        LOGGER.log(source.inReadError ? Level.FINE : Level.INFO, ex.getMessage(), ex);
                        source.inReadError = true;
                    } else {
                        throw ex;
                    }
                }
            }

            if (result == null) {
                throw new NoSuchDataException();
            }

            return new GridCoverage2D(canvas, sampleDimensions, result);
        } else {

            boolean foundDatas = false;
            final BufferedImage[] bandImages = new BufferedImage[bands.size()];
            for (int bandIndex = 0, n = bands.size(); bandIndex < n; bandIndex++) {
                final VirtualBand band = bands.get(bandIndex);

                //pick only intersection resources
                final List<Source> sorted = prepareSources(band.sources, candidates, canvas);
                if (sorted.isEmpty()) {
                    continue;
                }

                bandImages[bandIndex] = aggregate(sorted, canvas, bandIndex, interpolation);
                foundDatas |= (bandImages[bandIndex] != null);
            }

            //if all band images are null, this means not datas really intersected
            if (!foundDatas) {
                throw new NoSuchDataException("No data on requested domain");
            }

            final int dataType = defineDataType(bandImages);
            //create blank images with NoData value if a band image is missing
            for (int i = 0; i < bandImages.length; i++) {
                if (bandImages[i] == null) {
                    bandImages[i] = BufferedImages.createImage(
                            (int) canvas.getExtent().getSize(0),
                            (int) canvas.getExtent().getSize(1),
                            1, dataType, new double[]{noData[i]});
                }
            }

            BufferedImage result;
            if (bandImages.length == 1) {
                result = bandImages[0];

                if (result.getSampleModel().getDataType() != dataType) {
                    final BufferedImage cp = BufferedImages.createImage(result, null, null, null, dataType);
                    final PixelIterator readIte = new PixelIterator.Builder().setIteratorOrder(SequenceType.LINEAR).create(result);
                    final WritablePixelIterator writeIte = new PixelIterator.Builder().setIteratorOrder(SequenceType.LINEAR).createWritable(cp);
                    final double[] buffer = new double[cp.getSampleModel().getNumBands()];
                    while (readIte.next() && writeIte.next()) {
                        writeIte.setPixel(readIte.getPixel(buffer));
                    }
                    result = cp;
                }

            } else {
                result = BufferedImages.createImage(bandImages[0].getWidth(), bandImages[1].getHeight(), bandImages.length, dataType);
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
            return new GridCoverage2D(canvas, sampleDimensions, result);
        }
    }

    private int defineDataType(final RenderedImage[] images) throws NoSuchDataException {
        return defineDataType(Arrays.stream(images));
    }

    private int defineDataType(final Stream<RenderedImage> images) throws NoSuchDataException {
        return defineDataType(images.mapToInt(img -> img.getSampleModel().getDataType()));
    }

    private int defineDataType(final IntStream sourceDataTypes) throws NoSuchDataException {
        if (this.dataType < 0) {
            final int dataType = sourceDataTypes
                    .max()
                    .orElseThrow(() -> new NoSuchDataException("No band to aggregate. Cannot determine appropriate data type."));
            final boolean canContainNaN = bands.stream().anyMatch(band -> band.userDefinedSampleDimension != null && band.userDefinedSampleDimension.allowsNaN());
            if (canContainNaN && dataType < DataBuffer.TYPE_FLOAT) return DataBuffer.TYPE_DOUBLE;
            return dataType;
        }

        return this.dataType;
    }

    /**
     * Select sources matching queried domain.
     *
     * @param domain The region of interest queried by user.
     * @return A list of all sources matching given domain. Never null, never empty.
     * @throws NoSuchDataException if no source candidate can be found in given domain.
     * @throws DataStoreException if we cannot compare input domain with source ones.
     */
    private Set<GridCoverageResource> getCandidates(final GridGeometry domain) throws DataStoreException {
        Envelope envelope = domain.getEnvelope();
        try {
            envelope = Envelopes.transform(envelope, outputCrs);
        } catch (TransformException ex) {
            throw new DataStoreException(ex.getMessage(), ex);
        }

        final JTSEnvelope2D searchEnv = new JTSEnvelope2D(envelope);
        final Set<GridCoverageResource> treeResults = ((Stream<IndexedResource>) tree.query(searchEnv).stream())
                .filter(r -> r.key.intersects(searchEnv))
                .map(r -> r.value)
                .collect(Collectors.toSet());

        if (treeResults == null || treeResults.isEmpty()) {
            throw new NoSuchDataException("No data on requested domain");
        }
        return treeResults;
    }

    /**
     * Select and sort sources matching queried domain.
     *
     * @param candidates Sources to consider as possible output.
     * @param sourcesMatchingDomain Available resources for current domain.
     * @param domain The region of interest queried by user, serves to sort output.
     * @return A sorted list of all candidate sources matching given domain. Never null, but can be empty.
     * @throws NoSuchDataException if no source candidate can be found in given domain.
     * @throws DataStoreException if we cannot compare input domain with source ones.
     */
    private List<Source> prepareSources(Collection<Source> candidates, Collection<GridCoverageResource> sourcesMatchingDomain, GridGeometry domain) throws DataStoreException {
        //pick only intersection resources
        final List<Source> sorted = new ArrayList<>();
        for (Source candidate : candidates) {
            if (sourcesMatchingDomain.contains(candidate.resource)) {
                sorted.add(candidate);
            }
        }

        // Sort resources according to their scale.
        if (!sorted.isEmpty()) {
            sort(domain, sorted, mode);
        }

        return sorted;
    }

    private BufferedImage aggregate(List<Source> ordered, final GridGeometry canvas, int bandIndex, final Interpolation interpolation) throws DataStoreException {
        final double noDataValue = this.noData[bandIndex];
        final double[] noData = new double[]{noDataValue};

        //create result image
        final GridExtent extent = canvas.getExtent();
        final int sizeX = Math.toIntExact(extent.getSize(0));
        final int sizeY = Math.toIntExact(extent.getSize(1));

        //canvas accuracy
        final double[] canvasResolution = canvas.getResolution(true);
        final Unit<?> canvasUnit = canvas.getCoordinateReferenceSystem().getCoordinateSystem().getAxis(0).getUnit();
        final Quantity accuracy = Quantities.create(Math.min(canvasResolution[0], canvasResolution[1]) / 2.0, canvasUnit);

        //create a mask of filled datas
        final BitSet2D mask = new BitSet2D(sizeX, sizeY);

        final int tmpDataType = this.dataType < 0 ? DataBuffer.TYPE_DOUBLE : this.dataType;
        final BufferedImage result = BufferedImages.createImage(sizeX, sizeY, 1, tmpDataType, noData);

        // HACK: To try and preserve color space if possible, we keep aggregated source images in memory
        final List<Integer> sourceDataTypes = new ArrayList<>(ordered.size());
        //for espadon projet : altimetry data is used twice
        //cache each source result, they may be used several times
        final Map<Entry<GridCoverageResource,Integer>, Entry<RenderedImage,double[]>> reuse = new HashMap<>();

        for (Source source : ordered) {
            final GridGeometry readGeometry = adapt(canvas, mask);
            if (readGeometry == null) break; // No more empty space to fill

            final Entry<GridCoverageResource,Integer> key = new AbstractMap.SimpleImmutableEntry<>(source.resource, source.bandIndex);
            Entry<RenderedImage, double[]> r = reuse.get(key);
            double[] sourceNoData = (r != null) ? r.getValue() : null;
            RenderedImage resampledImage = (r != null) ? r.getKey(): null;
            try {
                if (r == null) {
                    final SingleBandedFilteredSource filteredSource = readSingleBandedSource(source, readGeometry).orElse(null);
                    if (filteredSource == null) continue; // Source not available for remaining area to fill

                    sourceNoData = new double[]{filteredSource.fillValue};

                    //resample coverage
                    try {
                        final RenderedImage coverageImage = filteredSource.image;
                        sourceDataTypes.add(coverageImage.getSampleModel().getDataType());
                        final BufferedImage image = BufferedImages.createImage(result, null, null, null, DataBuffer.TYPE_DOUBLE, sourceNoData);

                        final MathTransform toSource = createTransform(canvas, image, filteredSource.geometry, coverageImage);

                        Interpolation inter = interpolation;
                        final Dimension dim = interpolation.getSupportSize();
                        if (coverageImage.getWidth() < dim.width || coverageImage.getHeight() < dim.height) {
                            inter = Interpolation.NEAREST;
                        }

                        final ImageCombiner ic = new ImageCombiner(image);
                        ic.setInterpolation(inter);
                        ic.setPositionalAccuracyHints(accuracy);
                        ic.resample(coverageImage, new Rectangle(image.getWidth(), image.getHeight()), toSource);
                        resampledImage = image;
                    } catch (FactoryException ex) {
                        throw new DataStoreException(ex.getMessage(), ex);
                    }
                    if (reusable.contains(key)) reuse.put(key, new AbstractMap.SimpleImmutableEntry<>(resampledImage, sourceNoData));
                }

                final double[] cst_nodata = sourceNoData;

                //we need to merge image, replacing only not-NaN values
                final TriFunction<Point, double[], double[], double[]> merger = new TriFunction<Point, double[], double[], double[]>() {
                    @Override
                    public double[] apply(Point position, double[] sourcePixel, double[] targetPixel) {
                        //apply transform if defined before checking NaN
                        if (source.sampleTransform != null) try {
                            sourcePixel[0] = source.sampleTransform.transform(sourcePixel[0]);
                        } catch (TransformException ex) {
                            Logger.getLogger("org.geotoolkit.storage.coverage.mosaic").log(Level.INFO, ex.getMessage(), ex);
                            return null;
                        }

                        if (Double.isNaN(sourcePixel[0])) {
                            return null;
                        }

                        if (cst_nodata != null && Arrays.equals(cst_nodata, sourcePixel)) {
                            return null;
                        }
                        if (Arrays.equals(noData, targetPixel)) {
                            //fill the mask
                            mask.set2D(position.x, position.y);
                            return sourcePixel;
                        }
                        return null;
                    }
                };

                BufferedImages.mergeImages(resampledImage, result, true, merger);

            } catch (NoSuchDataException | DisjointExtentException ex) {
                //may happen, enveloppe is larger then data or mask do not intersect anymore
                //quad tree may also return more results
            } catch (TransformException ex) {
                if (neverfail) {
                    if (source.inReadError) {
                        LOGGER.log(Level.FINE, ex.getMessage(), ex);
                    } else {
                        LOGGER.log(Level.INFO, "This error wille be logged at FINE level in futur read operations : " + ex.getMessage(), ex);
                    }
                    source.inReadError = true;
                } else {
                    throw new DataStoreException("Error while aggregating source datasets into queried domain", ex);
                }
            } catch (Exception ex) {

                //propage exceptions which are caused by interruptions
                if (ex instanceof InterruptedStoreException) {
                    throw ex;
                } else if (DataStores.isInterrupted(ex)) {
                    throw new InterruptedStoreException(ex);
                }

                if (neverfail) {
                    if (source.inReadError) {
                        LOGGER.log(Level.FINE, ex.getMessage(), ex);
                    } else {
                        LOGGER.log(Level.INFO, "This error wille be logged at FINE level in futur read operations : " + ex.getMessage(), ex);
                    }
                    source.inReadError = true;
                } else {
                    throw ex;
                }
            }
        }

        // HACK: Aggregation should preserve source data types. Coverage API does not provide any hint about sample data
        // type, so we're forced to "fix" image after merge.
        // TODO: find a better solution
        final int dataType = defineDataType(sourceDataTypes.stream().mapToInt(i -> i));
        if (tmpDataType != dataType) {
            final PixelIterator.Builder builder = new PixelIterator.Builder().setIteratorOrder(SequenceType.LINEAR);
            PixelIterator source = builder.create(result);
            final BufferedImage newResult = BufferedImages.createImage(result.getWidth(), result.getHeight(), source.getNumBands(), dataType, noData);
            try (WritablePixelIterator destination = builder.createWritable(newResult, newResult)) {
                final double[] buffer= new double[source.getNumBands()];
                while (source.next() && destination.next()) destination.setPixel(source.getPixel(buffer));
            }
            return newResult;
        }

        return result;
    }

    private GridGeometry adapt(final GridGeometry domain, final BitSet2D mask) {
        // Test mask for remaining area to fill.
        GridExtent maskExtent = mask.areaCleared().orElse(null);
        if (maskExtent == null) return null;
        //add the base canvas offsets which may be negative or positive
        final GridExtent baseExtent = domain.getExtent();
        final long mincanvasx = baseExtent.getLow(0);
        final long mincanvasy = baseExtent.getLow(1);
        maskExtent = new GridExtent(null,
                new long[]{mincanvasx + maskExtent.getLow(0), mincanvasy + maskExtent.getLow(1)},
                new long[]{mincanvasx + maskExtent.getHigh(0), mincanvasy + maskExtent.getHigh(1)}, true);
        return new GridGeometry(maskExtent, PixelInCell.CELL_CENTER, domain.getGridToCRS(PixelInCell.CELL_CENTER), domain.getCoordinateReferenceSystem());
    }

    private Optional<SingleBandedFilteredSource> readSingleBandedSource(final Source source, final GridGeometry targetDomain) throws DataStoreException {
        try {
            //expend grid geometry a little for interpolation
            GridGeometry readGridGeom;
            GridGeometry coverageGridGeometry = source.resource.getGridGeometry();
            if (coverageGridGeometry.isDefined(GridGeometry.EXTENT)) {
                readGridGeom = coverageGridGeometry.derive()
                        .rounding(GridRoundingMode.ENCLOSING)
                        .margin(5, 5)
                        .subgrid(targetDomain)
                        .build();
            } else {
                readGridGeom = targetDomain.derive().margin(5, 5).build();
            }

            final GridCoverage coverage = source.resource.read(readGridGeom, source.bandIndex).forConvertedValues(forceTransformedValues);
            final List<SampleDimension> sampleDimensions = coverage.getSampleDimensions();
            if (sampleDimensions.size() != 1) {
                throw new DataStoreException(source.resource + " returned a coverage with more then one sample dimension, fix implementation");
            }

            return Optional.of(new SingleBandedFilteredSource(coverage.render(null), coverage.getGridGeometry(), extractNoData(sampleDimensions.get(0))));
        } catch (NoSuchDataException | DisjointExtentException ex) {
            //may happen, enveloppe is larger then data or mask do not intersect anymore
            //quad tree may also return more results
            return Optional.empty();
        }
    }

    private void aggregate(BufferedImage result, RenderedImage source, BitSet2D mask, double[] resultNoData, double[] sourceNoData) {

        //we need to merge image, replacing only not-NaN values
        final WritablePixelIterator resultIte = new WritablePixelIterator.Builder().setIteratorOrder(SequenceType.LINEAR).createWritable(result);
        //intersect source domain to result domain
        final PixelIterator sourceIte = new PixelIterator.Builder().setIteratorOrder(SequenceType.LINEAR).setRegionOfInterest(resultIte.getDomain()).create(source);
        final double[] sourcePx = new double[sourceIte.getNumBands()];
        final double[] resultPx = new double[sourceIte.getNumBands()];
        while (sourceIte.next()) {
            sourceIte.getPixel(sourcePx);
            if (sourceNoData != null && Arrays.equals(sourceNoData, sourcePx)) {
                continue;
            }

            final Point position = sourceIte.getPosition();
            resultIte.moveTo(position.x, position.y);
            resultIte.getPixel(resultPx);

            if (Arrays.equals(resultNoData, resultPx)) {
                resultIte.setPixel(sourcePx);
                //fill the mask
                mask.set2D(position.x, position.y);
            }
        }
    }

    private void sort(GridGeometry canvas, List<Source> sorted, Mode mode) throws DataStoreException {
        if (Mode.SCALE.equals(mode)) {
            //the most accurate order is to render in order coverages
            //with relative scale going from 1.0 to 0.0 then from 1.0 to +N
            //filling only the gaps at each coverage

            final List<SourceToSort> ordered = new ArrayList<>();
            for (Source entry : sorted) {
                final SourceToSort sts = compute(entry, canvas);
                if (sts != null) ordered.add(sts);
            }
            ordered.sort(SCALEAREA_COMPARATOR);
            sorted.clear();
            for (SourceToSort sts : ordered) sorted.add(sts.source);
        } else if (Mode.ORDER.equals(mode)) {
            //do nothing
        }
    }

    private SourceToSort compute(final Source entry, final GridGeometry canvas) throws DataStoreException {
        try {
            GridGeometry sourceGrid = entry.resource.getGridGeometry();
            double ratio = estimateRatio(sourceGrid, canvas);
            if (Double.isNaN(ratio)) {
                sourceGrid = entry.resource.read(canvas).getGridGeometry();
                ratio = estimateRatio(sourceGrid, canvas);
            }

            double order = ratio;
            if (ratio <= 1.05) { //little tolerance du to crs deformations and math
                order = 1.05 - ratio;
            }

            return new SourceToSort(entry, order, ratio(sourceGrid.getEnvelope(), canvas.getEnvelope()));
        } catch (DisjointExtentException ex) {
            return null;
        } catch (FactoryException | TransformException ex) {
            return null;
        } catch (DataStoreException|RuntimeException ex) {
            if (neverfail) {
                return null;
            } else {
                throw ex;
            }
        }
    }

    @Override
    public <T extends StoreEvent> void addListener(Class<T> eventType, StoreListener<? super T> listener) {
        listeners.addListener(eventType, listener);
    }

    @Override
    public <T extends StoreEvent> void removeListener(Class<T> eventType, StoreListener<? super T> listener) {
        listeners.removeListener(eventType, listener);
    }

    @Override
    public String toString() {
        try {
            initModel();
        } catch (DataStoreException ex) {
            Logger.getLogger("org.geotoolkit.coverage").log(Level.INFO, ex.getMessage(), ex);
        }

        final List<String> texts = new ArrayList<>();
        texts.add("Interpolate : "+ interpolation);
        texts.add("Mode : "+ mode);

        for (VirtualBand vb : bands) {
            String name = vb.cachedSampleDimension.getName().toString();
            final List<String> sources = new ArrayList<>();

            int i=0;
            for (Source source : vb.sources) {
                String trstxt = source.sampleTransform == null ? "" : source.sampleTransform.getClass().getSimpleName();
                final StringBuilder sb = new StringBuilder();
                sb.append("Source(").append(source.bandIndex).append(", ").append(trstxt).append(") ");
                sb.append(source.resource);
                sources.add(sb.toString());
                i++;
                if (i>10) {
                    sources.add("... ("+vb.sources.size()+" entries) ...");
                    break;
                }
            }
            texts.add(StringUtilities.toStringTree(name, sources));
        }

        final String name = getIdentifier().orElse(NamesExt.create("Unnamed")).toString();

        return StringUtilities.toStringTree(name + " aggregated coverage resource", texts);
    }

    public static MathTransform createTransform(GridGeometry source, RenderedImage sourceImg, GridGeometry target, RenderedImage targetImg) throws FactoryException, NoninvertibleTransformException {
        final long[] sourceCorner = source.getExtent().getLow().getCoordinateValues();
        final MathTransform sourceOffset = MathTransforms.translation((sourceCorner[0] - sourceImg.getMinX()), (sourceCorner[1] - sourceImg.getMinY()));
        final MathTransform gridSourceToCrs = source.getGridToCRS(PixelInCell.CELL_CENTER);
        final MathTransform imgSourceToCrs = MathTransforms.concatenate(sourceOffset, gridSourceToCrs);

        final long[] targetCorner = target.getExtent().getLow().getCoordinateValues();
        final MathTransform targetOffset = MathTransforms.translation((targetCorner[0] - targetImg.getMinX()), (targetCorner[1] - targetImg.getMinY()));
        final MathTransform gridTargetToCrs = target.getGridToCRS(PixelInCell.CELL_CENTER);
        final MathTransform imgTargetToCrs = MathTransforms.concatenate(targetOffset, gridTargetToCrs);

        final MathTransform crsToCrs = CRS.findOperation(source.getCoordinateReferenceSystem(), target.getCoordinateReferenceSystem(), null).getMathTransform();
        return MathTransforms.concatenate(
                imgSourceToCrs,
                crsToCrs,
                imgTargetToCrs.inverse());
    }

    /**
     * Estimate the ratio of coverage grid geometry intersection in target grid geometry.
     * The ratio is an estimation of the resolution difference from the target grid resolution.
     * A value lower then 1 means the coverage has a higher resolution then the target grid, coverage is more accurate.
     * A value higher then 1 means the coverage has a lower resolution then the target grid, coverage is less accurate.
     * A value of NaN indicates the impossibility to estimate the resolution.
     *
     * @param coverageGridGeom
     * @param target
     * @return
     */
    private static double estimateRatio(GridGeometry coverageGridGeom, GridGeometry target) throws FactoryException, TransformException {
        //intersect grid geometries
        final GridGeometry result = coverageGridGeom.derive().subgrid(target.getEnvelope()).build();
        if (result.isDefined(GridGeometry.GRID_TO_CRS)) {
            //compute transform
            final MathTransform gridToCRS = result.getGridToCRS(PixelInCell.CELL_CENTER);
            final CoordinateOperation crsToCrs = CRS.findOperation(result.getCoordinateReferenceSystem(), target.getCoordinateReferenceSystem(), null);
            final MathTransform trs = MathTransforms.concatenate(gridToCRS, crsToCrs.getMathTransform(), target.getGridToCRS(PixelInCell.CELL_CENTER).inverse());
            //transform a unitary vector at most representative point
            double[] point = result.getExtent().getPointOfInterest(PixelInCell.CELL_CENTER);
            double[] vector = Arrays.copyOf(point, 4);
            double diagonal = 1.0 / Math.sqrt(2); //for a vector of length = 1
            vector[2] = point[0] + diagonal;
            vector[3] = point[1] + diagonal;
            trs.transform(vector, 0, vector, 0, 2);
            return norm(vector);
        } else if (result.isDefined(GridGeometry.RESOLUTION)) {
            final double[] resolution = result.getResolution(true);
            final CoordinateOperation crsToCrs = CRS.findOperation(result.getCoordinateReferenceSystem(), target.getCoordinateReferenceSystem(), null);
            final MathTransform trs = MathTransforms.concatenate(crsToCrs.getMathTransform(), target.getGridToCRS(PixelInCell.CELL_CENTER).inverse());
            final double centerx = result.getEnvelope().getMedian(0);
            final double centery = result.getEnvelope().getMedian(1);
            //transform a unitary vector
            double[] vector = new double[] {
                centerx,
                centery,
                centerx + resolution[0],
                centery + resolution[1],
            };
            trs.transform(vector, 0, vector, 0, 2);
            return norm(vector);
        } else {
            return Double.NaN;
        }
    }

    /**
     * @param vector A 2D vector in the form [minX, minY, maxX, maxY]
     * @return norm of input 2D vector
     */
    private static double norm(double[] vector) {
        double x = Math.abs(vector[0] - vector[2]);
        double y = Math.abs(vector[1] - vector[3]);
        return Math.sqrt(x * x + y * y);
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

    /**
     * Extract name and resource path if possible.
     * @param r
     * @return composed name.
     */
    private static String resourceName(Resource r) {
        String name = "Unamed";
        try {
            Optional<GenericName> id = r.getIdentifier();
            if (id.isPresent()) {
                name = id.get().toString();
            }
        } catch (Exception ex) {
            //do nothing
        }

        if (r instanceof ResourceOnFileSystem) {
            final ResourceOnFileSystem rfs = (ResourceOnFileSystem) r;
            try {
                final Path[] paths = rfs.getComponentFiles();
                if (paths != null && paths.length > 0 && paths[0] != null) {
                    name += " " + paths[0].toUri().toString();
                }
            } catch (Exception ex) {
                //do nothing
            }
        }
        return name;
    }

    private static double ratio(Envelope source, Envelope target) {
        final GeneralEnvelope env = new GeneralEnvelope(target);
        try {
            env.intersect(Envelopes.transform(source, target.getCoordinateReferenceSystem()));
        } catch (TransformException e) {
            throw new BackingStoreException(e);
        }

        return pseudoArea(env) / pseudoArea(target);
    }

    private static double pseudoArea(Envelope target) {
        return IntStream.range(0, target.getDimension())
                .mapToDouble(target::getSpan)
                .reduce(1, (d1, d2) -> d1 * d2);
    }

    public static class VirtualBand {
        private SampleDimension userDefinedSampleDimension;
        SampleDimension cachedSampleDimension;
        private final List<Source> sources = new ArrayList<>();

        public List<Source> getSources() {
            return Collections.unmodifiableList(sources);
        }

        public void setSources(Source ... sources) {
            this.sources.clear();
            this.sources.addAll(Arrays.asList(sources));
        }
        public void setSources(List<Source> sources) {
            this.sources.clear();
            this.sources.addAll(sources);
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
        public final MathTransform1D sampleTransform;

        /**
         * Keep a flag if a resource caused an error.
         * We will still try to use it afterward but we won't log at the same level.
         */
        private boolean inMetaError = false;
        private boolean inReadError = false;

        public Source(GridCoverageResource resource, int bandIndex) {
            this(resource, bandIndex, null);
        }

        public Source(GridCoverageResource resource, int bandIndex, MathTransform1D sampleTransform) {
            this.resource = resource;
            this.bandIndex = bandIndex;
            this.sampleTransform = sampleTransform;
        }
    }

    private static class SingleBandedFilteredSource {
        final RenderedImage image;
        final GridGeometry geometry;
        final double fillValue;

        SingleBandedFilteredSource(RenderedImage image, GridGeometry geometry, double fillValue) {
            this.image = image;
            this.geometry = geometry;
            this.fillValue = fillValue;
        }
    }

    private static void log(Collection<Source> sources, Envelope target) {
        if (sources.size() < 4) return;
        else {
            final String debug = sources.stream()
                    .limit(5)
                    .map(source -> {
                try {
                    return source.resource.getGridGeometry();
                } catch (DataStoreException e) {
                    throw new BackingStoreException(e);
                }
            })
                    .map(grid ->
                            "scale: " + grid.getResolution(true)[0] + " ; roi: " + ratio(grid.getEnvelope(), target)
                    )
                    .collect(Collectors.joining(System.lineSeparator(), "\n=== Tile ===\n", "\n"));

            System.out.println(debug);
        }
    }

    private static class IndexedResource {
        final JTSEnvelope2D key;
        final GridCoverageResource value;

        public IndexedResource(JTSEnvelope2D key, GridCoverageResource value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class SourceToSort {
        final Source source;

        final double scaleRatio;
        final double areaRatio;

        public SourceToSort(Source source, double scaleRatio, double areaRatio) {
            this.source = source;
            this.scaleRatio = scaleRatio;
            this.areaRatio = areaRatio;
        }
    }
}
