/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2008 - 2012, Geomatys
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


package org.geotoolkit.gml.xml.v321;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataBlockType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DataBlockType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml/3.2}rangeParameters"/>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml/3.2}tupleList"/>
 *           &lt;element ref="{http://www.opengis.net/gml/3.2}doubleOrNilReasonTupleList"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataBlockType", propOrder = {
    "rangeParameters",
    "tupleList",
    "doubleOrNilReasonTupleList"
})
public class DataBlockType {

    @XmlElement(required = true)
    private AssociationRoleType rangeParameters;
    private CoordinatesType tupleList;
    @XmlList
    private List<String> doubleOrNilReasonTupleList;

    /**
     * Gets the value of the rangeParameters property.
     *
     * @return
     *     possible object is
     *     {@link AssociationRoleType }
     *
     */
    public AssociationRoleType getRangeParameters() {
        return rangeParameters;
    }

    /**
     * Sets the value of the rangeParameters property.
     *
     * @param value
     *     allowed object is
     *     {@link AssociationRoleType }
     *
     */
    public void setRangeParameters(AssociationRoleType value) {
        this.rangeParameters = value;
    }

    /**
     * Gets the value of the tupleList property.
     *
     * @return
     *     possible object is
     *     {@link CoordinatesType }
     *
     */
    public CoordinatesType getTupleList() {
        return tupleList;
    }

    /**
     * Sets the value of the tupleList property.
     *
     * @param value
     *     allowed object is
     *     {@link CoordinatesType }
     *
     */
    public void setTupleList(CoordinatesType value) {
        this.tupleList = value;
    }

    /**
     * Gets the value of the doubleOrNilReasonTupleList property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the doubleOrNilReasonTupleList property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDoubleOrNilReasonTupleList().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     *
     *
     */
    public List<String> getDoubleOrNilReasonTupleList() {
        if (doubleOrNilReasonTupleList == null) {
            doubleOrNilReasonTupleList = new ArrayList<String>();
        }
        return this.doubleOrNilReasonTupleList;
    }

}
