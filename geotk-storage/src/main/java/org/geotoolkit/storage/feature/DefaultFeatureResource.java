/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2017, Geomatys
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

import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.sis.metadata.iso.DefaultMetadata;
import org.apache.sis.metadata.iso.citation.DefaultCitation;
import org.apache.sis.metadata.iso.identification.DefaultDataIdentification;
import org.apache.sis.referencing.NamedIdentifier;
import org.apache.sis.storage.DataStore;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.FeatureSet;
import org.apache.sis.storage.IllegalFeatureTypeException;
import org.apache.sis.storage.ReadOnlyStorageException;
import org.apache.sis.storage.WritableFeatureSet;
import org.apache.sis.storage.base.StoreResource;
import org.apache.sis.storage.event.StoreEvent;
import org.apache.sis.storage.event.StoreListener;
import org.apache.sis.util.ArgumentChecks;
import org.geotoolkit.feature.FeatureExt;
import org.geotoolkit.storage.AbstractResource;
import org.geotoolkit.storage.event.FeatureStoreContentEvent;
import org.geotoolkit.storage.event.FeatureStoreManagementEvent;
import org.geotoolkit.storage.event.StorageListener;
import org.geotoolkit.storage.feature.query.Query;
import org.geotoolkit.storage.feature.query.QueryUtilities;
import org.geotoolkit.util.NamesExt;
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureType;
import org.opengis.filter.Filter;
import org.opengis.geometry.Envelope;
import org.opengis.util.GenericName;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class DefaultFeatureResource extends AbstractResource implements WritableFeatureSet, StoreListener<StoreEvent>, StoreResource {

    private final StorageListener.Weak weakListener = new StorageListener.Weak(this);
    private final FeatureStore store;
    private final Query query;

    public DefaultFeatureResource(FeatureStore store, GenericName name) throws DataStoreException {
        this(store,new Query(name));
    }

    public DefaultFeatureResource(FeatureStore store, Query query) throws DataStoreException {
        super(store.getFeatureType(query.getTypeName()).getName());
        this.store = store;
        this.query = query;
        weakListener.registerSource(store);
    }

    @Override
    public DefaultMetadata createMetadata() throws DataStoreException {
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
    public FeatureType getType() throws DataStoreException {
        return store.getFeatureType(query);
    }

    @Override
    public Optional<Envelope> getEnvelope() throws DataStoreException {
        return Optional.empty();
    }

    @Override
    public DataStore getOriginator() {
        return (DataStore) store;
    }

    @Override
    public FeatureSet subset(org.apache.sis.storage.Query query) throws DataStoreException {
        if (query == null) return this;
        if (query instanceof Query) {
            return new DefaultFeatureResource(store, QueryUtilities.subQuery(this.query, (Query) query));
        }
        return WritableFeatureSet.super.subset(query);
    }

    @Override
    public Stream<Feature> features(boolean parallal) throws DataStoreException {
        final FeatureReader reader = store.getFeatureReader(query);
        final Spliterator<Feature> spliterator = Spliterators.spliteratorUnknownSize((Iterator)reader, Spliterator.ORDERED);
        final Stream<Feature> stream = StreamSupport.stream(spliterator, false);
        return stream.onClose(reader::close);
    }

    /**
     * This method fallback on {@link FeatureStore}
     * @param features
     * @throws ReadOnlyStorageException
     * @throws DataStoreException
     */
    @Override
    public void add(Iterator<? extends Feature> features) throws ReadOnlyStorageException, DataStoreException {
        try (final FeatureWriter writer = store.getFeatureWriter(Query.filtered(query.getTypeName(), Filter.exclude()))) {
            while (features.hasNext()) {
                FeatureExt.copy(features.next(), writer.next(), true);
                writer.write();
            }
        }
    }

    @Override
    public void removeIf(Predicate<? super Feature> filter) throws ReadOnlyStorageException, DataStoreException {
        ArgumentChecks.ensureNonNull("predicate", filter);
        boolean removed = false;
        try (final FeatureWriter writer = store.getFeatureWriter(query)) {
            while (writer.hasNext()) {
                Feature feature = writer.next();
                if (filter.test(feature)) {
                    writer.remove();
                    removed = true;
                }
            }
        }
    }

    @Override
    public void replaceIf(Predicate<? super Feature> filter, UnaryOperator<Feature> updater) throws ReadOnlyStorageException, DataStoreException {
        ArgumentChecks.ensureNonNull("predicate", filter);
        ArgumentChecks.ensureNonNull("updater", updater);
        try (final FeatureWriter writer = store.getFeatureWriter(query)) {
            while (writer.hasNext()) {
                Feature feature = writer.next();
                if (filter.test(feature)) {
                    feature = updater.apply(feature);
                    if (feature == null) {
                       writer.remove();
                    } else {
                        writer.write();
                    }
                }
            }
        }
    }

    /**
     * Forward event to listeners by changing source.
     */
    @Override
    public void eventOccured(StoreEvent event) {

        if (event instanceof FeatureStoreManagementEvent) {
            final FeatureStoreManagementEvent fevent = (FeatureStoreManagementEvent) event;
            //forward events only if the collection is typed and match the type name
            if (NamesExt.match(fevent.getFeatureTypeName(), query.getTypeName())) {
                sendEvent(fevent.copy(this));
            }
        } else if (event instanceof FeatureStoreContentEvent) {
            final FeatureStoreContentEvent fevent = (FeatureStoreContentEvent) event;
            //forward events only if the collection is typed and match the type name
            if (NamesExt.match(fevent.getFeatureTypeName(), query.getTypeName())) {
                sendEvent(fevent.copy(this));
            }
        }
    }

    @Override
    public void updateType(FeatureType newType) throws IllegalFeatureTypeException, DataStoreException {
        throw new DataStoreException("Feature type update not supported.");
    }
}
