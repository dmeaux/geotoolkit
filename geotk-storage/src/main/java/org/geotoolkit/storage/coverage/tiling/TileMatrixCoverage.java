/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2022, Geomatys
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
package org.geotoolkit.storage.coverage.tiling;

import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.util.Arrays;
import java.util.List;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridExtent;
import org.apache.sis.coverage.grid.GridGeometry;
import org.apache.sis.coverage.grid.GridRoundingMode;
import org.apache.sis.storage.tiling.TileMatrix;
import org.apache.sis.util.ArgumentChecks;
import org.geotoolkit.storage.multires.TileMatrices;
import org.opengis.coverage.CannotEvaluateException;

/**
 * View a tile matrix composed of GridCoverage tiles as a GridCoverage.
 *
 * @author Johann Sorel (Geomatys)
 */
final class TileMatrixCoverage extends GridCoverage {

    private final TileMatrixCoverageResource resource;
    private final int[] range;
    private final long[] tileSize;

    /**
     *
     * @param matrix
     * @param schemeIntersection intersected tile range in the matrix
     * @param coverageGrid intersection same size or smaller area then the scheme intersection
     * @param coverageIntersection
     * @param tileSize
     * @param sampleDimensions
     */
    TileMatrixCoverage(
            TileMatrixCoverageResource resource,
            GridExtent schemeIntersection,
            long[] tileSize,
            int[] range,
            List<SampleDimension> sampleDimensions) {
        super(buildGridGeometry(schemeIntersection, resource.getMatrix(), tileSize), sampleDimensions);
        ArgumentChecks.ensureNonNull("resource", resource);
        ArgumentChecks.ensureNonNull("tileSize", tileSize);
        ArgumentChecks.ensureNonNull("range", range);
        ArgumentChecks.ensureExpectedCount("sample dimensions size", range.length, sampleDimensions.size());
        this.resource = resource;
        this.tileSize = tileSize.clone();
        this.range = range.clone();
    }

    private static GridGeometry buildGridGeometry(GridExtent extent, TileMatrix matrix, long[] tileSize) {
        final GridGeometry tilingScheme = matrix.getTilingScheme();
        return TileMatrices.surSampling(tilingScheme.derive().rounding(GridRoundingMode.ENCLOSING).subgrid(extent).build(), tileSize);
    }

    @Override
    public RenderedImage render(GridExtent userExtent) throws CannotEvaluateException {

        final GridGeometry gridGeometry = getGridGeometry();

        final GridGeometry userGeometry;
        if (userExtent == null) {
            userGeometry = gridGeometry;
            userExtent = userGeometry.getExtent();
        } else {
            userGeometry = gridGeometry.derive().subgrid(userExtent).build();
        }
        final GridExtent readExtent = userGeometry.getExtent();

        //ensure we have a valid 2D image extent
        //will raise an exception if incorrect
        final int[] xyAxes = readExtent.getSubspaceDimensions(2);

        //convert the requested extent to tile range.
        final GridExtent absoluteTileExtent = readExtent.subsample(Arrays.stream(tileSize).mapToInt(Math::toIntExact).toArray());
        final GridExtent absolutedReadExtent = TileMatrices.surSampling(absoluteTileExtent, tileSize);

        //compute image model and image
        final Object[] structure = resource.getImageModel(readExtent, range);
        final SampleModel sampleModel = (SampleModel) structure[0];
        final ColorModel colorModel = (ColorModel) structure[1];
        final Raster rasterModel = (Raster) structure[2];
        final double[] fillPixel = (double[]) structure[3];

        /* Compute image offset
        tile matrix origin
        +--------
        |  coverage origin, always on the corner of a tile
        |---+--------
        |   |  render extent origin, anywhere in the image
        |---|   +--------
        |   |   |
         */
        final int minX = (int) (absolutedReadExtent.getLow(xyAxes[0]) - userExtent.getLow(xyAxes[0]));
        final int minY = (int) (absolutedReadExtent.getLow(xyAxes[1]) - userExtent.getLow(xyAxes[1]));
        final int width = Math.toIntExact(userExtent.getSize(xyAxes[0]));
        final int height = Math.toIntExact(userExtent.getSize(xyAxes[1]));

        //create image
        return new TileMatrixImage(
                resource.getMatrix(),
                absoluteTileExtent,
                userGeometry,
                tileSize,
                sampleModel,
                colorModel,
                rasterModel,
                range,
                fillPixel,
                minX, minY,
                width, height
        );
    }

}
