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
package org.geotoolkit.sml.xml.v100;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.sml.xml.AbstractProcessModel;


/**
 * Complex Type for atomic processes
 *
 * <p>Java class for ProcessModelType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ProcessModelType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/sensorML/1.0}AbstractPureProcessType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/sensorML/1.0}method"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessModelType", propOrder = {
    "method"
})
public class ProcessModelType extends AbstractPureProcessType implements AbstractProcessModel {

    @XmlElement(required = true)
    private MethodPropertyType method;

    public ProcessModelType() {

    }

    public ProcessModelType(final AbstractProcessModel pm) {
        super(pm);
        if (pm != null && pm.getMethod() != null) {
            this.method = new MethodPropertyType(pm.getMethod());
        }
    }

    /**
     * Gets the value of the method property.
     */
    public MethodPropertyType getMethod() {
        return method;
    }

    /**
     * Sets the value of the method property.
     */
    public void setMethod(final MethodPropertyType value) {
        this.method = value;
    }

}
