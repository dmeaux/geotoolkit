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
package org.geotoolkit.se.xml.v110;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PolygonSymbolizerType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PolygonSymbolizerType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/se}SymbolizerType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/se}Geometry" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/se}Fill" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/se}Stroke" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/se}Displacement" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/se}PerpendicularOffset" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolygonSymbolizerType", propOrder = {
    "geometry",
    "fill",
    "stroke",
    "displacement",
    "perpendicularOffset"
})
public class PolygonSymbolizerType
    extends SymbolizerType
{

    //NOTE : we support more large types of expressions, not only PropertyName
    @XmlElement(name = "Geometry")
    protected ParameterValueType geometry;
    @XmlElement(name = "Fill")
    protected FillType fill;
    @XmlElement(name = "Stroke")
    protected StrokeType stroke;
    @XmlElement(name = "Displacement")
    protected DisplacementType displacement;
    @XmlElement(name = "PerpendicularOffset")
    protected ParameterValueType perpendicularOffset;

    /**
     * Gets the value of the geometry property.
     *
     * @return
     *     possible object is
     *     {@link GeometryType }
     *
     */
    public ParameterValueType getGeometry() {
        return geometry;
    }

    /**
     * Sets the value of the geometry property.
     *
     * @param value
     *     allowed object is
     *     {@link GeometryType }
     *
     */
    public void setGeometry(final ParameterValueType value) {
        this.geometry = value;
    }

    /**
     * Gets the value of the fill property.
     *
     * @return
     *     possible object is
     *     {@link FillType }
     *
     */
    public FillType getFill() {
        return fill;
    }

    /**
     * Sets the value of the fill property.
     *
     * @param value
     *     allowed object is
     *     {@link FillType }
     *
     */
    public void setFill(final FillType value) {
        this.fill = value;
    }

    /**
     * Gets the value of the stroke property.
     *
     * @return
     *     possible object is
     *     {@link StrokeType }
     *
     */
    public StrokeType getStroke() {
        return stroke;
    }

    /**
     * Sets the value of the stroke property.
     *
     * @param value
     *     allowed object is
     *     {@link StrokeType }
     *
     */
    public void setStroke(final StrokeType value) {
        this.stroke = value;
    }

    /**
     * Gets the value of the displacement property.
     *
     * @return
     *     possible object is
     *     {@link DisplacementType }
     *
     */
    public DisplacementType getDisplacement() {
        return displacement;
    }

    /**
     * Sets the value of the displacement property.
     *
     * @param value
     *     allowed object is
     *     {@link DisplacementType }
     *
     */
    public void setDisplacement(final DisplacementType value) {
        this.displacement = value;
    }

    /**
     * Gets the value of the perpendicularOffset property.
     *
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *
     */
    public ParameterValueType getPerpendicularOffset() {
        return perpendicularOffset;
    }

    /**
     * Sets the value of the perpendicularOffset property.
     *
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *
     */
    public void setPerpendicularOffset(final ParameterValueType value) {
        this.perpendicularOffset = value;
    }

}
