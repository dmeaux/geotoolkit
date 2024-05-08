/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2008, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2009, Geomatys
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
package org.geotoolkit.temporal.object;

import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import static org.apache.sis.feature.AbstractIdentifiedType.NAME_KEY;
import org.junit.Test;
import static org.junit.Assert.*;
import org.opengis.temporal.Period;
import org.opengis.temporal.TemporalGeometricPrimitive;


/**
 *
 * @author Mehdi Sidhoum (Geomatys)
 */
public class DefaultTemporalGeometricPrimitiveTest {

    private DefaultInstant temporalGeomericPrimitive1;
    private DefaultInstant temporalGeomericPrimitive2;
    private final Calendar cal = Calendar.getInstance();

    public DefaultTemporalGeometricPrimitiveTest() {
       cal.set(1981, 6, 25);
       Date date = cal.getTime();

       temporalGeomericPrimitive1 = new DefaultInstant(Collections.singletonMap(NAME_KEY, "id1"), date.toInstant());
       temporalGeomericPrimitive2 = new DefaultInstant(Collections.singletonMap(NAME_KEY, "id2"), Instant.now());
    }

    /**
     * Test of distance method.
     */
    @Test
    public void testDistance() {
        TemporalGeometricPrimitive other;

        //calcul Distance with instant objects
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 0, 1);
        other = new DefaultInstant(Collections.singletonMap(NAME_KEY, "id1"), cal.getTime().toInstant());
        TemporalAmount result = temporalGeomericPrimitive1.distance(other);
        assertFalse(temporalGeomericPrimitive2.distance(other).equals(result));

        //calcul Distance with instant and period
        cal.set(2009, 1, 1);
        var i1 = new DefaultInstant(Collections.singletonMap(NAME_KEY, "id1"), cal.getTime().toInstant());
        cal.set(2012, 1, 1);
        var i2 = new DefaultInstant(Collections.singletonMap(NAME_KEY, "id1"), cal.getTime().toInstant());
        other = new DefaultPeriod(Collections.singletonMap(NAME_KEY, "tp1"), i1, i2);
        result = temporalGeomericPrimitive1.distance(other);
        assertFalse(temporalGeomericPrimitive2.distance(other).equals(result));

        //calcul Distance between Period objects
        Period tp1 = new DefaultPeriod(Collections.singletonMap(NAME_KEY, "tp1"), temporalGeomericPrimitive1, temporalGeomericPrimitive2);
        Period tp2 = new DefaultPeriod(Collections.singletonMap(NAME_KEY, "tp2"), i1, temporalGeomericPrimitive2);
        result = tp1.distance(other);
        assertTrue(tp2.distance(other).equals(result));

    }

    /**
     * Test of length method.
     */
    @Test
    public void testLength() {
        Calendar cal = Calendar.getInstance();
        cal.set(2033, 0, 1);
        Period tp1 = new DefaultPeriod(Collections.singletonMap(NAME_KEY, "tp1"), temporalGeomericPrimitive1, temporalGeomericPrimitive2);
        Period tp2 = new DefaultPeriod(Collections.singletonMap(NAME_KEY, "tp2"), temporalGeomericPrimitive2, new DefaultInstant(Collections.singletonMap(NAME_KEY, "id1"), cal.getTime().toInstant()));
        TemporalAmount result = tp1.length();
        assertFalse(tp2.length().equals(result));
    }
}
