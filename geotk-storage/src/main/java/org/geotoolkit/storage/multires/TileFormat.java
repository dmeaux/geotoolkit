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
package org.geotoolkit.storage.multires;

import java.util.ArrayList;
import java.util.List;
import org.apache.sis.util.ArgumentChecks;
import org.apache.sis.util.iso.Types;
import org.opengis.util.CodeList;

/**
 * Describes the encoding of tiles in the resource.
 *
 * Warning : This description is a fallback until a better approach has been found.
 *
 * @author Johann Sorel (Geomatys)
 */
public class TileFormat {

    public static final TileFormat UNDEFINED = new TileFormat(null, null, Compression.UNDEFINED);

    protected String mimeType;
    protected String providerId;
    protected Compression compression;

    protected TileFormat() {
        this.compression = Compression.NONE;
    }

    public TileFormat(String mimeType, String providerId, Compression compression) {
        ArgumentChecks.ensureNonNull("compression", compression);
        this.mimeType = mimeType;
        this.providerId = providerId;
        this.compression = compression;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getProviderId() {
        return providerId;
    }

    public Compression getCompression() {
        return compression;
    }

    public static class Compression extends CodeList<Compression> {

        public static final Compression UNDEFINED = valueOf("UNDEFINED");
        public static final Compression NONE = valueOf("NONE");
        public static final Compression ZIP = valueOf("ZIP");
        public static final Compression GZ = valueOf("GZ");
        /**
         * Provide less compression then GZ but greater input and output performances.
         */
        public static final Compression LZ4 = valueOf("LZ4");


        private Compression(final String name) {
            super(name);
        }

        @Override
        public Compression[] family() {
            return values(Compression.class);
        }

        public static Compression valueOf(String code) {
            return valueOf(Compression.class, code, Compression::new).get();
        }
    }
}
