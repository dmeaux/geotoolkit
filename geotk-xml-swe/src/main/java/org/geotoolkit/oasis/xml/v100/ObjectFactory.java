/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2012, Geomatys
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

package org.geotoolkit.oasis.xml.v100;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.oasis_open.docs.wsn.t_1 package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TopicNamespace_QNAME = new QName("http://docs.oasis-open.org/wsn/t-1", "TopicNamespace");
    private final static QName _TopicSet_QNAME = new QName("http://docs.oasis-open.org/wsn/t-1", "TopicSet");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.oasis_open.docs.wsn.t_1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TopicNamespaceType.Topic }
     *
     */
    public TopicNamespaceType.Topic createTopicNamespaceTypeTopic() {
        return new TopicNamespaceType.Topic();
    }

    /**
     * Create an instance of {@link Documentation }
     *
     */
    public Documentation createDocumentation() {
        return new Documentation();
    }

    /**
     * Create an instance of {@link TopicNamespaceType }
     *
     */
    public TopicNamespaceType createTopicNamespaceType() {
        return new TopicNamespaceType();
    }

    /**
     * Create an instance of {@link TopicSetType }
     *
     */
    public TopicSetType createTopicSetType() {
        return new TopicSetType();
    }

    /**
     * Create an instance of {@link TopicType }
     *
     */
    public TopicType createTopicType() {
        return new TopicType();
    }

    /**
     * Create an instance of {@link QueryExpressionType }
     *
     */
    public QueryExpressionType createQueryExpressionType() {
        return new QueryExpressionType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopicNamespaceType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsn/t-1", name = "TopicNamespace")
    public JAXBElement<TopicNamespaceType> createTopicNamespace(TopicNamespaceType value) {
        return new JAXBElement<TopicNamespaceType>(_TopicNamespace_QNAME, TopicNamespaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopicSetType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsn/t-1", name = "TopicSet")
    public JAXBElement<TopicSetType> createTopicSet(TopicSetType value) {
        return new JAXBElement<TopicSetType>(_TopicSet_QNAME, TopicSetType.class, null, value);
    }

}
