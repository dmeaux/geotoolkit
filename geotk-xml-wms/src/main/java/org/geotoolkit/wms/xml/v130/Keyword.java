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
package org.geotoolkit.wms.xml.v130;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import org.geotoolkit.wms.xml.AbstractKeyword;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="vocabulary" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 *
 * @author Guilhem Legal
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "Keyword")
public class Keyword implements AbstractKeyword{

    @XmlValue
    private String value;
    @XmlAttribute
    private String vocabulary;


     /**
     * An empty constructor used by JAXB.
     */
     Keyword() {
     }

     /**
     * Build a new Keyword object.
     */
    public Keyword(final String value) {
        this.value      = value;
    }

    /**
     * Build a new Keyword object.
     */
    public Keyword(final String value, final String vocabulary) {
        this.value      = value;
        this.vocabulary = vocabulary;
    }

    /**
     * Gets the value of the value property.
     */
    @Override
    public String getValue() {
        return value;
    }

   /**
    * Gets the value of the vocabulary property.
    */
    @Override
    public String getVocabulary() {
        return vocabulary;
    }
}
