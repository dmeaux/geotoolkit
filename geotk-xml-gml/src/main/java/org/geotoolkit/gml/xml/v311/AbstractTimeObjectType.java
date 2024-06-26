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
package org.geotoolkit.gml.xml.v311;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.apache.sis.metadata.MetadataStandard;
import org.apache.sis.metadata.simple.SimpleCitation;
import org.geotoolkit.gml.xml.AbstractGML;


/**
 * The abstract supertype for temporal objects.
 *
 * <p>Java class for AbstractTimeObjectType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AbstractTimeObjectType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGMLType">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractTimeObjectType")
@XmlSeeAlso({
    AbstractTimePrimitiveType.class,
    AbstractTimeComplexType.class
})
public abstract class AbstractTimeObjectType extends AbstractGMLType {

    public AbstractTimeObjectType() {

    }

    public AbstractTimeObjectType(final String id) {
        super(id);
    }

    public AbstractTimeObjectType(final AbstractGML that) {
        super(that);
    }

    @Override
    public MetadataStandard getStandard() {
        return new MetadataStandard(new SimpleCitation("Temporal"), Package.getPackage("org.opengis.temporal"));
    }
}
