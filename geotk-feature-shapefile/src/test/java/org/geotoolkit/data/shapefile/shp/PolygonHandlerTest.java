/*
 *    GeotoolKit - An Open source Java GIS Toolkit
 *    http://geotoolkit.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotoolkit.data.shapefile.shp;

import org.junit.Test;
import java.util.ArrayList;

import org.geotoolkit.data.shapefile.AbstractTestCaseSupport;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.PrecisionModel;
import java.util.List;

import static org.junit.Assert.*;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.impl.PackedCoordinateSequence;

/**
 *
 * @version $Id$
 * @author Ian Schneider
 * @module
 */
public class PolygonHandlerTest extends AbstractTestCaseSupport {

    @Test
    public void testPolygonHandler() {
        Coordinate[] c = new Coordinate[3];
        c[0] = new Coordinate(0, 0, 0);
        c[1] = new Coordinate(1, 1, Double.NaN);
        c[2] = new Coordinate(1, 2, 3);
        final CoordinateSequence cs = new PackedCoordinateSequence.Double(c);
        PolygonHandler handler = new PolygonHandler(true);
        assertTrue(handler.getShapeType() == ShapeType.POLYGON);
        for (int i = 0, ii = c.length; i < ii; i++) {
            assertTrue(handler.pointInList(c[i], cs));
        }
    }

    @Test
    public void testHoleAssignment() {
        java.awt.Dimension ps = new java.awt.Dimension(500, 500);
        PrecisionModel precision = new PrecisionModel();

        ArrayList shells = new ArrayList();
        ArrayList holes = new ArrayList();

        int x = 10;
        int y = 10;

        shells.add(copyTo(x, y, ps.width - 2 * x, ps.height - 2 * y, rectangle(
                precision, 0)));

        int w = 11;
        int h = 11;
        int s = 10;

        int nx = (ps.width - 2 * x) / (w + s);
        int ny = (ps.height - 2 * y) / (h + s);

        for (int i = 0; i < nx; i++) {
            for (int j = 0; j < ny; j++) {
                holes.add(copyTo(x + s + i * (w + s), y + s + j * (h + s), w,
                        h, rectangle(precision, 0)));
            }
        }

        PolygonHandler ph = new PolygonHandler(true);
        List assigned = ph.assignHolesToShells(shells, holes);
        assertEquals(((ArrayList) assigned.get(0)).size(), holes.size());

    }

    public static Geometry rectangle(final PrecisionModel pm, final int SRID) {
        Coordinate[] coords = new Coordinate[5];
        for (int i = 0; i < coords.length; i++) {
            coords[i] = new Coordinate();
        }
        return org.geotoolkit.geometry.jts.JTS.getFactory().createLinearRing(coords);
    }

    public static Geometry copyTo(final double x, final double y, final double w, final double h,
            final Geometry g) {
        if (g.getNumPoints() != 5)
            throw new IllegalArgumentException("Geometry must have 5 points");
        if (!LinearRing.class.isAssignableFrom(g.getClass()))
            throw new IllegalArgumentException("Geometry must be linear ring");
        Coordinate[] coords = g.getCoordinates();
        coords[0].x = x;
        coords[0].y = y;
        coords[1].x = x + w;
        coords[1].y = y;
        coords[2].x = x + w;
        coords[2].y = y + h;
        coords[3].x = x;
        coords[3].y = y + h;
        coords[4].x = x;
        coords[4].y = y;
        return g;
    }
}
