/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2012, Geomatys
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
package org.geotoolkit.storage.coverage;

import java.awt.image.RenderedImage;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.internal.feature.AttributeConvention;
import org.apache.sis.referencing.NamedIdentifier;
import org.apache.sis.storage.DataStoreException;
import org.geotoolkit.coverage.GridCoverageStack;
import org.geotoolkit.coverage.GridSampleDimension;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.apache.sis.coverage.grid.GridGeometry;
import org.geotoolkit.coverage.io.CoverageStoreException;
import org.geotoolkit.coverage.io.GridCoverageReadParam;
import org.geotoolkit.coverage.io.GridCoverageReader;
import org.geotoolkit.coverage.io.GridCoverageWriter;
import org.geotoolkit.geometry.GeometricUtilities;
import org.geotoolkit.geometry.jts.JTS;
import org.geotoolkit.internal.feature.CoverageFeature;
import org.geotoolkit.internal.feature.TypeConventions;
import org.locationtech.jts.geom.Geometry;
import org.opengis.coverage.CannotEvaluateException;
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureAssociationRole;
import org.opengis.feature.FeatureType;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;

/**
 * Resource to a coverage in the coverage store.
 *
 * @author Johann Sorel (Geomatys)
 * @module
 */
public interface GridCoverageResource extends CoverageResource, org.apache.sis.storage.GridCoverageResource {
    /**
     * Same as {@link org.apache.sis.storage.Resource} without exception.
     *
     * @todo restore the exception.
     */
    @Override
    NamedIdentifier getIdentifier();

    /**
     * Get a reader for this coverage.
     * When you have finished using it, return it using the recycle method.
     */
    @Override
    GridCoverageReader acquireReader() throws CoverageStoreException;

    /**
     * Get a writer for this coverage.
     * When you have finished using it, return it using the recycle method.
     */
    @Override
    GridCoverageWriter acquireWriter() throws CoverageStoreException;

    @Override
    public default FeatureType getType() throws DataStoreException {
        final GridCoverageReader reader = acquireReader();
        try {
            final FeatureType type = CoverageFeature.createCoverageType(reader);
            recycle(reader);
            return type;
        } catch (CoverageStoreException ex) {
            try {
                reader.dispose();
            } catch (CoverageStoreException ex2) {
                ex.addSuppressed(ex2);
            }
            throw ex;
        }
    }

    @Override
    public default Stream<Feature> features(boolean parallal) throws DataStoreException {
        final FeatureType type = getType();
        final FeatureAssociationRole role = (FeatureAssociationRole) type.getProperty(TypeConventions.RANGE_ELEMENTS_PROPERTY.toString());
        final Feature feature = type.newInstance();

        final GridCoverageReader reader = acquireReader();
        try {
            final GridGeometry gridGeom = reader.getGridGeometry(getImageIndex());
            Envelope envelope = gridGeom.getEnvelope();
            if (envelope != null) {
                Geometry geom = GeometricUtilities.toJTSGeometry(envelope, GeometricUtilities.WrapResolution.SPLIT);
                if (geom != null) {
                    JTS.setCRS(geom, gridGeom.getCoordinateReferenceSystem());
                    feature.setPropertyValue(AttributeConvention.GEOMETRY_PROPERTY.toString(), geom);
                }
            }
            recycle(reader);
        } catch (CoverageStoreException ex) {
            try {
                reader.dispose();
            } catch (CoverageStoreException ex2) {
                ex.addSuppressed(ex2);
            }
            throw ex;
        }
        feature.setProperty(CoverageFeature.coverageRecords(this,role));
        return Stream.of(feature);
    }

    @Override
    default GridCoverage read(org.apache.sis.coverage.grid.GridGeometry domain, int... range) throws DataStoreException {
        final GridCoverageReader reader = acquireReader();
        try {
            final GridCoverageReadParam param = new GridCoverageReadParam();
            param.setSourceBands(range);

            if (domain.isDefined(org.apache.sis.coverage.grid.GridGeometry.ENVELOPE)) {
                param.setEnvelope(domain.getEnvelope());
                final double[] resolution = domain.getResolution(true);
                param.setResolution(resolution);
            }

            org.geotoolkit.coverage.grid.GridCoverage cov = reader.read(getImageIndex(), param);
            while (cov instanceof GridCoverageStack) {
                //pick the first slice
                cov = (org.geotoolkit.coverage.grid.GridCoverage) ((GridCoverageStack) cov).coverageAtIndex(0);
            }

            if (!(cov instanceof GridCoverage2D)) {
                throw new DataStoreException("Read coverage is not a GridCoverage2D");
            }
            final GridCoverage2D cov2d = (GridCoverage2D) cov;
            final List<GridSampleDimension> sampleDims = Arrays.asList(cov2d.getSampleDimensions());
            final List<SampleDimension> bands = org.geotoolkit.internal.coverage.CoverageUtilities.toSIS(sampleDims);

            return new GridCoverage(domain, bands) {
                @Override
                public RenderedImage render(DirectPosition slicePoint) throws CannotEvaluateException {
                    return cov2d.getRenderedImage();
                }
            };
        } finally {
            recycle(reader);
        }
    }

    @Override
    default List<SampleDimension> getSampleDimensions() throws DataStoreException {
        final GridCoverageReader reader = acquireReader();
        try {
            final List<GridSampleDimension> dims = reader.getSampleDimensions(getImageIndex());
            return org.geotoolkit.internal.coverage.CoverageUtilities.toSIS(dims);
        } finally {
            recycle(reader);
        }
    }

    @Override
    default org.apache.sis.coverage.grid.GridGeometry getGridGeometry() throws DataStoreException {
        final GridCoverageReader reader = acquireReader();
        try {
            return reader.getGridGeometry(getImageIndex());
        } finally {
            recycle(reader);
        }
    }
}
