/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2012, Geomatys
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
package org.geotoolkit.ogc.xml.v100;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import org.opengis.filter.DistanceOperatorName;

/**
 *
 * @author Guilhem Legal (Geomatys)
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DWithin")
public class DWithinType extends DistanceBufferType {
    public DWithinType() {
    }

    /**
     * Build a new DWithin Filter
     */
    public DWithinType(final String propertyName, final Object geometry, final double distance, final String unit) {
        super(propertyName, geometry, distance, unit);
    }

    public DWithinType(final DWithinType that) {
        super(that);
    }

    @Override
    public SpatialOpsType getClone() {
        return new DWithinType(this);
    }

    @Override
    public DistanceOperatorName getOperatorType() {
        return DistanceOperatorName.WITHIN;
    }

    @Override
    public String getOperator() {
        return "DWithin";
    }
}
