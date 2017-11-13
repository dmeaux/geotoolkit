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
package org.geotoolkit.wfs.xml.v100;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import org.apache.sis.util.Version;
import org.geotoolkit.wfs.xml.DescribeFeatureType;


/**
 * The DescribeFeatureType operation allows a client application
 * to request that a Web Feature Service describe one or more
 * feature types.
 * A Web Feature Service must be able to generate
 * feature descriptions as valid GML2 application schemas.
 *
 * The schemas generated by the DescribeFeatureType operation can
 * be used by a client application to validate the output.
 *
 * Feature instances within the WFS interface must be specified
 * using GML2.  The schema of feature instances specified within
 * the WFS interface must validate against the feature schemas
 * generated by the DescribeFeatureType request.
 *
 *
 * <p>Java class for DescribeFeatureTypeType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DescribeFeatureTypeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TypeName" type="{http://www.w3.org/2001/XMLSchema}QName" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="1.0.0" />
 *       &lt;attribute name="service" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="WFS" />
 *       &lt;attribute name="outputFormat" type="{http://www.w3.org/2001/XMLSchema}string" default="XMLSCHEMA" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescribeFeatureTypeType", propOrder = {
    "typeName"
})
@XmlRootElement(name = "DescribeFeatureType")
public class DescribeFeatureTypeType implements DescribeFeatureType {

    @XmlElement(name = "TypeName")
    private List<QName> typeName;
    @XmlAttribute(required = true)
    private String version;
    @XmlAttribute(required = true)
    private String service;
    @XmlAttribute
    private String outputFormat;

    @XmlTransient
    private Map<String, String> prefixMapping;

    public DescribeFeatureTypeType() {

    }

    public DescribeFeatureTypeType(final String service, final String version, final List<QName> typeName, final String outputFormat) {
        this.service      = service;
        this.version      = version;
        this.outputFormat = outputFormat;
        this.typeName     = typeName;
    }

    /**
     * Gets the value of the typeName property.
     *
     */
    @Override
    public List<QName> getTypeName() {
        if (typeName == null) {
            typeName = new ArrayList<QName>();
        }
        return this.typeName;
    }

    /**
     * Gets the value of the version property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Override
    public Version getVersion() {
        if (version == null) {
            return new Version("1.0.0");
        } else {
            return new Version(version);
        }
    }

    /**
     * Sets the value of the version property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Override
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the service property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Override
    public String getService() {
        if (service == null) {
            return "WFS";
        } else {
            return service;
        }
    }

    /**
     * Sets the value of the service property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Override
    public void setService(String value) {
        this.service = value;
    }

    /**
     * Gets the value of the outputFormat property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Override
    public String getOutputFormat() {
        if (outputFormat == null) {
            return "XMLSCHEMA";
        } else {
            return outputFormat;
        }
    }

    /**
     * Sets the value of the outputFormat property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Override
    public void setOutputFormat(String value) {
        this.outputFormat = value;
    }

    @Override
    public String getHandle() {
        return null; // not implemented in 1.0.0 version
    }

    @Override
    public void setHandle(String value) {
        // do nothing
    }

    @Override
    public Map<String, String> getPrefixMapping() {
        return prefixMapping;
    }

    @Override
    public void setPrefixMapping(Map<String, String> prefixMapping) {
        this.prefixMapping = prefixMapping;
    }

}
