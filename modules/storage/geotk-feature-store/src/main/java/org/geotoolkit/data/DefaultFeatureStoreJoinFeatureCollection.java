/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2010, Geomatys
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
import java.util.logging.Level;
import org.apache.sis.storage.DataStoreException;
import org.geotoolkit.data.query.Join;
import org.geotoolkit.data.query.Query;
import org.geotoolkit.data.query.QueryUtilities;
import org.geotoolkit.data.query.Source;
import org.geotoolkit.data.session.Session;
import org.geotoolkit.factory.Hints;
import org.opengis.filter.Filter;
import org.apache.sis.util.logging.Logging;
import org.opengis.feature.FeatureType;

/**
 * FeatureCollection that takes it's source from a join query.
 *
 * @author Johann Sorel (Geomatys)
 * @module
 */
public class DefaultFeatureStoreJoinFeatureCollection extends AbstractFeatureCollection{

    private final Session session;
    private final Query query;
    private FeatureType type = null;

    public DefaultFeatureStoreJoinFeatureCollection(final String id, final Query query){
        super(id,query.getSource());
        this.query = query;

        final Source source = query.getSource();
        if(!(source instanceof Join)){
            throw new IllegalArgumentException("Query must have a join source.");
        }

        if(!QueryUtilities.isAbsolute(source)){
            throw new IllegalArgumentException("Query source must be absolute.");
        }

        final Collection<Session> sessions = QueryUtilities.getSessions(source, null);

        if(sessions.size() == 1 && sessions.iterator().next().getFeatureStore().getQueryCapabilities().handleCrossQuery()){
            session = sessions.iterator().next();
        }else{
            throw new IllegalArgumentException("Query source must have a single session.");
        }

    }

    @Override
    public Join getSource() {
        return (Join) super.getSource();
    }

    @Override
    public synchronized FeatureType getFeatureType(){
        if(type == null){
            try {
                FeatureReader reader = (FeatureReader) session.getFeatureIterator(query);
                type = reader.getFeatureType();
                reader.close();
            } catch (DataStoreException ex) {
                Logging.getLogger("org.geotoolkit.data").log(Level.WARNING, null, ex);
            }
        }

        return type;
    }

    @Override
    public FeatureCollection subCollection(final Query query) throws DataStoreException {
        final Query combine = QueryUtilities.subQuery(this.query, query);
        //the result should be an absolute query too.
        return QueryUtilities.evaluate("sub-"+getID(), combine);
    }

    @Override
    public FeatureIterator iterator(final Hints hints) throws FeatureStoreRuntimeException {
        try {
            return session.getFeatureIterator(query);
        } catch (DataStoreException ex) {
            throw new FeatureStoreRuntimeException(ex);
        }
    }

    @Override
    public void update(final Filter filter, final Map<String,?> values) throws DataStoreException {
        if(isWritable()){
            throw new UnsupportedOperationException("Not supported yet.");
        }else{
            throw new DataStoreException("Collection is not writable.");
        }
    }

    @Override
    public void remove(final Filter filter) throws DataStoreException {
        if(isWritable()){
            throw new UnsupportedOperationException("Not supported yet.");
        }else{
            throw new DataStoreException("Collection is not writable.");
        }
    }

}
