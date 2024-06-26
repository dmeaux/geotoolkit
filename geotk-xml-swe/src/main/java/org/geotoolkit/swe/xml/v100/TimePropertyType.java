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
package org.geotoolkit.swe.xml.v100;

import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.swe.xml.AbstractTimeProperty;


/**
 * Time is a data-type so usually appears "by value" rather than by reference.
 *
 * <p>Java class for TimePropertyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TimePropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/swe/1.0}Time"/>
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
@XmlType(name = "TimePropertyType", propOrder = {
    "time"
})
public class TimePropertyType implements AbstractTimeProperty {

    @XmlElement(name = "Time", required = true)
    private TimeType time;

    public TimePropertyType() {

    }

    public TimePropertyType(final TimeType time) {
        this.time = time;
    }

    public TimePropertyType(final AbstractTimeProperty tp) {
        if (tp != null && tp.getTime() != null) {
            this.time = new TimeType(tp.getTime());
        }
    }

    /**
     * Gets the value of the time property.
     */
    public TimeType getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     */
    public void setTime(final TimeType value) {
        this.time = value;
    }

    /**
     * Verify if this entry is identical to specified object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }

        if (object instanceof TimePropertyType && super.equals(object)) {
            final TimePropertyType  that = (TimePropertyType) object;
            return Objects.equals(this.time, that.time);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.time != null ? this.time.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(super.toString());
        if (time != null) {
            s.append("time:").append(time).append('\n');
        }
        return s.toString();
    }

}
