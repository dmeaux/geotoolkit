/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvs/ctree/LiteGO1/src/jar/com/polexis/lite/spatialschema/geometry/primitive/SurfaceImpl.java,v $
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.geotoolkit.geometry.isoonjts.spatialschema.geometry.primitive;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.geotoolkit.geometry.isoonjts.spatialschema.geometry.JTSGeometry;
import org.geotoolkit.geometry.isoonjts.JTSUtils;
import org.geotoolkit.geometry.isoonjts.spatialschema.geometry.AbstractJTSGeometry;

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.complex.CompositeSurface;
import org.opengis.geometry.primitive.OrientableSurface;
import org.opengis.geometry.primitive.Primitive;
import org.opengis.geometry.primitive.Surface;
import org.opengis.geometry.primitive.SurfacePatch;
import org.opengis.geometry.primitive.SurfaceBoundary;


/**
 * Surface with a positive orientation.
 * {@code Surface} is a subclass of {@link Primitive} and is the basis for 2-dimensional
 * geometry. Unorientable surfaces such as the M&ouml;bius band are not allowed. The orientation of
 * a surface chooses an "up" direction through the choice of the upward normal, which, if the
 * surface is not a cycle, is the side of the surface from which the exterior boundary appears
 * counterclockwise. Reversal of the surface orientation reverses the curve orientation of each
 * boundary component, and interchanges the conceptual "up" and "down" direction of the surface.
 * If the surface is the boundary of a solid, the "up" direction is usually outward. For closed
 * surfaces, which have no boundary, the up direction is that of the surface patches, which must
 * be consistent with one another. Its included {@linkplain SurfacePatch surface patches} describe
 * the interior structure of a {@code Surface}.
 *
 * <blockquote><font size=2>
 * <strong>NOTE:</strong> Other than the restriction on orientability, no other "validity" condition is required for GM_Surface.
 * </font></blockquote>
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public class JTSSurface extends AbstractJTSGeometry implements Surface {
    protected List<JTSSurfacePatch> patches;

    public JTSSurface() {
        this(null);
    }

    public JTSSurface(final CoordinateReferenceSystem crs) {
        super(crs);
        patches = new ArrayList();
    }

    /**
     * {@inheritDoc }
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<JTSSurfacePatch> getPatches() {
        return patches;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public SurfaceBoundary getBoundary() {
        return (SurfaceBoundary) super.getBoundary();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public double [] getUpNormal(final DirectPosition point) {
        return new double [] { 0, 0, 1 };
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public double getPerimeter() {
        return getJTSGeometry().getBoundary().getLength();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public double getArea() {
        return getJTSGeometry().getArea();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public CompositeSurface getComposite() {
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int getOrientation() {
        return 0;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Surface getPrimitive() {
        return this;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Set getComplexes() {
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Set getContainingPrimitives() {
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public OrientableSurface[] getProxy() {
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Set getContainedPrimitives() {
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected org.locationtech.jts.geom.Geometry computeJTSPeer() {
        if (patches.size() > 1) {
            //throw new UnsupportedOperationException("This implementation does not support surfaces with multiple patches.");
            final org.locationtech.jts.geom.Polygon[] polygons =
                new org.locationtech.jts.geom.Polygon[patches.size()];
            for (int i = 0; i < patches.size(); i++) {
                final JTSGeometry jtsGeometry = (JTSGeometry) patches.get(i);
                polygons[i] = (org.locationtech.jts.geom.Polygon) jtsGeometry.getJTSGeometry();
            }
            return JTSUtils.GEOMETRY_FACTORY.createMultiPolygon(polygons);
        }
        return ((JTSGeometry) patches.get(0)).getJTSGeometry();
    }
}
