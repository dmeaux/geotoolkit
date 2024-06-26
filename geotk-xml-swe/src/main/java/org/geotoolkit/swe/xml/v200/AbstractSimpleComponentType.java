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
import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.swe.xml.AbstractDataComponent;


/**
 * <p>Java class for AbstractSimpleComponentType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AbstractSimpleComponentType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/swe/2.0}AbstractDataComponentType">
 *       &lt;sequence>
 *         &lt;element name="quality" type="{http://www.opengis.net/swe/2.0}QualityPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nilValues" type="{http://www.opengis.net/swe/2.0}NilValuesPropertyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="referenceFrame" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="axisID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractSimpleComponentType", propOrder = {
    "quality",
    "nilValues"
})
@XmlSeeAlso({
    CountRangeType.class,
    CountType.class,
    TimeType.class,
    QuantityRangeType.class,
    QuantityType.class,
    CategoryRangeType.class,
    BooleanType.class,
    TextType.class,
    TimeRangeType.class,
    CategoryType.class
})
public abstract class AbstractSimpleComponentType extends AbstractDataComponentType {

    protected List<QualityPropertyType> quality = new ArrayList<>();
    private NilValuesPropertyType nilValues;
    @XmlAttribute
    @XmlSchemaType(name = "anyURI")
    protected String referenceFrame;
    @XmlAttribute
    protected String axisID;

    public AbstractSimpleComponentType() {

    }

    public AbstractSimpleComponentType(final AbstractDataComponent component) {
        super(component);
        if (component instanceof AbstractSimpleComponentType asc) {
            this.axisID         = asc.axisID;
            this.referenceFrame = asc.referenceFrame;
            this.nilValues      = asc.nilValues;
            if (asc.quality != null) {
                this.quality    = new ArrayList<>(asc.quality);
            }
        }
    }

    public AbstractSimpleComponentType(final String id, final String definition, final String axisID) {
        this(id, definition, axisID, new ArrayList<>());
    }

    public AbstractSimpleComponentType(final String id, final String definition, final String axisID, final List<QualityPropertyType> quality) {
        this(id, null, null, definition, axisID, quality);
    }

    public AbstractSimpleComponentType(final String id, final String identifier, final String description, final String definition, final String axisID, final List<QualityPropertyType> quality) {
        super(id, identifier, description, definition, null);
        this.axisID = axisID;
        this.quality = quality;
    }

    /**
     * Gets the value of the quality property.
     *
     * Objects of the following type(s) are allowed in the list
     * {@link QualityPropertyType }
     *
     *
     */
    public List<QualityPropertyType> getQuality() {
        if (quality == null) {
            quality = new ArrayList<>();
        }
        return this.quality;
    }

    public void setQuality(List<QualityPropertyType> quality) {
       this.quality = quality;
    }

    /**
     * Gets the value of the nilValues property.
     *
     * @return
     *     possible object is
     *     {@link NilValuesPropertyType }
     *
     */
    public NilValuesPropertyType getNilValues() {
        return nilValues;
    }

    /**
     * Sets the value of the nilValues property.
     *
     * @param value
     *     allowed object is
     *     {@link NilValuesPropertyType }
     *
     */
    public void setNilValues(NilValuesPropertyType value) {
        this.nilValues = value;
    }

    /**
     * Gets the value of the referenceFrame property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReferenceFrame() {
        return referenceFrame;
    }

    /**
     * Sets the value of the referenceFrame property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReferenceFrame(String value) {
        this.referenceFrame = value;
    }

    /**
     * Gets the value of the axisID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAxisID() {
        return axisID;
    }

    /**
     * Sets the value of the axisID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAxisID(String value) {
        this.axisID = value;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof AbstractSimpleComponentType && super.equals(object)) {
            final AbstractSimpleComponentType that = (AbstractSimpleComponentType) object;

            return Objects.equals(this.axisID,         that.axisID) &&
                   Objects.equals(this.nilValues,      that.nilValues) &&
                   Objects.equals(this.referenceFrame, that.referenceFrame) &&
                   Objects.equals(this.quality,        that.quality);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();;
        hash = 47 * hash + (this.axisID != null ? this.axisID.hashCode() : 0);
        hash = 47 * hash + (this.nilValues != null ? this.nilValues.hashCode() : 0);
        hash = 47 * hash + (this.referenceFrame != null ? this.referenceFrame.hashCode() : 0);
        hash = 47 * hash + (this.quality != null ? this.quality.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder(super.toString());
        if (axisID != null) {
            s.append("axisID=").append(axisID).append('\n');
        }
        if (nilValues != null) {
            s.append("nilValues=").append(nilValues).append('\n');
        }
        if (referenceFrame != null) {
            s.append("referenceFrame=").append(referenceFrame).append('\n');
        }
        if (quality != null) {
            s.append("quality: [\n");
            for (QualityPropertyType q : quality) {
                s.append(q).append('\n');
            }
             s.append("]");
        }
        return s.toString();
    }
}
