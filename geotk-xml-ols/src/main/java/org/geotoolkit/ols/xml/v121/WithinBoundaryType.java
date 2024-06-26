/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2011, Geomatys
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

package org.geotoolkit.ols.xml.v121;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Defines a spatial filter which selects POIs located within a specified bounding area.
 *
 * <p>Java class for WithinBoundaryType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="WithinBoundaryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/xls}AOI"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WithinBoundaryType", propOrder = {
    "aoi"
})
public class WithinBoundaryType {

    @XmlElement(name = "AOI", required = true)
    private AreaOfInterestType aoi;

    /**
     * Gets the value of the aoi property.
     *
     * @return
     *     possible object is
     *     {@link AreaOfInterestType }
     *
     */
    public AreaOfInterestType getAOI() {
        return aoi;
    }

    /**
     * Sets the value of the aoi property.
     *
     * @param value
     *     allowed object is
     *     {@link AreaOfInterestType }
     *
     */
    public void setAOI(AreaOfInterestType value) {
        this.aoi = value;
    }

}
