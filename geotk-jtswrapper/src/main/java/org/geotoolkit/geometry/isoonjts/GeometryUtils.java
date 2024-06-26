/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2004-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotoolkit.geometry.isoonjts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.measure.UnitConverter;
import javax.measure.Unit;

import org.geotoolkit.geometry.isoonjts.spatialschema.geometry.geometry.JTSGeometryFactory;
import org.geotoolkit.geometry.isoonjts.spatialschema.geometry.primitive.JTSPrimitiveFactory;
import org.apache.sis.referencing.CRS;
import org.apache.sis.referencing.CommonCRS;
import org.apache.sis.measure.Units;

import org.opengis.util.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;
import org.opengis.coordinate.MismatchedDimensionException;
import org.opengis.geometry.complex.CompositeCurve;
import org.opengis.geometry.coordinate.LineString;
import org.opengis.geometry.coordinate.PointArray;
import org.opengis.geometry.coordinate.Polygon;
import org.opengis.geometry.coordinate.PolyhedralSurface;
import org.opengis.geometry.primitive.Curve;
import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.geometry.primitive.Ring;
import org.opengis.geometry.primitive.SurfaceBoundary;
import org.apache.sis.geometry.Envelopes;

/**
 * @author crossley
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @module
 */
public final class GeometryUtils {

    private static final Logger LOGGER = Logger.getLogger("org.geotoolkit.geometry.isoonjts");
    private static final Envelope WHOLE_WORLD;

    static{
        CoordinateReferenceSystem crs = CommonCRS.WGS84.geographic();
        final JTSGeometryFactory geometryFactory = new JTSGeometryFactory(crs);

        final DirectPosition lowerCorner = geometryFactory.createDirectPosition(new double[] {-90,-180});
        final DirectPosition upperCorner = geometryFactory.createDirectPosition(new double[] {90,180});

        WHOLE_WORLD = geometryFactory.createEnvelope(lowerCorner, upperCorner);
    }

    /**
     * Prevents creating a new {@code GeometryUtils}.
     */
    private GeometryUtils() { }

    public static Envelope getWholeWorld() {
        return WHOLE_WORLD;
    }

    public static CoordinateReferenceSystem getCRS(final Envelope envelope) {
        return envelope.getLowerCorner().getCoordinateReferenceSystem();
    }

    // PENDING(jdc): need to respect a given Unit for the return array.
    /**
     * Converts an {@code Envelope} to a "minx, miny, maxx, maxy" array.
     * @param envelope
     * @param unit
     * @return double[]
     */
    public static double[] getBBox(Envelope envelope, final Unit unit) {

        if (unit.equals(Units.DEGREE)) {
            try {
                envelope = Envelopes.transform(envelope, CommonCRS.WGS84.normalizedGeographic());
            } catch (TransformException ex) {
                LOGGER.severe("unable to reproject the envelope:" + ex.getMessage());
            }
        }
        final double[] returnable = new double[4];

        final DirectPosition lowerCorner = envelope.getLowerCorner();
        final DirectPosition upperCorner = envelope.getUpperCorner();

        final CoordinateSystem cs = getCRS(envelope).getCoordinateSystem();
        final int xIndex = getDirectedAxisIndex(cs, AxisDirection.EAST);
        final Unit xUnit = getDirectedAxisUnit(cs, AxisDirection.EAST);
        final int yIndex = getDirectedAxisIndex(cs, AxisDirection.NORTH);
        final Unit yUnit = getDirectedAxisUnit(cs, AxisDirection.NORTH);

        //edited to use javax.measure.unit.Convertor
        UnitConverter xConverter = xUnit.getConverterTo(unit);
        UnitConverter yConverter = yUnit.getConverterTo(unit);

        returnable[0] = xConverter.convert(lowerCorner.getCoordinate(xIndex));
        returnable[1] = yConverter.convert(lowerCorner.getCoordinate(yIndex));
        returnable[2] = xConverter.convert(upperCorner.getCoordinate(xIndex));
        returnable[3] = yConverter.convert(upperCorner.getCoordinate(yIndex));

        return returnable;
    }

    public static Envelope createCRSEnvelope(
            final CoordinateReferenceSystem crs,
            final double minx,
            final double miny,
            final double maxx,
            final double maxy) {
        final JTSGeometryFactory geometryFactory = new JTSGeometryFactory(crs);

        final DirectPosition lowerCorner = geometryFactory.createDirectPosition();
        lowerCorner.setCoordinate(0, minx);
        lowerCorner.setCoordinate(1, miny);

        final DirectPosition upperCorner = geometryFactory.createDirectPosition();
        upperCorner.setCoordinate(0, maxx);
        upperCorner.setCoordinate(1, maxy);

        return geometryFactory.createEnvelope(lowerCorner, upperCorner);
    }

    /**
     * DOCUMENT ME.
     * @param crs
     * @param minx
     * @param miny
     * @param maxx
     * @param maxy
     * @param unit
     * @return Envelope
     */
    public static Envelope createEnvelope(
            final CoordinateReferenceSystem crs,
            final double minx,
            final double miny,
            final double maxx,
            final double maxy,
            final Unit unit) {
        final JTSGeometryFactory geometryFactory = new JTSGeometryFactory(crs);

        final CoordinateSystem cs = crs.getCoordinateSystem();

        final int xIndex = getDirectedAxisIndex(cs, AxisDirection.EAST);
        final Unit xUnit = getDirectedAxisUnit(cs, AxisDirection.EAST);
        final int yIndex = getDirectedAxisIndex(cs, AxisDirection.NORTH);
        final Unit yUnit = getDirectedAxisUnit(cs, AxisDirection.NORTH);

        // HACK(jdc): need to determine the order of the axes...
        /*int[] indices = CSUtils.getDirectedAxisIndices(
                crs.getCoordinateSystem(),
                new AxisDirection[] { AxisDirection.EAST, AxisDirection.NORTH });*/

        //edited to use javax.measure.unit.Convertor
        UnitConverter xConverter = xUnit.getConverterTo(unit);
        UnitConverter yConverter = yUnit.getConverterTo(unit);

        double[] lowerOrdinates = new double[crs.getCoordinateSystem().getDimension()];
        lowerOrdinates[xIndex] = xConverter.convert(minx);
        lowerOrdinates[yIndex] = yConverter.convert(miny);

        /*for (int i = 0; i < lowerOrdinates.length; i++) {
            // the east or x ordinate
            if (i == indices[0]) {
                lowerOrdinates[i] = minx;
            // the north or y ordinate
            } else if (i == indices[1]) {
                lowerOrdinates[i] = miny;
            } else {
                lowerOrdinates[i] = 0;
            }
        }*/
        double[] upperOrdinates = new double[crs.getCoordinateSystem().getDimension()];
        upperOrdinates[xIndex] = xConverter.convert(maxx);
        upperOrdinates[yIndex] = yConverter.convert(maxy);

        /*for (int i = 0; i < upperOrdinates.length; i++) {
            // the east or x ordinate
            if (i == indices[0]) {
                upperOrdinates[i] = maxx;
            // the north or y ordinate
            } else if (i == indices[1]) {
                upperOrdinates[i] = maxy;
            } else {
                upperOrdinates[i] = 0;
            }
        }*/
        final DirectPosition lowerCorner = geometryFactory.createDirectPosition(lowerOrdinates);
        final DirectPosition upperCorner = geometryFactory.createDirectPosition(upperOrdinates);

        return geometryFactory.createEnvelope(lowerCorner, upperCorner);
    }



    /**
     * DOCUMENT ME.
     * @param envelope
     * @param crs
     * @param minx
     * @param miny
     * @param maxx
     * @param maxy
     * @return boolean
     */
    public static boolean within(
            final Envelope envelope,
            final CoordinateReferenceSystem crs,
            final double minx,
            final double miny,
            final double maxx,
            final double maxy) {


        final CoordinateSystem cs = crs.getCoordinateSystem();
        final int xIndex = getDirectedAxisIndex(cs, AxisDirection.EAST);
        final int yIndex = getDirectedAxisIndex(cs, AxisDirection.NORTH);
        return ( (minx <= envelope.getMinimum(xIndex)) && (maxx >= envelope.getMaximum(xIndex)) &&
                 (miny <= envelope.getMinimum(yIndex)) && (maxy >= envelope.getMaximum(yIndex)) );

    }

    /*public static boolean overlaps(
            final Envelope envelope,
            final CoordinateReferenceSystem crs,
            final double minx,
            final double miny,
            final double maxx,
            final double maxy) {


        final CoordinateSystem cs = crs.getCoordinateSystem();
        final int xIndex = CSUtils.getDirectedAxisIndex(cs, AxisDirection.EAST);
        final int yIndex = CSUtils.getDirectedAxisIndex(cs, AxisDirection.NORTH);
        return ( (minx <= envelope.getMinimum(xIndex)) || (maxx >= envelope.getMaximum(xIndex)) ||
                 (miny <= envelope.getMinimum(yIndex)) || (maxy >= envelope.getMaximum(yIndex)) );

    }*/

    /**
     * DOCUMENT ME.
     * @param envelope1
     * @param envelope2
     * @return boolean
     */
    public static boolean equals(final Envelope envelope1, final Envelope envelope2) {
        //getLog().debug("PENDING(jdc): implement the method instead of returning false...");
        if (envelope1 == null || envelope2 == null) {
            return false;
        }
        final double[] bbox1 = getBBox(envelope1, Units.DEGREE);
        final double[] bbox2 = getBBox(envelope2, Units.DEGREE);
        return
            bbox1[0] == bbox2[0] &&
            bbox1[1] == bbox2[1] &&
            bbox1[2] == bbox2[2] &&
            bbox1[3] == bbox2[3];
    }

    /**
     * Determines whether or not the two specified Envelopes intersect.
     * Currently this method requires that the defining corners of the two Envelopes
     * must all have the same CRS, otherwise an Exception is thrown.
     * @param envelope1
     * @param envelope2
     * @return True if the Envelopes overlap
     */
    public static boolean intersects(final Envelope envelope1, final Envelope envelope2) {
        DirectPosition top1 = envelope1.getUpperCorner();
        DirectPosition bot1 = envelope1.getLowerCorner();
        DirectPosition top2 = envelope2.getUpperCorner();
        DirectPosition bot2 = envelope2.getLowerCorner();
        CoordinateReferenceSystem crs = top1.getCoordinateReferenceSystem();
        if (!crs.equals(bot1.getCoordinateReferenceSystem())
                || !crs.equals(top2.getCoordinateReferenceSystem())
                || !crs.equals(bot2.getCoordinateReferenceSystem())) {
            throw new IllegalArgumentException(
                "Current implementation of GeoemtryUtils.intersect requires that the corners of both Envelopes have the same CRS");
        }
        double minx1 = bot1.getCoordinate(0);
        double maxx1 = top1.getCoordinate(0);
        double miny1 = bot1.getCoordinate(1);
        double maxy1 = top1.getCoordinate(1);
        double minx2 = bot2.getCoordinate(0);
        double maxx2 = top2.getCoordinate(0);
        double miny2 = bot2.getCoordinate(1);
        double maxy2 = top2.getCoordinate(1);
        boolean xoverlap = minx2 < maxx1 && maxx2 > minx1;
        return xoverlap && (miny2 < maxy1 && maxy2 > miny1);
    }

    /**
     * Converts a double array to an array of {@code DirectPosition}s.
     * @param points the source data
     * @param sourceDirections the source data's axes' directions
     * @param sourceUnits the source data's axes' units
     * @param crs the target {@code CoordinateReferenceSystem}.  the {@code crs}'s
     *        dimension must match the 'dimension' in the source double array.
     * @return an array of DirectPositions
     */
    public static DirectPosition[] getDirectPositions(
            final double[] points,
            final AxisDirection[] sourceDirections,
            final Unit[] sourceUnits,
            final CoordinateReferenceSystem crs) {
        int dimension = crs.getCoordinateSystem().getDimension();
        int length = points.length / dimension;
        DirectPosition[] returnable = new DirectPosition[length];
        for (int i = 0; i < length; i++) {

            LOGGER.fine("need to make a DirectPosition");
            // umm, how am i gonna make a DirectPosition here?
            //FactoryManager.getCommonFactory().getGeometryFactory(crs).createDirectPosition
        }
        return returnable;
    }

    /**
     * Converts an array of {@code DirectPosition}s to a double array.
     * @param positions the source data
     * @param targetDirections the target data's axes' directions
     * @param targetUnits the target data's axes' units
     * @return an array of doubles
     */
    public static double[] getPoints(
            final DirectPosition[] positions,
            final AxisDirection[] targetDirections,
            final Unit[] targetUnits) {

        // make our returnable array of doubles
        int length = positions.length * targetDirections.length;
        double[] returnable = new double[length];

        // just get the first CRS from the first position
        // these should be homogenous DirectPositions
        CoordinateReferenceSystem crs = positions[0].getCoordinateReferenceSystem();
        CoordinateSystem cs = crs.getCoordinateSystem();
        int dimension = cs.getDimension();

        // find the indices for the axes we want
        int[] axisIndices = new int[targetDirections.length];
        // also need the unit converters
        UnitConverter[] converters = new UnitConverter[targetUnits.length];

        // loop through the directions that were passed in
        for (int i = 0; i < targetDirections.length; i++) {

            // loop through the cs' axes, checking their direction.
            // store the index once we've found it
            boolean notfound = true;
            for (int j = 0; notfound && j < dimension; j++) {

                // if we match, store the axis' index and the converter
                if (cs.getAxis(j).getDirection().equals(targetDirections[i])) {
                    axisIndices[i] = j;
                    converters[i] = cs.getAxis(j).getUnit().getConverterTo(targetUnits[i]);
                    notfound = false;
                }
            }
        }

        // now loop through the given directpositions
        for (int i = 0; i < positions.length; i++) {

            // loop through the position by dimension and store the converted ordinate
            for (int j = 0; j < axisIndices.length; j++) {
                returnable[(i * dimension) + j] = converters[j].convert(positions[i].getCoordinate(axisIndices[j]));
            }
        }

        // return our fancy, new array of doubles
        return returnable;
    }

    /**
     * Verifies the CRS of the specified {@code DirectPosition} is
     * WGS84, and returns it unmodified if it is.
     * If not, transforms the input into a new DirectPosition
     * with a WGS84 CRS.  Returns it as a LatLonAlt if input was LatLonAlt.
     * @param dp The DirectPosition to examine and transform if necessary
     * @return The original DirectPosition if it was already WGS84,
     * or the transformed DirectPosition.
     */
    public static DirectPosition ensureWGS84(DirectPosition dp) {
        CoordinateReferenceSystem crs = dp.getCoordinateReferenceSystem();
        int dim = crs.getCoordinateSystem().getDimension();
        CoordinateReferenceSystem bcrs = crs instanceof ProjectedCRS
            ? ((ProjectedCRS) crs).getBaseCRS() : crs;

        GeographicCRS wgs84crs = CommonCRS.WGS84.geographic3D();

        //have doubts about following line, was the commented out 2nd clause to condition doing anything - colin
        if (bcrs.equals(wgs84crs)) {    // || bcrs.equals(CRSUtils.WGS84_PROJ)) {
            return dp;
        }
        //again, what does the follllowing achieve? - colin
        if (bcrs.toWKT().indexOf("WGS84") > -1) {
            return dp;
        }
        if (bcrs instanceof GeographicCRS) {
            if (((GeographicCRS) bcrs).getDatum().equals(wgs84crs.getDatum())) {
                return dp;
            }
        }
        //not going to need CommonFactory.getCoordinateOperationFactory(),
        //can use transform util in org.geotoolkit.referencing.CRS instaed
        //CoordinateReferenceSystem crs2 = dim == 2 ? wgs84crs : CRSUtils.WGS84_PROJ;
        //same equality issues as above
        DirectPosition dp2 = new JTSGeometryFactory(wgs84crs).createDirectPosition();
        try{
            MathTransform transform = CRS.findOperation(crs, wgs84crs, null).getMathTransform();
            transform.transform(dp, dp2);
        } catch (FactoryException fe) {
            LOGGER.log(Level.WARNING,"Could not create CoordinateOperation to convert DirectPosition CRS "
                + crs.getName() + " to WGS84, using original coordinates", fe);
            //throw new IllegalArgumentException("Unconvertible coordinate CRS");
        } catch (TransformException e) {
            LOGGER.log(Level.WARNING,"Could not transform DirectPosition CRS "
                + crs.getName() + " to WGS84, using original coordinates", e);
            //throw new IllegalArgumentException("Unconvertible coordinate CRS");
        } catch (MismatchedDimensionException e) {
            // PENDING(NL): There's probably something better we can do here
            // than just throw an exception.  Normally we only care about lat and lon,
            // and if one has altitude and the other doesn't that shouldn't
            // be a showstopper.
            LOGGER.log(Level.WARNING,"Dimension mismatch prevented conversion of DirectPosition CRS "
                + crs.getName() + " to WGS84, using original coordinates", e);
            //throw new IllegalArgumentException("Unconvertible coordinate CRS");
        }
        return dp2;

        //hmm, not sure about following line,
        //think the LatLongAlt class was specific to how the polexis code works
        //and is not needed here
        //boolean wasLatLonAlt = dp instanceof LatLongAlt;
        /*
        if (wasLatLonAlt) {
            dp = commonFactory.getGeometryFactory(crs).createDirectPosition();
        }
        */
        /*
        CommonFactory commonFactory = FactoryManager.getCommonFactory();
        CoordinateOperationFactory coopFactory = commonFactory.getCoordinateOperationFactory();
        try {
            CoordinateReferenceSystem crs2 = dim == 2 ? wgs84crs : CRSUtils.WGS84_PROJ;
            CoordinateOperation coOp = coopFactory.createOperation(crs, crs2);
            DirectPosition dp2 = commonFactory.getGeometryFactory(crs2).createDirectPosition();
            dp2 = coOp.getMathTransform().transform(dp, dp2);
            if (dp2.getCoordinateReferenceSystem() != null) {
                if (wasLatLonAlt) {
                    dp2 = new LatLonAlt(dp2);
                }
                return dp2;
            } else {
                getLog().warn(
                    "Attempted to convert coordinate CRS, transform method returned DirectPosition with null CRS, using original coordinates",
                    new IllegalArgumentException("Unconvertible coordinate CRS"));
            }
        } catch (FactoryException fe) {
            getLog().warn("Could not create CoordinateOperation to convert DirectPosition CRS "
                + crs.getName() + " to WGS84, using original coordinates", fe);
            //throw new IllegalArgumentException("Unconvertible coordinate CRS");
        } catch (TransformException e) {
            getLog().warn("Could not transform DirectPosition CRS "
                + crs.getName() + " to WGS84, using original coordinates", e);
            //throw new IllegalArgumentException("Unconvertible coordinate CRS");
        } catch (MismatchedDimensionException e) {
            // PENDING(NL): There's probably something better we can do here
            // than just throw an exception.  Normally we only care about lat and lon,
            // and if one has altitude and the other doesn't that shouldn't
            // be a showstopper.
            getLog().warn("Dimension mismatch prevented conversion of DirectPosition CRS "
                + crs.getName() + " to WGS84, using original coordinates", e);
            //throw new IllegalArgumentException("Unconvertible coordinate CRS");
        } catch (RuntimeException e) {
            getLog().warn("Could not convert DirectPosition CRS "
                + crs.getName() + " to WGS84, using original coordinates", e);
            //throw e;
        }
        return dp;*/
    }

    /**
     * Populates the specified PointArray with the specified points.  Any and all preexisting
     * points in the PointArray will be overwritten.
     * @param pointArray The PointArray to be populated.  This must not be null.
     * @param newPts The list of new points
     * /
    public static void populatePointArray(PointArray pointArray, List newPts) {
        List pts = pointArray.positions();
        pts.clear();
        // PENDING(NL): Verify points are really DirectPositions --
        // convert from Positions if not
        // Probably should save this method for when we can use 1.5
        pts.addAll(newPts);
    }
*/
    /**
     * Populates the specified PointArray with the specified points.  Any and all preexisting
     * points in the PointArray will be overwritten.
     * @param pointArray The PointArray to be populated.  This must not be null.
     * @param dps The new array of points
     */
    public static void populatePointArray(final PointArray pointArray, final DirectPosition[] dps) {
        List pts = pointArray;
        pts.clear();
        int count = dps.length;
        for (int i = 0; i < count; i++) {
            pts.add(dps[i]);
        }
    }

    /**
     * Populates the specified PointArray with the specified points, starting at
     * the specified index.  Overwrites points with the specified index or higher.
     * @param pointArray The PointArray to be populated.  This must not be null.
     * @param dps The array of points to be added
     * @param startIndex The first position in the PointArray to overwrite
     * @throws ArrayIndexOutOfBoundsException if the start index is negative or
     * exceeds the number of points initially in the PointArray
     * /
    public static void populatePointArray(PointArray pointArray, DirectPosition[] dps,
            int startIndex) {
        if (startIndex < 0 || startIndex > pointArray.length()) {
            throw new ArrayIndexOutOfBoundsException("Specified start index was "
                    + startIndex + ", PointArray size was " + pointArray.length());
        }
        List pts = pointArray.positions();
        pts.clear();
        int count = dps.length;
        for (int i = 0; i < count; i++) {
            pointArray.set(i, dps[i]);
        }
    }
*/

    /**
     * Returns an array of LineCharSequences corresponding
     * to the primitive elements of the specified CompositeCurve.
     * This will be empty if the CompositeCurve is empty.
     * Throws an exception if any element of the CompositeCurve cannot be converted
     * to a LineString.
     * @param cc The CompositeCurve of interest
     * @return an array of LineCharSequences
     * @throws IllegalArgumentException if any element cannot be converted.
     * For the present version, only Curves that wrap only LineCharSequences are convertible.
     */
    public static LineString[] getLineCharSequences(final CompositeCurve cc) {
        ArrayList lsList = getLineCharSequences(cc, new ArrayList());
        if (lsList == null) {
            throw new IllegalArgumentException(
                    "Unable to convert all elements of CompositeCurve to LineString");
        }
        return (LineString[]) lsList.toArray(new LineString[lsList.size()]);
    }

    /**
     * Recursively populates the specified List with LineCharSequences corresponding
     * to the primitive elements of the specified CompositeCurve.
     * Returns null if any element of the CompositeCurve cannot be converted
     * to a LineString.
     * @param cc The CompositeCurve of interest
     * @param lsList The ArrayList to be populated
     * @return The populated List, or null if not valid
     */
    private static ArrayList getLineCharSequences(final CompositeCurve cc, final ArrayList lsList) {
        // Cast below can be removed when Types will be allowed to abandon Java 1.4 support.
        List elements = (List) cc.getGenerators();
        boolean valid = true;
        if (!elements.isEmpty()) {
            Iterator it = elements.iterator();
            LineString ls = null;
            while (it.hasNext() && valid) {
                Object element = it.next();
                if (element instanceof CompositeCurve) {
                    valid = getLineCharSequences((CompositeCurve) element, lsList) != null;
                } else if (element instanceof Curve) {
                    // PENDING(NL):  When we have arc geometries implemented,
                    // make provision to pass in real parameters for spacing and offset.
                    // What we have below essentially just returns start and end points
                    // if it's not a LineString
                    ls = ((Curve) element).asLineString(Double.MAX_VALUE, Double.MAX_VALUE);
                    if (ls != null) {
                        lsList.add(ls);
                    } else {
                        valid = false;
                    }
                } else {
                    valid = false;
                }
            }
        }
        if (valid) {
            return null;
        }
        return lsList;
    }

    public static DirectPosition[] getDirectPositions(final LineString lineString) {
        final PointArray controlPoints = lineString.getControlPoints();
        final DirectPosition[] returnable = new DirectPosition[controlPoints.size()];
        for (int i = 0; i < controlPoints.size(); i++) {
            returnable[i] = controlPoints.getDirectPosition(i, null);
        }
        return returnable;
    }

    public static DirectPosition[] getDirectPositions(final Ring ring) {
        final List directPositionList = new ArrayList();
        // Cast below can be removed when Types will be allowed to abandon Java 1.4 support.
        final List/*<Curve>*/ generators = (List) ring.getGenerators();
        for (int i = 0; i < generators.size(); i++) {
            final Curve curve = (Curve) generators.get(i);
            final List/*<CurveSegments>*/ segments = curve.getSegments();
            for (int j = 0; j < segments.size(); j++) {
                final CurveSegment curveSegment = (CurveSegment) segments.get(j);
                if (curveSegment instanceof LineString) {
                    final LineString lineString = (LineString) curveSegment;
                    final DirectPosition[] positions = getDirectPositions(lineString);
                    directPositionList.addAll(Arrays.asList(positions));
                    /*final List<Position> positions = lineString.getControlPoints().positions();
                    for (int k = 0; k < positions.size(); k++) {
                        Position position = (Position) positions.get(k);
                        directPositionList.add(position.getPosition());
                    }*/

                }
            }
        }
        if (directPositionList.size() > 0) {
            return (DirectPosition[]) directPositionList.toArray(new DirectPosition[directPositionList.size()]);
        }
        return new DirectPosition[0];
    }

    public static DirectPosition[] getExteriorDirectPositions(final Polygon polygon) {
        final SurfaceBoundary surfaceBoundary = polygon.getBoundary();
        final Ring exteriorRing = surfaceBoundary.getExterior();
        return GeometryUtils.getDirectPositions(exteriorRing);
    }

    public static DirectPosition[][] getInteriorDirectPositions(final Polygon polygon) {
        final SurfaceBoundary surfaceBoundary = polygon.getBoundary();
        final List interiorRings = surfaceBoundary.getInteriors();
        final DirectPosition[][] returnable = new DirectPosition[interiorRings.size()][];
        for (int i = 0; i < interiorRings.size(); i++) {
            returnable[i] = getDirectPositions((Ring)interiorRings.get(i));
        }
        return returnable;
    }

    public static PolyhedralSurface createPolyhedralSurface(final DirectPosition[][] patchPoints) {
        // get the crs and factories
        final CoordinateReferenceSystem crs = patchPoints[0][0].getCoordinateReferenceSystem();
        final var geometryFactory = new JTSGeometryFactory(crs);

        // create polygons from each of the arrays of directPositions
        final List polygons = new ArrayList(patchPoints.length);
        for (int i = 0; i < patchPoints.length; i++) {
            final Polygon polygon = createPolygon(patchPoints[i]);
            polygons.add(polygon);
        }
        return geometryFactory.createPolyhedralSurface(polygons);
    }

    public static Polygon createPolygon(
            final DirectPosition[] exteriorRing) {
        return createPolygon(exteriorRing, new DirectPosition[0][0]);
    }

    public static Polygon createPolygon(
            final DirectPosition[] exteriorRingPoints,
            final DirectPosition[][] interiorRingsPoints) {

        final CoordinateReferenceSystem crs = exteriorRingPoints[0].getCoordinateReferenceSystem();
        final var geometryFactory = new JTSGeometryFactory(crs);
        final var primitiveFactory = new JTSPrimitiveFactory(crs);

        final Ring exteriorRing = createRing(primitiveFactory, exteriorRingPoints);

        List interiorRingList = interiorRingsPoints.length == 0 ?
                Collections.EMPTY_LIST :
                    new ArrayList(interiorRingsPoints.length);
        for (int i = 0; i < interiorRingsPoints.length; i++) {
            final DirectPosition[] interiorRingPoints = interiorRingsPoints[i];
            interiorRingList.add(createRing(primitiveFactory,interiorRingPoints));
        }

        final SurfaceBoundary surfaceBoundary =
            primitiveFactory.createSurfaceBoundary(exteriorRing, interiorRingList);

        return geometryFactory.createPolygon(surfaceBoundary);
    }

    public static SurfaceBoundary createSurfaceBoundary(
            final DirectPosition[] exteriorRingPoints,
            final DirectPosition[][] interiorRingsPoints) {
        final CoordinateReferenceSystem crs = exteriorRingPoints[0].getCoordinateReferenceSystem();
        final JTSPrimitiveFactory primitiveFactory = new JTSPrimitiveFactory(crs);
        return createSurfaceBoundary(primitiveFactory, exteriorRingPoints, interiorRingsPoints);
    }

    private static SurfaceBoundary createSurfaceBoundary(
            final JTSPrimitiveFactory primitiveFactory,
            final DirectPosition[] exteriorRingPoints,
            final DirectPosition[][] interiorRingsPoints) {

        final Ring exteriorRing = createRing(primitiveFactory, exteriorRingPoints);

        final List interiorRingList = interiorRingsPoints.length == 0 ?
                Collections.EMPTY_LIST :
                    new ArrayList();
        for (int i = 0; i < interiorRingsPoints.length; i++) {
            interiorRingList.add(createRing(primitiveFactory, interiorRingsPoints[i]));
        }

        final SurfaceBoundary surfaceBoundary =
            primitiveFactory.createSurfaceBoundary(exteriorRing, interiorRingList);
        return surfaceBoundary;
    }

    public static Ring createRing(final DirectPosition[] points) {
        final CoordinateReferenceSystem crs = points[0].getCoordinateReferenceSystem();
        final JTSPrimitiveFactory primitiveFactory = new JTSPrimitiveFactory(crs);
        return createRing(primitiveFactory, points);
    }

    private static Ring createRing(
            final JTSPrimitiveFactory primitiveFactory,
            final DirectPosition[] points) {

        final List curveList = Collections.singletonList(createCurve(primitiveFactory, points));

        final Ring ring = primitiveFactory.createRing(curveList);
        return ring;
    }

    public static Curve createCurve(final DirectPosition[] points) {
        final CoordinateReferenceSystem crs = points[0].getCoordinateReferenceSystem();
        final var primitiveFactory = new JTSPrimitiveFactory(crs);
        return createCurve(primitiveFactory, points);
    }

    private static Curve createCurve(
            final JTSPrimitiveFactory primitiveFactory,
            final DirectPosition[] points) {

        final var geometryFactory = new JTSGeometryFactory(primitiveFactory.getCoordinateReferenceSystem());

        final List curveSegmentList = Collections.singletonList(createLineString(geometryFactory, points));

        final Curve curve = primitiveFactory.createCurve(curveSegmentList);
        return curve;
    }

    public static LineString createLineString(final DirectPosition[] points) {
        final CoordinateReferenceSystem crs = points[0].getCoordinateReferenceSystem();
        final var geometryFactory = new JTSGeometryFactory(crs);
        return createLineString(geometryFactory, points);
    }

    private static LineString createLineString(
            final JTSGeometryFactory geometryFactory,
            final DirectPosition[] points) {

        final LineString lineString = geometryFactory.createLineString(new ArrayList(Arrays.asList(points)));
        return lineString;
    }

    /**
     * Check if a reference coordinate system has the expected number of dimensions.
     * - code lifted from com.polexis.referencing.CRSUtils - thanks Jesse!
     * - not sure i see the need for both this method and CRS.ensureDimensionMatch()
     *
     * @param name     The argument name.
     * @param crs      The coordinate reference system to check.
     * @param expected The expected number of dimensions.
     */
    public static void checkDimension(
            final String name,
            final CoordinateReferenceSystem crs,
            final int expected) {
        if (crs != null) {
            final int actual = crs.getCoordinateSystem().getDimension();
            if (actual != expected) {
                throw new IllegalArgumentException(/*Resources.format(
                 ResourceKeys.ERROR_MISMATCHED_DIMENSION_3,
                 name, new Integer(actual), new Integer(expected))*/"");
            }
        }
    }

    /**
     * Convenience method for checking object dimension validity.
     * This method is usually invoked for argument checking.
     *- code lifted from com.polexis.referencing.CRSUtils - thanks Jesse!
     *
     * @param  name The name of the argument to check.
     * @param  dimension The object dimension.
     * @param  expectedDimension The Expected dimension for the object.
     * @throws MismatchedDimensionException if the object doesn't have the expected dimension.
     */
    public static void ensureDimensionMatch(
            final String name,
            final int dimension,
            final int expectedDimension) throws MismatchedDimensionException {
        if (dimension != expectedDimension) {
            throw new MismatchedDimensionException(name + " does not have " + dimension + "dimension(s)"
                                                    /*
                                                    * Resources.format(
                                                    * ResourceKeys.ERROR_MISMATCHED_DIMENSION_3,
                                                    * name, new
                                                    * Integer(dimension), new
                                                    * Integer(expectedDimension))
                                                    */);
        }
    }

        /**
     * Returns the {@code CoordinateSystemAxis} with the given {@code AxisDirection}.
     * @param cs the {@code CoordinateSystem} to check
     * @param direction the {@code AxisDirection} to check for
     * @return CoordinateSystemAxis
     */
    public static CoordinateSystemAxis getDirectedAxis(
            final CoordinateSystem cs,
            final AxisDirection direction) {

        int dimension = cs.getDimension();
        for (int i = 0; i < dimension; i++) {
            if (cs.getAxis(i).getDirection().equals(direction)) {
                return cs.getAxis(i);
            }
        }
        return null;
    }

    /*
     * reurns the index of an axis in a given coordinate system,
     * axes are specified using org.opengis.referencing.cs.AxisDirection
     * used by JTS geometry wrappers
     * - code from com.polexis.referencing.cs.CSUtils
     * - is AbstractCS a more appropriate place for this?
     */
    public static int getDirectedAxisIndex(
         final CoordinateSystem cs,
         final AxisDirection direction) {
       int dimension = cs.getDimension();
       for (int i = 0; i < dimension; i++) {
       if (cs.getAxis(i).getDirection().equals(direction)) {
            return i;
            }
        }
        return -1;
    }

    /*
     * reurns the index of an axis in a given coordinate system,
     * axes are specified using org.opengis.referencing.cs.AxisDirection
     * used by JTS geometry wrappers
     * - code from com.polexis.referencing.cs.CSUtils
     * - is AbstractCS a more appropriate place for this?
     */
    public static Unit getDirectedAxisUnit(
            final CoordinateSystem cs,
            final AxisDirection direction) {
        CoordinateSystemAxis axis = getDirectedAxis(cs, direction);
        if (axis != null) {
            return axis.getUnit();
        }
        return null;
    }

}

