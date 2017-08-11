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

package org.geotoolkit.data;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.sis.referencing.NamedIdentifier;
import org.apache.sis.storage.DataStoreException;
import org.geotoolkit.data.query.Query;
import org.geotoolkit.data.query.Source;
import org.geotoolkit.data.session.Session;
import org.geotoolkit.factory.Hints;
import org.geotoolkit.storage.StorageListener;
import org.geotoolkit.util.NamesExt;
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureType;
import org.opengis.filter.Filter;
import org.opengis.geometry.Envelope;
import org.opengis.metadata.Identifier;

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
 */
public interface FeatureCollection extends Collection<Feature>, FeatureResource {

    /**
     * A feature collection is created with an id.
     * This can be used for different purposes.
     *
     * @return String, never null
     */
    String getID();

    @Override
    default Identifier getIdentifier() {
        return new NamedIdentifier(NamesExt.create(getID()));
    }

    /**
     * A collection may be linked to a session, this implies that changes maid
     * in the collection may not be send to the FeatureStore now.
     * A session.commit() call must be done.
     *
     * @return Session or null if not related to a session.
     */
    Session getSession();

    /**
     * A collection always takes it's data from somewhere, it can be any kind
     * of FeatureStore.
     *
     * @return feature source of this collection.
     */
    Source getSource();


    /**
     * If all features in this collection are of the same type then
     * this method will return this feature type.
     * This is uses for performance reasons to avoid redondunt type test.
     *
     * @return Feature type or null if features doesn't have always the same type.
     */
    FeatureType getType();

    /**
     * Get the envelope of all features in this collection.
     *
     * @return envelope or null if there are no features or no geometrics attributes
     * available.
     * @throws DataStoreException
     */
    Envelope getEnvelope() throws DataStoreException;

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
    FeatureCollection subCollection(Query query) throws DataStoreException;

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
        return stream();
    }

    /**
     * Convenient method to update a single feature.
     * @see #update(org.opengis.feature.type.Name, org.opengis.filter.Filter, java.util.Map)
     */
    void update(Feature feature) throws DataStoreException;

    /**
     * Update all features that match the given filter and update there attributes values
     * with the values from the given map.
     *
     * @param filter : updating filter
     * @param values : new attributes values
     * @throws DataStoreException
     */
    void update(Filter filter, Map<String, ?> values) throws DataStoreException;

    /**
     * Remove all features from this collection that match the given filter.
     * @param filter : removing filter
     * @throws DataStoreException
     */
    void remove(Filter filter) throws DataStoreException;

}
