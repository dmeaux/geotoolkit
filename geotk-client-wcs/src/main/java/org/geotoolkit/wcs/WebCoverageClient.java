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
package org.geotoolkit.wcs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.xml.bind.Unmarshaller;
import org.apache.sis.storage.Aggregate;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.DataStoreProvider;
import org.apache.sis.storage.Resource;
import org.apache.sis.util.iso.Names;
import org.geotoolkit.client.AbstractClient;
import org.geotoolkit.client.Client;
import org.geotoolkit.ogc.xml.exception.ServiceExceptionReport;
import org.geotoolkit.security.ClientSecurity;
import org.geotoolkit.storage.DataStores;
import org.geotoolkit.storage.DefaultAggregate;
import org.geotoolkit.wcs.v100.DescribeCoverage100;
import org.geotoolkit.wcs.v100.GetCapabilities100;
import org.geotoolkit.wcs.v100.GetCoverage100;
import org.geotoolkit.wcs.xml.WCSMarshallerPool;
import org.geotoolkit.wcs.xml.WCSVersion;
import org.geotoolkit.wcs.xml.v100.ContentMetadata;
import org.geotoolkit.wcs.xml.v100.CoverageOfferingBriefType;
import org.geotoolkit.wcs.xml.v100.WCSCapabilitiesType;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.util.GenericName;


/**
 * WCS client, used to aquiere capabilites and requests objects.
 *
 * @author Cédric Briançon (Geomatys)
 * @module
 */
public class WebCoverageClient extends AbstractClient implements Client, Aggregate {

    private static final Logger LOGGER = Logger.getLogger("org.geotoolkit.wcs");

    private WCSCapabilitiesType capabilities;
    private DefaultAggregate rootNode = null;

    public WebCoverageClient(final URL serverURL, final String version) {
        this(serverURL,null,version);
    }

    public WebCoverageClient(final URL serverURL, final ClientSecurity security, final String version) {
        super(create(WCSProvider.PARAMETERS, serverURL, security));
        if (version.equals("1.0.0")) {
            parameters.getOrCreate(WCSProvider.VERSION).setValue(version);
        } else {
            throw new IllegalArgumentException("unknowned version : " + version);
        }
    }

    public WebCoverageClient(final URL serverURL, final ClientSecurity security, final WCSVersion version) {
        super(create(WCSProvider.PARAMETERS, serverURL, security));
        if (version == null) {
            throw new IllegalArgumentException("unknowned version : " + version);
        }
        parameters.getOrCreate(WCSProvider.VERSION).setValue(version.getCode());
    }

    public WebCoverageClient(final ParameterValueGroup params) {
        super(params);
    }

    @Override
    public DataStoreProvider getProvider() {
        return DataStores.getProviderById(WCSProvider.NAME);
    }

    @Override
    public Optional<GenericName> getIdentifier() {
        return Optional.empty();
    }

    /**
     * Returns the currently used version for this server
     */
    public WCSVersion getVersion() {
        return WCSVersion.fromCode(parameters.getValue(WCSProvider.VERSION));
    }

    /**
     * Request a new capabilities to check updateSequence.
     * If capabilities updateSequence has changed, resources are updated.
     *
     * @return true if capabilities and resources has changed.
     */
    public boolean checkForUpdates() {

        String currentUpdateSequence = null;
        WCSCapabilitiesType capas = this.capabilities;
        if (capas != null) {
            currentUpdateSequence = capas.getUpdateSequence();
        }

        final GetCapabilitiesRequest getCaps = createGetCapabilities();

        getCaps.setUpdateSequence(currentUpdateSequence);

        String newUpdateSequence = null;
        boolean changed = true;
        try {
            final Unmarshaller unmarshaller = WCSMarshallerPool.getInstance().acquireUnmarshaller();
            Object lastCapa = unmarshaller.unmarshal(getCaps.getResponseStream());
            WCSMarshallerPool.getInstance().recycle(unmarshaller);
            if (lastCapa instanceof ServiceExceptionReport) {
                ServiceExceptionReport report = (ServiceExceptionReport) lastCapa;
                if (!report.getException().isEmpty() && "CurrentUpdateSequence".equalsIgnoreCase(report.getException().get(0).getExceptionCode())) {
                    newUpdateSequence = currentUpdateSequence;
                    changed = false;
                } else {
                     throw report.toException();
                }

            } else if (lastCapa instanceof WCSCapabilitiesType) {
                WCSCapabilitiesType lc = (WCSCapabilitiesType) lastCapa;
                newUpdateSequence = lc.getUpdateSequence();
            }
            changed = !Objects.equals(currentUpdateSequence, newUpdateSequence);
            if (changed) {
                capabilities = (WCSCapabilitiesType) lastCapa;
            }

        } catch (Exception ex) {
            capabilities = null;
            try {
                LOGGER.log(Level.WARNING, "Wrong URL, the server doesn't answer : " +
                        createGetCapabilities().getURL().toString(), ex);
            } catch (MalformedURLException ex1) {
                LOGGER.log(Level.WARNING, "Malformed URL, the server doesn't answer. ", ex1);
            }
        }

        if (changed) {
            rootNode = null;
        }
        return changed;
    }

    @Override
    public Collection<? extends Resource> components() throws DataStoreException {
        checkForUpdates();
        return getRootResource().components();
    }

    private synchronized Aggregate getRootResource() throws DataStoreException {
        if (rootNode != null) {
            return rootNode;
        }

        rootNode = new DefaultAggregate(Names.createLocalName(null, null, "root"));

        final WCSCapabilitiesType capa = getServiceCapabilities();
        final ContentMetadata contentMetadata = capa.getContentMetadata();
        final List<CoverageOfferingBriefType> briefs = contentMetadata.getCoverageOfferingBrief();
        for (CoverageOfferingBriefType brief : briefs) {
            rootNode.addResource(new WCSResource(this, brief));
        }
        return rootNode;
    }

    /**
     * Returns the {@linkplain WCSCapabilitiesType capabilities} response for this
     * server.
     */
    public WCSCapabilitiesType getServiceCapabilities() {

        if (capabilities != null) {
            return capabilities;
        }
        //Thread to prevent infinite request on a server
        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    final Unmarshaller unmarshaller = WCSMarshallerPool.getInstance().acquireUnmarshaller();
                    final GetCapabilitiesRequest request = createGetCapabilities();
                    capabilities = (WCSCapabilitiesType) unmarshaller.unmarshal(request.getURL());
                    WCSMarshallerPool.getInstance().recycle(unmarshaller);
                } catch (Exception ex) {
                    capabilities = null;
                    try {
                        LOGGER.log(Level.WARNING, "Wrong URL, the server doesn't answer : " +
                                createGetCapabilities().getURL().toString(), ex);
                    } catch (MalformedURLException ex1) {
                        LOGGER.log(Level.WARNING, "Malformed URL, the server doesn't answer. ", ex1);
                    }
                }
            }
        };
        thread.start();
        final long start = System.currentTimeMillis();
        try {
            thread.join(10000);
        } catch (InterruptedException ex) {
            LOGGER.log(Level.WARNING, "The thread to obtain GetCapabilities doesn't answer.", ex);
        }
        if ((System.currentTimeMillis() - start) > 10000) {
            LOGGER.log(Level.WARNING, "TimeOut error, the server takes too much time to answer. ");
        }

        return capabilities;
    }

    /**
     * Create a describe coverage request.
     * @return DescribeCoverageRequest : describe coverage request.
     */
    public DescribeCoverageRequest createDescribeCoverage() {

        switch (getVersion()) {
            case v100:
                return new DescribeCoverage100(serverURL.toString(),getClientSecurity());
            default:
                throw new IllegalArgumentException("Version was not defined");
        }
    }

    /**
     * Create a getCapabilities request.
     * @return GetCapabilitiesRequest : getCapabilities request.
     */
    public GetCapabilitiesRequest createGetCapabilities() {

        switch (getVersion()) {
            case v100:
                return new GetCapabilities100(serverURL.toString(),getClientSecurity());
            default:
                throw new IllegalArgumentException("Version was not defined");
        }
    }

    /**
     * Create a getCoverage request.
     * @return GetCoverageRequest : getCoverage request.
     */
    public GetCoverageRequest createGetCoverage() {

        switch (getVersion()) {
            case v100:
                return new GetCoverage100(serverURL.toString(),getClientSecurity());
            default:
                throw new IllegalArgumentException("Version was not defined");
        }
    }

    @Override
    public void close() throws DataStoreException {
    }

}
