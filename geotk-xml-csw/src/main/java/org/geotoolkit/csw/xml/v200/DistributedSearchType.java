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
package org.geotoolkit.csw.xml.v200;

import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.csw.xml.DistributedSearch;


/**
 *
 * Governs the behaviour of a distributed search.
 *
 * hopCount     - The maximum number of message hops before the search is terminated.
 *                Each catalogue decrements this when the request is received, and does not forward it if hopCount=0.
 *
 *
 * <p>Java class for DistributedSearchType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DistributedSearchType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="hopCount" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" default="2" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistributedSearchType")
public class DistributedSearchType implements DistributedSearch {

    @XmlAttribute
    @XmlSchemaType(name = "positiveInteger")
    private Integer hopCount;

    /**
     * An empty constructor used by JAXB
     */
    public DistributedSearchType(){

    }

    /**
     * Build a new Distributed search
     */
    public DistributedSearchType(final Integer hopCount){
        this.hopCount = hopCount;
    }

    public DistributedSearchType(final DistributedSearchType other){
        if (other != null && other.hopCount != null) {
            this.hopCount = new Integer(other.hopCount);
        }
    }

    /**
     * Gets the value of the hopCount property.
     *
     */
    @Override
    public Integer getHopCount() {
        if (hopCount == null) {
            return 2;
        } else {
            return hopCount;
        }
    }

    /**
     * Sets the value of the hopCount property.
     *
     */
    @Override
    public void setHopCount(final Integer value) {
        this.hopCount = value;
    }

     /**
     * Verify if this entry is identical to the specified object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof DistributedSearchType) {
            DistributedSearchType that = (DistributedSearchType) object;
            return Objects.equals(this.hopCount,  that.hopCount);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.hopCount != null ? this.hopCount.hashCode() : 0);
        return hash;
    }
}
