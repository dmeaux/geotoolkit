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

import java.util.Objects;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.geotoolkit.gml.xml.SurfaceProperty;


/**
 * A property that has a surface as its value domain can either be an appropriate geometry element encapsulated in an element of this type or an XLink reference to a remote geometry element (where remote includes geometry elements located elsewhere in the same document). Either the reference or the contained element must be given, but neither both nor none.
 *
 * <p>Java class for SurfacePropertyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SurfacePropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element ref="{http://www.opengis.net/gml}AbstractSurface"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opengis.net/gml}AssociationAttributeGroup"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 * @module
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SurfacePropertyType", propOrder = {
    "abstractSurface"
})
public class SurfacePropertyType implements SurfaceProperty {

    @XmlElementRef(name = "AbstractSurface", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    private JAXBElement<? extends AbstractSurfaceType> abstractSurface;
    @XmlAttribute(namespace = "http://www.opengis.net/gml")
    @XmlSchemaType(name = "anyURI")
    private String remoteSchema;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    private String type;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    private String href;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    private String role;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    private String arcrole;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    private String title;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    private String show;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    private String actuate;

    public SurfacePropertyType() {

    }

    public SurfacePropertyType(final AbstractSurfaceType surface) {
        final ObjectFactory factory = new ObjectFactory();
        if (surface instanceof OrientableSurfaceType) {
            this.abstractSurface = factory.createOrientableSurface((OrientableSurfaceType)surface);
        } else if (surface instanceof TriangulatedSurfaceType) {
            this.abstractSurface = factory.createTriangulatedSurface((TriangulatedSurfaceType)surface);
        } else if (surface instanceof PolyhedralSurfaceType) {
            this.abstractSurface = factory.createPolyhedralSurface((PolyhedralSurfaceType)surface);
        } else if (surface instanceof PolygonType) {
            this.abstractSurface = factory.createPolygon((PolygonType)surface);
        } else if (surface instanceof TinType) {
            this.abstractSurface = factory.createTin((TinType)surface);
        } else if (surface instanceof SurfaceType) {
            this.abstractSurface = factory.createSurface((SurfaceType)surface);
        } else if (surface instanceof AbstractSurfaceType) {
            this.abstractSurface = factory.createAbstractSurface((AbstractSurfaceType)surface);
        }
    }

    /**
     * Gets the value of the abstractSurface property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OrientableSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TriangulatedSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PolyhedralSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PolygonType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TinType }{@code >}
     *
     */
    public JAXBElement<? extends AbstractSurfaceType> getJbAbstractSurface() {
        return abstractSurface;
    }

    /**
     * Sets the value of the abstractSurface property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OrientableSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TriangulatedSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PolyhedralSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PolygonType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TinType }{@code >}
     *
     */
    public void setJbAbstractSurface(final JAXBElement<? extends AbstractSurfaceType> value) {
        this.abstractSurface = ((JAXBElement<? extends AbstractSurfaceType> ) value);
    }


    /**
     * Gets the value of the abstractSurface property.
     *
     * @return
     *     possible object is
     *     {@code <}{@link SurfaceType }{@code >}
     *     {@code <}{@link OrientableSurfaceType }{@code >}
     *     {@code <}{@link AbstractSurfaceType }{@code >}
     *     {@code <}{@link TriangulatedSurfaceType }{@code >}
     *     {@code <}{@link PolyhedralSurfaceType }{@code >}
     *     {@code <}{@link PolygonType }{@code >}
     *     {@code <}{@link TinType }{@code >}
     *
     */
    public AbstractSurfaceType getAbstractSurface() {
        if (abstractSurface != null) {
            return abstractSurface.getValue();
        }
        return null;
    }

    /**
     * Sets the value of the abstractSurface property.
     *
     * @param value
     *     allowed object is
     *     {@code <}{@link SurfaceType }{@code >}
     *     {@code <}{@link OrientableSurfaceType }{@code >}
     *     {@code <}{@link AbstractSurfaceType }{@code >}
     *     {@code <}{@link TriangulatedSurfaceType }{@code >}
     *     {@code <}{@link PolyhedralSurfaceType }{@code >}
     *     {@code <}{@link PolygonType }{@code >}
     *     {@code <}{@link TinType }{@code >}
     *
     */
    public void setAbstractSurface(AbstractSurfaceType value) {
        if (value != null) {
            final ObjectFactory factory = new ObjectFactory();
            if (value instanceof TinType) {
                this.abstractSurface = factory.createTin((TinType) value);
            } else if (value instanceof PolygonType) {
                this.abstractSurface = factory.createPolygon((PolygonType) value);
            } else if (value instanceof PolyhedralSurfaceType) {
                this.abstractSurface = factory.createPolyhedralSurface((PolyhedralSurfaceType) value);
            } else if (value instanceof TriangulatedSurfaceType) {
                this.abstractSurface = factory.createTriangulatedSurface((TriangulatedSurfaceType) value);
            } else if (value instanceof SurfaceType) {
                this.abstractSurface = factory.createSurface((SurfaceType) value);
            } else if (value instanceof OrientableSurfaceType) {
                this.abstractSurface = factory.createOrientableSurface((OrientableSurfaceType) value);
            } else if (value instanceof AbstractSurfaceType) {
                this.abstractSurface = factory.createAbstractSurface((AbstractSurfaceType) value);
            }
        } else {
            value = null;
        }
    }

    /**
     * Gets the value of the remoteSchema property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRemoteSchema() {
        return remoteSchema;
    }

    /**
     * Sets the value of the remoteSchema property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRemoteSchema(final String value) {
        this.remoteSchema = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setType(final String value) {
        this.type = value;
    }

    /**
     * Gets the value of the href property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHref(final String value) {
        this.href = value;
    }

    /**
     * Gets the value of the role property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRole(final String value) {
        this.role = value;
    }

    /**
     * Gets the value of the arcrole property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getArcrole() {
        return arcrole;
    }

    /**
     * Sets the value of the arcrole property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setArcrole(final String value) {
        this.arcrole = value;
    }

    /**
     * Gets the value of the title property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTitle(final String value) {
        this.title = value;
    }

    /**
     * Gets the value of the show property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getShow() {
        return show;
    }

    /**
     * Sets the value of the show property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setShow(final String value) {
        this.show = value;
    }

    /**
     * Gets the value of the actuate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getActuate() {
        return actuate;
    }

    /**
     * Sets the value of the actuate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setActuate(final String value) {
        this.actuate = value;
    }

    /**
     * Verify if this entry is identical to the specified object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof SurfacePropertyType) {
            final SurfacePropertyType that = (SurfacePropertyType) object;

            return Objects.equals(this.actuate,              that.actuate)          &&
                   Objects.equals(this.arcrole,              that.arcrole)          &&
                   Objects.equals(this.type,                 that.type)             &&
                   Objects.equals(this.href,                 that.href)             &&
                   Objects.equals(this.remoteSchema,         that.remoteSchema)     &&
                   Objects.equals(this.show,                 that.show)             &&
                   Objects.equals(this.role,                 that.role)             &&
                   Objects.equals(this.title,                that.title)            &&
                   Objects.equals(this.getAbstractSurface(), that.getAbstractSurface());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.remoteSchema != null ? this.remoteSchema.hashCode() : 0);
        hash = 19 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 19 * hash + (this.href != null ? this.href.hashCode() : 0);
        hash = 19 * hash + (this.role != null ? this.role.hashCode() : 0);
        hash = 19 * hash + (this.arcrole != null ? this.arcrole.hashCode() : 0);
        hash = 19 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 19 * hash + (this.show != null ? this.show.hashCode() : 0);
        hash = 19 * hash + (this.actuate != null ? this.actuate.hashCode() : 0);
        hash = 19 * hash + (this.getAbstractSurface() != null ? this.getAbstractSurface().hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[SurfacePropertyType]").append("\n");
        if (abstractSurface != null) {
            sb.append("abstractSurface: ").append(abstractSurface.getValue()).append('\n');
        }
        if (remoteSchema != null) {
            sb.append("remoteSchema: ").append(remoteSchema).append('\n');
        }
        if (actuate != null) {
            sb.append("actuate: ").append(actuate).append('\n');
        }
        if (arcrole != null) {
            sb.append("actuate: ").append(arcrole).append('\n');
        }
        if (href != null) {
            sb.append("href: ").append(href).append('\n');
        }
        if (role != null) {
            sb.append("role: ").append(role).append('\n');
        }
        if (show != null) {
            sb.append("show: ").append(show).append('\n');
        }
        if (title != null) {
            sb.append("title: ").append(title).append('\n');
        }
        if (type != null) {
            sb.append("type: ").append(type).append('\n');
        }
        return sb.toString();
    }
}
