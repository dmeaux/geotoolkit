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

package org.geotoolkit.swe.xml.v200;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AllowedTimesType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AllowedTimesType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/swe/2.0}AbstractSWEType">
 *       &lt;sequence>
 *         &lt;element name="value" type="{http://www.opengis.net/swe/2.0}TimePosition" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="interval" type="{http://www.opengis.net/swe/2.0}TimePair" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="significantFigures" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllowedTimesType", propOrder = {
    "value",
    "interval",
    "significantFigures"
})
public class AllowedTimesType extends AbstractSWEType {

    @XmlElementRef(name = "value", namespace = "http://www.opengis.net/swe/2.0", type = JAXBElement.class)
    private List<JAXBElement<List<String>>> value;
    @XmlElementRef(name = "interval", namespace = "http://www.opengis.net/swe/2.0", type = JAXBElement.class)
    private List<JAXBElement<List<String>>> interval;
    private Integer significantFigures;

    /**
     * Gets the value of the value property.
     *
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}
     *
     */
    public List<JAXBElement<List<String>>> getValue() {
        if (value == null) {
            value = new ArrayList<JAXBElement<List<String>>>();
        }
        return this.value;
    }

    /**
     * Gets the value of the interval property.
     *
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}
     *
     */
    public List<JAXBElement<List<String>>> getInterval() {
        if (interval == null) {
            interval = new ArrayList<JAXBElement<List<String>>>();
        }
        return this.interval;
    }

    /**
     * Gets the value of the significantFigures property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSignificantFigures() {
        return significantFigures;
    }

    /**
     * Sets the value of the significantFigures property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSignificantFigures(Integer value) {
        this.significantFigures = value;
    }

}
