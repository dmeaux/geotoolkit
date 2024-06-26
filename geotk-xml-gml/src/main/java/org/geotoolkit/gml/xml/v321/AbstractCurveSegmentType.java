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

import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.gml.xml.AbstractCurveSegment;


/**
 * <p>Java class for AbstractCurveSegmentType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AbstractCurveSegmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="numDerivativesAtStart" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="numDerivativesAtEnd" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="numDerivativeInterior" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractCurveSegmentType")
@XmlSeeAlso({
    GeodesicStringType.class,
    LineStringSegmentType.class,
    ArcStringType.class,
    BSplineType.class,
    OffsetCurveType.class,
    ClothoidType.class,
    CubicSplineType.class,
    ArcByCenterPointType.class,
    ArcStringByBulgeType.class
})
public abstract class AbstractCurveSegmentType implements AbstractCurveSegment {

    @XmlAttribute
    private Integer numDerivativesAtStart;
    @XmlAttribute
    private Integer numDerivativesAtEnd;
    @XmlAttribute
    private Integer numDerivativeInterior;

    /**
     * Gets the value of the numDerivativesAtStart property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getNumDerivativesAtStart() {
        if (numDerivativesAtStart == null) {
            return new Integer("0");
        } else {
            return numDerivativesAtStart;
        }
    }

    /**
     * Sets the value of the numDerivativesAtStart property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setNumDerivativesAtStart(Integer value) {
        this.numDerivativesAtStart = value;
    }

    /**
     * Gets the value of the numDerivativesAtEnd property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getNumDerivativesAtEnd() {
        if (numDerivativesAtEnd == null) {
            return new Integer("0");
        } else {
            return numDerivativesAtEnd;
        }
    }

    /**
     * Sets the value of the numDerivativesAtEnd property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setNumDerivativesAtEnd(Integer value) {
        this.numDerivativesAtEnd = value;
    }

    /**
     * Gets the value of the numDerivativeInterior property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getNumDerivativeInterior() {
        if (numDerivativeInterior == null) {
            return new Integer("0");
        } else {
            return numDerivativeInterior;
        }
    }

    /**
     * Sets the value of the numDerivativeInterior property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setNumDerivativeInterior(Integer value) {
        this.numDerivativeInterior = value;
    }

    /**
     * Verify that the point is identical to the specified object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof AbstractCurveSegmentType) {
            final AbstractCurveSegmentType that = (AbstractCurveSegmentType) object;
            return  Objects.equals(this.numDerivativeInterior, that.numDerivativeInterior) &&
                    Objects.equals(this.numDerivativesAtEnd,   that.numDerivativesAtEnd)   &&
                    Objects.equals(this.numDerivativesAtStart, that.numDerivativesAtStart);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.numDerivativesAtStart != null ? this.numDerivativesAtStart.hashCode() : 0);
        hash = 31 * hash + (this.numDerivativesAtEnd   != null ? this.numDerivativesAtEnd.hashCode()   : 0);
        hash = 31 * hash + (this.numDerivativeInterior != null ? this.numDerivativeInterior.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[").append(this.getClass().getSimpleName()).append("]\n");
        if (numDerivativesAtStart != null) {
            sb.append("numDerivativesAtStart:").append(numDerivativesAtStart).append('\n');
        }
        if (numDerivativesAtEnd != null) {
            sb.append("numDerivativesAtEnd:").append(numDerivativesAtEnd).append('\n');
        }
        if (numDerivativeInterior != null) {
            sb.append("numDerivativeInterior:").append(numDerivativeInterior).append('\n');
        }
        return sb.toString();
    }
}
