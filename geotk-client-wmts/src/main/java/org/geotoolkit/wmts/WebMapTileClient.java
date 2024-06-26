/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2009-2010, Geomatys
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
package org.geotoolkit.wmts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.sis.storage.Aggregate;
import org.apache.sis.storage.DataStoreException;
import org.geotoolkit.client.AbstractClient;
import org.geotoolkit.client.AbstractClientProvider;
import org.geotoolkit.client.Client;
import org.geotoolkit.ows.xml.AbstractCapabilitiesBase;
import org.geotoolkit.security.ClientSecurity;
import org.geotoolkit.storage.DataStores;
import org.geotoolkit.util.NamesExt;
import org.geotoolkit.wmts.v100.GetCapabilities100;
import org.geotoolkit.wmts.v100.GetTile100;
import org.geotoolkit.wmts.xml.WMTSBindingUtilities;
import org.geotoolkit.wmts.xml.WMTSVersion;
import org.geotoolkit.wmts.xml.v100.Capabilities;
import org.geotoolkit.wmts.xml.v100.LayerType;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.util.GenericName;


/**
 * Generates WMTS requests objects on a WMTS server.
 *
 * @author Guilhem Legal (Geomatys)
 * @module
 */
public class WebMapTileClient extends AbstractClient implements Client, Aggregate {

    private static final Logger LOGGER = Logger.getLogger("org.geotoolkit.wmts");

    private Capabilities capabilities;
    private List<WMTSResource> resources = null;

    /**
     * Defines the timeout in milliseconds for the GetCapabilities request.
     * default is 10 seconds.
     */
    private static final long TIMEOUT_GETCAPS = 10000L;

    /**
     * The request header map for this server
     * that contains a set of key-value for HTTP header fields (user-agent, referer, accept-language...)
     */
    private final Map<String,String> requestHeaderMap = new HashMap<>();

    /**
     * Builds a web map server with the given server url and version.
     *
     * @param serverURL The server base url.
     * @param version A string representation of the service version.
     * @throws IllegalArgumentException if the version specified is not applyable.
     */
    public WebMapTileClient(final URL serverURL, final String version) {
        this(serverURL, WMTSVersion.getVersion(version));
    }

    /**
     * Builds a web map server with the given server url, a security and version.
     *
     * @param serverURL The server base url.
     * @param security The server security.
     * @param version The service version.
     * @throws IllegalArgumentException if the version specified is not applyable.
     */
    public WebMapTileClient(final URL serverURL, final ClientSecurity security, final WMTSVersion version) {
        this(serverURL, security, version, null, false, null);
    }

    /**
     * Builds a web map server with the given server url and version.
     *
     * @param serverURL The server base url.
     * @param version The service version.
     */
    public WebMapTileClient(final URL serverURL, final WMTSVersion version) {
        this(serverURL, null, version, null, false, null);
    }

    /**
     * Builds a web map server with the given server url, version and getCapabilities response.
     *
     * @param serverURL The server base url.
     * @param version A string representation of the service version.
     * @param capabilities A getCapabilities response.
     * @throws IllegalArgumentException if the version specified is not applyable.
     */
    public WebMapTileClient(final URL serverURL, final String version, final Capabilities capabilities) {
        this(serverURL, null, WMTSVersion.getVersion(version), capabilities, false, null);
    }

    /**
     * Builds a web map server with the given server url, version and getCapabilities response.
     *
     * @param serverURL The server base url.
     * @param version A string representation of the service version.
     * @param capabilities A getCapabilities response.
     */
    public WebMapTileClient(final URL serverURL, final WMTSVersion version, final Capabilities capabilities) {
        this(serverURL,null,version,capabilities,false, null);
    }

    /**
     * Builds a web map server with the given server url, version and getCapabilities response.
     *
     * @param serverURL The server base url.
     * @param security The server security.
     * @param version A string representation of the service version.
     * @param capabilities A getCapabilities response.
     */
    public WebMapTileClient(final URL serverURL, final ClientSecurity security,
            final WMTSVersion version, final Capabilities capabilities, boolean cacheImage) {
        this(serverURL, security, version, capabilities, cacheImage, null);
    }

    /**
     * Builds a web map server with the given server url, version and getCapabilities response.
     *
     * @param serverURL The server base url.
     * @param security The server security.
     * @param version A string representation of the service version.
     * @param capabilities A getCapabilities response.
     * @param timeout defaut connection timeout in millisecond (default 20000).
     */
    public WebMapTileClient(final URL serverURL, final ClientSecurity security,
            final WMTSVersion version, final Capabilities capabilities, boolean cacheImage, final Integer timeout) {
        super(create(WMTSProvider.PARAMETERS, serverURL, security));
        parameters.getOrCreate(WMTSProvider.VERSION).setValue(version.getCode());
        parameters.getOrCreate(WMTSProvider.IMAGE_CACHE).setValue(cacheImage);
        parameters.getOrCreate(WMTSProvider.TIMEOUT).setValue(timeout);
        this.capabilities = capabilities;
    }

    public WebMapTileClient(ParameterValueGroup param){
        super(param);
    }

    @Override
    public WMTSProvider getProvider() {
        return (WMTSProvider) DataStores.getProviderById(WMTSProvider.NAME);
    }

    /**
     * Returns the {@linkplain Capabilities capabilities} response for this request.
     *
     * @return {@linkplain Capabilities capabilities} response but never {@code null}.
     * @see {@link #getCapabilities(long)}
     */
    public Capabilities getServiceCapabilities() {
        return getCapabilities(TIMEOUT_GETCAPS);
    }

    /**
     * Returns the {@linkplain Capabilities capabilities} response for this
     * request.
     *
     * @param timeout Timeout in milliseconds
     */
    public Capabilities getCapabilities(final long timeout) {

        if (capabilities != null) {
            return capabilities;
        }
        //Thread to prevent infinite request on a server
        final Thread thread = new Thread() {
            @Override
            public void run() {
                final GetCapabilitiesRequest getCaps = createGetCapabilities();
                //Filling the request header map from the map of the layer's server
                final Map<String, String> headerMap = getRequestHeaderMap();
                getCaps.getHeaderMap().putAll(headerMap);
                try {
                    capabilities = WMTSBindingUtilities.unmarshall(getCaps.getResponseStream(), getVersion());
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
            thread.join(timeout);
        } catch (InterruptedException ex) {
            LOGGER.log(Level.WARNING, "The thread to obtain GetCapabilities doesn't answer.", ex);
        }
        if ((System.currentTimeMillis() - start) > timeout) {
            LOGGER.log(Level.WARNING, "TimeOut error, the server takes too much time to answer. ");
        }

        return capabilities;
    }

    /**
     * Request a new capabilities to check updateSequence.
     * If capabilities updateSequence has changed, resources are updated.
     *
     * @param newCapabilities, the new capabilities, or null to fetch it
     * @return true if capabilities and resources has changed.
     */
    public synchronized boolean checkForUpdates(Capabilities newCapabilities) {
        String currentUpdateSequence = null;
        AbstractCapabilitiesBase capas = this.capabilities;
        if (capas != null) {
            currentUpdateSequence = capas.getUpdateSequence();
        }

        boolean changed = true;
        String newUpdateSequence = null;
        if (newCapabilities == null) {
            final GetCapabilitiesRequest getCaps = createGetCapabilities();
            //Filling the request header map from the map of the layer's server
            final Map<String, String> headerMap = getRequestHeaderMap();
            getCaps.getHeaderMap().putAll(headerMap);
            getCaps.setUpdateSequence(currentUpdateSequence);
            try {
                newCapabilities = WMTSBindingUtilities.unmarshall(getCaps.getResponseStream(), getVersion());
            } catch (Exception ex) {
                this.capabilities = null;
                try {
                    LOGGER.log(Level.WARNING, "Wrong URL, the server doesn't answer : " +
                            createGetCapabilities().getURL().toString(), ex);
                } catch (MalformedURLException ex1) {
                    LOGGER.log(Level.WARNING, "Malformed URL, the server doesn't answer. ", ex1);
                }
            }
        }

        if (newCapabilities != null) {
            newUpdateSequence = newCapabilities.getUpdateSequence();
        }
        changed = !Objects.equals(currentUpdateSequence, newUpdateSequence);
        if (changed) {
            this.capabilities = newCapabilities;
        }

        if (changed) {
            resources = createOrUpdateResources(resources, newCapabilities);
            //TODO events
        }
        return changed;
    }

    /**
     * Returns the request version.
     */
    public WMTSVersion getVersion() {
        return WMTSVersion.getVersion(parameters.getValue(WMTSProvider.VERSION));
    }

    public boolean getImageCache() {
        return parameters.getValue(AbstractClientProvider.IMAGE_CACHE);
    }

    /**
     * Returns the request object, in the version chosen.
     *
     * @throws IllegalArgumentException if the version requested is not supported.
     */
    public GetTileRequest createGetTile() {
        switch (getVersion()) {
            case v100:
                return new GetTile100(serverURL.toString(),getClientSecurity(), getTimeOutValue());
            default:
                throw new IllegalArgumentException("Version was not defined");
        }
    }

    /**
     * Returns the request object, in the version chosen.
     *
     * @throws IllegalArgumentException if the version requested is not supported.
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
     * Returns the request header map for this server.
     * @return {@code Map}
     */
    public Map<String,String> getRequestHeaderMap() {
        return requestHeaderMap;
    }

    @Override
    public synchronized Collection<WMTSResource> components() throws DataStoreException {
        List<WMTSResource> resources = this.resources;
        if (resources == null) {
            resources = new ArrayList<>();

            final Capabilities capa = getServiceCapabilities();
            if (capa == null) {
                throw new DataStoreException("Could not get Capabilities.");
            }

            resources = createOrUpdateResources(null, capa);
            this.resources = resources;
        }
        return Collections.unmodifiableList(resources);
    }

    private List<WMTSResource> createOrUpdateResources(List<WMTSResource> existing, Capabilities capa) {
        final List<WMTSResource> newres = new ArrayList<>();

        if (capa == null) {
            return newres;
        }
        final List<LayerType> layers = capa.getContents().getLayers();
        for (LayerType lt : layers) {
            final String name = lt.getIdentifier().getValue();
            final GenericName nn = NamesExt.create(name);

            //try to reuse existing resource
            if (existing != null) {
                WMTSResource previous = null;
                for (WMTSResource r : existing) {
                    if (nn.equals(r.getIdentifier().orElse(null))) {
                        previous = r;
                        break;
                    }
                }

                if (previous != null) {
                    previous.resetCache();
                    newres.add(previous);
                    continue;
                }
            }

            //create a new resource
            newres.add(new WMTSResource(this,nn,getImageCache()));
        }

        return newres;
    }

    @Override
    public void close() {
    }

}
