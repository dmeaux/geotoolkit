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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.geotoolkit.csw.xml.DomainValues;


/**
 * <p>Classe Java pour DomainValuesType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="DomainValuesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="ValueReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="ParameterName" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;/choice>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="ListOfValues" type="{http://www.opengis.net/cat/csw/3.0}ListOfValuesType"/>
 *           &lt;element name="ConceptualScheme" type="{http://www.opengis.net/cat/csw/3.0}ConceptualSchemeType" maxOccurs="unbounded"/>
 *           &lt;element name="RangeOfValues" type="{http://www.opengis.net/cat/csw/3.0}RangeOfValuesType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *       &lt;attribute name="resultType" use="required" type="{http://www.opengis.net/cat/csw/3.0}ResultTypeType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DomainValuesType", propOrder = {
    "valueReference",
    "parameterName",
    "listOfValues",
    "conceptualScheme",
    "rangeOfValues"
})
public class DomainValuesType implements DomainValues {

    @XmlElement(name = "ValueReference")
    protected String valueReference;
    @XmlElement(name = "ParameterName")
    @XmlSchemaType(name = "anyURI")
    protected String parameterName;
    @XmlElement(name = "ListOfValues")
    protected ListOfValuesType listOfValues;
    @XmlElement(name = "ConceptualScheme")
    protected List<ConceptualSchemeType> conceptualScheme;
    @XmlElement(name = "RangeOfValues")
    protected RangeOfValuesType rangeOfValues;
    @XmlAttribute(name = "type", required = true)
    protected QName type;
    @XmlAttribute(name = "resultType", required = true)
    protected ResultTypeType resultType;

    /**
     * An empty constructor used by JAXB
     */
    public DomainValuesType() {

    }

    /**
     * Build a new Domain values type with the specified list Of Values.
     * One of parameterName or valueReference must be null.
     *
     */
    public DomainValuesType(final String parameterName, final String valueReference, final ListOfValuesType listOfValues, final QName type) {

        if (valueReference != null && parameterName != null) {
            throw new IllegalArgumentException("One of valueReference or parameterName must be null");
        } else if (valueReference == null && parameterName == null) {
            throw new IllegalArgumentException("One of valueReference or parameterName must be filled");
        }
        this.valueReference = valueReference;
        this.parameterName  = parameterName;
        this.listOfValues   = listOfValues;
        this.type           = type;
    }

    /**
     * Build a new Domain values type with the specified list Of Values.
     * One of parameterName or valueReference must be null.
     */
    public DomainValuesType(final String parameterName, final String valueReference, final List<Object> listOfValues, final QName type) {

        if (valueReference != null && parameterName != null) {
            throw new IllegalArgumentException("One of valueReference or parameterName must be null");
        } else if (valueReference == null && parameterName == null) {
            throw new IllegalArgumentException("One of valueReference or parameterName must be filled");
        }
        this.valueReference  = valueReference;
        this.parameterName = parameterName;
        this.listOfValues  = new ListOfValuesType(listOfValues);
        this.type          = type;
    }

    /**
     * Obtient la valeur de la propriété valueReference.
     */
    public String getValueReference() {
        return valueReference;
    }

    @Override
    public String getPropertyName() {
        return valueReference;
    }

    /**
     * Définit la valeur de la propriété valueReference.
     */
    public void setValueReference(String value) {
        this.valueReference = value;
    }

    /**
     * Obtient la valeur de la propriété parameterName.
     */
    @Override
    public String getParameterName() {
        return parameterName;
    }

    /**
     * Définit la valeur de la propriété parameterName.
     */
    public void setParameterName(String value) {
        this.parameterName = value;
    }

    /**
     * Obtient la valeur de la propriété listOfValues.
     */
    @Override
    public ListOfValuesType getListOfValues() {
        return listOfValues;
    }

    /**
     * Définit la valeur de la propriété listOfValues.
     */
    public void setListOfValues(ListOfValuesType value) {
        this.listOfValues = value;
    }

    /**
     * Gets the value of the conceptualScheme property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conceptualScheme property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConceptualScheme().add(newItem);
     * </pre>
     */
    public List<ConceptualSchemeType> getConceptualScheme() {
        if (conceptualScheme == null) {
            conceptualScheme = new ArrayList<>();
        }
        return this.conceptualScheme;
    }

    /**
     * Obtient la valeur de la propriété rangeOfValues.
     */
    public RangeOfValuesType getRangeOfValues() {
        return rangeOfValues;
    }

    /**
     * Définit la valeur de la propriété rangeOfValues.
     */
    public void setRangeOfValues(RangeOfValuesType value) {
        this.rangeOfValues = value;
    }

    /**
     * Obtient la valeur de la propriété type.
     */
    @Override
    public QName getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     */
    public void setType(QName value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété resultType.
     */
    public ResultTypeType getResultType() {
        return resultType;
    }

    /**
     * Définit la valeur de la propriété resultType.
     */
    public void setResultType(ResultTypeType value) {
        this.resultType = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[DomainValuesType]").append('\n');
        if (conceptualScheme != null) {
            sb.append("conceptualScheme:").append(conceptualScheme).append('\n');
        }
        if (listOfValues != null) {
            sb.append("listOfValues:").append(listOfValues).append('\n');
        }
        if (parameterName != null) {
            sb.append("parameterName:").append(parameterName).append('\n');
        }
        if (valueReference != null) {
            sb.append("valueReference:").append(valueReference).append('\n');
        }
        if (rangeOfValues != null) {
            sb.append("rangeOfValues:").append(rangeOfValues).append('\n');
        }
        if (type != null) {
            sb.append("type:").append(type).append('\n');
        }
        if (resultType != null) {
            sb.append("resultType:").append(resultType).append('\n');
        }
        return sb.toString();
    }

    /**
     * Verify if this entry is identical to the specified object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof DomainValuesType) {
            final DomainValuesType that = (DomainValuesType) object;

            return  Objects.equals(this.conceptualScheme, that.conceptualScheme) &&
                    Objects.equals(this.listOfValues,     that.listOfValues) &&
                    Objects.equals(this.parameterName,    that.parameterName) &&
                    Objects.equals(this.valueReference,   that.valueReference) &&
                    Objects.equals(this.rangeOfValues,    that.rangeOfValues) &&
                    Objects.equals(this.type,             that.type) &&
                    Objects.equals(this.resultType,       that.resultType);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.valueReference != null ? this.valueReference.hashCode() : 0);
        hash = 97 * hash + (this.parameterName != null ? this.parameterName.hashCode() : 0);
        hash = 97 * hash + (this.listOfValues != null ? this.listOfValues.hashCode() : 0);
        hash = 97 * hash + (this.conceptualScheme != null ? this.conceptualScheme.hashCode() : 0);
        hash = 97 * hash + (this.rangeOfValues != null ? this.rangeOfValues.hashCode() : 0);
        hash = 97 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 97 * hash + (this.resultType != null ? this.resultType.hashCode() : 0);
        return hash;
    }
}
