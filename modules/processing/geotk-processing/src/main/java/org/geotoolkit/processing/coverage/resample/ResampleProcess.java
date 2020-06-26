/*
 *    Geotoolkit.org - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2012, Geomatys
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
package org.geotoolkit.processing.coverage.resample;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridExtent;
import org.apache.sis.coverage.grid.GridGeometry;
import org.apache.sis.image.WritablePixelIterator;
import org.apache.sis.parameter.Parameters;
import org.apache.sis.referencing.operation.transform.LinearTransform;
import org.geotoolkit.image.BufferedImages;
import org.geotoolkit.image.interpolation.InterpolationCase;
import org.geotoolkit.image.interpolation.Resample;
import org.geotoolkit.image.interpolation.ResampleBorderComportement;
import org.geotoolkit.internal.coverage.CoverageUtilities;
import org.geotoolkit.process.ProcessException;
import org.geotoolkit.processing.AbstractProcess;
import static org.geotoolkit.processing.coverage.resample.ResampleDescriptor.*;
import org.geotoolkit.resources.Errors;
import org.opengis.coverage.CannotEvaluateException;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;


/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class ResampleProcess extends AbstractProcess {

    public ResampleProcess(GridCoverage coverage, CoordinateReferenceSystem targetCrs, double[] background) {
        super(INSTANCE, asParameters(coverage, targetCrs,  null, null, background));
    }

    public ResampleProcess(GridCoverage coverage, CoordinateReferenceSystem targetCrs,
                           GridGeometry gridGeom, InterpolationCase interpolation, double[] background) {
        super(INSTANCE, asParameters(coverage, targetCrs, gridGeom, interpolation, background));
    }

    public ResampleProcess(final ParameterValueGroup input) {
        super(INSTANCE, input);
    }

    private static ParameterValueGroup asParameters(GridCoverage coverage, CoordinateReferenceSystem targetCrs,
            GridGeometry gridGeom, InterpolationCase interpolation, double[] background){
        final Parameters params = Parameters.castOrWrap(ResampleDescriptor.INPUT_DESC.createValue());
        params.getOrCreate(IN_COVERAGE).setValue(coverage);
        if (targetCrs != null) {
            params.getOrCreate( IN_COORDINATE_REFERENCE_SYSTEM).setValue(targetCrs);
        }
        if (gridGeom != null) {
            params.getOrCreate(IN_GRID_GEOMETRY).setValue(gridGeom);
        }
        if (background != null) {
            params.getOrCreate(IN_BACKGROUND).setValue(background);
        }
        if (interpolation != null) {
            params.getOrCreate(IN_INTERPOLATION_TYPE).setValue(interpolation);
        }
        return params;
    }

    public GridCoverage executeNow() throws ProcessException {
        execute();
        return outputParameters.getValue(OUT_COVERAGE);
    }

    /**
     * Resamples a grid coverage.
     */
    @Override
    protected void execute() throws ProcessException {
        final GridCoverage source = inputParameters.getValue(IN_COVERAGE);
        final double[] background = inputParameters.getValue(IN_BACKGROUND);
        InterpolationCase interpolation = inputParameters.getValue(IN_INTERPOLATION_TYPE);
        final ResampleBorderComportement border = inputParameters.getValue(IN_BORDER_COMPORTEMENT_TYPE);

        CoordinateReferenceSystem targetCRS = (CoordinateReferenceSystem) inputParameters.parameter("CoordinateReferenceSystem").getValue();
        final GridGeometry targetGG = inputParameters.getValue(IN_GRID_GEOMETRY);
        final GridCoverage target;

        try {
            target = reproject(source, targetCRS, targetGG, interpolation, border, background);
        } catch (FactoryException exception) {
            throw new CannotReprojectException(Errors.format(
                    Errors.Keys.CantReprojectCoverage_1, CoverageUtilities.getName(source)), exception);
        } catch (TransformException exception) {
            throw new CannotReprojectException(Errors.format(
                    Errors.Keys.CantReprojectCoverage_1, CoverageUtilities.getName(source)), exception);
        }
        outputParameters.getOrCreate(OUT_COVERAGE).setValue(target);
    }

    /**
     * Creates a new coverage with a different coordinate reference reference system. If a
     * grid geometry is supplied, only its {@linkplain GridGeometry#getExtent()}  grid envelope}
     * and {@linkplain GridGeometry#getGridToCRS grid to CRS} transform are taken in account.
     *
     * @param sourceCoverage The source grid coverage.
     * @param targetCRS      Coordinate reference system for the new grid coverage, or {@code null}.
     * @param targetGG       The target grid geometry, or {@code null} for default.
     * @param background     The background values, or {@code null} for default.
     * @param interpolationType  The interpolation to use, or {@code null} if none.
     * @return  The new grid coverage, or {@code sourceCoverage} if no resampling was needed.
     * @throws  FactoryException If a transformation step can't be created.
     * @throws TransformException If a transformation failed.
     */
    public static GridCoverage reproject(GridCoverage              sourceCoverage,
                                           CoordinateReferenceSystem targetCRS,
                                           GridGeometry              targetGG,
                                           InterpolationCase         interpolationType,
                                           double[]                  background)
            throws FactoryException, TransformException
    {
        return reproject(sourceCoverage, targetCRS, targetGG, interpolationType, null, background);
    }

    /**
     * Creates a new coverage with a different coordinate reference reference system. If a
     * grid geometry is supplied, only its {@linkplain GridGeometry#getExtent()}  grid envelope}
     * and {@linkplain GridGeometry#getGridToCRS grid to CRS} transform are taken in account.
     *
     * @param sourceCov      The source grid coverage.
     * @param targetCRS      Coordinate reference system for the new grid coverage, or {@code null}.
     * @param targetGG       The target grid geometry, or {@code null} for default.
     * @param background     The background values, or {@code null} for default.
     * @param borderComportement The comportement used when points are outside of the source coverage,
     *          or {@code null} for default. Default is EXTRAPOLATION.
     * @param interpolationType  The interpolation to use, or {@code null} if none.
     * @return  The new grid coverage, or {@code sourceCoverage} if no resampling was needed.
     * @throws  FactoryException If a transformation step can't be created.
     * @throws TransformException If a transformation failed.
     */
    public static GridCoverage reproject(GridCoverage              sourceCov,
                                           CoordinateReferenceSystem targetCRS,
                                           GridGeometry              targetGG,
                                           InterpolationCase         interpolationType,
                                           ResampleBorderComportement borderComportement,
                                           double[]                  background)
            throws FactoryException, TransformException
    {
        //set default values

        if (interpolationType == null) {
            interpolationType = InterpolationCase.NEIGHBOR;
        }
        if(borderComportement==null)
            borderComportement = ResampleBorderComportement.EXTRAPOLATION;
        // Temporary HACK because org.geotoolkit.image.interpolation.Resample does not support cropping.
        else if (ResampleBorderComportement.CROP.equals(borderComportement))
            borderComportement = ResampleBorderComportement.FILL_VALUE;


        ////////////////////////////////////////////////////////////////////////////////////////
        ////                                                                                ////
        //// =======>>  STEP 1: Extracts needed information from the parameters   <<====== ////
        ////            STEP 2: Creates the "target to source" MathTransform                ////
        ////            STEP 3: Computes the target image layout                            ////
        ////            STEP 4: Applies the transform operation ("Affine","Warp")           ////
        ////                                                                                ////
        ////////////////////////////////////////////////////////////////////////////////////////

        /*
         * The projection are usually applied on floating-point values, in order to gets maximal precision and to handle
         * correctly the special case of NaN values. However, we can apply the projection on integer values if the
         * interpolation type is "nearest neighbor", since this is not really an interpolation. We can also keep integer
         * values if sample conversion from packed to geophysic is fully linear, and no "no-data" value is defined. This
         * last requirement is very important, in order to avoid mixing of aberrant values.
         *
         * If one of the two conditions above is meet, then we verify if an "integer version" of the image is available
         * as a source of the source coverage (i.e. the floating-point image is derived from the integer image, not the
         *  converse).
         */
        final List<SampleDimension> sds = sourceCov.getSampleDimensions();
        if (sds == null || sds.isEmpty())
            throw new IllegalArgumentException("Input coverage does not properly declare sample dimensions");
        final int nBands = sds.size();

        final boolean interpolationIsNearestNeighbor = InterpolationCase.NEIGHBOR.equals(interpolationType);
        final boolean geophysicRequired = !interpolationIsNearestNeighbor && isGeophysicRequired(sds);
        sourceCov = sourceCov.forConvertedValues(geophysicRequired);

        //extract fill value after the resampling view type has been chosen. Note that if no geophysic view is needed, no fill value can be correctly applied.
        final double[] fillValue;
        if (background != null) {
            if (nBands != background.length) {
                throw new TransformException("Invalid default values, expected size " + nBands + " but was " + background.length);
            }
            fillValue = background;
        } else if (geophysicRequired || interpolationIsNearestNeighbor) {
            fillValue = getFillValue(sourceCov);
        } else fillValue = null;

        ////////////////////////////////////////////////////////////////////////////////////////
        ////                                                                                ////
        ////            STEP 1: Extracts needed informations from the parameters            ////
        //// =======>>  STEP 2: Creates the "target to source" MathTransform       <<====== ////
        ////            STEP 3: Computes the target image layout                            ////
        ////            STEP 4: Applies the transform operation ("Affine","Warp")           ////
        ////                                                                                ////
        ////////////////////////////////////////////////////////////////////////////////////////

        final OutputGridBuilder builder = new OutputGridBuilder(sourceCov.getGridGeometry(), targetGG)
                .setTargetCrs(targetCRS);

        /* For now, we use only one rendering to pass from source to target. However, in the future, we could split
         * processing into many tiles, each one with its own transform layout for rendering. We could also use
         * progressive rendering, and make rendering transform layout vary upon user request.
         */
        MathTransform targetToSource = builder.forDefaultRendering();

        ////////////////////////////////////////////////////////////////////////////////////////
        ////                                                                                ////
        ////            STEP 1: Extracts needed informations from the parameters            ////
        ////            STEP 2: Creates the "target to source" MathTransform                ////
        //// =======>>  STEP 3: Computes the target image layout                   <<====== ////
        ////            STEP 4: Applies the transform operation ("Affine","Warp")           ////
        ////                                                                                ////
        ////////////////////////////////////////////////////////////////////////////////////////

        RenderedImage sourceImage = sourceCov.render(null); // TODO : We should check if only a subset of source image is required
        final List<SampleDimension> outputSampleDims = geophysicRequired?
                sds.stream()
                        .map(sd -> sd.forConvertedValues(true))
                        .collect(Collectors.toList())
                : sds;

        final Dimension outputDim = builder.getTargetImageDimension();
        final BufferedImage targetImage = BufferedImages.createImage(outputDim.width, outputDim.height, sourceImage);
        final WritableRaster targetRaster = targetImage.getRaster();
        //fill target image with fill values
        if (fillValue != null) {
            //if fill values are all 0 do nothing
            //zero is the default value in created raster
            boolean allZero = true;
            for (double d : fillValue) {
                if (d != 0.0) {
                    allZero = false;
                    break;
                }
            }
            if (!allZero) {
                final WritablePixelIterator writer = WritablePixelIterator.create(targetImage);
                while (writer.next()) {
                    writer.setPixel(fillValue);
                }
                writer.close();
            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////
        ////                                                                                ////
        ////            STEP 1: Extracts needed informations from the parameters            ////
        ////            STEP 2: Creates the "target to source" MathTransform                ////
        ////            STEP 3: Computes the target image layout                            ////
        //// =======>>  STEP 4: Applies the transform operation ("Affine","Warp")  <<====== ////
        ////                                                                                ////
        ////////////////////////////////////////////////////////////////////////////////////////

        if (targetToSource.isIdentity()) {
            // TODO: couldn't we return directly source coverage ?
            if (sourceImage.getWidth() == targetRaster.getWidth() && sourceImage.getHeight() == targetRaster.getHeight()) {
                //we can directly copy raster to raster
                targetRaster.setDataElements(0, 0, sourceImage.getData());
            } else {
                // the setRect method is more expensive but will make the appropriate clipping
                targetRaster.setRect(0, 0, sourceImage.getData());
            }
        } else {
            final Resample resample = new Resample(targetToSource, targetImage, sourceImage,
                    interpolationType, borderComportement, fillValue);
            resample.fillImage();
        }

        return new NoConversionCoverage(builder.target, outputSampleDims, targetImage, !geophysicRequired);
    }

    private static double[] getFillValue(GridCoverage source) {
        return source.getSampleDimensions().stream()
                .mapToDouble(sd -> getFillValue(sd, Double.NaN))
                .toArray();
    }

    private static double getFillValue(final SampleDimension sd, final double defaultValue) {
        final Optional<Number> bg = sd.getBackground();
        if (bg.isPresent())
            return bg.get().doubleValue();
        final Set<Number> noDataValues = sd.getNoDataValues();
        if (noDataValues.isEmpty())
            return defaultValue;
        return noDataValues.iterator().next().doubleValue();
    }

    /**
     * Test given coverage sample dimension to determine if pixel interpolation requires geophysic values, or can be
     * processed upon packed values (which can be more efficient). The only cases where packed values are sufficient are
     * when no fill value exists, and transfer function is fully linear.
     *
     * @param source Coverage to check.
     * @return True if resample HAVE TO USE geophysic values. False otherwise.
     */
    private static boolean isGeophysicRequired(final List<SampleDimension> source) {
        for (SampleDimension sd : source) {
            // If there's missing data in the image, we cannot interpolate packed values, as it would possibly mix
            // an aberrant value (Ex: -32767 in a short packed image) with valid packed data.
            if (!sd.getNoDataValues().isEmpty())
                return true;

            final boolean isLinear = sd.getTransferFunction()
                    .map(tr -> (tr instanceof LinearTransform))
                    .orElse(Boolean.TRUE);
            if (!isLinear)
                return true;
        }

        // If we get here, all sample dimensions have got a linear transfer function, geophysic interpolation is not required.
        return false;
    }

    static class NoConversionCoverage extends GridCoverage {

        final RenderedImage buffer;

        private final boolean allowConversion;

        /**
         * Constructs a grid coverage using the specified grid geometry and sample dimensions.
         *  @param grid  the grid extent, CRS and conversion from cell indices to CRS.
         * @param bands sample dimensions for each image band.
         */
        NoConversionCoverage(GridGeometry grid, Collection<? extends SampleDimension> bands, RenderedImage buffer, boolean allowConversion) {
            super(grid, bands);
            this.buffer = buffer;
            this.allowConversion = allowConversion;
        }

        @Override
        public synchronized  GridCoverage forConvertedValues(boolean converted) {
            if (allowConversion) return super.forConvertedValues(converted);
            return this;
        }

        @Override
        public RenderedImage render(GridExtent sliceExtent) throws CannotEvaluateException {
            if (sliceExtent == null || sliceExtent.equals(getGridGeometry().getExtent()))
                return buffer;
            if (buffer instanceof BufferedImage) {
                final BufferedImage img = (BufferedImage) buffer;
                return CoverageUtilities.subgrid(img, sliceExtent);
            } else {
                throw new UnsupportedOperationException("TODO: generic case for cropped view.");
            }
        }
    }
}
