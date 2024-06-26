/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2019, Geomatys
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


package org.geotoolkit.dif.xml.v102;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 *
 *                 NOTE: The /DIF/Personnel field not /DIF/Organization/Personnel
 *
 *                 Defines the point of contact for the data set or the metadata.
 *                 The contact personnel are defined by the Role, which include:
 *                 Investigator: The person who headed the investigation or experiment that resulted in the acquisition of the data described (i.e., Principal Investigator, Experiment Team Leader)
 *                 Technical Contact: The person who is knowledgeable about the technical content of the data (quality, processing methods, units, available software for further processing)
 *                 Metadata Author: The person who is responsible for the content of the Metadata. If the responsibility shifts from the original author to another person, the Metadata Author field should be updated to the new responsible person.
 *
 *                 Changes:
 *                 Personnel field was changed by moving all fields but Role to a
 *                 subfield called Contact_Person or Contact_Group which is shared with the
 *                 Organization field.
 *
 *                 | DIF 9 | ECHO 10      |UMM               | DIF 10           | Note                                        |
 *                 | ----- | ------------ | ---------------- | ---------------- | ------------------------------------------- |
 *                 | Role  | Job_Position | Role             | Role             | no change                                   |
 *                 |   -   |       -      |         -        | Contact_Person/* | All Fields but Role moved to Contact_Person |
 *
 *
 *
 * <p>Classe Java pour PersonnelType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="PersonnelType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Role" type="{http://gcmd.gsfc.nasa.gov/Aboutus/xml/dif/}PersonnelRoleEnum" maxOccurs="unbounded"/>
 *         &lt;choice>
 *           &lt;element name="Contact_Person" type="{http://gcmd.gsfc.nasa.gov/Aboutus/xml/dif/}ContactPersonType" maxOccurs="unbounded"/>
 *           &lt;element name="Contact_Group" type="{http://gcmd.gsfc.nasa.gov/Aboutus/xml/dif/}ContactGroupType" maxOccurs="unbounded"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonnelType", propOrder = {
    "role",
    "contactPerson",
    "contactGroup"
})
public class PersonnelType {

    @XmlElement(name = "Role", required = true)
    @XmlSchemaType(name = "string")
    protected List<PersonnelRoleEnum> role;
    @XmlElement(name = "Contact_Person")
    protected List<ContactPersonType> contactPerson;
    @XmlElement(name = "Contact_Group")
    protected List<ContactGroupType> contactGroup;

    /**
     * Gets the value of the role property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the role property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRole().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonnelRoleEnum }
     *
     *
     */
    public List<PersonnelRoleEnum> getRole() {
        if (role == null) {
            role = new ArrayList<PersonnelRoleEnum>();
        }
        return this.role;
    }

    /**
     * Gets the value of the contactPerson property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactPerson property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactPerson().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactPersonType }
     *
     *
     */
    public List<ContactPersonType> getContactPerson() {
        if (contactPerson == null) {
            contactPerson = new ArrayList<ContactPersonType>();
        }
        return this.contactPerson;
    }

    /**
     * Gets the value of the contactGroup property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactGroup property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactGroup().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactGroupType }
     *
     *
     */
    public List<ContactGroupType> getContactGroup() {
        if (contactGroup == null) {
            contactGroup = new ArrayList<ContactGroupType>();
        }
        return this.contactGroup;
    }

}
