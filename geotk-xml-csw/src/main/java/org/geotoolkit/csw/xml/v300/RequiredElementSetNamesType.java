/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2008 - 2019, Geomatys
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

package org.geotoolkit.csw.xml.v300;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour RequiredElementSetNamesType.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="RequiredElementSetNamesType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="brief"/>
 *     &lt;enumeration value="summary"/>
 *     &lt;enumeration value="full"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "RequiredElementSetNamesType")
@XmlEnum
public enum RequiredElementSetNamesType {

    @XmlEnumValue("brief")
    BRIEF("brief"),
    @XmlEnumValue("summary")
    SUMMARY("summary"),
    @XmlEnumValue("full")
    FULL("full");
    private final String value;

    RequiredElementSetNamesType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RequiredElementSetNamesType fromValue(String v) {
        for (RequiredElementSetNamesType c: RequiredElementSetNamesType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
