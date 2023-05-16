/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2008 - 2009, Geomatys
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
package org.geotoolkit.ogc.xml.v110;

import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import org.opengis.filter.ComparisonOperatorName;

/**
 *
 * @author Guilhem Legal
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyIsEqualTo")
public class PropertyIsEqualToType extends BinaryComparisonOpType {
    /**
     * Empty constructor used by JAXB
     */
    public PropertyIsEqualToType() {
    }

    /**
     * Build a new Binary comparison operator
     */
    public PropertyIsEqualToType(final List<JAXBElement<?>> expression, final Boolean matchCase) {
        super(expression,matchCase);
    }

    /**
     * Build a new Binary comparison operator
     */
    public PropertyIsEqualToType(final LiteralType literal, final PropertyNameType propertyName, final Boolean matchCase) {
        super(literal, propertyName, matchCase);
    }

    public PropertyIsEqualToType(final PropertyIsEqualToType that) {
        super(that);
    }

    @Override
    public ComparisonOpsType getClone() {
        return new PropertyIsEqualToType(this);
    }

    @Override
    public ComparisonOperatorName getOperatorType() {
        return ComparisonOperatorName.PROPERTY_IS_EQUAL_TO;
    }
}
