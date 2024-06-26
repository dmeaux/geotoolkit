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
package org.geotoolkit.gml.xml.v212;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * A LineString is defined by two or more coordinate tuples,
 * with linear interpolation between them.
 *
 *
 * <p>Java class for LineStringType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="LineStringType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeometryType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}coord" maxOccurs="unbounded" minOccurs="2"/>
 *           &lt;element ref="{http://www.opengis.net/gml}coordinates"/>
 *         &lt;/choice>
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
@XmlType(name = "LineStringType", propOrder = {
    "coord",
    "coordinates"
})
public class LineStringType extends AbstractGeometryType {

    private List<CoordType> coord;
    private CoordinatesType coordinates;

    public LineStringType() {

    }

    public LineStringType(final LineStringType that) {
        super(that);
        if (that != null) {
            if (that.coordinates != null) {
                this.coordinates = new CoordinatesType(that.coordinates);
            }
            if (that.coord != null) {
                this.coord = new ArrayList<CoordType>();
                for (CoordType co : coord) {
                    this.coord.add(new CoordType(co));
                }
            }
        }
    }

    /**
     * Gets the value of the coord property.
     *
     * Objects of the following type(s) are allowed in the list
     * {@link CoordType }
     *
     *
     */
    public List<CoordType> getCoord() {
        if (coord == null) {
            coord = new ArrayList<CoordType>();
        }
        return this.coord;
    }

    /**
     * Gets the value of the coordinates property.
     *
     * @return
     *     possible object is
     *     {@link CoordinatesType }
     *
     */
    public CoordinatesType getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the value of the coordinates property.
     *
     * @param value
     *     allowed object is
     *     {@link CoordinatesType }
     *
     */
    public void setCoordinates(final CoordinatesType value) {
        this.coordinates = value;
    }

    @Override
    public AbstractGeometryType getClone() {
        return new LineStringType(this);
    }
}
