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
package org.geotoolkit.sld.xml.v100;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/sld}BrightnessOnly" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}ReliefFactor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "brightnessOnly",
    "reliefFactor"
})
@XmlRootElement(name = "ShadedRelief")
public class ShadedRelief {

    @XmlElement(name = "BrightnessOnly")
    protected Boolean brightnessOnly;
    @XmlElement(name = "ReliefFactor")
    protected Double reliefFactor;

    /**
     * Gets the value of the brightnessOnly property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isBrightnessOnly() {
        return brightnessOnly;
    }

    /**
     * Sets the value of the brightnessOnly property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setBrightnessOnly(final Boolean value) {
        this.brightnessOnly = value;
    }

    /**
     * Gets the value of the reliefFactor property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getReliefFactor() {
        return reliefFactor;
    }

    /**
     * Sets the value of the reliefFactor property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setReliefFactor(final Double value) {
        this.reliefFactor = value;
    }

}
