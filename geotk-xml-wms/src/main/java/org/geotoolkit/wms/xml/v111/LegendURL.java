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
package org.geotoolkit.wms.xml.v111;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.wms.xml.AbstractLegendURL;


/**
 * <p>Java class for anonymous complex type.
 *
 * @author Guilhem Legal
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "format",
    "onlineResource"
})
@XmlRootElement(name = "LegendURL")
public class LegendURL implements AbstractLegendURL {

    @XmlElement(name = "Format", required = true)
    private String format;
    @XmlElement(name = "OnlineResource", required = true)
    private OnlineResource onlineResource;
    @XmlAttribute
    @XmlSchemaType(name = "positiveInteger")
    private Integer width;
    @XmlAttribute
    @XmlSchemaType(name = "positiveInteger")
    private Integer height;

    /**
     * An empty constructor used by JAXB.
     */
     LegendURL() {
     }

     /**
     * Build a legend URL without specifyng the dimensions.
     */
     public LegendURL(final String format, final OnlineResource onlineResource) {
         this.format         = format;
         this.onlineResource = onlineResource;
     }

    /**
     * Build a new legend url object.
     */
    public LegendURL(final String format, final OnlineResource onlineResource, final Integer width,
            final Integer height) {
        this.format         = format;
        this.height         = height;
        this.onlineResource = onlineResource;
        this.width          = width;
    }

    /**
     * Build a new Contact person primary object.
     */
    public LegendURL(final AbstractLegendURL that) {
        if (that != null) {
            this.format         = that.getFormat();
            if (that.getOnlineResource() != null) {
                this.onlineResource = new OnlineResource(onlineResource);
            }
            this.height = that.getHeight();
            this.width  = that.getWidth();
        }
    }

    /**
     * Gets the value of the format property.
     *
     */
    @Override
    public String getFormat() {
        return format;
    }

    /**
     * Gets the value of the onlineResource property.
     *
     */
    @Override
    public OnlineResource getOnlineResource() {
        return onlineResource;
    }

    /**
     * Gets the value of the width property.
     */
    @Override
    public Integer getWidth() {
        return width;
    }

    /**
     * Gets the value of the height property.
     *
     */
    @Override
    public Integer getHeight() {
        return height;
    }
}
