/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2021, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotoolkit.feature.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.geotoolkit.atom.xml.Link;

/**
 * @author Rohan FERRE (Geomatys)
 */
@XmlRootElement(name = "Collection")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Collection {

    @XmlElement(name = "Id")
    private String id;
    @XmlElement(name = "Title")
    private String title;
    @XmlElement(name = "Description")
    private String description;
    @XmlElement(name = "link", namespace = "http://www.w3.org/2005/Atom")
    private List<Link> links;
    @XmlElement(name = "Extent")
    private Extent extent;
    private String itemType = "feature";
    private List<String> crs;

    public Collection() {
        links = new ArrayList<>();
        extent = new Extent();
        crs = new ArrayList<>();
        crs.add("http://www.opengis.net/def/crs/OGC/1.3/CRS84");
    }

    public Collection(String id, String title, String description, List<Link> links, Extent extent, String itemType) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.links = links;
        this.extent = extent;
        this.itemType = itemType;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the array list of links
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * @param links the array list of links to set
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * @return the extent
     */
    public Extent getExtent() {
        return extent;
    }

    /**
     * @param extent the extent to set
     */
    public void setExtent(Extent extent) {
        this.extent = extent;
    }

    /**
     * @return the itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType the itemType
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * @return the array list of coordinate reference systems supported by the
     * service
     */
    public List<String> getCrs() {
        return crs;
    }

    /**
     * @param crs the array list to set
     */
    public void setCrs(List<String> crs) {
        this.crs = crs;
    }

    public void setXMLBBoxMode() {
        if (this.extent != null && this.extent.getSpatial() != null) {
            Spatial spa = this.extent.getSpatial();
            spa.setLowerCorner();
            spa.setUpperCorner();
            spa.getBbox().clear();
        }
    }
}
