/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2020, Geomatys
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
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.sis.internal.storage.AbstractFeatureSet;
import org.apache.sis.storage.FeatureQuery;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.FeatureSet;
import org.apache.sis.storage.Query;
import org.apache.sis.storage.UnsupportedQueryException;
import org.geotoolkit.storage.multires.GeneralProgressiveResource;
import org.geotoolkit.storage.multires.MultiResolutionModel;
import org.geotoolkit.storage.multires.MultiResolutionResource;
import org.geotoolkit.storage.multires.TileGenerator;
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureType;
import org.opengis.geometry.Envelope;
import org.opengis.util.GenericName;
import org.geotoolkit.storage.multires.TileMatrixSet;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class ProgressiveFeatureSetResource<T extends FeatureSet & MultiResolutionResource>
        extends GeneralProgressiveResource implements FeatureSet, MultiResolutionResource {

    private T base = null;

    public ProgressiveFeatureSetResource(T resource, TileGenerator generator) throws DataStoreException {
        super(resource, generator);
        this.base = resource;
    }

    @Override
    public Optional<Envelope> getEnvelope() throws DataStoreException {
        final Collection<? extends MultiResolutionModel> models = base.getModels();
        //search for a pyramid
        //-- we use the first pyramid as default
        for (MultiResolutionModel model : models) {
            if (model instanceof org.geotoolkit.storage.multires.TileMatrixSet) {
                org.geotoolkit.storage.multires.TileMatrixSet pyramid = (org.geotoolkit.storage.multires.TileMatrixSet) model;
                return Optional.ofNullable(pyramid.getEnvelope());
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<TileMatrixSet> getModels() throws DataStoreException {
        return (Collection<TileMatrixSet>) super.getModels();
    }

    @Override
    public FeatureType getType() throws DataStoreException {
        return base.getType();
    }

    @Override
    public FeatureSet subset(Query query) throws UnsupportedQueryException, DataStoreException {
        if (query instanceof FeatureQuery) {
            return new SubSet((FeatureQuery) query);
        } else {
            throw new UnsupportedQueryException();
        }
    }

    @Override
    public Stream<Feature> features(boolean parallel) throws DataStoreException {
        return new TileMatrixSetFeatureReader(this, getType()).features(null, parallel);
    }

    private final class SubSet extends AbstractFeatureSet {

        private final FeatureQuery query;

        private SubSet(FeatureQuery query) {
            super(null);
            this.query = query;
        }

        @Override
        public Optional<GenericName> getIdentifier() throws DataStoreException {
            return ProgressiveFeatureSetResource.this.getIdentifier();
        }

        @Override
        public FeatureType getType() throws DataStoreException {
            return ProgressiveFeatureSetResource.this.getType();
        }

        @Override
        public Optional<Envelope> getEnvelope() throws DataStoreException {
            return ProgressiveFeatureSetResource.this.getEnvelope();
        }

        @Override
        public Stream<Feature> features(boolean parallel) throws DataStoreException {
            return new TileMatrixSetFeatureReader(ProgressiveFeatureSetResource.this, getType()).features(this.query, parallel);
        }
    }
}
