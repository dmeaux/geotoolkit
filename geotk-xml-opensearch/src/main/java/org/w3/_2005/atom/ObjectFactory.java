/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2014, Geomatys
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


package org.w3._2005.atom;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import jakarta.xml.bind.annotation.adapters.NormalizedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.w3._2005.atom package.
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

    public final static QName _EntryTypeTitle_QNAME = new QName("http://www.w3.org/2005/Atom", "title");
    public final static QName _EntryTypeCategory_QNAME = new QName("http://www.w3.org/2005/Atom", "category");
    public final static QName _EntryTypeAuthor_QNAME = new QName("http://www.w3.org/2005/Atom", "author");
    public final static QName _EntryTypeSummary_QNAME = new QName("http://www.w3.org/2005/Atom", "summary");
    public final static QName _EntryTypeId_QNAME = new QName("http://www.w3.org/2005/Atom", "id");
    public final static QName _EntryTypeContent_QNAME = new QName("http://www.w3.org/2005/Atom", "content");
    public final static QName _EntryTypeLink_QNAME = new QName("http://www.w3.org/2005/Atom", "link");
    public final static QName _EntryTypeContributor_QNAME = new QName("http://www.w3.org/2005/Atom", "contributor");
    public final static QName _EntryTypeUpdated_QNAME = new QName("http://www.w3.org/2005/Atom", "updated");
    public final static QName _EntryTypeSource_QNAME = new QName("http://www.w3.org/2005/Atom", "source");
    public final static QName _EntryTypeRights_QNAME = new QName("http://www.w3.org/2005/Atom", "rights");
    public final static QName _EntryTypePublished_QNAME = new QName("http://www.w3.org/2005/Atom", "published");
    public final static QName _Entry_QNAME = new QName("http://www.w3.org/2005/Atom", "entry");
    public final static QName _Feed_QNAME = new QName("http://www.w3.org/2005/Atom", "feed");
    public final static QName _PersonTypeName_QNAME = new QName("http://www.w3.org/2005/Atom", "name");
    public final static QName _PersonTypeEmail_QNAME = new QName("http://www.w3.org/2005/Atom", "email");
    public final static QName _PersonTypeUri_QNAME = new QName("http://www.w3.org/2005/Atom", "uri");
    public final static QName _FeedTypeGenerator_QNAME = new QName("http://www.w3.org/2005/Atom", "generator");
    public final static QName _FeedTypeSubtitle_QNAME = new QName("http://www.w3.org/2005/Atom", "subtitle");
    public final static QName _FeedTypeIcon_QNAME = new QName("http://www.w3.org/2005/Atom", "icon");
    public final static QName _FeedTypeLogo_QNAME = new QName("http://www.w3.org/2005/Atom", "logo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.w3._2005.atom
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EntryType }
     *
     */
    public EntryType createEntryType() {
        return new EntryType();
    }

    /**
     * Create an instance of {@link FeedType }
     *
     */
    public FeedType createFeedType() {
        return new FeedType();
    }

    /**
     * Create an instance of {@link SourceType }
     *
     */
    public SourceType createSourceType() {
        return new SourceType();
    }

    /**
     * Create an instance of {@link IdType }
     *
     */
    public IdType createIdType() {
        return new IdType();
    }

    /**
     * Create an instance of {@link GeneratorType }
     *
     */
    public GeneratorType createGeneratorType() {
        return new GeneratorType();
    }

    /**
     * Create an instance of {@link CategoryType }
     *
     */
    public CategoryType createCategoryType() {
        return new CategoryType();
    }

    /**
     * Create an instance of {@link LinkType }
     *
     */
    public LinkType createLinkType() {
        return new LinkType();
    }

    /**
     * Create an instance of {@link UriType }
     *
     */
    public UriType createUriType() {
        return new UriType();
    }

    /**
     * Create an instance of {@link ContentType }
     *
     */
    public ContentType createContentType() {
        return new ContentType();
    }

    /**
     * Create an instance of {@link LogoType }
     *
     */
    public LogoType createLogoType() {
        return new LogoType();
    }

    /**
     * Create an instance of {@link DateTimeType }
     *
     */
    public DateTimeType createDateTimeType() {
        return new DateTimeType();
    }

    /**
     * Create an instance of {@link TextType }
     *
     */
    public TextType createTextType() {
        return new TextType();
    }

    /**
     * Create an instance of {@link PersonType }
     *
     */
    public PersonType createPersonType() {
        return new PersonType();
    }

    /**
     * Create an instance of {@link IconType }
     *
     */
    public IconType createIconType() {
        return new IconType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TextType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "title", scope = EntryType.class)
    public JAXBElement<TextType> createEntryTypeTitle(TextType value) {
        return new JAXBElement<TextType>(_EntryTypeTitle_QNAME, TextType.class, EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoryType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "category", scope = EntryType.class)
    public JAXBElement<CategoryType> createEntryTypeCategory(CategoryType value) {
        return new JAXBElement<CategoryType>(_EntryTypeCategory_QNAME, CategoryType.class, EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "author", scope = EntryType.class)
    public JAXBElement<PersonType> createEntryTypeAuthor(PersonType value) {
        return new JAXBElement<PersonType>(_EntryTypeAuthor_QNAME, PersonType.class, EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TextType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "summary", scope = EntryType.class)
    public JAXBElement<TextType> createEntryTypeSummary(TextType value) {
        return new JAXBElement<TextType>(_EntryTypeSummary_QNAME, TextType.class, EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "id", scope = EntryType.class)
    public JAXBElement<IdType> createEntryTypeId(IdType value) {
        return new JAXBElement<IdType>(_EntryTypeId_QNAME, IdType.class, EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContentType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "content", scope = EntryType.class)
    public JAXBElement<ContentType> createEntryTypeContent(ContentType value) {
        return new JAXBElement<ContentType>(_EntryTypeContent_QNAME, ContentType.class, EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "link", scope = EntryType.class)
    public JAXBElement<LinkType> createEntryTypeLink(LinkType value) {
        return new JAXBElement<LinkType>(_EntryTypeLink_QNAME, LinkType.class, EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "contributor", scope = EntryType.class)
    public JAXBElement<PersonType> createEntryTypeContributor(PersonType value) {
        return new JAXBElement<PersonType>(_EntryTypeContributor_QNAME, PersonType.class, EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DateTimeType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "updated", scope = EntryType.class)
    public JAXBElement<DateTimeType> createEntryTypeUpdated(DateTimeType value) {
        return new JAXBElement<DateTimeType>(_EntryTypeUpdated_QNAME, DateTimeType.class, EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SourceType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "source", scope = EntryType.class)
    public JAXBElement<SourceType> createEntryTypeSource(SourceType value) {
        return new JAXBElement<SourceType>(_EntryTypeSource_QNAME, SourceType.class, EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TextType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "rights", scope = EntryType.class)
    public JAXBElement<TextType> createEntryTypeRights(TextType value) {
        return new JAXBElement<TextType>(_EntryTypeRights_QNAME, TextType.class, EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DateTimeType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "published", scope = EntryType.class)
    public JAXBElement<DateTimeType> createEntryTypePublished(DateTimeType value) {
        return new JAXBElement<DateTimeType>(_EntryTypePublished_QNAME, DateTimeType.class, EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntryType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "entry")
    public JAXBElement<EntryType> createEntry(EntryType value) {
        return new JAXBElement<EntryType>(_Entry_QNAME, EntryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeedType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "feed")
    public JAXBElement<FeedType> createFeed(FeedType value) {
        return new JAXBElement<FeedType>(_Feed_QNAME, FeedType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "name", scope = PersonType.class)
    public JAXBElement<String> createPersonTypeName(String value) {
        return new JAXBElement<String>(_PersonTypeName_QNAME, String.class, PersonType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "email", scope = PersonType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createPersonTypeEmail(String value) {
        return new JAXBElement<String>(_PersonTypeEmail_QNAME, String.class, PersonType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UriType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "uri", scope = PersonType.class)
    public JAXBElement<UriType> createPersonTypeUri(UriType value) {
        return new JAXBElement<UriType>(_PersonTypeUri_QNAME, UriType.class, PersonType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoryType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "category", scope = FeedType.class)
    public JAXBElement<CategoryType> createFeedTypeCategory(CategoryType value) {
        return new JAXBElement<CategoryType>(_EntryTypeCategory_QNAME, CategoryType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TextType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "title", scope = FeedType.class)
    public JAXBElement<TextType> createFeedTypeTitle(TextType value) {
        return new JAXBElement<TextType>(_EntryTypeTitle_QNAME, TextType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "author", scope = FeedType.class)
    public JAXBElement<PersonType> createFeedTypeAuthor(PersonType value) {
        return new JAXBElement<PersonType>(_EntryTypeAuthor_QNAME, PersonType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "id", scope = FeedType.class)
    public JAXBElement<IdType> createFeedTypeId(IdType value) {
        return new JAXBElement<IdType>(_EntryTypeId_QNAME, IdType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntryType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "entry", scope = FeedType.class)
    public JAXBElement<EntryType> createFeedTypeEntry(EntryType value) {
        return new JAXBElement<EntryType>(_Entry_QNAME, EntryType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "contributor", scope = FeedType.class)
    public JAXBElement<PersonType> createFeedTypeContributor(PersonType value) {
        return new JAXBElement<PersonType>(_EntryTypeContributor_QNAME, PersonType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DateTimeType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "updated", scope = FeedType.class)
    public JAXBElement<DateTimeType> createFeedTypeUpdated(DateTimeType value) {
        return new JAXBElement<DateTimeType>(_EntryTypeUpdated_QNAME, DateTimeType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratorType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "generator", scope = FeedType.class)
    public JAXBElement<GeneratorType> createFeedTypeGenerator(GeneratorType value) {
        return new JAXBElement<GeneratorType>(_FeedTypeGenerator_QNAME, GeneratorType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TextType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "subtitle", scope = FeedType.class)
    public JAXBElement<TextType> createFeedTypeSubtitle(TextType value) {
        return new JAXBElement<TextType>(_FeedTypeSubtitle_QNAME, TextType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IconType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "icon", scope = FeedType.class)
    public JAXBElement<IconType> createFeedTypeIcon(IconType value) {
        return new JAXBElement<IconType>(_FeedTypeIcon_QNAME, IconType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "logo", scope = FeedType.class)
    public JAXBElement<LogoType> createFeedTypeLogo(LogoType value) {
        return new JAXBElement<LogoType>(_FeedTypeLogo_QNAME, LogoType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "link", scope = FeedType.class)
    public JAXBElement<LinkType> createFeedTypeLink(LinkType value) {
        return new JAXBElement<LinkType>(_EntryTypeLink_QNAME, LinkType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TextType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "rights", scope = FeedType.class)
    public JAXBElement<TextType> createFeedTypeRights(TextType value) {
        return new JAXBElement<TextType>(_EntryTypeRights_QNAME, TextType.class, FeedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TextType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "title", scope = SourceType.class)
    public JAXBElement<TextType> createSourceTypeTitle(TextType value) {
        return new JAXBElement<TextType>(_EntryTypeTitle_QNAME, TextType.class, SourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoryType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "category", scope = SourceType.class)
    public JAXBElement<CategoryType> createSourceTypeCategory(CategoryType value) {
        return new JAXBElement<CategoryType>(_EntryTypeCategory_QNAME, CategoryType.class, SourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "logo", scope = SourceType.class)
    public JAXBElement<LogoType> createSourceTypeLogo(LogoType value) {
        return new JAXBElement<LogoType>(_FeedTypeLogo_QNAME, LogoType.class, SourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IconType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "icon", scope = SourceType.class)
    public JAXBElement<IconType> createSourceTypeIcon(IconType value) {
        return new JAXBElement<IconType>(_FeedTypeIcon_QNAME, IconType.class, SourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "author", scope = SourceType.class)
    public JAXBElement<PersonType> createSourceTypeAuthor(PersonType value) {
        return new JAXBElement<PersonType>(_EntryTypeAuthor_QNAME, PersonType.class, SourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "id", scope = SourceType.class)
    public JAXBElement<IdType> createSourceTypeId(IdType value) {
        return new JAXBElement<IdType>(_EntryTypeId_QNAME, IdType.class, SourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "link", scope = SourceType.class)
    public JAXBElement<LinkType> createSourceTypeLink(LinkType value) {
        return new JAXBElement<LinkType>(_EntryTypeLink_QNAME, LinkType.class, SourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "contributor", scope = SourceType.class)
    public JAXBElement<PersonType> createSourceTypeContributor(PersonType value) {
        return new JAXBElement<PersonType>(_EntryTypeContributor_QNAME, PersonType.class, SourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DateTimeType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "updated", scope = SourceType.class)
    public JAXBElement<DateTimeType> createSourceTypeUpdated(DateTimeType value) {
        return new JAXBElement<DateTimeType>(_EntryTypeUpdated_QNAME, DateTimeType.class, SourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratorType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "generator", scope = SourceType.class)
    public JAXBElement<GeneratorType> createSourceTypeGenerator(GeneratorType value) {
        return new JAXBElement<GeneratorType>(_FeedTypeGenerator_QNAME, GeneratorType.class, SourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TextType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "subtitle", scope = SourceType.class)
    public JAXBElement<TextType> createSourceTypeSubtitle(TextType value) {
        return new JAXBElement<TextType>(_FeedTypeSubtitle_QNAME, TextType.class, SourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TextType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "rights", scope = SourceType.class)
    public JAXBElement<TextType> createSourceTypeRights(TextType value) {
        return new JAXBElement<TextType>(_EntryTypeRights_QNAME, TextType.class, SourceType.class, value);
    }

}
