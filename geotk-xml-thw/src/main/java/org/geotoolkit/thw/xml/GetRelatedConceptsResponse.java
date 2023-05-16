/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2015, Geomatys
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
package org.geotoolkit.thw.xml;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.skos.xml.Concept;


/**
 * <p>Java class for getRelatedConceptsResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="getRelatedConceptsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.w3.org/2004/02/skos/core#}Concept" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRelatedConceptsResponse", propOrder = {
    "response"
})
@XmlRootElement(name = "GetRelatedConceptsResponse", namespace = "http://ws.geotk.org/")
public class GetRelatedConceptsResponse {

    @XmlElement(name = "return")
    private List<Concept> response;

    public GetRelatedConceptsResponse() {

    }

    public GetRelatedConceptsResponse(List<Concept> response) {
        this.response = response;
    }

    /**
     * Gets the value of the return property.
     */
    public List<Concept> getReturn() {
        if (response == null) {
            response = new ArrayList<Concept>();
        }
        return this.response;
    }

}
