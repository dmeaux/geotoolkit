/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2011-2018, Geomatys
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
package org.geotoolkit.wmts.model;

import java.util.Collections;
import java.util.SortedMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.sis.referencing.CRS;
import org.apache.sis.referencing.crs.AbstractCRS;
import org.apache.sis.referencing.cs.AxesConvention;
import org.geotoolkit.storage.multires.AbstractTileMatrixSet;
import org.geotoolkit.storage.multires.ScaleSortedMap;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.FactoryException;
import org.opengis.util.GenericName;

/**
 *
 * @author Johann Sorel (Geomatys)
 * @module
 */
public class WMTSTileMatrixSet extends AbstractTileMatrixSet {

    private final WMTSTileMatrixSets set;
    private final org.geotoolkit.wmts.xml.v100.TileMatrixSetLink link;
    private final org.geotoolkit.wmts.xml.v100.TileMatrixSet matrixset;
    private CoordinateReferenceSystem crs;
    private final SortedMap<GenericName,WMTSTileMatrix> mosaics;

    public WMTSTileMatrixSet(final WMTSTileMatrixSets set, final org.geotoolkit.wmts.xml.v100.TileMatrixSetLink link){
        super(null);
        this.set = set;
        this.link = link;
        matrixset = set.getCapabilities().getContents().getTileMatrixSetByIdentifier(link.getTileMatrixSet());

        final String crsstr = matrixset.getSupportedCRS();
        try {
            // WMTS is made for display like WMS, so longitude is expected to be on the X axis.
            // Note : this is not written in the spec.
            crs = AbstractCRS.castOrCopy(CRS.forCode(crsstr)).forConvention(AxesConvention.RIGHT_HANDED);
        } catch (NoSuchAuthorityCodeException ex) {
            try {
                crs = CRS.forCode("EPSG:"+crsstr);
            } catch (Exception e) {
                e.addSuppressed(ex);
                Logger.getLogger("org.geotoolkit.wmts.model").log(Level.WARNING, null, e);
            }
        } catch (FactoryException ex) {
            Logger.getLogger("org.geotoolkit.wmts.model").log(Level.WARNING, null, ex);
        }

        final org.geotoolkit.wmts.xml.v100.TileMatrixSetLimits limits = link.getTileMatrixSetLimits();

        final ScaleSortedMap<WMTSTileMatrix> m = new ScaleSortedMap<>();
        for (int i=0,n=matrixset.getTileMatrix().size();i<n;i++) {
            final org.geotoolkit.wmts.xml.v100.TileMatrix matrix = matrixset.getTileMatrix().get(i);
            org.geotoolkit.wmts.xml.v100.TileMatrixLimits limit = null;
            if(limits != null){
                for(org.geotoolkit.wmts.xml.v100.TileMatrixLimits li : limits.getTileMatrixLimits()){
                    if(li.getTileMatrix().equals(matrix.getIdentifier().getValue())){
                        limit = li;
                        break;
                    }
                }
            }
            m.insertByScale(new WMTSTileMatrix(this, matrix, limit));
        }
        this.mosaics = Collections.unmodifiableSortedMap(m);
    }

    public org.geotoolkit.wmts.xml.v100.TileMatrixSet getMatrixset() {
        return matrixset;
    }

    public WMTSTileMatrixSets getPyramidSet() {
        return set;
    }

    @Override
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return crs;
    }

    @Override
    public SortedMap<GenericName,WMTSTileMatrix> getTileMatrices() {
        return mosaics;
    }

}
