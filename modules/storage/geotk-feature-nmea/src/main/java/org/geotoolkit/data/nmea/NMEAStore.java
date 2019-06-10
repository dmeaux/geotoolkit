/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2013-2019, Geomatys
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
package org.geotoolkit.data.nmea;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.sis.feature.builder.AttributeRole;
import org.apache.sis.feature.builder.FeatureTypeBuilder;
import org.apache.sis.internal.storage.ResourceOnFileSystem;
import org.apache.sis.internal.storage.query.SimpleQuery;
import org.apache.sis.metadata.iso.DefaultMetadata;
import org.apache.sis.parameter.Parameters;
import org.apache.sis.referencing.CommonCRS;
import org.apache.sis.storage.DataStore;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.FeatureSet;
import org.apache.sis.storage.Query;
import org.apache.sis.storage.StorageConnector;
import org.apache.sis.storage.UnsupportedQueryException;
import org.apache.sis.storage.event.ChangeEvent;
import org.apache.sis.storage.event.ChangeListener;
import org.geotoolkit.data.query.QueryFeatureSet;
import org.geotoolkit.nio.IOUtilities;
import org.geotoolkit.storage.DataStores;
import org.geotoolkit.util.NamesExt;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureType;
import org.opengis.geometry.Envelope;
import org.opengis.metadata.Metadata;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.util.GenericName;

/**
 * A feature store for GPS measures of NMEA standard.
 * Note that for the moment, only reading is supported. Two readings are possible :
 * - From a text file containing a list of measures.
 * - Directly by getting messages sent by a GPS device on serial port.
 *
 * @author Alexis Manin (Geomatys)
 * @author Johann Sorel (Geomatys)
 */
public class NMEAStore extends DataStore implements FeatureSet, ResourceOnFileSystem {

    public final static GenericName TYPE_NAME  = NamesExt.create(null, "NMEA POINT");
    public final static GenericName GEOM_NAME  = NamesExt.create(null, "Location");
    public final static GenericName ALT_NAME   = NamesExt.create(null, "Altitude");
    public final static GenericName DEPTH_NAME = NamesExt.create(null, "Sea-depth");
    public final static GenericName DATE_NAME  = NamesExt.create(null, "Date");
    public final static GenericName SPEED_NAME = NamesExt.create(null, "Speed");

    /** Feature type to use for nmea data encapsulation */
    public static final FeatureType NMEA_TYPE;
    static {
        final FeatureTypeBuilder builder = new FeatureTypeBuilder();
        builder.setName(TYPE_NAME);
        builder.addAttribute(Point.class).setName(GEOM_NAME).setCRS(CommonCRS.WGS84.normalizedGeographic()).addRole(AttributeRole.DEFAULT_GEOMETRY);
        builder.addAttribute(Double.class).setName(ALT_NAME);
        builder.addAttribute(Double.class).setName(DEPTH_NAME);
        builder.addAttribute(java.util.Date.class).setName(DATE_NAME);
        builder.addAttribute(Double.class).setName(SPEED_NAME);
        NMEA_TYPE = builder.build();
    }

    private final Parameters parameters;
    private final Path file;

    public NMEAStore(final ParameterValueGroup params) throws DataStoreException {
        super(DataStores.getProviderById(NMEAProvider.NAME), new StorageConnector(Parameters.castOrWrap(params).getMandatoryValue(NMEAProvider.PATH)));
        parameters = Parameters.unmodifiable(params);
        final URI uri = parameters.getMandatoryValue(NMEAProvider.PATH);
        try {
            this.file = IOUtilities.toPath(uri);
        } catch (IOException ex) {
            throw new DataStoreException(ex);
        }
    }

    @Override
    public GenericName getIdentifier() throws DataStoreException {
        return getType().getName();
    }

    @Override
    public ParameterValueGroup getOpenParameters() {
        return parameters;
    }

    @Override
    public Metadata getMetadata() throws DataStoreException {
        final DefaultMetadata meta = new DefaultMetadata();
        return meta;
    }

    @Override
    public Envelope getEnvelope() throws DataStoreException {
        return null;
    }

    @Override
    public FeatureType getType() throws DataStoreException {
        return NMEA_TYPE;
    }

    @Override
    public FeatureSet subset(Query query) throws UnsupportedQueryException, DataStoreException {
        if (query instanceof SimpleQuery) {
            return ((SimpleQuery) query).execute(this);
        } else if (query instanceof org.geotoolkit.data.query.Query) {
            return QueryFeatureSet.apply(this, (org.geotoolkit.data.query.Query) query);
        }
        return FeatureSet.super.subset(query);
    }

    @Override
    public Stream<Feature> features(boolean bln) throws DataStoreException {
        try {
            final NMEAFileReader reader = new NMEAFileReader(openConnexion());
            final Stream<Feature> stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(reader, Spliterator.ORDERED), false);
            stream.onClose(reader::close);
            return stream;
        } catch (IOException ex) {
            throw new DataStoreException(ex.getLocalizedMessage(), ex);
        }
    }

    private InputStream openConnexion() throws IOException {
        final URI source = parameters.parameter(NMEAProvider.PATH.getName().getCode()).valueFile();
        final Path tmpFile = Paths.get(source);
        return Files.newInputStream(tmpFile);
    }

    @Override
    public Path[] getComponentFiles() throws DataStoreException {
        return new Path[]{file};
    }

    @Override
    public <T extends ChangeEvent> void addListener(ChangeListener<? super T> cl, Class<T> type) {
    }

    @Override
    public <T extends ChangeEvent> void removeListener(ChangeListener<? super T> cl, Class<T> type) {
    }

    @Override
    public void close() throws DataStoreException {
    }

}
