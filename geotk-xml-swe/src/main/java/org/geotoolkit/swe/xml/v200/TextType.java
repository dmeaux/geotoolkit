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

import java.util.List;
import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.swe.xml.AbstractText;


/**
 * <p>Java class for TextType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TextType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/swe/2.0}AbstractSimpleComponentType">
 *       &lt;sequence>
 *         &lt;element name="constraint" type="{http://www.opengis.net/swe/2.0}AllowedTokensPropertyType" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TextType", propOrder = {
    "constraint",
    "value"
})
public class TextType extends AbstractSimpleComponentType implements AbstractText {

    private AllowedTokensPropertyType constraint;
    private String value;

    public TextType() {

    }

    public TextType(final String definition, final String value) {
        this(null, definition, value);
    }

    public TextType(final String id, final String definition, final String value) {
        this(id, definition, value, null);
    }

    public TextType(final String id, final String definition, final String value, final List<QualityPropertyType> quality) {
        this(id, null, null, definition, value, quality);
    }

    public TextType(final String id, final String identifier, final String description, final String definition, final String value, final List<QualityPropertyType> quality) {
        super(id, identifier, description, definition, null, quality);
        this.value = value;
    }

    public TextType(final AbstractText tx) {
        super(tx);
        if (tx != null) {
            this.value = tx.getValue();
        }
    }

    /**
     * Gets the value of the constraint property.
     *
     * @return
     *     possible object is
     *     {@link AllowedTokensPropertyType }
     *
     */
    public AllowedTokensPropertyType getConstraint() {
        return constraint;
    }

    /**
     * Sets the value of the constraint property.
     *
     * @param value
     *     allowed object is
     *     {@link AllowedTokensPropertyType }
     *
     */
    public void setConstraint(AllowedTokensPropertyType value) {
        this.constraint = value;
    }

    /**
     * Gets the value of the value property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof TextType && super.equals(object)) {
            final TextType that = (TextType) object;

            return Objects.equals(this.constraint,  that.constraint) &&
                   Objects.equals(this.value,       that.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 47 * hash + (this.constraint != null ? this.constraint.hashCode() : 0);
        hash = 47 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder(super.toString());
        if (constraint != null) {
            s.append("constraint=").append(constraint).append('\n');
        }
        if (value != null) {
            s.append("value=").append(value).append('\n');
        }
        return s.toString();
    }
}
