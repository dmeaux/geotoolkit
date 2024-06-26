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

package org.geotoolkit.sampling.xml.v100;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SamplingFeatureRelationPropertyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SamplingFeatureRelationPropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/sampling/1.0}SamplingFeatureRelation"/>
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
@XmlType(name = "SamplingFeatureRelationPropertyType", propOrder = {
    "samplingFeatureRelation"
})
public class SamplingFeatureRelationPropertyType {

    @XmlElement(name = "SamplingFeatureRelation", required = true)
    private SamplingFeatureRelationType samplingFeatureRelation;

    /**
     * Gets the value of the samplingFeatureRelation property.
     *
     * @return
     *     possible object is
     *     {@link SamplingFeatureRelationType }
     *
     */
    public SamplingFeatureRelationType getSamplingFeatureRelation() {
        return samplingFeatureRelation;
    }

    /**
     * Sets the value of the samplingFeatureRelation property.
     *
     * @param value
     *     allowed object is
     *     {@link SamplingFeatureRelationType }
     *
     */
    public void setSamplingFeatureRelation(final SamplingFeatureRelationType value) {
        this.samplingFeatureRelation = value;
    }

}
