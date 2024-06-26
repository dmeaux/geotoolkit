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
package org.geotoolkit.sos.xml.v100;

import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.swes.xml.InsertSensorResponse;


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
 *         &lt;element name="AssignedSensorId" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 * @author Guilhem Legal
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisterSensorResponse", propOrder = {
    "assignedSensorId"
})
@XmlRootElement(name = "RegisterSensorResponse")
public class RegisterSensorResponse extends ResponseBaseType implements InsertSensorResponse {

    @XmlElement(name = "AssignedSensorId", required = true)
    @XmlSchemaType(name = "anyURI")
    private String assignedSensorId;

    /**
     * An empty constructor used by JAXB.
     */
    RegisterSensorResponse () {}

    /**
     * Build a new response with the specified sensor ID.
     *
     * @param assignedSensorId The id of the sensor whitch have been inserted
     * previously.
     */
    public RegisterSensorResponse(final String assignedSensorId) {
        this.assignedSensorId = assignedSensorId;
    }

    /**
     * Gets the value of the assignedSensorId property.
     */
    @Override
    public String getAssignedProcedure() {
        return assignedSensorId;
    }

    @Override
    public String getAssignedOffering() {
        return null; // no assigned offering in SOS 1.0.0
    }

    /**
     * Verify if this entry is identical to the specified object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof RegisterSensorResponse) {
            final RegisterSensorResponse that = (RegisterSensorResponse) object;
            return Objects.equals(this.assignedSensorId, that.assignedSensorId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = assignedSensorId.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "RegisterSensorResponse: assignedSensorId=" + assignedSensorId;
    }
}
