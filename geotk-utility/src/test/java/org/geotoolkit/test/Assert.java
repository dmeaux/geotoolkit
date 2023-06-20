/*
 *    Geotoolkit.org - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2010-2012, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2010-2012, Geomatys
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
package org.geotoolkit.test;

import java.awt.image.RenderedImage;
import javax.media.jai.iterator.RectIter;
import javax.media.jai.iterator.RectIterFactory;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.Matrix;
import org.apache.sis.referencing.operation.transform.LinearTransform;
import org.apache.sis.util.ComparisonMode;
import org.apache.sis.util.Utilities;

import static java.lang.StrictMath.min;
import static org.junit.Assert.*;
import static org.geotoolkit.test.image.ImageTestBase.SAMPLE_TOLERANCE;


/**
 * Assertion methods used by the Geotk project in addition of the JUnit, GeoAPI and SIS assertions.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
public final class Assert {
    /**
     * Small tolerance for comparisons of floating point values.
     */
    private static final double EPS = 1E-7;

    /**
     * Do not allow instantiation.
     */
    private Assert() {
    }

    /**
     * Asserts that the two given objects are equal ignoring metadata.
     * See {@link ComparisonMode#IGNORE_METADATA} for more information.
     *
     * @param expected  The expected object.
     * @param actual    The actual object.
     * @param strictlyDifferent {@code true} if the objects should not be strictly equal.
     */
    public static void assertEqualsIgnoreMetadata(final Object expected, final Object actual, final boolean strictlyDifferent) {
        assertTrue("Should be approximatively equals",    Utilities.deepEquals(expected, actual, ComparisonMode.DEBUG));
        assertTrue("DEBUG inconsistent with APPROXIMATE", Utilities.deepEquals(expected, actual, ComparisonMode.APPROXIMATE));
        assertTrue("Should be equals, ignoring metadata", Utilities.deepEquals(expected, actual, ComparisonMode.IGNORE_METADATA));
        if (strictlyDifferent) {
            assertFalse("Should not be strictly equals",  Utilities.deepEquals(expected, actual, ComparisonMode.STRICT));
        }
    }

    /**
     * Asserts that the given transform is represented by diagonal matrix where every elements
     * on the diagonal have the given values. The matrix doesn't need to be square. The last
     * row is handled especially if the {@code affine} argument is {@code true}.
     *
     * @param tr     The transform.
     * @param affine If {@code true}, then the last row is expected to contains the value 1
     *               in the last column, and all other columns set to 0.
     * @param values The values which are expected on the diagonal. If this array length is
     *               smaller than the diagonal length, then the last element in the array
     *               is repeated for all remaining diagonal elements.
     *
     * @since 3.07
     */
    public static void assertDiagonalMatrix(final MathTransform tr, final boolean affine, final double... values) {
        assertTrue("The transform shall be linear.", tr instanceof LinearTransform);
        final Matrix matrix = ((LinearTransform) tr).getMatrix();
        final int numRows = matrix.getNumRow();
        final int numCols = matrix.getNumCol();
        for (int j=0; j<numRows; j++) {
            for (int i=0; i<numCols; i++) {
                final double expected;
                if (affine && j == numRows-1) {
                    expected = (i == numCols-1) ? 1 : 0;
                } else if (i == j) {
                    expected = values[min(values.length-1, i)];
                } else {
                    expected = 0;
                }
                assertEquals("matrix(" + j + ',' + i + ')', expected, matrix.getElement(j, i), EPS);
            }
        }
    }

    /**
     * Compares two rasters for equality. The sample values are compared with {@code float}
     * precision, because this is the format used by the majority of geophysics raster data.
     * <p>
     * This method does not test if {@linkplain #assertBoundEquals bounds are equal} (actually,
     * it ensures that the image are of the same size but doesn't check if the origin is the
     * same). It is user responsibility to invoke the above method if desired.
     *
     * @param expected The image containing the expected pixel values.
     * @param actual   The image containing the actual pixel values.
     */
    public static void assertRasterEquals(final RenderedImage expected, final RenderedImage actual) {
        final RectIter e = RectIterFactory.create(expected, null);
        final RectIter a = RectIterFactory.create(actual,   null);
        if (!e.finishedLines()) do {
            assertFalse(a.finishedLines());
            if (!e.finishedPixels()) do {
                assertFalse(a.finishedPixels());
                if (!e.finishedBands()) do {
                    assertFalse(a.finishedBands());
                    final float pe = e.getSampleFloat();
                    final float pa = a.getSampleFloat();
                    assertEquals(pe, pa, SAMPLE_TOLERANCE);
                    a.nextBand();
                } while (!e.nextBandDone());
                assertTrue(a.finishedBands());
                a.nextPixel();
                a.startBands();
                e.startBands();
            } while (!e.nextPixelDone());
            assertTrue(a.finishedPixels());
            a.nextLine();
            a.startPixels();
            e.startPixels();
        } while (!e.nextLineDone());
        assertTrue(a.finishedLines());
    }
}
