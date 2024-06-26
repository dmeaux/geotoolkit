/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2009, Geomatys
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

package org.geotoolkit.storage.feature;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.sis.metadata.iso.DefaultMetadata;
import org.apache.sis.metadata.iso.citation.DefaultCitation;
import org.apache.sis.metadata.iso.identification.DefaultDataIdentification;
import org.apache.sis.referencing.NamedIdentifier;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.FeatureSet;
import org.geotoolkit.factory.Hints;
import org.geotoolkit.storage.feature.query.Query;
import org.geotoolkit.storage.feature.session.Session;
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureType;
import org.opengis.metadata.Metadata;
import org.opengis.util.GenericName;

/**
 * A java collection that may hold only features.
 * This interface offer additional methods to manipulate it's content in
 * a more normalized manner, with filter, envelope and so one.
 *
 * Still it can be used a normal java collection.
 *
 * Warning : don't forget to catch FeatureStoreRuntimeException that might
 * occur on some methods.
 *
 * @author Johann Sorel (Geomatys)
 * @module
 * @deprecated replaced by Apache SIS FeatureSet
 */
public interface FeatureCollection extends Collection<Feature>, FeatureSet {

    @Override
    default Metadata getMetadata() throws DataStoreException {
        final DefaultMetadata metadata = new DefaultMetadata();
        final DefaultDataIdentification identification = new DefaultDataIdentification();
        final NamedIdentifier identifier = NamedIdentifier.castOrCopy(getIdentifier().get());
        final DefaultCitation citation = new DefaultCitation(identifier.toString());
        citation.setIdentifiers(Collections.singleton(identifier));
        identification.setCitation(citation);
        metadata.setIdentificationInfo(Collections.singleton(identification));

        //NOTE : add count, may be expensive, remove it ?
//        final DefaultFeatureCatalogueDescription fcd = new DefaultFeatureCatalogueDescription();
//        final DefaultFeatureTypeInfo info = new DefaultFeatureTypeInfo();
//        info.setFeatureInstanceCount((int)features(false).count());
//        fcd.getFeatureTypeInfo().add(info);
//        metadata.getContentInfo().add(fcd);

        metadata.transitionTo(DefaultMetadata.State.FINAL);
        return metadata;
    }

    @Override
    Optional<GenericName> getIdentifier();

    /**
     * A collection may be linked to a session, this implies that changes maid
     * in the collection may not be send to the FeatureStore now.
     * A session.commit() call must be done.
     *
     * @return Session or null if not related to a session.
     */
    Session getSession();

    /**
     * If all features in this collection are of the same type then
     * this method will return this feature type.
     * This is uses for performance reasons to avoid redondunt type test.
     *
     * @return Feature type or null if features doesn't have always the same type.
     */
    FeatureType getType();

    /**
     * Check if we can modify this collection.
     *
     * @return true is edition operation are possible on this collection, false otherwise.
     */
    boolean isWritable();

    /**
     * Aquiere a sub collection of features that match the query.
     * The query type name is ignore here, it will inhirite the current collection
     * type name.
     *
     * @param query
     * @return FeatureCollection , never null.
     * @throws DataStoreException
     */
    FeatureCollection subset(Query query) throws DataStoreException;

    /**
     * Override Iterator to return a limited type FeatureIterator.
     *
     * @see FeatureCollection#iterator(org.geotoolkit.factory.Hints)
     *
     * @return FeatureIterator
     * @throws FeatureStoreRuntimeException
     */
    @Override
    FeatureIterator iterator() throws FeatureStoreRuntimeException;

    /**
     * Get an iterator using some extra hints to configure the reader parameters.
     *
     * If the collection has several sources for origin, the returned feature type
     * combine each selector, the returned features have one complex attribute for each
     * selector, the attribute has the name of the selector.
     *
     * This approach is the counterpart of javax.jcr.query.QueryResult.getRows
     * from JSR-283 (Java Content Repository 2).
     *
     * @param hints : Extra hints
     * @return FeatureIterator
     * @throws FeatureStoreRuntimeException
     */
    FeatureIterator iterator(Hints hints) throws FeatureStoreRuntimeException;

    @Override
    default Stream<Feature> features(boolean parallal) throws DataStoreException {
        final FeatureIterator reader = iterator();
        final Spliterator<Feature> spliterator = Spliterators.spliteratorUnknownSize(reader, Spliterator.ORDERED);
        final Stream<Feature> stream = StreamSupport.stream(spliterator, false);
        return stream.onClose(reader::close);
    }

}
