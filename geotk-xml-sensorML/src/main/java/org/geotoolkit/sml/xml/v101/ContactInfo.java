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
package org.geotoolkit.sml.xml.v101;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.sml.xml.AbstractContactInfo;
import org.geotoolkit.sml.xml.AbstractOnlineResource;
import org.apache.sis.util.ComparisonMode;


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
 *         &lt;element name="phone" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="voice" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="facsimile" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="address" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="deliveryPoint" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="administrativeArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="postalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="electronicMailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.opengis.net/sensorML/1.0.1}onlineResource" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="hoursOfService" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "phone",
    "address",
    "onlineResource",
    "hoursOfService",
    "contactInstructions"
})
@XmlRootElement(name = "ContactInfo")
public class ContactInfo extends SensorObject implements AbstractContactInfo {

    private Phone phone;
    private Address address;
    private List<OnlineResource> onlineResource;
    private String hoursOfService;
    private String contactInstructions;

    public ContactInfo() {

    }

    public ContactInfo(final AbstractContactInfo ci) {
        if (ci != null) {
            if (ci.getAddress() != null) {
                this.address = new Address(ci.getAddress());
            }
            if (ci.getPhone() != null) {
                this.phone = new Phone(ci.getPhone());
            }
            this.hoursOfService = ci.getHoursOfService();
            this.contactInstructions = ci.getContactInstructions();
            if (ci.getOnlineResource() != null) {
                this.onlineResource = new ArrayList<OnlineResource>();
                for (AbstractOnlineResource or : ci.getOnlineResource()) {
                    this.onlineResource.add(new OnlineResource(or));
                }
            }
        }
    }

    public ContactInfo(final Phone phone, final Address address) {
        this.address = address;
        this.phone   = phone;
    }

    /**
     * Gets the value of the phone property.
     *
     * @return
     *     possible object is
     *     {@link ContactInfo.Phone }
     *
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     *
     * @param value
     *     allowed object is
     *     {@link ContactInfo.Phone }
     *
     */
    public void setPhone(final Phone value) {
        this.phone = value;
    }

    /**
     * Gets the value of the address property.
     *
     * @return
     *     possible object is
     *     {@link ContactInfo.Address }
     *
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     *
     * @param value
     *     allowed object is
     *     {@link ContactInfo.Address }
     *
     */
    public void setAddress(final Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the onlineResource property.
     */
    public List<OnlineResource> getOnlineResource() {
        if (onlineResource == null) {
            onlineResource = new ArrayList<OnlineResource>();
        }
        return this.onlineResource;
    }

    /**
     * sets the value of the onlineResource property.
     */
    public void setOnlineResource(final List<OnlineResource> or) {
        this.onlineResource = or;
    }

    /**
     * Gets the value of the onlineResource property.
     */
    public void setOnlineResource(final OnlineResource or) {
        if (onlineResource == null) {
            onlineResource = new ArrayList<OnlineResource>();
        }
        this.onlineResource.add(or);
    }

    /**
     * Gets the value of the hoursOfService property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHoursOfService() {
        return hoursOfService;
    }

    /**
     * Sets the value of the hoursOfService property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHoursOfService(final String value) {
        this.hoursOfService = value;
    }

    /**
     * Gets the value of the contactInstructions property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getContactInstructions() {
        return contactInstructions;
    }

    /**
     * Sets the value of the contactInstructions property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setContactInstructions(final String value) {
        this.contactInstructions = value;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ContactInfo]").append("\n");
        if (phone != null) {
            sb.append("phone: ").append(phone).append('\n');
        }
        if (address != null) {
            sb.append("address: ").append(address).append('\n');
        }
        if (hoursOfService != null) {
            sb.append("hoursOfService: ").append(hoursOfService).append('\n');
        }
        if (contactInstructions != null) {
            sb.append("contactInstructions: ").append(contactInstructions).append('\n');
        }
        if (onlineResource != null) {
            for (OnlineResource o : onlineResource) {
                sb.append("onlineResource: ").append(o).append('\n');
            }
        }
        return sb.toString();
    }

    /**
     * Verify if this entry is identical to specified object.
     */
    @Override
    public boolean equals(final Object object, final ComparisonMode mode) {
        if (object == this) {
            return true;
        }

        if (object instanceof ContactInfo) {
            final ContactInfo that = (ContactInfo) object;
            return Objects.equals(this.address,             that.address)             &&
                   Objects.equals(this.contactInstructions, that.contactInstructions) &&
                   Objects.equals(this.hoursOfService,      that.hoursOfService)      &&
                   Objects.equals(this.onlineResource,      that.onlineResource)      &&
                   Objects.equals(this.phone,               that.phone);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.phone != null ? this.phone.hashCode() : 0);
        hash = 23 * hash + (this.address != null ? this.address.hashCode() : 0);
        hash = 23 * hash + (this.onlineResource != null ? this.onlineResource.hashCode() : 0);
        hash = 23 * hash + (this.hoursOfService != null ? this.hoursOfService.hashCode() : 0);
        hash = 23 * hash + (this.contactInstructions != null ? this.contactInstructions.hashCode() : 0);
        return hash;
    }
}
