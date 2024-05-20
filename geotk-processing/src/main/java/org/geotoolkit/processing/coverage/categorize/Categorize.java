package org.geotoolkit.processing.coverage.categorize;

import java.awt.image.RenderedImage;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridCoverageBuilder;
import org.apache.sis.coverage.grid.GridExtent;
import org.apache.sis.coverage.grid.GridGeometry;
import org.apache.sis.coverage.grid.GridOrientation;
import org.apache.sis.coverage.grid.GridRoundingMode;
import org.apache.sis.geometry.Envelopes;
import org.apache.sis.geometry.GeneralEnvelope;
import org.apache.sis.parameter.Parameters;
import org.apache.sis.referencing.CRS;
import org.apache.sis.referencing.operation.transform.MathTransforms;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.GridCoverageResource;
import org.apache.sis.storage.WritableGridCoverageResource;
import org.apache.sis.util.Utilities;
import org.geotoolkit.coverage.grid.GridCoverageStack;
import org.geotoolkit.coverage.grid.GridGeometryIterator;
import org.geotoolkit.image.interpolation.InterpolationCase;
import org.geotoolkit.process.DismissProcessException;
import org.geotoolkit.process.ProcessDescriptor;
import org.geotoolkit.process.ProcessException;
import org.geotoolkit.processing.AbstractProcess;
import org.geotoolkit.processing.coverage.resample.ResampleProcess;
import org.geotoolkit.processing.image.sampleclassifier.SampleClassifier;
import org.geotoolkit.processing.image.sampleclassifier.SampleClassifierDescriptor;
import org.geotoolkit.referencing.ReferencingUtilities;
import org.opengis.geometry.Envelope;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.parameter.ParameterValueGroup;
import org.apache.sis.coverage.grid.PixelInCell;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

/**
 *
 * @author Alexis Manin (Geomatys)
 */
public class Categorize extends AbstractProcess {

    private static final Logger LOGGER = Logger.getLogger("org.geotoolkit.processing");
    private static final SampleClassifierDescriptor IMG_CAT_DESC = new SampleClassifierDescriptor();

    public Categorize() {
        super(new CategorizeDescriptor());
        inputParameters = Parameters.castOrWrap(CategorizeDescriptor.INPUT.createValue());
    }

    public Categorize(ProcessDescriptor desc, ParameterValueGroup input) {
        super(desc, input);
    }

    @Override
    protected void execute() throws ProcessException {
        final GridCoverageResource source = getSource();
        final WritableGridCoverageResource destination = getDestination();

        try {
            final GridGeometry inputGG = source.getGridGeometry();

            final GridGeometry readGeom;
            Envelope env = getEnvelope();
            if (env == null) {
                env = inputGG.getEnvelope();
                readGeom = inputGG;
            } else {
                MathTransform gridToCRS = inputGG.getGridToCRS(PixelInCell.CELL_CORNER);
                GeographicBoundingBox bbox = null;
                try {
                    bbox = ReferencingUtilities.findGeographicBBox(source).orElse(null);
                } catch (DataStoreException e) {
                    /* This error is not directly related to data. It could be
                     * caused by malformed metadata. In which case, we just
                     * ignore it.
                     */
                    LOGGER.log(Level.FINE, "Cannot deduce geographic extent from metadata.", e);
                }

                if (env.getCoordinateReferenceSystem() != null) {
                    final CoordinateOperation op = CRS.findOperation(
                            inputGG.getCoordinateReferenceSystem(),
                            env.getCoordinateReferenceSystem(),
                            bbox
                    );

                    gridToCRS = MathTransforms.concatenate(gridToCRS, op.getMathTransform());
                    // Crop area of interest on source coverage area
                    final GeneralEnvelope sourceEnv;
                    try {
                        sourceEnv = Envelopes.transform(op, inputGG.getEnvelope());
                    } catch (TransformException ex) {
                        throw new ProcessException("Cannot check input envelope validity against source coverage.", this, ex);
                    }
                    sourceEnv.intersect(env);
                    env = sourceEnv;
                } else {
                    final GeneralEnvelope tmpEnv = new GeneralEnvelope(env);
                    tmpEnv.setCoordinateReferenceSystem(inputGG.getCoordinateReferenceSystem());
                    // Crop area of interest on source coverage area
                    tmpEnv.intersect(inputGG.getEnvelope());
                    env = tmpEnv;
                }

                readGeom = new GridGeometry(PixelInCell.CELL_CORNER, gridToCRS, env, GridRoundingMode.ENCLOSING);
            }

            final GridGeometryIterator it = new GridGeometryIterator(readGeom);
            while (it.hasNext()) {
                final GridGeometry sliceGeom = it.next();
                final GeneralEnvelope expectedSliceEnvelope = GeneralEnvelope.castOrCopy(sliceGeom.getEnvelope());
                GridCoverage sourceCvg = source.read(sliceGeom);
                if (sourceCvg instanceof GridCoverageStack) {
                    // Try to unravel expected slice
                    final Optional<GridCoverage> slice = extractSlice((GridCoverageStack) sourceCvg, sliceGeom.getEnvelope());
                    if (slice.isPresent()) {
                        sourceCvg = slice.get();
                    }
                }

                // If the reader has not returned a coverage fitting queried
                // geometry, we have to resample input ourselves.
                GridCoverage source2D = sourceCvg;
                source2D = source2D.forConvertedValues(true);
                final boolean compliantCrs = Utilities.equalsApproximately(expectedSliceEnvelope.getCoordinateReferenceSystem(), source2D.getCoordinateReferenceSystem());
                final boolean compliantEnvelope = expectedSliceEnvelope.contains(source2D.getGridGeometry().getEnvelope(), true);
                if (!(compliantCrs && compliantEnvelope)) {
                    source2D = resample(source2D, sliceGeom);
                }

                final RenderedImage slice = categorize(source2D.render(null));

                final GridCoverageBuilder builder = new GridCoverageBuilder();
                builder.setDomain(source2D.getGridGeometry());
                builder.setValues(slice);

                final GridCoverage resultCoverage = builder.build();

                destination.write(resultCoverage);
            }
        } catch (TransformException ex) {
            throw new ProcessException("Cannot adapt input geometry", this, ex);
        } catch (FactoryException ex) {
            throw new ProcessException("Failure on EPSG database use", this, ex);
        } catch (DataStoreException ex) {
            throw new ProcessException("Cannot access either input or output data source", this, ex);
        } catch (CancellationException ex) {
            throw new DismissProcessException("Process cancelled", this, ex);
        }
    }

    /**
     * Try to find a 2D coverage matching input envelope in the given stack.
     * @param source A coverage stack to search for 2D slice into.
     * @param aoi The envelope of the 2D coverage to find.
     * @return If we find a 2D data contained in the given envelope, we return it.
     * Otherwise, we return nothing.
     */
    private static Optional<GridCoverage> extractSlice(final GridCoverageStack source, final Envelope aoi) {
        int stackSize = source.getStackSize();
        for (int i = 0; i < stackSize; i++) {
            final GridCoverage cvg = source.coverageAtIndex(i);
            final GeneralEnvelope subsetEnvelope = GeneralEnvelope.castOrCopy(cvg.getGridGeometry().getEnvelope());
            if (subsetEnvelope.contains(aoi, true)) {
                if (cvg instanceof GridCoverageStack) {
                    return extractSlice((GridCoverageStack) cvg, aoi);
                } else {
                    return Optional.of(cvg);
                }
            }
        }

        return Optional.empty();
    }

    /**
     * Apply classification as parameterized in this process input on a single
     * image.
     * @param input The image to classify (lookup algorithm).
     * @return Classified image. It's a copy of the original one.
     * @throws ProcessException If an error happens while classifying.
     */
    private RenderedImage categorize(final RenderedImage input) throws ProcessException {
        final ParameterValueGroup values = IMG_CAT_DESC.getInputDescriptor().createValue();
        values.parameter(SampleClassifierDescriptor.IMAGE.getName().getCode()).setValue(input);
        values.parameter(SampleClassifierDescriptor.FILL_VALUE.getName().getCode()).setValue(getFillValue());
        final String catParamCode = SampleClassifierDescriptor.CATEGORIES.getName().getCode();
        inputParameters.groups(catParamCode).stream()
                .forEach(category -> Parameters.copy(category, values.addGroup(catParamCode)));

        final SampleClassifier classifier = new SampleClassifier(IMG_CAT_DESC, values);
        final Parameters result = Parameters.castOrWrap(classifier.call());
        return result.getMandatoryValue(SampleClassifierDescriptor.IMAGE);
    }

    /**
     * @implNote: The grid geometry of the returned coverage might not be the
     * same as the given one. Its grid envelope and grid to crs transform might
     * be differents. However, the envelope and resolution should match.
     * @param source The coverage to crop/subsample.
     * @param target Definition of the output data space.
     * @return The resampled data, never null.
     * @throws ProcessException
     */
    private GridCoverage resample(final GridCoverage source, final GridGeometry target) throws ProcessException, TransformException {
        final ResampleProcess resample = new ResampleProcess(
                source,
                target.getCoordinateReferenceSystem(),
                target,
                InterpolationCase.NEIGHBOR,
                new double[]{getFillValue()}
        );

        return resample.executeNow();
    }

    public GridCoverageResource getSource() {
        return inputParameters.getMandatoryValue(CategorizeDescriptor.IN_COVERAGE);
    }

    public void setSource(final GridCoverageResource source) {
        inputParameters.getOrCreate(CategorizeDescriptor.IN_COVERAGE).setValue(source);
    }

    /**
     * Create a new category and add it to the categories to use for classification.
     * No collision test is done.
     *
     * @param inclusiveMin The lower boundary for category range. Inclusive.
     * @param exclusiveMax The upper boundary for category range. Exclusive.
     * @param classValue The class to associate to the created category.
     */
    public void addInterval(final float inclusiveMin, final float exclusiveMax, final byte classValue) {
        SampleClassifier.addInterval(inputParameters, inclusiveMin, exclusiveMax, classValue);
    }

    public WritableGridCoverageResource getDestination() {
        return inputParameters.getMandatoryValue(CategorizeDescriptor.OUT_COVERAGE);
    }

    public void setDestination(final GridCoverageResource destination) {
        inputParameters.getOrCreate(CategorizeDescriptor.OUT_COVERAGE).setValue(destination);
    }

    public Byte getFillValue() {
        return inputParameters.getValue(SampleClassifierDescriptor.FILL_VALUE);
    }

    /**
     * Specify the value to use as a fallback for samples which does not fit in
     * any category.
     * @param fillValue The value to use as a fallback.
     */
    public void setFillValue(final byte fillValue) {
        inputParameters.getOrCreate(SampleClassifierDescriptor.FILL_VALUE).setValue(fillValue);
    }

    public Envelope getEnvelope() {
        return inputParameters.getValue(CategorizeDescriptor.ENVELOPE);
    }

    public void setEnvelope(final Envelope roi) {
        inputParameters.getOrCreate(CategorizeDescriptor.ENVELOPE).setValue(roi);
    }
}
