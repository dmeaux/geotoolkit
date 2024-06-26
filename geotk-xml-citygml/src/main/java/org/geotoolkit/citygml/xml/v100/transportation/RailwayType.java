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
package org.geotoolkit.citygml.xml.v100.transportation;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Type describing the class for railways. As subclass of _CityObject, a Railway inherits all
 *                 attributes and relations, in particular an id, names, external references, and generalization relations.
 *
 *
 * <p>Java class for RailwayType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RailwayType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/citygml/transportation/1.0}TransportationComplexType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/citygml/transportation/1.0}_GenericApplicationPropertyOfRailway" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RailwayType", propOrder = {
    "genericApplicationPropertyOfRailway"
})
public class RailwayType extends TransportationComplexType {

    @XmlElement(name = "_GenericApplicationPropertyOfRailway")
    protected List<Object> genericApplicationPropertyOfRailway;

    /**
     * Gets the value of the genericApplicationPropertyOfRailway property.
     */
    public List<Object> getGenericApplicationPropertyOfRailway() {
        if (genericApplicationPropertyOfRailway == null) {
            genericApplicationPropertyOfRailway = new ArrayList<Object>();
        }
        return this.genericApplicationPropertyOfRailway;
    }

}
