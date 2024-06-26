/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2013, Geomatys
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

package org.geotoolkit.wcs.xml.v200;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CoverageOfferingsType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CoverageOfferingsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/wcs/2.0}ServiceMetadata"/>
 *         &lt;element ref="{http://www.opengis.net/wcs/2.0}OfferedCoverage" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoverageOfferingsType", propOrder = {
    "serviceMetadata",
    "offeredCoverage"
})
public class CoverageOfferingsType {

    @XmlElement(name = "ServiceMetadata", required = true)
    private ServiceMetadataType serviceMetadata;
    @XmlElement(name = "OfferedCoverage")
    private List<OfferedCoverageType> offeredCoverage;

    /**
     * Gets the value of the serviceMetadata property.
     *
     * @return
     *     possible object is
     *     {@link ServiceMetadataType }
     *
     */
    public ServiceMetadataType getServiceMetadata() {
        return serviceMetadata;
    }

    /**
     * Sets the value of the serviceMetadata property.
     *
     * @param value
     *     allowed object is
     *     {@link ServiceMetadataType }
     *
     */
    public void setServiceMetadata(ServiceMetadataType value) {
        this.serviceMetadata = value;
    }

    /**
     * Gets the value of the offeredCoverage property.
     *
     */
    public List<OfferedCoverageType> getOfferedCoverage() {
        if (offeredCoverage == null) {
            offeredCoverage = new ArrayList<OfferedCoverageType>();
        }
        return this.offeredCoverage;
    }

}
