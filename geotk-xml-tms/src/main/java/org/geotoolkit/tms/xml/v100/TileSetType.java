/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2015, Geomatys
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
package org.geotoolkit.tms.xml.v100;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TileSetType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TileSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="units-per-pixel" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="order" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TileSetType")
public class TileSetType {

    @XmlAttribute(name = "href")
    protected String href;
    @XmlAttribute(name = "units-per-pixel")
    protected Double unitsPerPixel;
    @XmlAttribute(name = "order")
    protected Integer order;

    /**
     * Gets the value of the href property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Gets the value of the unitsPerPixel property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getUnitsPerPixel() {
        return unitsPerPixel;
    }

    /**
     * Sets the value of the unitsPerPixel property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setUnitsPerPixel(Double value) {
        this.unitsPerPixel = value;
    }

    /**
     * Gets the value of the order property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setOrder(Integer value) {
        this.order = value;
    }

}
