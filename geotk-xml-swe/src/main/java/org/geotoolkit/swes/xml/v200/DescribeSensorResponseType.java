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

package org.geotoolkit.swes.xml.v200;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DescribeSensorResponseType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DescribeSensorResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/swes/2.0}ExtensibleResponseType">
 *       &lt;sequence>
 *         &lt;element name="procedureDescriptionFormat" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="description" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.opengis.net/swes/2.0}SensorDescription"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescribeSensorResponseType", propOrder = {
    "procedureDescriptionFormat",
    "description"
})
@XmlRootElement(name="DescribeSensorResponse")
public class DescribeSensorResponseType extends ExtensibleResponseType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    private String procedureDescriptionFormat;
    private List<DescribeSensorResponseType.Description> description;

    /**
     * Gets the value of the procedureDescriptionFormat property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProcedureDescriptionFormat() {
        return procedureDescriptionFormat;
    }

    /**
     * Sets the value of the procedureDescriptionFormat property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProcedureDescriptionFormat(String value) {
        this.procedureDescriptionFormat = value;
    }

    /**
     * Gets the value of the description property.
     *
     * Objects of the following type(s) are allowed in the list
     * {@link DescribeSensorResponseType.Description }
     *
     *
     */
    public List<DescribeSensorResponseType.Description> getDescription() {
        if (description == null) {
            description = new ArrayList<DescribeSensorResponseType.Description>();
        }
        return this.description;
    }


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
     *         &lt;element ref="{http://www.opengis.net/swes/2.0}SensorDescription"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "sensorDescription"
    })
    public static class Description {

        @XmlElement(name = "SensorDescription", required = true)
        private SensorDescriptionType sensorDescription;

        /**
         * Gets the value of the sensorDescription property.
         *
         * @return
         *     possible object is
         *     {@link SensorDescriptionType }
         *
         */
        public SensorDescriptionType getSensorDescription() {
            return sensorDescription;
        }

        /**
         * Sets the value of the sensorDescription property.
         *
         * @param value
         *     allowed object is
         *     {@link SensorDescriptionType }
         *
         */
        public void setSensorDescription(SensorDescriptionType value) {
            this.sensorDescription = value;
        }

    }

}
