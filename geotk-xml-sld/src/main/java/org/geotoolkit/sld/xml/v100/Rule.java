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
package org.geotoolkit.sld.xml.v100;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.ogc.xml.v100.FilterType;


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
 *         &lt;element ref="{http://www.opengis.net/sld}Name" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Title" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Abstract" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}LegendGraphic" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element ref="{http://www.opengis.net/ogc}Filter"/>
 *           &lt;element ref="{http://www.opengis.net/sld}ElseFilter"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/sld}MinScaleDenominator" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}MaxScaleDenominator" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Symbolizer" maxOccurs="unbounded"/>
 *       &lt;/sequence>
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
    "name",
    "title",
    "_abstract",
    "legendGraphic",
    "filter",
    "elseFilter",
    "minScaleDenominator",
    "maxScaleDenominator",
    "symbolizer"
})
@XmlRootElement(name = "Rule")
public class Rule {

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElement(name = "LegendGraphic")
    protected LegendGraphic legendGraphic;
    @XmlElement(name = "Filter", namespace = "http://www.opengis.net/ogc")
    protected FilterType filter;
    @XmlElement(name = "ElseFilter")
    protected ElseFilter elseFilter;
    @XmlElement(name = "MinScaleDenominator")
    protected Double minScaleDenominator;
    @XmlElement(name = "MaxScaleDenominator")
    protected Double maxScaleDenominator;
    @XmlElementRef(name = "Symbolizer", namespace = "http://www.opengis.net/sld", type = JAXBElement.class)
    protected List<JAXBElement<? extends SymbolizerType>> symbolizer;

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(final String value) {
        this.name = value;
    }

    /**
     * Gets the value of the title property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTitle(final String value) {
        this.title = value;
    }

    /**
     * Gets the value of the abstract property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * Sets the value of the abstract property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAbstract(final String value) {
        this._abstract = value;
    }

    /**
     * Gets the value of the legendGraphic property.
     *
     * @return
     *     possible object is
     *     {@link LegendGraphic }
     *
     */
    public LegendGraphic getLegendGraphic() {
        return legendGraphic;
    }

    /**
     * Sets the value of the legendGraphic property.
     *
     * @param value
     *     allowed object is
     *     {@link LegendGraphic }
     *
     */
    public void setLegendGraphic(final LegendGraphic value) {
        this.legendGraphic = value;
    }

    /**
     * Gets the value of the filter property.
     *
     * @return
     *     possible object is
     *     {@link FilterType }
     *
     */
    public FilterType getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     *
     * @param value
     *     allowed object is
     *     {@link FilterType }
     *
     */
    public void setFilter(final FilterType value) {
        this.filter = value;
    }

    /**
     * Gets the value of the elseFilter property.
     *
     * @return
     *     possible object is
     *     {@link ElseFilter }
     *
     */
    public ElseFilter getElseFilter() {
        return elseFilter;
    }

    /**
     * Sets the value of the elseFilter property.
     *
     * @param value
     *     allowed object is
     *     {@link ElseFilter }
     *
     */
    public void setElseFilter(final ElseFilter value) {
        this.elseFilter = value;
    }

    /**
     * Gets the value of the minScaleDenominator property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getMinScaleDenominator() {
        return minScaleDenominator;
    }

    /**
     * Sets the value of the minScaleDenominator property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setMinScaleDenominator(final Double value) {
        this.minScaleDenominator = value;
    }

    /**
     * Gets the value of the maxScaleDenominator property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getMaxScaleDenominator() {
        return maxScaleDenominator;
    }

    /**
     * Sets the value of the maxScaleDenominator property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setMaxScaleDenominator(final Double value) {
        this.maxScaleDenominator = value;
    }

    /**
     * Gets the value of the symbolizer property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the symbolizer property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSymbolizer().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TextSymbolizer }{@code >}
     * {@link JAXBElement }{@code <}{@link LineSymbolizer }{@code >}
     * {@link JAXBElement }{@code <}{@link SymbolizerType }{@code >}
     * {@link JAXBElement }{@code <}{@link RasterSymbolizer }{@code >}
     * {@link JAXBElement }{@code <}{@link PointSymbolizer }{@code >}
     * {@link JAXBElement }{@code <}{@link PolygonSymbolizer }{@code >}
     *
     *
     */
    public List<JAXBElement<? extends SymbolizerType>> getSymbolizer() {
        if (symbolizer == null) {
            symbolizer = new ArrayList<JAXBElement<? extends SymbolizerType>>();
        }
        return this.symbolizer;
    }

}
