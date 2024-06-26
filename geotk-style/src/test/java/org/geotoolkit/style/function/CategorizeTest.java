/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2010, Geomatys
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

package org.geotoolkit.style.function;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.geotoolkit.filter.visitor.ListingPropertyVisitor;
import org.geotoolkit.style.StyleConstants;
import org.junit.Test;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.Expression;
import static org.junit.Assert.*;
import static java.awt.Color.*;
import org.apache.sis.feature.builder.FeatureTypeBuilder;
import org.geotoolkit.filter.FilterUtilities;
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureType;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class CategorizeTest {

    public CategorizeTest() {
    }

    @Test
    public void categorize(){
        final String attribut = "att_value";

        final FilterFactory ff = FilterUtilities.FF;

        final FeatureTypeBuilder sftb = new FeatureTypeBuilder();
        sftb.setName("test");
        sftb.addAttribute(Double.class).setName(attribut);
        final FeatureType sft = sftb.build();

        final Feature f1 = sft.newInstance();
        f1.setPropertyValue(attribut, -5d);
        final Feature f2 = sft.newInstance();
        f2.setPropertyValue(attribut, 8d);
        final Feature f3 = sft.newInstance();
        f3.setPropertyValue(attribut, 30d);

        final Expression Lookup = ff.property(attribut);
        final Map<Expression,Expression> values = new HashMap<Expression, Expression>();
        values.put(StyleConstants.CATEGORIZE_LESS_INFINITY, ff.literal(GREEN));
        values.put(ff.literal(1d), ff.literal(RED));
        values.put(ff.literal(8d), ff.literal(YELLOW));
        values.put(ff.literal(15d), ff.literal(BLUE));

        Categorize categorize = new DefaultCategorize(Lookup, values, ThreshholdsBelongTo.PRECEDING, null);

        Color c = (Color) categorize.apply(f1);
        assertEquals(c, GREEN);
        c = (Color) categorize.apply(f2);
        assertEquals(c, YELLOW);
        c = (Color) categorize.apply(f3);
        assertEquals(c, BLUE);

        categorize = new DefaultCategorize(Lookup, values, ThreshholdsBelongTo.SUCCEEDING, null);
        c = (Color) categorize.apply(f1);
        assertEquals(c, GREEN);
        c = (Color) categorize.apply(f2);
        assertEquals(c, RED);
        c = (Color) categorize.apply(f3);
        assertEquals(c, BLUE);


        //test get lookup property
        Collection<String> requieredAttributs = new HashSet<String>();
        ListingPropertyVisitor.VISITOR.visit(categorize, requieredAttributs);

        assertEquals(requieredAttributs.size(), 1);
        assertEquals(requieredAttributs.iterator().next(), attribut);
    }
}
