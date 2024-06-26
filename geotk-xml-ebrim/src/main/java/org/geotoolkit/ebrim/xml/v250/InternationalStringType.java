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
package org.geotoolkit.ebrim.xml.v250;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.ebrim.xml.EbrimInternationalString;


/**
 * <p>Java class for InternationalStringType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="InternationalStringType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{urn:oasis:names:tc:ebxml-regrep:rim:xsd:2.5}LocalizedString"/>
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
@XmlType(name = "InternationalStringType", propOrder = {
    "localizedString"
})
public class InternationalStringType implements EbrimInternationalString {

    @XmlElement(name = "LocalizedString")
    private List<LocalizedStringType> localizedString;

    /**
     * Gets the value of the localizedString property.
     */
    public List<LocalizedStringType> getLocalizedString() {
        if (localizedString == null) {
            localizedString = new ArrayList<LocalizedStringType>();
        }
        return this.localizedString;
    }

    /**
     * Set the values of localizedString.
     *
     * @param localizedString
     */
    public void setLocalizedString(final List<LocalizedStringType> localizedString) {
        this.localizedString = localizedString;
    }

    /**
     * Add a singleton value to the localizedString list.
     *
     * @param localizedString
     */
    public void setLocalizedString(final LocalizedStringType localizedString) {
        if (this.localizedString == null) {
            this.localizedString = new ArrayList<LocalizedStringType>();
        }
        this.localizedString.add(localizedString);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[InternationalStringType]\n");
        if (localizedString != null) {
            sb.append("localizedString:\n");
            for (LocalizedStringType cl : localizedString) {
                sb.append(cl).append('\n');
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InternationalStringType) {
            final InternationalStringType that = (InternationalStringType) obj;
            return Objects.equals(this.localizedString,     that.localizedString);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.localizedString != null ? this.localizedString.hashCode() : 0);
        return hash;
    }
}
