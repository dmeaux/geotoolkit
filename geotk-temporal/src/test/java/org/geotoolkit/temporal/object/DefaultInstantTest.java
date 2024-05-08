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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.sis.referencing.NamedIdentifier;
import org.junit.Test;
import static org.junit.Assert.*;
import org.opengis.referencing.IdentifiedObject;

/**
 *
 * @author Mehdi Sidhoum (Geomatys)
 */
public class DefaultInstantTest {

    private DefaultInstant instant1;
    private DefaultInstant instant2;
    private final Calendar cal = Calendar.getInstance();

    public DefaultInstantTest() {
        NamedIdentifier name1 = new NamedIdentifier(null, "position 1");
        final Map<String, Object> properties1 = new HashMap<>();
        properties1.put(IdentifiedObject.NAME_KEY, name1);
        cal.set(2000, 1, 1);
        instant1  = new DefaultInstant(properties1, cal.getTime().toInstant());

        NamedIdentifier name2 = new NamedIdentifier(null, "position 2");
        final Map<String, Object> properties2 = new HashMap<>();
        properties2.put(IdentifiedObject.NAME_KEY, name2);
        cal.set(1998, 1, 1);
        instant2  = new DefaultInstant(properties2, cal.getTime().toInstant());
    }

    /**
     * Test of getPosition method, of class DefaultInstant.
     */
    @Test
    public void testGetPosition() {
        Date result = instant1.getDate();
        assertFalse(instant2.getDate().equals(result));
    }

    /**
     * Test of equals method, of class DefaultInstant.
     */
    @Test
    public void testEquals() {
        cal.set(2000, 1, 1);
        assertNotNull(instant1);
        assertEquals(cal.getTime().getTime(), instant1.getDate().getTime());
        assertFalse(instant1.equals(instant2));
    }

    /**
     * Test of hashCode method, of class DefaultInstant.
     */
    @Test
    public void testHashCode() {
        int result = instant1.hashCode();
        assertFalse(instant2.hashCode() == result);
    }

    /**
     * Test of toString method, of class DefaultInstant.
     */
    @Test
    public void testToString() {
        String result = instant1.toString();
        assertFalse(instant2.toString().equals(result));
    }
}
