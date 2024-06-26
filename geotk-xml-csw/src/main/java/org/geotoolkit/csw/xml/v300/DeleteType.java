/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2008 - 2019, Geomatys
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

package org.geotoolkit.csw.xml.v300;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import org.geotoolkit.csw.xml.Delete;


/**
 *
 *             Deletes one or more catalogue items that satisfy some set of
 *             conditions.
 *
 *
 * <p>Classe Java pour DeleteType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="DeleteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/cat/csw/3.0}Constraint"/>
 *       &lt;/sequence>
 *       &lt;attribute name="typeName" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *       &lt;attribute name="handle" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteType", propOrder = {
    "constraint"
})
public class DeleteType implements Delete {

    @XmlElement(name = "Constraint", required = true)
    protected QueryConstraintType constraint;
    @XmlAttribute(name = "typeName")
    protected QName typeName;
    @XmlAttribute(name = "handle")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String handle;

    public DeleteType() {

    }

    public DeleteType(final QName typeName, final QueryConstraintType constraint) {
        this.typeName   = typeName;
        this.constraint = constraint;
    }

    /**
     * Obtient la valeur de la propriété constraint.
     */
    @Override
    public QueryConstraintType getConstraint() {
        return constraint;
    }

    /**
     * Définit la valeur de la propriété constraint.
     */
    public void setConstraint(QueryConstraintType value) {
        this.constraint = value;
    }

    /**
     * Obtient la valeur de la propriété typeName.
     */
    @Override
    public QName getTypeName() {
        return typeName;
    }

    /**
     * Définit la valeur de la propriété typeName.
     */
    public void setTypeName(QName value) {
        this.typeName = value;
    }

    /**
     * Obtient la valeur de la propriété handle.
     */
    @Override
    public String getHandle() {
        return handle;
    }

    /**
     * Définit la valeur de la propriété handle.
     */
    public void setHandle(String value) {
        this.handle = value;
    }
}
