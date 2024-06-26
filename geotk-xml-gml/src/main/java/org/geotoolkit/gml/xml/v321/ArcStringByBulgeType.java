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
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArcStringByBulgeType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArcStringByBulgeType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml/3.2}AbstractCurveSegmentType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;choice maxOccurs="unbounded" minOccurs="2">
 *             &lt;element ref="{http://www.opengis.net/gml/3.2}pos"/>
 *             &lt;element ref="{http://www.opengis.net/gml/3.2}pointProperty"/>
 *             &lt;element ref="{http://www.opengis.net/gml/3.2}pointRep"/>
 *           &lt;/choice>
 *           &lt;element ref="{http://www.opengis.net/gml/3.2}posList"/>
 *           &lt;element ref="{http://www.opengis.net/gml/3.2}coordinates"/>
 *         &lt;/choice>
 *         &lt;element name="bulge" type="{http://www.w3.org/2001/XMLSchema}double" maxOccurs="unbounded"/>
 *         &lt;element name="normal" type="{http://www.opengis.net/gml/3.2}VectorType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="interpolation" type="{http://www.opengis.net/gml/3.2}CurveInterpolationType" fixed="circularArc2PointWithBulge" />
 *       &lt;attribute name="numArc" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArcStringByBulgeType", propOrder = {
    "posOrPointPropertyOrPointRep",
    "posList",
    "coordinates",
    "bulge",
    "normal"
})
@XmlSeeAlso({
    ArcByBulgeType.class
})
public class ArcStringByBulgeType
    extends AbstractCurveSegmentType
{

    @XmlElementRefs({
        @XmlElementRef(name = "pos", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class),
        @XmlElementRef(name = "pointProperty", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class),
        @XmlElementRef(name = "pointRep", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class)
    })
    private List<JAXBElement<?>> posOrPointPropertyOrPointRep;
    private DirectPositionListType posList;
    private CoordinatesType coordinates;
    @XmlElement(type = Double.class)
    private List<Double> bulge;
    @XmlElement(required = true)
    private List<VectorType> normal;
    @XmlAttribute
    private CurveInterpolationType interpolation;
    @XmlAttribute
    private Integer numArc;

    /**
     * Gets the value of the posOrPointPropertyOrPointRep property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the posOrPointPropertyOrPointRep property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPosOrPointPropertyOrPointRep().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link DirectPositionType }{@code >}
     * {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     *
     *
     */
    public List<JAXBElement<?>> getPosOrPointPropertyOrPointRep() {
        if (posOrPointPropertyOrPointRep == null) {
            posOrPointPropertyOrPointRep = new ArrayList<JAXBElement<?>>();
        }
        return this.posOrPointPropertyOrPointRep;
    }

    /**
     * Gets the value of the posList property.
     *
     * @return
     *     possible object is
     *     {@link DirectPositionListType }
     *
     */
    public DirectPositionListType getPosList() {
        return posList;
    }

    /**
     * Sets the value of the posList property.
     *
     * @param value
     *     allowed object is
     *     {@link DirectPositionListType }
     *
     */
    public void setPosList(DirectPositionListType value) {
        this.posList = value;
    }

    /**
     * Gets the value of the coordinates property.
     *
     * @return
     *     possible object is
     *     {@link CoordinatesType }
     *
     */
    public CoordinatesType getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the value of the coordinates property.
     *
     * @param value
     *     allowed object is
     *     {@link CoordinatesType }
     *
     */
    public void setCoordinates(CoordinatesType value) {
        this.coordinates = value;
    }

    /**
     * Gets the value of the bulge property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bulge property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBulge().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Double }
     *
     *
     */
    public List<Double> getBulge() {
        if (bulge == null) {
            bulge = new ArrayList<Double>();
        }
        return this.bulge;
    }

    /**
     * Gets the value of the normal property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the normal property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNormal().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VectorType }
     *
     *
     */
    public List<VectorType> getNormal() {
        if (normal == null) {
            normal = new ArrayList<VectorType>();
        }
        return this.normal;
    }

    /**
     * Gets the value of the interpolation property.
     *
     * @return
     *     possible object is
     *     {@link CurveInterpolationType }
     *
     */
    public CurveInterpolationType getInterpolation() {
        if (interpolation == null) {
            return CurveInterpolationType.CIRCULAR_ARC_2_POINT_WITH_BULGE;
        } else {
            return interpolation;
        }
    }

    /**
     * Sets the value of the interpolation property.
     *
     * @param value
     *     allowed object is
     *     {@link CurveInterpolationType }
     *
     */
    public void setInterpolation(CurveInterpolationType value) {
        this.interpolation = value;
    }

    /**
     * Gets the value of the numArc property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getNumArc() {
        return numArc;
    }

    /**
     * Sets the value of the numArc property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setNumArc(Integer value) {
        this.numArc = value;
    }

}
