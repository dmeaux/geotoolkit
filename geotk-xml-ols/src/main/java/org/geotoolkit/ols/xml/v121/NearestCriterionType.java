/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2011, Geomatys
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

package org.geotoolkit.ols.xml.v121;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NearestCriterionType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NearestCriterionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Proximity"/>
 *     &lt;enumeration value="Fastest"/>
 *     &lt;enumeration value="Shortest"/>
 *     &lt;enumeration value="Easiest"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "NearestCriterionType")
@XmlEnum
public enum NearestCriterionType {

    @XmlEnumValue("Proximity")
    PROXIMITY("Proximity"),
    @XmlEnumValue("Fastest")
    FASTEST("Fastest"),
    @XmlEnumValue("Shortest")
    SHORTEST("Shortest"),
    @XmlEnumValue("Easiest")
    EASIEST("Easiest");
    private final String value;

    NearestCriterionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NearestCriterionType fromValue(String v) {
        for (NearestCriterionType c: NearestCriterionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
