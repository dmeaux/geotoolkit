/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2010, Johann Sorel
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

package org.geotoolkit.storage.memory;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.IllegalFeatureTypeException;
import org.apache.sis.storage.ReadOnlyStorageException;
import org.apache.sis.storage.event.StoreEvent;
import org.apache.sis.storage.event.StoreListener;
import org.geotoolkit.factory.Hints;
import org.geotoolkit.storage.feature.FeatureCollection;
import org.geotoolkit.storage.feature.FeatureIterator;
import org.geotoolkit.storage.feature.FeatureStoreRuntimeException;
import org.geotoolkit.storage.feature.query.Query;
import org.geotoolkit.storage.feature.session.Session;
import org.geotoolkit.storage.memory.mapping.DefaultFeatureMapper;
import org.geotoolkit.storage.memory.mapping.FeatureMapper;
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureType;
import org.opengis.feature.PropertyType;
import org.opengis.geometry.Envelope;
import org.opengis.util.GenericName;

/**
 * Basic support for a FeatureCollection that moves attributs to a new type definition
 * using a mapping objet.
 *
 * @author Johann Sorel (Puzzle-GIS)
 * @module
 */
public class GenericMappingFeatureCollection extends AbstractCollection<Feature> implements FeatureCollection {

    private final FeatureCollection original;
    private final FeatureType type;
    private final FeatureMapper mapper;

    public GenericMappingFeatureCollection(final FeatureCollection original, final FeatureType newType,
            final Map<PropertyType,List<PropertyType>> mapping,
            final Map<PropertyType,Object> defaults){
        this(original,
                new DefaultFeatureMapper(
                        original.getType(),
                        newType,
                        mapping,
                        defaults)
                );
    }

    public GenericMappingFeatureCollection(final FeatureCollection original, final FeatureMapper mapper){
        this.original = original;
        this.mapper = mapper;
        this.type = mapper.getTargetType();
    }

    @Override
    public Optional<GenericName> getIdentifier() {
        return original.getIdentifier();
    }

    @Override
    public Session getSession() {
        return original.getSession();
    }

    @Override
    public FeatureType getType() {
        return type;
    }

    @Override
    public Optional<Envelope> getEnvelope() throws DataStoreException {
        return original.getEnvelope();
    }

    @Override
    public boolean isWritable() {
        return false;
    }

    @Override
    public FeatureCollection subset(final Query query) throws DataStoreException {
        throw new UnsupportedOperationException("Mapping feature collection is not made to allow sub query.");
    }

    @Override
    public FeatureIterator iterator() throws FeatureStoreRuntimeException {
        return iterator(null);
    }

    @Override
    public FeatureIterator iterator(final Hints hints) throws FeatureStoreRuntimeException {
        return new GenericMappingFeatureIterator(original.iterator(), mapper);
    }

    public void updateType(FeatureType newType) throws IllegalFeatureTypeException, DataStoreException {
        throw new ReadOnlyStorageException();
    }

    public void add(Iterator<? extends Feature> features) throws DataStoreException {
        throw new ReadOnlyStorageException();
    }

    public void replaceIf(Predicate<? super Feature> filter, UnaryOperator<Feature> updater) throws DataStoreException {
        throw new ReadOnlyStorageException();
    }

    @Override
    public <T extends StoreEvent> void addListener(Class<T> eventType, StoreListener<? super T> listener) {
    }

    @Override
    public <T extends StoreEvent> void removeListener(Class<T> eventType, StoreListener<? super T> listener) {
    }

    @Override
    public int size() {
        return original.size();
    }

    @Override
    public boolean isEmpty() {
        return original.isEmpty();
    }

    ////////////////////////////////////////////////////////////////////////////
    // not writable ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean add(final Feature e) {
        throw new FeatureStoreRuntimeException("Not writable");
    }

    @Override
    public boolean remove(final Object o) {
        throw new FeatureStoreRuntimeException("Not writable");
    }

    @Override
    public boolean addAll(final Collection<? extends Feature> clctn) {
        throw new FeatureStoreRuntimeException("Not writable");
    }

    @Override
    public boolean removeAll(final Collection<?> clctn) {
        throw new FeatureStoreRuntimeException("Not writable");
    }

    @Override
    public void clear() {
        throw new FeatureStoreRuntimeException("Not writable");
    }
}
