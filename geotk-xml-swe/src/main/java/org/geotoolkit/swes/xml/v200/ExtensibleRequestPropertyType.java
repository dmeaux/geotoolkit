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
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.xlink.xml.v100.ActuateType;
import org.geotoolkit.xlink.xml.v100.ShowType;


/**
 * <p>Java class for ExtensibleRequestPropertyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ExtensibleRequestPropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element ref="{http://www.opengis.net/swes/2.0}ExtensibleRequest"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opengis.net/gml/3.2}AssociationAttributeGroup"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensibleRequestPropertyType", propOrder = {
    "extensibleRequest"
})
public class ExtensibleRequestPropertyType {

    @XmlElementRef(name = "ExtensibleRequest", namespace = "http://www.opengis.net/swes/2.0", type = JAXBElement.class)
    private JAXBElement<? extends ExtensibleRequestType> extensibleRequest;
    @XmlAttribute
    private List<String> nilReason;
    @XmlAttribute(namespace = "http://www.opengis.net/gml/3.2")
    @XmlSchemaType(name = "anyURI")
    private String remoteSchema;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    private String type;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    private String href;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    private String role;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    private String arcrole;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    private String titleTemp;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    private ShowType show;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    private ActuateType actuate;

    /**
     * Gets the value of the extensibleRequest property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetObservationType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DeleteSensorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GetObservationByIdType }{@code >}
     *     {@link JAXBElement }{@code <}{@link InsertResultTemplateType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DescribeSensorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link UpdateSensorDescriptionType }{@code >}
     *     {@link JAXBElement }{@code <}{@link InsertResultType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GetFeatureOfInterestType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExtensibleRequestType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GetResultTemplateType }{@code >}
     *     {@link JAXBElement }{@code <}{@link InsertSensorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link InsertObservationType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GetResultType }{@code >}
     *
     */
    public JAXBElement<? extends ExtensibleRequestType> getExtensibleRequest() {
        return extensibleRequest;
    }

    /**
     * Sets the value of the extensibleRequest property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetObservationType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DeleteSensorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GetObservationByIdType }{@code >}
     *     {@link JAXBElement }{@code <}{@link InsertResultTemplateType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DescribeSensorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link UpdateSensorDescriptionType }{@code >}
     *     {@link JAXBElement }{@code <}{@link InsertResultType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GetFeatureOfInterestType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExtensibleRequestType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GetResultTemplateType }{@code >}
     *     {@link JAXBElement }{@code <}{@link InsertSensorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link InsertObservationType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GetResultType }{@code >}
     *
     */
    public void setExtensibleRequest(JAXBElement<? extends ExtensibleRequestType> value) {
        this.extensibleRequest = ((JAXBElement<? extends ExtensibleRequestType> ) value);
    }

    /**
     * Gets the value of the nilReason property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nilReason property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNilReason().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     *
     *
     */
    public List<String> getNilReason() {
        if (nilReason == null) {
            nilReason = new ArrayList<String>();
        }
        return this.nilReason;
    }

    /**
     * Gets the value of the remoteSchema property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRemoteSchema() {
        return remoteSchema;
    }

    /**
     * Sets the value of the remoteSchema property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRemoteSchema(String value) {
        this.remoteSchema = value;
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
        if (type == null) {
            return "simple";
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the href property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Gets the value of the role property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Gets the value of the arcrole property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getArcrole() {
        return arcrole;
    }

    /**
     * Sets the value of the arcrole property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setArcrole(String value) {
        this.arcrole = value;
    }

    /**
     * Gets the value of the titleTemp property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTitleTemp() {
        return titleTemp;
    }

    /**
     * Sets the value of the titleTemp property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTitleTemp(String value) {
        this.titleTemp = value;
    }

    /**
     * Gets the value of the show property.
     *
     * @return
     *     possible object is
     *     {@link ShowType }
     *
     */
    public ShowType getShow() {
        return show;
    }

    /**
     * Sets the value of the show property.
     *
     * @param value
     *     allowed object is
     *     {@link ShowType }
     *
     */
    public void setShow(ShowType value) {
        this.show = value;
    }

    /**
     * Gets the value of the actuate property.
     *
     * @return
     *     possible object is
     *     {@link ActuateType }
     *
     */
    public ActuateType getActuate() {
        return actuate;
    }

    /**
     * Sets the value of the actuate property.
     *
     * @param value
     *     allowed object is
     *     {@link ActuateType }
     *
     */
    public void setActuate(ActuateType value) {
        this.actuate = value;
    }

}
