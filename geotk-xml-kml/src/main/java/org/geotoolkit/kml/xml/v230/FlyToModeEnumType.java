/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2023, Geomatys
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

package org.geotoolkit.kml.xml.v230;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour flyToModeEnumType.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="flyToModeEnumType">
 *   &lt;restriction base="{http://www.opengis.net/kml/2.2}enumBaseType">
 *     &lt;enumeration value="bounce"/>
 *     &lt;enumeration value="smooth"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "flyToModeEnumType", namespace = "http://www.opengis.net/kml/2.2")
@XmlEnum
public enum FlyToModeEnumType {

    @XmlEnumValue("bounce")
    BOUNCE("bounce"),
    @XmlEnumValue("smooth")
    SMOOTH("smooth");
    private final String value;

    FlyToModeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FlyToModeEnumType fromValue(String v) {
        for (FlyToModeEnumType c: FlyToModeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
