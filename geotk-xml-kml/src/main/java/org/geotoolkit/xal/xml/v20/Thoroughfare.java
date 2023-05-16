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
package org.geotoolkit.xal.xml.v20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


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
 *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}AddressLine" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumber"/>
 *           &lt;element name="ThoroughfareNumberRange">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}AddressLine" maxOccurs="unbounded" minOccurs="0"/>
 *                     &lt;element name="ThoroughfareNumberFrom">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}AddressLine" maxOccurs="unbounded" minOccurs="0"/>
 *                               &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberPrefix" maxOccurs="unbounded" minOccurs="0"/>
 *                               &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumber" maxOccurs="unbounded"/>
 *                               &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberSuffix" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;/sequence>
 *                             &lt;attGroup ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}grPostal"/>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="ThoroughfareNumberTo">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}AddressLine" maxOccurs="unbounded" minOccurs="0"/>
 *                               &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberPrefix" maxOccurs="unbounded" minOccurs="0"/>
 *                               &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumber" maxOccurs="unbounded"/>
 *                               &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberSuffix" maxOccurs="unbounded" minOccurs="0"/>
 *                             &lt;/sequence>
 *                             &lt;attGroup ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}grPostal"/>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/sequence>
 *                   &lt;attGroup ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}grPostal"/>
 *                   &lt;attribute name="RangeType">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *                         &lt;enumeration value="Odd"/>
 *                         &lt;enumeration value="Even"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/attribute>
 *                   &lt;attribute name="Indicator" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *                   &lt;attribute name="Separator" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *                   &lt;attribute name="IndicatorOccurrence">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *                         &lt;enumeration value="Before"/>
 *                         &lt;enumeration value="After"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/attribute>
 *                   &lt;attribute name="NumberRangeOccurrence">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *                         &lt;enumeration value="BeforeName"/>
 *                         &lt;enumeration value="AfterName"/>
 *                         &lt;enumeration value="BeforeType"/>
 *                         &lt;enumeration value="AfterType"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/attribute>
 *                   &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberPrefix" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberSuffix" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ThoroughfarePreDirection" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfarePreDirectionType" minOccurs="0"/>
 *         &lt;element name="ThoroughfareLeadingType" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareLeadingTypeType" minOccurs="0"/>
 *         &lt;element name="ThoroughfareName" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ThoroughfareTrailingType" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareTrailingTypeType" minOccurs="0"/>
 *         &lt;element name="ThoroughfarePostDirection" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfarePostDirectionType" minOccurs="0"/>
 *         &lt;element name="DependentThoroughfare" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}AddressLine" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="ThoroughfarePreDirection" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfarePreDirectionType" minOccurs="0"/>
 *                   &lt;element name="ThoroughfareLeadingType" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareLeadingTypeType" minOccurs="0"/>
 *                   &lt;element name="ThoroughfareName" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNameType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="ThoroughfareTrailingType" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareTrailingTypeType" minOccurs="0"/>
 *                   &lt;element name="ThoroughfarePostDirection" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfarePostDirectionType" minOccurs="0"/>
 *                   &lt;any/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="DependentLocality" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}DependentLocalityType"/>
 *           &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}Premise"/>
 *           &lt;element name="Firm" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}FirmType"/>
 *           &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}PostalCode"/>
 *         &lt;/choice>
 *         &lt;any/>
 *       &lt;/sequence>
 *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="DependentThoroughfares">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             &lt;enumeration value="Yes"/>
 *             &lt;enumeration value="No"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="DependentThoroughfaresIndicator" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="DependentThoroughfaresConnector" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="DependentThoroughfaresType" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
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
    "addressLine",
    "thoroughfareNumberOrThoroughfareNumberRange",
    "thoroughfareNumberPrefix",
    "thoroughfareNumberSuffix",
    "thoroughfarePreDirection",
    "thoroughfareLeadingType",
    "thoroughfareName",
    "thoroughfareTrailingType",
    "thoroughfarePostDirection",
    "dependentThoroughfare",
    "dependentLocality",
    "premise",
    "firm",
    "postalCode",
    "any"
})
@XmlRootElement(name = "Thoroughfare")
public class Thoroughfare {

    @XmlElement(name = "AddressLine")
    private List<AddressLine> addressLine;
    @XmlElements({
        @XmlElement(name = "ThoroughfareNumberRange", type = Thoroughfare.ThoroughfareNumberRange.class),
        @XmlElement(name = "ThoroughfareNumber", type = ThoroughfareNumber.class)
    })
    private List<Object> thoroughfareNumberOrThoroughfareNumberRange;
    @XmlElement(name = "ThoroughfareNumberPrefix")
    private List<ThoroughfareNumberPrefix> thoroughfareNumberPrefix;
    @XmlElement(name = "ThoroughfareNumberSuffix")
    private List<ThoroughfareNumberSuffix> thoroughfareNumberSuffix;
    @XmlElement(name = "ThoroughfarePreDirection")
    private ThoroughfarePreDirectionType thoroughfarePreDirection;
    @XmlElement(name = "ThoroughfareLeadingType")
    private ThoroughfareLeadingTypeType thoroughfareLeadingType;
    @XmlElement(name = "ThoroughfareName")
    private List<ThoroughfareNameType> thoroughfareName;
    @XmlElement(name = "ThoroughfareTrailingType")
    private ThoroughfareTrailingTypeType thoroughfareTrailingType;
    @XmlElement(name = "ThoroughfarePostDirection")
    private ThoroughfarePostDirectionType thoroughfarePostDirection;
    @XmlElement(name = "DependentThoroughfare")
    private Thoroughfare.DependentThoroughfare dependentThoroughfare;
    @XmlElement(name = "DependentLocality")
    private DependentLocalityType dependentLocality;
    @XmlElement(name = "Premise")
    private Premise premise;
    @XmlElement(name = "Firm")
    private FirmType firm;
    @XmlElement(name = "PostalCode")
    private PostalCode postalCode;
    @XmlAnyElement(lax = true)
    private List<Object> any;
    @XmlAttribute(name = "Type")
    @XmlSchemaType(name = "anySimpleType")
    private String type;
    @XmlAttribute(name = "DependentThoroughfares")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    private String dependentThoroughfares;
    @XmlAttribute(name = "DependentThoroughfaresIndicator")
    @XmlSchemaType(name = "anySimpleType")
    private String dependentThoroughfaresIndicator;
    @XmlAttribute(name = "DependentThoroughfaresConnector")
    @XmlSchemaType(name = "anySimpleType")
    private String dependentThoroughfaresConnector;
    @XmlAttribute(name = "DependentThoroughfaresType")
    @XmlSchemaType(name = "anySimpleType")
    private String dependentThoroughfaresType;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the addressLine property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressLine property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressLine().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressLine }
     *
     *
     */
    public List<AddressLine> getAddressLine() {
        if (addressLine == null) {
            addressLine = new ArrayList<AddressLine>();
        }
        return this.addressLine;
    }

    /**
     * Gets the value of the thoroughfareNumberOrThoroughfareNumberRange property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the thoroughfareNumberOrThoroughfareNumberRange property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getThoroughfareNumberOrThoroughfareNumberRange().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Thoroughfare.ThoroughfareNumberRange }
     * {@link ThoroughfareNumber }
     *
     *
     */
    public List<Object> getThoroughfareNumberOrThoroughfareNumberRange() {
        if (thoroughfareNumberOrThoroughfareNumberRange == null) {
            thoroughfareNumberOrThoroughfareNumberRange = new ArrayList<Object>();
        }
        return this.thoroughfareNumberOrThoroughfareNumberRange;
    }

    /**
     * Gets the value of the thoroughfareNumberPrefix property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the thoroughfareNumberPrefix property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getThoroughfareNumberPrefix().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ThoroughfareNumberPrefix }
     *
     *
     */
    public List<ThoroughfareNumberPrefix> getThoroughfareNumberPrefix() {
        if (thoroughfareNumberPrefix == null) {
            thoroughfareNumberPrefix = new ArrayList<ThoroughfareNumberPrefix>();
        }
        return this.thoroughfareNumberPrefix;
    }

    /**
     * Gets the value of the thoroughfareNumberSuffix property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the thoroughfareNumberSuffix property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getThoroughfareNumberSuffix().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ThoroughfareNumberSuffix }
     *
     *
     */
    public List<ThoroughfareNumberSuffix> getThoroughfareNumberSuffix() {
        if (thoroughfareNumberSuffix == null) {
            thoroughfareNumberSuffix = new ArrayList<ThoroughfareNumberSuffix>();
        }
        return this.thoroughfareNumberSuffix;
    }

    /**
     * Gets the value of the thoroughfarePreDirection property.
     *
     * @return
     *     possible object is
     *     {@link ThoroughfarePreDirectionType }
     *
     */
    public ThoroughfarePreDirectionType getThoroughfarePreDirection() {
        return thoroughfarePreDirection;
    }

    /**
     * Sets the value of the thoroughfarePreDirection property.
     *
     * @param value
     *     allowed object is
     *     {@link ThoroughfarePreDirectionType }
     *
     */
    public void setThoroughfarePreDirection(final ThoroughfarePreDirectionType value) {
        this.thoroughfarePreDirection = value;
    }

    /**
     * Gets the value of the thoroughfareLeadingType property.
     *
     * @return
     *     possible object is
     *     {@link ThoroughfareLeadingTypeType }
     *
     */
    public ThoroughfareLeadingTypeType getThoroughfareLeadingType() {
        return thoroughfareLeadingType;
    }

    /**
     * Sets the value of the thoroughfareLeadingType property.
     *
     * @param value
     *     allowed object is
     *     {@link ThoroughfareLeadingTypeType }
     *
     */
    public void setThoroughfareLeadingType(final ThoroughfareLeadingTypeType value) {
        this.thoroughfareLeadingType = value;
    }

    /**
     * Gets the value of the thoroughfareName property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the thoroughfareName property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getThoroughfareName().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ThoroughfareNameType }
     *
     *
     */
    public List<ThoroughfareNameType> getThoroughfareName() {
        if (thoroughfareName == null) {
            thoroughfareName = new ArrayList<ThoroughfareNameType>();
        }
        return this.thoroughfareName;
    }

    /**
     * Gets the value of the thoroughfareTrailingType property.
     *
     * @return
     *     possible object is
     *     {@link ThoroughfareTrailingTypeType }
     *
     */
    public ThoroughfareTrailingTypeType getThoroughfareTrailingType() {
        return thoroughfareTrailingType;
    }

    /**
     * Sets the value of the thoroughfareTrailingType property.
     *
     * @param value
     *     allowed object is
     *     {@link ThoroughfareTrailingTypeType }
     *
     */
    public void setThoroughfareTrailingType(final ThoroughfareTrailingTypeType value) {
        this.thoroughfareTrailingType = value;
    }

    /**
     * Gets the value of the thoroughfarePostDirection property.
     *
     * @return
     *     possible object is
     *     {@link ThoroughfarePostDirectionType }
     *
     */
    public ThoroughfarePostDirectionType getThoroughfarePostDirection() {
        return thoroughfarePostDirection;
    }

    /**
     * Sets the value of the thoroughfarePostDirection property.
     *
     * @param value
     *     allowed object is
     *     {@link ThoroughfarePostDirectionType }
     *
     */
    public void setThoroughfarePostDirection(final ThoroughfarePostDirectionType value) {
        this.thoroughfarePostDirection = value;
    }

    /**
     * Gets the value of the dependentThoroughfare property.
     *
     * @return
     *     possible object is
     *     {@link Thoroughfare.DependentThoroughfare }
     *
     */
    public Thoroughfare.DependentThoroughfare getDependentThoroughfare() {
        return dependentThoroughfare;
    }

    /**
     * Sets the value of the dependentThoroughfare property.
     *
     * @param value
     *     allowed object is
     *     {@link Thoroughfare.DependentThoroughfare }
     *
     */
    public void setDependentThoroughfare(final Thoroughfare.DependentThoroughfare value) {
        this.dependentThoroughfare = value;
    }

    /**
     * Gets the value of the dependentLocality property.
     *
     * @return
     *     possible object is
     *     {@link DependentLocalityType }
     *
     */
    public DependentLocalityType getDependentLocality() {
        return dependentLocality;
    }

    /**
     * Sets the value of the dependentLocality property.
     *
     * @param value
     *     allowed object is
     *     {@link DependentLocalityType }
     *
     */
    public void setDependentLocality(final DependentLocalityType value) {
        this.dependentLocality = value;
    }

    /**
     * Gets the value of the premise property.
     *
     * @return
     *     possible object is
     *     {@link Premise }
     *
     */
    public Premise getPremise() {
        return premise;
    }

    /**
     * Sets the value of the premise property.
     *
     * @param value
     *     allowed object is
     *     {@link Premise }
     *
     */
    public void setPremise(final Premise value) {
        this.premise = value;
    }

    /**
     * Gets the value of the firm property.
     *
     * @return
     *     possible object is
     *     {@link FirmType }
     *
     */
    public FirmType getFirm() {
        return firm;
    }

    /**
     * Sets the value of the firm property.
     *
     * @param value
     *     allowed object is
     *     {@link FirmType }
     *
     */
    public void setFirm(final FirmType value) {
        this.firm = value;
    }

    /**
     * Gets the value of the postalCode property.
     *
     * @return
     *     possible object is
     *     {@link PostalCode }
     *
     */
    public PostalCode getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     *
     * @param value
     *     allowed object is
     *     {@link PostalCode }
     *
     */
    public void setPostalCode(final PostalCode value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the any property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     *
     *
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    /**
     * Gets the value of the type property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setType(final String value) {
        this.type = value;
    }

    /**
     * Gets the value of the dependentThoroughfares property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDependentThoroughfares() {
        return dependentThoroughfares;
    }

    /**
     * Sets the value of the dependentThoroughfares property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDependentThoroughfares(final String value) {
        this.dependentThoroughfares = value;
    }

    /**
     * Gets the value of the dependentThoroughfaresIndicator property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDependentThoroughfaresIndicator() {
        return dependentThoroughfaresIndicator;
    }

    /**
     * Sets the value of the dependentThoroughfaresIndicator property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDependentThoroughfaresIndicator(final String value) {
        this.dependentThoroughfaresIndicator = value;
    }

    /**
     * Gets the value of the dependentThoroughfaresConnector property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDependentThoroughfaresConnector() {
        return dependentThoroughfaresConnector;
    }

    /**
     * Sets the value of the dependentThoroughfaresConnector property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDependentThoroughfaresConnector(final String value) {
        this.dependentThoroughfaresConnector = value;
    }

    /**
     * Gets the value of the dependentThoroughfaresType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDependentThoroughfaresType() {
        return dependentThoroughfaresType;
    }

    /**
     * Sets the value of the dependentThoroughfaresType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDependentThoroughfaresType(final String value) {
        this.dependentThoroughfaresType = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     *
     * <p>
     * the map is keyed by the name of the attribute and
     * the value is the string value of the attribute.
     *
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     *
     *
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
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
     *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}AddressLine" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="ThoroughfarePreDirection" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfarePreDirectionType" minOccurs="0"/>
     *         &lt;element name="ThoroughfareLeadingType" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareLeadingTypeType" minOccurs="0"/>
     *         &lt;element name="ThoroughfareName" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNameType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="ThoroughfareTrailingType" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareTrailingTypeType" minOccurs="0"/>
     *         &lt;element name="ThoroughfarePostDirection" type="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfarePostDirectionType" minOccurs="0"/>
     *         &lt;any/>
     *       &lt;/sequence>
     *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "addressLine",
        "thoroughfarePreDirection",
        "thoroughfareLeadingType",
        "thoroughfareName",
        "thoroughfareTrailingType",
        "thoroughfarePostDirection",
        "any"
    })
    public static class DependentThoroughfare {

        @XmlElement(name = "AddressLine")
        private List<AddressLine> addressLine;
        @XmlElement(name = "ThoroughfarePreDirection")
        private ThoroughfarePreDirectionType thoroughfarePreDirection;
        @XmlElement(name = "ThoroughfareLeadingType")
        private ThoroughfareLeadingTypeType thoroughfareLeadingType;
        @XmlElement(name = "ThoroughfareName")
        private List<ThoroughfareNameType> thoroughfareName;
        @XmlElement(name = "ThoroughfareTrailingType")
        private ThoroughfareTrailingTypeType thoroughfareTrailingType;
        @XmlElement(name = "ThoroughfarePostDirection")
        private ThoroughfarePostDirectionType thoroughfarePostDirection;
        @XmlAnyElement(lax = true)
        private List<Object> any;
        @XmlAttribute(name = "Type")
        @XmlSchemaType(name = "anySimpleType")
        private String type;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the addressLine property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the addressLine property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAddressLine().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AddressLine }
         *
         *
         */
        public List<AddressLine> getAddressLine() {
            if (addressLine == null) {
                addressLine = new ArrayList<AddressLine>();
            }
            return this.addressLine;
        }

        /**
         * Gets the value of the thoroughfarePreDirection property.
         *
         * @return
         *     possible object is
         *     {@link ThoroughfarePreDirectionType }
         *
         */
        public ThoroughfarePreDirectionType getThoroughfarePreDirection() {
            return thoroughfarePreDirection;
        }

        /**
         * Sets the value of the thoroughfarePreDirection property.
         *
         * @param value
         *     allowed object is
         *     {@link ThoroughfarePreDirectionType }
         *
         */
        public void setThoroughfarePreDirection(final ThoroughfarePreDirectionType value) {
            this.thoroughfarePreDirection = value;
        }

        /**
         * Gets the value of the thoroughfareLeadingType property.
         *
         * @return
         *     possible object is
         *     {@link ThoroughfareLeadingTypeType }
         *
         */
        public ThoroughfareLeadingTypeType getThoroughfareLeadingType() {
            return thoroughfareLeadingType;
        }

        /**
         * Sets the value of the thoroughfareLeadingType property.
         *
         * @param value
         *     allowed object is
         *     {@link ThoroughfareLeadingTypeType }
         *
         */
        public void setThoroughfareLeadingType(final ThoroughfareLeadingTypeType value) {
            this.thoroughfareLeadingType = value;
        }

        /**
         * Gets the value of the thoroughfareName property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the thoroughfareName property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getThoroughfareName().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ThoroughfareNameType }
         *
         *
         */
        public List<ThoroughfareNameType> getThoroughfareName() {
            if (thoroughfareName == null) {
                thoroughfareName = new ArrayList<ThoroughfareNameType>();
            }
            return this.thoroughfareName;
        }

        /**
         * Gets the value of the thoroughfareTrailingType property.
         *
         * @return
         *     possible object is
         *     {@link ThoroughfareTrailingTypeType }
         *
         */
        public ThoroughfareTrailingTypeType getThoroughfareTrailingType() {
            return thoroughfareTrailingType;
        }

        /**
         * Sets the value of the thoroughfareTrailingType property.
         *
         * @param value
         *     allowed object is
         *     {@link ThoroughfareTrailingTypeType }
         *
         */
        public void setThoroughfareTrailingType(final ThoroughfareTrailingTypeType value) {
            this.thoroughfareTrailingType = value;
        }

        /**
         * Gets the value of the thoroughfarePostDirection property.
         *
         * @return
         *     possible object is
         *     {@link ThoroughfarePostDirectionType }
         *
         */
        public ThoroughfarePostDirectionType getThoroughfarePostDirection() {
            return thoroughfarePostDirection;
        }

        /**
         * Sets the value of the thoroughfarePostDirection property.
         *
         * @param value
         *     allowed object is
         *     {@link ThoroughfarePostDirectionType }
         *
         */
        public void setThoroughfarePostDirection(final ThoroughfarePostDirectionType value) {
            this.thoroughfarePostDirection = value;
        }

        /**
         * Gets the value of the any property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the any property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAny().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Object }
         *
         *
         */
        public List<Object> getAny() {
            if (any == null) {
                any = new ArrayList<Object>();
            }
            return this.any;
        }

        /**
         * Gets the value of the type property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setType(final String value) {
            this.type = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         *
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         *
         * @return
         *     always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

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
     *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}AddressLine" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="ThoroughfareNumberFrom">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}AddressLine" maxOccurs="unbounded" minOccurs="0"/>
     *                   &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberPrefix" maxOccurs="unbounded" minOccurs="0"/>
     *                   &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumber" maxOccurs="unbounded"/>
     *                   &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberSuffix" maxOccurs="unbounded" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attGroup ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}grPostal"/>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="ThoroughfareNumberTo">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}AddressLine" maxOccurs="unbounded" minOccurs="0"/>
     *                   &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberPrefix" maxOccurs="unbounded" minOccurs="0"/>
     *                   &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumber" maxOccurs="unbounded"/>
     *                   &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberSuffix" maxOccurs="unbounded" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attGroup ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}grPostal"/>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attGroup ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}grPostal"/>
     *       &lt;attribute name="RangeType">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
     *             &lt;enumeration value="Odd"/>
     *             &lt;enumeration value="Even"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="Indicator" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *       &lt;attribute name="Separator" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *       &lt;attribute name="IndicatorOccurrence">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
     *             &lt;enumeration value="Before"/>
     *             &lt;enumeration value="After"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="NumberRangeOccurrence">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
     *             &lt;enumeration value="BeforeName"/>
     *             &lt;enumeration value="AfterName"/>
     *             &lt;enumeration value="BeforeType"/>
     *             &lt;enumeration value="AfterType"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "addressLine",
        "thoroughfareNumberFrom",
        "thoroughfareNumberTo"
    })
    public static class ThoroughfareNumberRange {

        @XmlElement(name = "AddressLine")
        private List<AddressLine> addressLine;
        @XmlElement(name = "ThoroughfareNumberFrom", required = true)
        private Thoroughfare.ThoroughfareNumberRange.ThoroughfareNumberFrom thoroughfareNumberFrom;
        @XmlElement(name = "ThoroughfareNumberTo", required = true)
        private Thoroughfare.ThoroughfareNumberRange.ThoroughfareNumberTo thoroughfareNumberTo;
        @XmlAttribute(name = "RangeType")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        private String rangeType;
        @XmlAttribute(name = "Indicator")
        @XmlSchemaType(name = "anySimpleType")
        private String indicator;
        @XmlAttribute(name = "Separator")
        @XmlSchemaType(name = "anySimpleType")
        private String separator;
        @XmlAttribute(name = "IndicatorOccurrence")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        private String indicatorOccurrence;
        @XmlAttribute(name = "NumberRangeOccurrence")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        private String numberRangeOccurrence;
        @XmlAttribute(name = "Type")
        @XmlSchemaType(name = "anySimpleType")
        private String type;
        @XmlAttribute(name = "Code")
        @XmlSchemaType(name = "anySimpleType")
        private String code;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the addressLine property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the addressLine property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAddressLine().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AddressLine }
         *
         *
         */
        public List<AddressLine> getAddressLine() {
            if (addressLine == null) {
                addressLine = new ArrayList<AddressLine>();
            }
            return this.addressLine;
        }

        /**
         * Gets the value of the thoroughfareNumberFrom property.
         *
         * @return
         *     possible object is
         *     {@link Thoroughfare.ThoroughfareNumberRange.ThoroughfareNumberFrom }
         *
         */
        public Thoroughfare.ThoroughfareNumberRange.ThoroughfareNumberFrom getThoroughfareNumberFrom() {
            return thoroughfareNumberFrom;
        }

        /**
         * Sets the value of the thoroughfareNumberFrom property.
         *
         * @param value
         *     allowed object is
         *     {@link Thoroughfare.ThoroughfareNumberRange.ThoroughfareNumberFrom }
         *
         */
        public void setThoroughfareNumberFrom(final Thoroughfare.ThoroughfareNumberRange.ThoroughfareNumberFrom value) {
            this.thoroughfareNumberFrom = value;
        }

        /**
         * Gets the value of the thoroughfareNumberTo property.
         *
         * @return
         *     possible object is
         *     {@link Thoroughfare.ThoroughfareNumberRange.ThoroughfareNumberTo }
         *
         */
        public Thoroughfare.ThoroughfareNumberRange.ThoroughfareNumberTo getThoroughfareNumberTo() {
            return thoroughfareNumberTo;
        }

        /**
         * Sets the value of the thoroughfareNumberTo property.
         *
         * @param value
         *     allowed object is
         *     {@link Thoroughfare.ThoroughfareNumberRange.ThoroughfareNumberTo }
         *
         */
        public void setThoroughfareNumberTo(final Thoroughfare.ThoroughfareNumberRange.ThoroughfareNumberTo value) {
            this.thoroughfareNumberTo = value;
        }

        /**
         * Gets the value of the rangeType property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getRangeType() {
            return rangeType;
        }

        /**
         * Sets the value of the rangeType property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setRangeType(final String value) {
            this.rangeType = value;
        }

        /**
         * Gets the value of the indicator property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getIndicator() {
            return indicator;
        }

        /**
         * Sets the value of the indicator property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setIndicator(final String value) {
            this.indicator = value;
        }

        /**
         * Gets the value of the separator property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getSeparator() {
            return separator;
        }

        /**
         * Sets the value of the separator property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setSeparator(final String value) {
            this.separator = value;
        }

        /**
         * Gets the value of the indicatorOccurrence property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getIndicatorOccurrence() {
            return indicatorOccurrence;
        }

        /**
         * Sets the value of the indicatorOccurrence property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setIndicatorOccurrence(final String value) {
            this.indicatorOccurrence = value;
        }

        /**
         * Gets the value of the numberRangeOccurrence property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getNumberRangeOccurrence() {
            return numberRangeOccurrence;
        }

        /**
         * Sets the value of the numberRangeOccurrence property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNumberRangeOccurrence(final String value) {
            this.numberRangeOccurrence = value;
        }

        /**
         * Gets the value of the type property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setType(final String value) {
            this.type = value;
        }

        /**
         * Gets the value of the code property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCode(final String value) {
            this.code = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         *
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         *
         * @return
         *     always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
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
         *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}AddressLine" maxOccurs="unbounded" minOccurs="0"/>
         *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberPrefix" maxOccurs="unbounded" minOccurs="0"/>
         *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumber" maxOccurs="unbounded"/>
         *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberSuffix" maxOccurs="unbounded" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attGroup ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}grPostal"/>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "content"
        })
        public static class ThoroughfareNumberFrom {

            @XmlElementRefs({
                @XmlElementRef(name = "ThoroughfareNumberSuffix", namespace = "urn:oasis:names:tc:ciq:xsdschema:xAL:2.0", type = ThoroughfareNumberSuffix.class),
                @XmlElementRef(name = "ThoroughfareNumber", namespace = "urn:oasis:names:tc:ciq:xsdschema:xAL:2.0", type = ThoroughfareNumber.class),
                @XmlElementRef(name = "ThoroughfareNumberPrefix", namespace = "urn:oasis:names:tc:ciq:xsdschema:xAL:2.0", type = ThoroughfareNumberPrefix.class),
                @XmlElementRef(name = "AddressLine", namespace = "urn:oasis:names:tc:ciq:xsdschema:xAL:2.0", type = AddressLine.class)
            })
            @XmlMixed
            private List<Object> content;
            @XmlAttribute(name = "Code")
            @XmlSchemaType(name = "anySimpleType")
            private String code;
            @XmlAnyAttribute
            private Map<QName, String> otherAttributes = new HashMap<QName, String>();

            /**
             * Gets the value of the content property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the content property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getContent().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * {@link ThoroughfareNumberSuffix }
             * {@link ThoroughfareNumber }
             * {@link ThoroughfareNumberPrefix }
             * {@link AddressLine }
             *
             *
             */
            public List<Object> getContent() {
                if (content == null) {
                    content = new ArrayList<Object>();
                }
                return this.content;
            }

            /**
             * Gets the value of the code property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getCode() {
                return code;
            }

            /**
             * Sets the value of the code property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setCode(final String value) {
                this.code = value;
            }

            /**
             * Gets a map that contains attributes that aren't bound to any typed property on this class.
             *
             * <p>
             * the map is keyed by the name of the attribute and
             * the value is the string value of the attribute.
             *
             * the map returned by this method is live, and you can add new attribute
             * by updating the map directly. Because of this design, there's no setter.
             *
             *
             * @return
             *     always non-null
             */
            public Map<QName, String> getOtherAttributes() {
                return otherAttributes;
            }

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
         *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}AddressLine" maxOccurs="unbounded" minOccurs="0"/>
         *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberPrefix" maxOccurs="unbounded" minOccurs="0"/>
         *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumber" maxOccurs="unbounded"/>
         *         &lt;element ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}ThoroughfareNumberSuffix" maxOccurs="unbounded" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attGroup ref="{urn:oasis:names:tc:ciq:xsdschema:xAL:2.0}grPostal"/>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "content"
        })
        public static class ThoroughfareNumberTo {

            @XmlElementRefs({
                @XmlElementRef(name = "ThoroughfareNumberSuffix", namespace = "urn:oasis:names:tc:ciq:xsdschema:xAL:2.0", type = ThoroughfareNumberSuffix.class),
                @XmlElementRef(name = "ThoroughfareNumber", namespace = "urn:oasis:names:tc:ciq:xsdschema:xAL:2.0", type = ThoroughfareNumber.class),
                @XmlElementRef(name = "ThoroughfareNumberPrefix", namespace = "urn:oasis:names:tc:ciq:xsdschema:xAL:2.0", type = ThoroughfareNumberPrefix.class),
                @XmlElementRef(name = "AddressLine", namespace = "urn:oasis:names:tc:ciq:xsdschema:xAL:2.0", type = AddressLine.class)
            })
            @XmlMixed
            private List<Object> content;
            @XmlAttribute(name = "Code")
            @XmlSchemaType(name = "anySimpleType")
            private String code;
            @XmlAnyAttribute
            private Map<QName, String> otherAttributes = new HashMap<QName, String>();

            /**
             * Gets the value of the content property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the content property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getContent().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * {@link ThoroughfareNumberSuffix }
             * {@link ThoroughfareNumber }
             * {@link ThoroughfareNumberPrefix }
             * {@link AddressLine }
             *
             *
             */
            public List<Object> getContent() {
                if (content == null) {
                    content = new ArrayList<Object>();
                }
                return this.content;
            }

            /**
             * Gets the value of the code property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getCode() {
                return code;
            }

            /**
             * Sets the value of the code property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setCode(final String value) {
                this.code = value;
            }

            /**
             * Gets a map that contains attributes that aren't bound to any typed property on this class.
             *
             * <p>
             * the map is keyed by the name of the attribute and
             * the value is the string value of the attribute.
             *
             * the map returned by this method is live, and you can add new attribute
             * by updating the map directly. Because of this design, there's no setter.
             *
             *
             * @return
             *     always non-null
             */
            public Map<QName, String> getOtherAttributes() {
                return otherAttributes;
            }

        }

    }

}
