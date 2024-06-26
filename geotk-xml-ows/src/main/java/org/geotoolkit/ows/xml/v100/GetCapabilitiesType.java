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
package org.geotoolkit.ows.xml.v100;

import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.ows.xml.AbstractGetCapabilities;
import org.apache.sis.util.Version;


/**
 * XML encoded GetCapabilities operation request.
 * This operation allows clients to retrieve service metadata about a specific service instance.
 * In this XML encoding, no "request" parameter is included,
 * since the element name specifies the specific operation.
 * This base type shall be extended by each specific OWS to include the additional required "service" attribute,
 * with the correct value for that OWS.
 *
 * <p>Java class for GetCapabilitiesType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GetCapabilitiesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AcceptVersions" type="{http://www.opengis.net/ows}AcceptVersionsType" minOccurs="0"/>
 *         &lt;element name="Sections" type="{http://www.opengis.net/ows}SectionsType" minOccurs="0"/>
 *         &lt;element name="AcceptFormats" type="{http://www.opengis.net/ows}AcceptFormatsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="updateSequence" type="{http://www.opengis.net/ows}UpdateSequenceType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetCapabilitiesType", propOrder = {
    "acceptVersions",
    "sections",
    "acceptFormats"
})
public class GetCapabilitiesType implements AbstractGetCapabilities {

    @XmlElement(name = "AcceptVersions")
    private AcceptVersionsType acceptVersions;
    @XmlElement(name = "Sections")
    private SectionsType sections;
    @XmlElement(name = "AcceptFormats")
    private AcceptFormatsType acceptFormats;
    @XmlAttribute
    private String updateSequence;

    @XmlAttribute
    private String service;

    /**
     * Empty constructor used by JAXB.
     */
    public GetCapabilitiesType(){
    }

    public GetCapabilitiesType(final String service){
        this.service = service;
    }

    /**
     * Build a new GetCapabilities base request.
     */
    public GetCapabilitiesType(final AcceptVersionsType acceptVersions, final SectionsType sections,
            final AcceptFormatsType acceptFormats, final String updateSequence, final String service){
        this.acceptFormats  = acceptFormats;
        this.acceptVersions = acceptVersions;
        this.sections       = sections;
        this.updateSequence = updateSequence;
        this.service        = service;
    }

    /**
     * Gets the value of the acceptVersions property.
     */
    @Override
    public AcceptVersionsType getAcceptVersions() {
        return acceptVersions;
    }

    public void setAcceptVersions(final AcceptVersionsType acceptVersions) {
        this.acceptVersions = acceptVersions;
    }

    /**
     * Gets the value of the sections property.
     */
    @Override
    public SectionsType getSections() {
        return sections;
    }

    public void setSections(final SectionsType sections) {
        this.sections = sections;
    }

    /**
     * Return true if the request contains the specified section.
     *
     * @param sectionName The name of the searched section.
     * @return true if the request contains the specified section.
     */
    @Override
    public boolean containsSection(final String sectionName) {
        if (sections != null) {
            return sections.containsSection(sectionName);
        }
        return false;
    }

   /**
    * Gets the value of the acceptFormats property.
    */
    @Override
    public AcceptFormatsType getAcceptFormats() {
        return acceptFormats;
    }

    public void setAcceptFormats(final AcceptFormatsType acceptFormats) {
        this.acceptFormats = acceptFormats;
    }

    /**
    * Return the first outputFormat of the is if there is one
    */
    @Override
    public String getFirstAcceptFormat() {
        if (acceptFormats != null) {
            if (acceptFormats.getOutputFormat().size() > 0) {
                return acceptFormats.getOutputFormat().get(0);
            }
        }
        return null;
    }

    /**
    * Return the first outputFormat of the is if there is one
    */
    public String getOutputFormat() {
        if (acceptFormats != null) {
            if (acceptFormats.getOutputFormat().size() > 0) {
                return acceptFormats.getOutputFormat().get(0);
            }
        }
        return null;
    }

    /**
    * Return the first outputFormat of the is if there is one
    */
    public void setOutputFormat(final String outputFormat) {
        acceptFormats = new AcceptFormatsType(outputFormat);
    }

    /**
     * Gets the value of the updateSequence property.
     */
    @Override
    public String getUpdateSequence() {
        return updateSequence;
    }

    public void setUpdateSequence(final String updateSequence) {
        this.updateSequence = updateSequence;
    }

    /**
     * inherited method from AbstractGetCapabilties
     */
    @Override
    public Version getVersion() {
        if (acceptVersions!= null && !acceptVersions.getVersion().isEmpty()) {
            return new Version(acceptVersions.getVersion().get(0));
        } return null;
    }

    @Override
    public void setVersion(final String version) {
        if (version != null) {
            if (acceptVersions == null) {
                this.acceptVersions = new AcceptVersionsType(version);
            } else {
                 this.acceptVersions.addFirstVersion(version);
            }
        }
    }
    /**
     * Gets the value of the service property.
     */
    @Override
    public String getService() {
        return service;
    }

    @Override
    public void setService(final String service) {
        this.service = service;
    }

    /**
     * Verify that this entry is identical to the specified object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof GetCapabilitiesType) {
            final GetCapabilitiesType that = (GetCapabilitiesType) object;
            return Objects.equals(this.acceptFormats,  that.acceptFormats)  &&
                   Objects.equals(this.acceptVersions, that.acceptVersions) &&
                   Objects.equals(this.sections,       that.sections)       &&
                   Objects.equals(this.service,        that.service)        &&
                   Objects.equals(this.updateSequence, that.updateSequence);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.acceptVersions != null ? this.acceptVersions.hashCode() : 0);
        hash = 73 * hash + (this.sections != null ? this.sections.hashCode() : 0);
        hash = 73 * hash + (this.acceptFormats != null ? this.acceptFormats.hashCode() : 0);
        hash = 73 * hash + (this.updateSequence != null ? this.updateSequence.hashCode() : 0);
        hash = 73 * hash + (this.service != null ? this.service.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[").append(this.getClass().getSimpleName()).append("]\n");
        if (service != null) {
            sb.append("service:").append(service).append('\n');
        }
        if (updateSequence != null) {
            sb.append("updateSequence:").append(updateSequence).append('\n');
        }
        if (acceptVersions != null) {
            sb.append("acceptVersions:").append(acceptVersions).append('\n');
        }
        if (sections != null) {
            sb.append("sections:").append(sections).append('\n');
        }
        if (acceptFormats != null) {
            sb.append("acceptFormats:").append(acceptFormats).append('\n');
        }
        return sb.toString();
    }
}
