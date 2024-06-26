/*
 *    Geotoolkit.org - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2007-2012, Open Source Geospatial Foundation (OSGeo)
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
package org.geotoolkit.factory;

import java.awt.RenderingHints;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.geotoolkit.test.Assertions.assertSerializedEquals;



/**
 * Tests {@link Hints}.
 *
 * @author Jody Garnett (Refractions)
 * @author Martin Desruisseaux (IRD)
 */
public final class HintsTest {
    /**
     * Makes sure that J2SE 1.4 assertions are enabled.
     */
    @Test
    public void testAssertionEnabled() {
        assertTrue("Assertions not enabled.", Hints.class.desiredAssertionStatus());
    }

    /**
     * Tests the {@link Hints#nameOf} static method.
     */
    @Test
    public void testNameOf() {
        assertEquals("CS_FACTORY",        Hints.nameOf(Hints.CS_FACTORY));
        assertEquals("KEY_INTERPOLATION", Hints.nameOf(RenderingHints.KEY_INTERPOLATION));
    }

    /**
     * Tests the serialization of a key.
     */
    @Test
    public void testKeySerialization() {
        assertSame(Hints.CS_FACTORY,    assertSerializedEquals(Hints.CS_FACTORY));
        assertSame(Hints.DATUM_FACTORY, assertSerializedEquals(Hints.DATUM_FACTORY));
    }
}
