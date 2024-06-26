/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2013, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotoolkit.style.io;

import java.awt.Color;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.geotoolkit.filter.FilterUtilities;
import org.geotoolkit.style.DefaultStyleFactory;
import org.geotoolkit.style.MutableStyleFactory;
import org.geotoolkit.style.StyleConstants;
import org.geotoolkit.style.function.Categorize;
import org.geotoolkit.style.function.DefaultInterpolationPoint;
import org.geotoolkit.style.function.Interpolate;
import org.geotoolkit.style.function.InterpolationPoint;
import static org.junit.Assert.*;
import org.junit.Test;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.Expression;
import org.opengis.style.ColorMap;


/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class PaletteReaderTest {

    protected static final FilterFactory FF = FilterUtilities.FF;
    protected static final MutableStyleFactory SF = DefaultStyleFactory.provider();

    @Test
    public void readCLR() throws IOException {
        String palette =
            "ColorMap 1 1\n" +
            "0.000000 143 0 0\n" +
            "500.000000 244 0 0\n" +
            "1000.000000 255 89 0\n" +
            "1500.000000 255 189 0\n" +
            "2000.000000 236 255 34\n" +
            "2500.000000 135 255 135\n" +
            "3000.000000 34 255 236\n" +
            "3500.000000 0 189 255\n" +
            "4000.000000 0 89 255\n" +
            "4500.000000 0 0 244\n" +
            "5000.000000 0 0 143";

        final ColorMap cm = new PaletteReader(PaletteReader.PATTERN_CLR).read(palette);
        assertTrue(cm.getFunction() instanceof Interpolate);
        final Interpolate interpolate = (Interpolate) cm.getFunction();
        final List<InterpolationPoint> steps = interpolate.getInterpolationPoints();
        assertEquals(12, steps.size());
        for (int i = 0;i < steps.size(); i++) {
            final InterpolationPoint step = steps.get(i);
            InterpolationPoint expected = null;
            switch (i) {
                case  0 : expected = new DefaultInterpolationPoint(Double.NaN, SF.literal(new Color(0,  0,  0, 0))); break;
                case  1 : expected = new DefaultInterpolationPoint(   0d, SF.literal(new Color(143,  0,  0))); break;
                case  2 : expected = new DefaultInterpolationPoint( 500d, SF.literal(new Color(244,  0,  0))); break;
                case  3 : expected = new DefaultInterpolationPoint(1000d, SF.literal(new Color(255, 89,  0))); break;
                case  4 : expected = new DefaultInterpolationPoint(1500d, SF.literal(new Color(255,189,  0))); break;
                case  5 : expected = new DefaultInterpolationPoint(2000d, SF.literal(new Color(236,255, 34))); break;
                case  6 : expected = new DefaultInterpolationPoint(2500d, SF.literal(new Color(135,255,135))); break;
                case  7 : expected = new DefaultInterpolationPoint(3000d, SF.literal(new Color( 34,255,236))); break;
                case  8 : expected = new DefaultInterpolationPoint(3500d, SF.literal(new Color(  0,189,255))); break;
                case  9 : expected = new DefaultInterpolationPoint(4000d, SF.literal(new Color(  0, 89,255))); break;
                case 10 : expected = new DefaultInterpolationPoint(4500d, SF.literal(new Color(  0,  0,244))); break;
                case 11 : expected = new DefaultInterpolationPoint(5000d, SF.literal(new Color(  0,  0,143))); break;
                default : fail("Unexpected number of elements.");
            }
            assertEquals(expected, step);
        }
    }

    @Test
    public void readCPT_RGB() throws IOException {
        String palette =
            "# Truly simulates the JET colormap in Matlab\n" +
            "# COLOR_MODEL = RGB\n" +
            "  0   0   0 143   1   0   0 159\n" +
            "  1   0   0 159   2  50   0 175\n" +
            "  2  50   0 175   3   0  12 191\n" +
            "  3   0  12 191   4   0   0 207\n" +
            "  4   0   0 207   5   0   0 223";

        final ColorMap cm = new PaletteReader(PaletteReader.PATTERN_CPT).read(palette);
        assertTrue(cm.getFunction() instanceof Categorize);
        final Categorize categorize = (Categorize) cm.getFunction();
        final Map<Expression,Expression> steps = categorize.getThresholds();
        assertEquals(8, steps.size());
        int i=0;
        for (Entry<Expression,Expression> entry : steps.entrySet()) {
            Entry<Expression,Expression> expected = null;
            switch (i) {
                case  0 : expected = new AbstractMap.SimpleEntry(StyleConstants.CATEGORIZE_LESS_INFINITY, SF.literal(new Color(0f,0f,0f,0f))); break;
                case  1 : expected = new AbstractMap.SimpleEntry(FF.literal(0d), SF.literal(new Color(  0,  0,143))); break;
                case  2 : expected = new AbstractMap.SimpleEntry(FF.literal(1d), SF.literal(new Color(  0,  0,159))); break;
                case  3 : expected = new AbstractMap.SimpleEntry(FF.literal(2d), SF.literal(new Color( 50,  0,175))); break;
                case  4 : expected = new AbstractMap.SimpleEntry(FF.literal(3d), SF.literal(new Color(  0, 12,191))); break;
                case  5 : expected = new AbstractMap.SimpleEntry(FF.literal(4d), SF.literal(new Color(  0,  0,207))); break;
                case  6 : expected = new AbstractMap.SimpleEntry(FF.literal(5d), SF.literal(new Color(  0,  0,223))); break;
                case  7 : expected = new AbstractMap.SimpleEntry(FF.literal(Double.NaN), SF.literal(new Color(  0,  0, 0, 0))); break;
                default : fail("Unexpected number of elements.");
            }
            assertEquals(expected, entry);
            i++;
        }
    }

    @Test
    public void readCPT_HSV() throws IOException {
        String palette =
            "# COLOR_MODEL = HSV\n" +
            "-6000   255     0.6     1       -5500   240     0.6     1       L\n" +
            "-5500   240     0.6     1       -5000   225     0.6     1\n" +
            "-5000   225     0.6     1       -4500   210     0.6     1       L\n" +
            "-4500   210     0.6     1       -4000   195     0.6     1\n" +
            "-4000   195     0.6     1       -3500   180     0.6     1       L\n" +
            "-3500   180     0.6     1       -3000   165     0.6     1\n" +
            "-3000   165     0.6     1       -2500   150     0.6     1       L\n" +
            "-2500   150     0.6     1       -2000   135     0.6     1\n" +
            "-2000   135     0.6     1       -1500   120     0.6     1       L\n" +
            "-1500   120     0.6     1       -1000   105     0.6     1\n" +
            "-1000   105     0.6     1       -500    90      0.6     1       L\n" +
            "-500    90      0.6     1       0       75      0.6     1\n" +
            "0       60      0.35    1       500     40      0.35    1       L\n" +
            "500     40      0.35    1       1000    20      0.35    1\n" +
            "1000    20      0.35    1       1500    0       0.35    1       L\n" +
            "1500    360     0.35    1       2000    345     0.3     1\n" +
            "2000    345     0.3     1       2500    330     0.25    1       L\n" +
            "2500    330     0.25    1       3000    315     0.2     1       U\n" +
            "B       255     0.6     1\n" +
            "F       315     0.2     1";

        final ColorMap cm = new PaletteReader(PaletteReader.PATTERN_CPT).read(palette);
        assertTrue(cm.getFunction() instanceof Categorize);
        final Categorize categorize = (Categorize) cm.getFunction();
        final Map<Expression,Expression> steps = categorize.getThresholds();
        assertEquals(22, steps.size());
        int i=0;
        for (Entry<Expression,Expression> entry : steps.entrySet()) {
            Entry<Expression,Expression> expected = null;
            switch (i) {
                case  0 : expected = new AbstractMap.SimpleEntry(StyleConstants.CATEGORIZE_LESS_INFINITY, SF.literal(new Color(0f,0f,0f,0f))); break;
                case  1 : expected = new AbstractMap.SimpleEntry(FF.literal(-6000d), SF.literal(Color.getHSBColor(255f / 360f, 0.6f, 1))); break;
                case  2 : expected = new AbstractMap.SimpleEntry(FF.literal(-5500d), SF.literal(Color.getHSBColor(240f / 360f, 0.6f, 1))); break;
                case  3 : expected = new AbstractMap.SimpleEntry(FF.literal(-5000d), SF.literal(Color.getHSBColor(225f / 360f, 0.6f, 1))); break;
                case  4 : expected = new AbstractMap.SimpleEntry(FF.literal(-4500d), SF.literal(Color.getHSBColor(210f / 360f, 0.6f, 1))); break;
                case  5 : expected = new AbstractMap.SimpleEntry(FF.literal(-4000d), SF.literal(Color.getHSBColor(195f / 360f, 0.6f, 1))); break;
                case  6 : expected = new AbstractMap.SimpleEntry(FF.literal(-3500d), SF.literal(Color.getHSBColor(180f / 360f, 0.6f, 1))); break;
                case  7 : expected = new AbstractMap.SimpleEntry(FF.literal(-3000d), SF.literal(Color.getHSBColor(165f / 360f, 0.6f, 1))); break;
                case  8 : expected = new AbstractMap.SimpleEntry(FF.literal(-2500d), SF.literal(Color.getHSBColor(150f / 360f, 0.6f, 1))); break;
                case  9 : expected = new AbstractMap.SimpleEntry(FF.literal(-2000d), SF.literal(Color.getHSBColor(135f / 360f, 0.6f, 1))); break;
                case 10 : expected = new AbstractMap.SimpleEntry(FF.literal(-1500d), SF.literal(Color.getHSBColor(120f / 360f, 0.6f, 1))); break;
                case 11 : expected = new AbstractMap.SimpleEntry(FF.literal(-1000d), SF.literal(Color.getHSBColor(105f / 360f, 0.6f, 1))); break;
                case 12 : expected = new AbstractMap.SimpleEntry(FF.literal( -500d), SF.literal(Color.getHSBColor( 90f / 360f, 0.6f, 1))); break;
                case 13 : expected = new AbstractMap.SimpleEntry(FF.literal(    0d), SF.literal(Color.getHSBColor( 75f / 360f, 0.6f, 1))); break;
                case 14 : expected = new AbstractMap.SimpleEntry(FF.literal(Math.nextUp(0.0)), SF.literal(Color.getHSBColor( 60f / 360f, 0.35f, 1))); break;
                case 15 : expected = new AbstractMap.SimpleEntry(FF.literal(  500d), SF.literal(Color.getHSBColor( 40f / 360f, 0.35f, 1))); break;
                case 16 : expected = new AbstractMap.SimpleEntry(FF.literal( 1000d), SF.literal(Color.getHSBColor( 20f / 360f, 0.35f, 1))); break;
                case 17 : expected = new AbstractMap.SimpleEntry(FF.literal( 1500d), SF.literal(Color.getHSBColor(  0f / 360f, 0.35f, 1))); break;
                case 18 : expected = new AbstractMap.SimpleEntry(FF.literal( 2000d), SF.literal(Color.getHSBColor(345f / 360f, 0.3f, 1))); break;
                case 19 : expected = new AbstractMap.SimpleEntry(FF.literal( 2500d), SF.literal(Color.getHSBColor(330f / 360f, 0.25f, 1))); break;
                case 20 : expected = new AbstractMap.SimpleEntry(FF.literal( 3000d), SF.literal(Color.getHSBColor(315f / 360f,  0.2f, 1))); break;
                case 21 : expected = new AbstractMap.SimpleEntry(FF.literal(Double.NaN), SF.literal(new Color(  0,  0, 0, 0))); break;
                default : fail("Unexpected number of elements.");
            }
            assertEquals("At index : " + i, expected, entry);
            i++;
        }
    }

    @Test
    public void readPAL() throws IOException {
        String palette =
            "0,0,143,\"5000 - 4500\"\n" +
            "0,0,244,\"4500 - 3500\"\n" +
            "236,255,34,\"3500 - 1000\"\n" +
            "255,89,0,\"1000 - 500\"\n" +
            "244,0,0,\"500 - 0\"\n" +
            "143,0,0,\"0\"";

        final ColorMap cm = new PaletteReader(PaletteReader.PATTERN_PAL).read(palette);
        assertTrue(cm.getFunction() instanceof Categorize);
        final Categorize categorize = (Categorize) cm.getFunction();
        final Map<Expression,Expression> steps = categorize.getThresholds();
        assertEquals(8, steps.size());
        int i=0;
        for (Entry<Expression,Expression> entry : steps.entrySet()) {
            Entry<Expression,Expression> expected = null;
            switch (i) {
                case  0 : expected = new AbstractMap.SimpleEntry(StyleConstants.CATEGORIZE_LESS_INFINITY, SF.literal(new Color(143,0,0))); break;
                case  1 : expected = new AbstractMap.SimpleEntry(FF.literal(   0d), SF.literal(new Color(244,  0,  0))); break;
                case  2 : expected = new AbstractMap.SimpleEntry(FF.literal( 500d), SF.literal(new Color(255, 89,  0))); break;
                case  3 : expected = new AbstractMap.SimpleEntry(FF.literal(1000d), SF.literal(new Color(236,255, 34))); break;
                case  4 : expected = new AbstractMap.SimpleEntry(FF.literal(3500d), SF.literal(new Color(  0,  0,244))); break;
                case  5 : expected = new AbstractMap.SimpleEntry(FF.literal(4500d), SF.literal(new Color(  0,  0,143))); break;
                case  6 : expected = new AbstractMap.SimpleEntry(FF.literal(5000d), SF.literal(new Color(0f,0f,0f,0f))); break;
                case  7 : expected = new AbstractMap.SimpleEntry(FF.literal(Double.NaN), SF.literal(new Color(  0,  0, 0, 0))); break;
                default : fail("Unexpected number of elements.");
            }
            assertEquals(expected, entry);
            i++;
        }
    }

}
