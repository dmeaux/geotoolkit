/*
 *    Geotoolkit.org - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2002-2012, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2009-2012, Geomatys
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
package org.geotoolkit.coverage.grid;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.Random;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.geometry.GeneralEnvelope;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridCoverageBuilder;
import org.apache.sis.referencing.CommonCRS;
import org.geotoolkit.test.image.ImageTestBase;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import static org.junit.Assert.*;
import static org.apache.sis.measure.Units.*;


/**
 * Base class for grid coverage tests. This class provides a {@link GridCoverage} field,
 * and some convenience methods working on it.
 *
 * @author Martin Desruisseaux (IRD)
 * @version 3.02
 *
 * @since 2.1
 */
public abstract class GridCoverageTestBase extends ImageTestBase {
    /**
     * Random number generator for this test.
     */
    private static final Random random = new Random(684673898634768L);

    /**
     * The coverage to be tested. An instance can be obtained by
     * {@link #loadSampleCoverage(SampleCoverage)} or {@link #createRandomCoverage()}.
     */
    protected GridCoverage coverage;

    /**
     * Creates a new test suite for the given class.
     *
     * @param testing The class to be tested.
     */
    protected GridCoverageTestBase(final Class<?> testing) {
        super(testing);
    }

    /**
     * Loads the given sample coverage. The result is stored in the {@link #coverage} field.
     *
     * @param  s The enum for the sample grid coverage to load.
     */
    protected final void loadSampleCoverage(final SampleCoverage s) {
        try {
            coverage = s.load();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
        s.verifyGridGeometry(coverage, 1E-10);
    }

    /**
     * Creates a grid coverage filled with random values. The coordinate
     * reference system default to {@link CommonCRS#defaultGeographic()}.
     */
    protected final void createRandomCoverage() {
        createRandomCoverage(CommonCRS.WGS84.normalizedGeographic());
    }

    /**
     * Creates a grid coverage filled with random values.
     * The result is stored in the {@link #coverage} field.
     *
     * @param crs The coverage coordinate reference system.
     */
    protected final void createRandomCoverage(final CoordinateReferenceSystem crs) {
        /*
         * Some constants used for the construction and tests of the grid coverage.
         */
        final double      SCALE = 0.1; // Scale factor for pixel transcoding.
        final double     OFFSET = 5.0; // Offset factor for pixel transcoding.
        final double PIXEL_SIZE = .25; // Pixel size (in degrees). Used in transformations.
        final int   BEGIN_VALID = 3;   // The minimal valid index for quantitative category.
        /*
         * Constructs the grid coverage. We will assume that the grid coverage use
         * (longitude,latitude) coordinates, pixels of 0.25 degrees and a lower
         * left corner at 10°W 30°N.
         */
        final GridCoverage  coverage;  // The final grid coverage.
        final BufferedImage   image;     // The GridCoverage's data.
        final WritableRaster  raster;    // The image's data as a raster.
        final Rectangle2D     bounds;    // The GridCoverage's envelope.
        final SampleDimension band;      // The only image's band.
        band = new SampleDimension.Builder()
                .setName        ("Temperature")
                .addQualitative ("No data", 0)
                .addQualitative ("Land",    1)
                .addQualitative ("Cloud",   2)
                .addQuantitative("Temperature", BEGIN_VALID, 256, SCALE, OFFSET, CELSIUS)
                .build();
        image  = new BufferedImage(120, 80, BufferedImage.TYPE_BYTE_INDEXED);
        raster = image.getRaster();
        for (int i=raster.getWidth(); --i>=0;) {
            for (int j=raster.getHeight(); --j>=0;) {
                raster.setSample(i,j,0, random.nextInt(256));
            }
        }
        bounds = new Rectangle2D.Double(-10, 30, PIXEL_SIZE*image.getWidth(),
                                                 PIXEL_SIZE*image.getHeight());
        final GeneralEnvelope envelope = new GeneralEnvelope(crs);
        envelope.setRange(0, bounds.getMinX(), bounds.getMaxX());
        envelope.setRange(1, bounds.getMinY(), bounds.getMaxY());
        for (int i=envelope.getDimension(); --i>=2;) {
            final double min = 10 * i;
            envelope.setRange(i, min, min + 5);
        }
        final GridCoverageBuilder gcb = new GridCoverageBuilder();
        gcb.setValues(image);
        gcb.setRanges(band);
        gcb.setDomain(envelope);
        coverage = gcb.build();
        /*
         * Grid coverage construction finished.  Now test it.  First we test the creation of a
         * "geophysics" view. This test make sure that the 'view(type)' method does not create
         * more grid coverages than needed.
         */
        final RenderedImage rendering = coverage.render(null);
        assertSame(image.getTile(0,0), rendering.getTile(0,0));
        assertFalse(coverage.getSampleDimensions().get(0).getTransferFunction().get().isIdentity());

        this.coverage = coverage;
    }

    /**
     * Shows the default rendering of the specified coverage.
     * This is used for debugging only.
     *
     * @param coverage The coverage to display.
     */
    protected final synchronized void show(final GridCoverage coverage) {
        if (!viewEnabled) {
            return;
        }
        final RenderedImage image = coverage.render(null);
        try {
            Class.forName("org.geotoolkit.gui.swing.image.OperationTreeBrowser")
                 .getMethod("show", new Class<?>[] {RenderedImage.class})
                 .invoke(null, new Object[]{image});
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            /*
             * The OperationTreeBrowser is not part of Geotk's core. It is optional and this
             * class should not fails if it is not presents. This is only a helper for debugging.
             */
            System.err.println(e);
        }
    }
}
