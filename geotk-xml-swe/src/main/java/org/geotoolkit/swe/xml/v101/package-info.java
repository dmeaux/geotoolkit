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
@jakarta.xml.bind.annotation.XmlSchema(namespace = "http://www.opengis.net/swe/1.0.1", elementFormDefault = jakarta.xml.bind.annotation.XmlNsForm.QUALIFIED,
xmlns = { @jakarta.xml.bind.annotation.XmlNs(prefix = "gml", namespaceURI= "http://www.opengis.net/gml"),
          @jakarta.xml.bind.annotation.XmlNs(prefix = "om", namespaceURI= "http://www.opengis.net/om/1.0"),
          @jakarta.xml.bind.annotation.XmlNs(prefix = "swe", namespaceURI= "http://www.opengis.net/swe/1.0.1"),
          @jakarta.xml.bind.annotation.XmlNs(prefix = "xlink", namespaceURI= "http://www.w3.org/1999/xlink")})
@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(RS_Identifier.class)
})
package org.geotoolkit.swe.xml.v101;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import org.apache.sis.xml.bind.referencing.RS_Identifier;
