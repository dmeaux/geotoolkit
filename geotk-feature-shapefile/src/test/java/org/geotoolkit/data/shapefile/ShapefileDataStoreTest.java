/*
 *    GeotoolKit - An Open source Java GIS Toolkit
 *    http://geotoolkit.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotoolkit.data.shapefile;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import org.apache.sis.feature.builder.AttributeRole;
import org.apache.sis.feature.builder.FeatureTypeBuilder;
import org.apache.sis.referencing.CRS;
import org.apache.sis.referencing.CommonCRS;
import org.apache.sis.util.Utilities;
import org.geotoolkit.ShapeTestData;
import org.geotoolkit.data.shapefile.shp.ShapefileHeader;
import org.geotoolkit.feature.FeatureExt;
import org.geotoolkit.filter.FilterUtilities;
import org.geotoolkit.geometry.jts.JTSEnvelope2D;
import org.geotoolkit.io.wkt.PrjFiles;
import org.geotoolkit.storage.feature.FeatureCollection;
import org.geotoolkit.storage.feature.FeatureIterator;
import org.geotoolkit.storage.feature.FeatureReader;
import org.geotoolkit.storage.feature.FeatureWriter;
import org.geotoolkit.storage.feature.query.Query;
import org.geotoolkit.test.TestData;
import org.geotoolkit.util.NamesExt;
import static org.junit.Assert.*;
import org.junit.Assume;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.opengis.feature.Feature;
import org.opengis.feature.FeatureType;
import org.opengis.feature.PropertyType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 *
 * @author Ian Schneider
 * @module
 */
public class ShapefileDataStoreTest extends AbstractTestCaseSupport {

    static final String STATE_POP = "shapes/statepop.shp";
    static final String STREAM = "shapes/stream.shp";
    static final String DANISH = "shapes/danish_point.shp";
    static final String CHINESE = "shapes/chinese_poly.shp";
    static final FilterFactory ff = FilterUtilities.FF;

    protected FeatureCollection loadFeatures(final String resource, Query query)
            throws Exception {
        assertNotNull(query);

        URL url = ShapeTestData.url(resource);
        ShapefileFeatureStore s = new ShapefileFeatureStore(url.toURI(), true, null);

        final Query builder = new Query();
        builder.copy(query);
        builder.setTypeName(s.getName());
        query = builder;

        return s.createSession(true).getFeatureCollection(query);
    }

    protected FeatureCollection loadFeatures(final String resource, final Charset charset, final Query q) throws Exception {

        URL url = ShapeTestData.url(resource);
        ShapefileFeatureStore s = new ShapefileFeatureStore(url.toURI(), false, charset);

        if(q == null){
            return s.createSession(true).getFeatureCollection(new Query(s.getName()));
        }else{
            return s.createSession(true).getFeatureCollection(q);
        }
    }

    protected FeatureCollection loadFeatures(final ShapefileFeatureStore s)
            throws Exception {
        return s.createSession(true).getFeatureCollection(new Query(s.getName()));
    }

    @Test
    public void testLoad() throws Exception {
        loadFeatures(STATE_POP, new Query(NamesExt.create("statepop")));
    }

    @Test
    public void testLoadDanishChars() throws Exception {
        FeatureCollection fc = loadFeatures(DANISH, new Query(NamesExt.create("danish_point")));
        Feature first = firstFeature(fc);

        // Charlotte (but with the o is stroked)
        assertEquals("Charl\u00F8tte", first.getPropertyValue("TEKST1"));
    }

    @Test
    public void testLoadChineseChars() throws Exception {
        try {
            FeatureCollection fc = loadFeatures(CHINESE, Charset
                    .forName("GB18030"), null);
            Feature first = firstFeature(fc);
            String s = (String) first.getPropertyValue("NAME");
            assertEquals("\u9ed1\u9f99\u6c5f\u7701", s);
        } catch (UnsupportedCharsetException notInstalledInJRE) {
                // this just means you have not installed
                // chinese support into your JRE
                // (as such it represents a bad configuration
                //  rather than a test failure)
                // we only wanted to ensure that if you have Chinese support
                // available - GeotoolKit can use it
                Assume.assumeNoException(notInstalledInJRE);
        }
    }

    @Test
    public void testSchema() throws Exception {
        URL url = ShapeTestData.url(STATE_POP);
        ShapefileFeatureStore shapeFeatureStore = new ShapefileFeatureStore(url.toURI());
        String typeName = shapeFeatureStore.getNames().iterator().next().toString();
        FeatureType schema = shapeFeatureStore.getFeatureType(typeName);
        Collection<? extends PropertyType> attributes = schema.getProperties(true);
        assertEquals("Number of Attributes", 256, attributes.size());
    }

    @Test
    public void testSpacesInPath() throws Exception {
        URL u = TestData.url(AbstractTestCaseSupport.class, "folder with spaces/pointtest.shp");
        File f = new File(URLDecoder.decode(u.getFile(), "UTF-8"));
        assertTrue(f.exists());
        ShapefileFeatureStore s = new ShapefileFeatureStore(u.toURI());
        loadFeatures(s);
    }

    /**
     * Test envelope versus old DataSource
     */
    @Test
    public void testEnvelope() throws Exception {
        FeatureCollection features = loadFeatures(STATE_POP, new Query(NamesExt.create("statepop")));
        ShapefileFeatureStore s = new ShapefileFeatureStore(ShapeTestData.url(STATE_POP).toURI());
        String typeName = s.getName().toString();
        FeatureCollection all = s.createSession(true).getFeatureCollection(new Query(s.getName()));

        assertEquals(features.getEnvelope(), all.getEnvelope());
    }

    @Test
    public void testLoadAndVerify() throws Exception {
        FeatureCollection features = loadFeatures(STATE_POP, new Query(NamesExt.create("statepop")));
        // FeatureCollection<SimpleFeatureType, SimpleFeature> features = loadFeaturesM2();
        int count = features.size();

        assertTrue("Have features", count > 0);
        // assertEquals("Number of Features loaded",49,features.size()); // FILE
        // (correct value)
        // assertEquals("Number of Features loaded",3, count); // JAR

        Feature firstFeature = firstFeature(features);
        FeatureType schema = firstFeature.getType();
        assertNotNull(FeatureExt.getDefaultGeometry(schema));
        assertEquals("Number of Attributes", 256, schema.getProperties(true).size());
        assertEquals("Value of statename is wrong", "Illinois", firstFeature
                .getPropertyValue("STATE_NAME"));
        assertEquals("Value of land area is wrong", 143986.61,
                ((Double) firstFeature.getPropertyValue("LAND_KM")).doubleValue(),
                0.001);
    }

    @Test
    public void testCreateSchemaWithEmptyCRS() throws Exception {
        File file = new File("test.shp");
        final URI toURI = file.toURI();
        final ShapefileFeatureStore ds = new ShapefileFeatureStore(toURI);

        final FeatureTypeBuilder ftb = new FeatureTypeBuilder();
        ftb.setName("test");
        ftb.addAttribute(MultiPolygon.class).setName("geom").addRole(AttributeRole.DEFAULT_GEOMETRY);
        final FeatureType toCreate = ftb.build();
        ds.createFeatureType(toCreate);

        assertEquals("test", ds.getName().toString());

        file.deleteOnExit();
        file = new File("test.dbf");
        file.deleteOnExit();
        file = new File("test.shp");
        file.deleteOnExit();

        file = new File("test.prj");
        if (file.exists())
            file.deleteOnExit();

        file = new File("test.shx");
        if (file.exists()){
            file.deleteOnExit();
        }

        file = new File("test.cpg");
        if (file.exists()){
            file.deleteOnExit();
        }
    }

    @Test
    public void testCreateSchemaWithCRS() throws Exception {
        File file = new File("test.shp");
        URI toURI = file.toURI();
        ShapefileFeatureStore ds = new ShapefileFeatureStore(toURI);
        final FeatureTypeBuilder ftb = new FeatureTypeBuilder();
        ftb.setName("test");
        ftb.addAttribute(MultiPolygon.class).setName("geom").setCRS(CRS.forCode("EPSG:32615")).addRole(AttributeRole.DEFAULT_GEOMETRY);
        FeatureType featureType = ftb.build();
        CoordinateReferenceSystem crs = FeatureExt.getCRS(featureType);
        assertNotNull( crs );

        ds.createFeatureType(featureType);

        assertEquals("test", ds.getName().tip().toString());

        CoordinateReferenceSystem crs2 = FeatureExt.getCRS(ds.getFeatureType("test"));
        assertNotNull( crs2 );
        assertTrue(Utilities.equalsIgnoreMetadata(crs, crs2));

        file.deleteOnExit();
        file = new File("test.dbf");
        file.deleteOnExit();
        file = new File("test.shp");
        file.deleteOnExit();

        file = new File("test.prj");
        if (file.exists())
            file.deleteOnExit();

        file = new File("test.shx");
        if (file.exists()){
            file.deleteOnExit();
        }

        file = new File("test.prj");
        if( file.exists()){
            file.deleteOnExit();
        }
        file = new File("test.cpg");
        if( file.exists()){
            file.deleteOnExit();
        }
    }

    /**
     * Create a set of features, then remove every other one, updating the
     * remaining. Test for removal and proper update after reloading...
     */
    @Test
    public void testUpdating() throws Throwable {
        ShapefileFeatureStore sds = createDataStore();
        loadFeatures(sds);

        FeatureWriter writer = null;
        try {
            writer = sds.getFeatureWriter(new Query(sds.getNames().iterator().next()));
            while (writer.hasNext()) {
                Feature feat = writer.next();
                Byte b = (Byte) feat.getPropertyValue("b");
                if (b.byteValue() % 2 == 0) {
                    writer.remove();
                } else {
                    feat.setPropertyValue("b", new Byte((byte) -1));
                }
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        FeatureCollection fc = loadFeatures(sds);

        assertEquals(10, fc.size());
        FeatureIterator i = fc.iterator();
        for (; i.hasNext();) {
            assertEquals(-1, ((Byte) (i.next()).getPropertyValue("b")).byteValue());
        }
        i.close();
    }

    /**
     * Create a test file, then continue removing the first entry until there
     * are no features left.
     */
    @Test
    public void testRemoveFromFrontAndClose() throws Throwable {
        ShapefileFeatureStore sds = createDataStore();

        int idx = loadFeatures(sds).size();

        while (idx > 0) {
            FeatureWriter writer = null;

            try {
                writer = sds.getFeatureWriter(new Query(sds.getNames().iterator().next()));
                writer.next();
                writer.remove();
            } finally {
                if (writer != null) {
                    writer.close();
                    writer = null;
                }
            }
            assertEquals(--idx, loadFeatures(sds).size());
        }
    }

    /**
     * Create a test file, then continue removing the first entry until there
     * are no features left.
     */
    @Test
    public void testRemoveFromFrontAndCloseTransaction() throws Throwable {
        ShapefileFeatureStore sds = createDataStore();

        int idx = loadFeatures(sds).size();

        while (idx > 0) {
            FeatureWriter writer = null;

            try {
                writer = sds.getFeatureWriter(new Query(sds.getName()));
//                System.out.println("classe : " + writer.getClass());
                writer.next();
                writer.remove();
            } finally {
                if (writer != null) {
                    writer.close();
                    writer = null;
                }
            }
            assertEquals(--idx, loadFeatures(sds).size());
        }
    }

    /**
     * Create a test file, then continue removing the last entry until there are
     * no features left.
     */
    @Test
    public void testRemoveFromBackAndClose() throws Throwable {
            ShapefileFeatureStore sds = createDataStore();

            int idx = loadFeatures(sds).size();

            while (idx > 0) {
                FeatureWriter writer = null;
                try {
                    writer = sds.getFeatureWriter(new Query(sds.getName()));
                    while (writer.hasNext()) {
                        writer.next();
                    }
                    writer.remove();
                } finally {
                    if (writer != null) {
                        writer.close();
                        writer = null;
                    }
                }
                assertEquals(--idx, loadFeatures(sds).size());
            }
    }

    @Test
    public void testWriteShapefileWithNoRecords() throws Exception {
        final FeatureTypeBuilder ftb = new FeatureTypeBuilder().setName("whatever");
        ftb.addAttribute(Polygon.class).setName("a").addRole(AttributeRole.DEFAULT_GEOMETRY);
        ftb.addAttribute(String.class).setName("b");
        FeatureType featureType = ftb.build();

        File tempFile = getTempFile();
        ShapefileFeatureStore shapefileFeatureStore = new ShapefileFeatureStore(tempFile.toURI());
        shapefileFeatureStore.createFeatureType(featureType);

        FeatureWriter featureWriter = shapefileFeatureStore.getFeatureWriter(new Query(shapefileFeatureStore.getName()));

        // don't add any features to the data store....

        // this should open a shapefile with no records. Not sure about the
        // semantics of this,
        // but it's meant to be used in the context of a FeatureCollection
        // iteration,
        // where the FeatureCollection<SimpleFeatureType, SimpleFeature> has nothing in it.
        featureWriter.close();
    }

    @Test
    public void testAttributesWriting() throws Exception {
        Collection<Feature> features = createFeatureCollection();
        File tmpFile = getTempFile();
        tmpFile.createNewFile();
        ShapefileFeatureStore s = new ShapefileFeatureStore(tmpFile.toURI());
        writeFeatures(s, features);
    }

    @Test
    public void testWriteReadBigNumbers() throws Exception {
        // open feature type
        final FeatureTypeBuilder ftb = new FeatureTypeBuilder().setName("junk");
        ftb.addAttribute(Point.class).setName("a").addRole(AttributeRole.DEFAULT_GEOMETRY);
        ftb.addAttribute(BigDecimal.class).setName("b");
        ftb.addAttribute(BigInteger.class).setName("c");
        FeatureType type = ftb.build();


        Collection<Feature> features = new ArrayList<>();

        BigInteger bigInteger = new BigInteger("1234567890123456789");
        BigDecimal bigDecimal = new BigDecimal(bigInteger, 2);

        final Feature feature = type.newInstance();
        feature.setPropertyValue("a", org.geotoolkit.geometry.jts.JTS.getFactory().createPoint(new Coordinate(1, -1)));
        feature.setPropertyValue("b", bigDecimal);
        feature.setPropertyValue("c", bigInteger);

        features.add(feature);

        // store features
        File tmpFile = getTempFile();
        tmpFile.createNewFile();
        ShapefileFeatureStore s = new ShapefileFeatureStore(tmpFile.toURI());
        writeFeatures(s, features);

        try ( // read them back
                FeatureReader reader = s.getFeatureReader(new Query(type.getName()))) {
            Feature f = reader.next();

            assertEquals("big decimal", bigDecimal.doubleValue(), ((Number) f
                    .getPropertyValue("b")).doubleValue(), 0.00001);
            assertEquals("big integer", bigInteger.longValue(), ((Number) f
                    .getPropertyValue("c")).longValue(), 0.00001);
        }
    }

    @Test
    public void testGeometriesWriting() throws Exception {

        String[] wktResources = new String[] { "point", "multipoint", "line",
                "multiline", "polygon", "multipolygon" };

        for (int i = 0; i < wktResources.length; i++) {
            Geometry geom = readGeometry(wktResources[i]);
            String testName = wktResources[i];
            try {
                runWriteReadTest(geom, false);
                make3D(geom);
                testName += "3d";
                runWriteReadTest(geom, true);
            } catch (Throwable e) {
                throw new Exception("Error in " + testName, e);
            }
        }
    }

    @Test
    public void testGetCount() throws Exception {
        assertTrue(copyShapefiles(STREAM).canRead());
        // The following test seems to fail in the URL point into the JAR file.
        ShapefileFeatureStore store = (ShapefileFeatureStore) new ShapefileProvider()
                .createDataStore(TestData.url(AbstractTestCaseSupport.class, STREAM).toURI());
        int count = 0;
        try (FeatureReader reader = store.getFeatureReader(new Query(store.getNames().iterator().next()))) {
            while (reader.hasNext()) {
                count++;
                reader.next();
            }
            assertEquals(count, store.getCount(new Query(store.getNames().iterator().next())));
        }
    }

    /**
     * Checks if feature reading optimizations still allow to execute the
     * queries or not
     */
    @Test
    public void testGetReaderOptimizations() throws Exception {
        URL url = ShapeTestData.url(STATE_POP);
        ShapefileFeatureStore s = new ShapefileFeatureStore(url.toURI());

        // attributes other than geometry can be ignored here
        Query builder = new Query();
        builder.setTypeName(s.getNames().iterator().next());
        builder.setSelection(Filter.include());
        builder.setProperties(new String[]{ShapefileHeader.ATTRIBUTE_NAME});
        Query query = builder;

         FeatureReader reader = s.getFeatureReader(query);
        assertEquals(1, reader.getFeatureType().getProperties(true).size());
        assertEquals(ShapefileHeader.ATTRIBUTE_NAME, reader.getFeatureType().getProperties(true).iterator().next().getName().tip().toString());

        // here too, the filter is using the geometry only
        GeometryFactory gc = org.geotoolkit.geometry.jts.JTS.getFactory();
        LinearRing ring = gc.createLinearRing(new Coordinate[] {
                new Coordinate(0, 0), new Coordinate(10, 0),
                new Coordinate(10, 10), new Coordinate(0, 10),
                new Coordinate(0, 0) });
        Polygon polygon = gc.createPolygon(ring, null);

        JTSEnvelope2D bounds = new JTSEnvelope2D(polygon
                .getEnvelopeInternal(), null);
        Filter gf = ff.bbox(ff.property(ShapefileHeader.ATTRIBUTE_NAME), bounds);

        builder = new Query();
        builder.setTypeName(s.getNames().iterator().next());
        builder.setSelection(gf);
        builder.setProperties(new String[]{ShapefileHeader.ATTRIBUTE_NAME});
        query = builder;

        reader.close();
        reader = s.getFeatureReader(query);
        assertEquals(1, reader.getFeatureType().getProperties(true).size());
        assertEquals(ShapefileHeader.ATTRIBUTE_NAME, reader.getFeatureType().getProperties(true).iterator().next().getName().tip().toString());

        reader.close();

        // here not, we need state_name in the feature type, so open the dbf
        // file please
        Filter cf = ff.equal(ff.property("STATE_NAME"), ff.literal("Illinois"));

        builder = new Query();
        builder.setTypeName(s.getNames().iterator().next());
        builder.setSelection(cf);
        builder.setProperties(new String[]{ShapefileHeader.ATTRIBUTE_NAME});
        query = builder;

        reader = s.getFeatureReader(query);
        assertEquals(1, reader.getFeatureType().getProperties(true).size());
        assertEquals(ShapefileHeader.ATTRIBUTE_NAME, reader.getFeatureType().getProperties(true).iterator().next().getName().tip().toString());
        reader.close();
    }

    @Test
    public void testWrite() throws Exception {
        // open feature type
        final FeatureTypeBuilder ftb = new FeatureTypeBuilder();
        ftb.setName("junk");
        ftb.addAttribute(Point.class).setName("a").addRole(AttributeRole.DEFAULT_GEOMETRY);
        ftb.addAttribute(BigDecimal.class).setName("b");
        ftb.addAttribute(BigInteger.class).setName("c");
        FeatureType type = ftb.build();

        BigInteger bigInteger = new BigInteger("1234567890123456789");
        BigDecimal bigDecimal = new BigDecimal(bigInteger, 2);

        Feature feature = type.newInstance();
        feature.setPropertyValue("a", org.geotoolkit.geometry.jts.JTS.getFactory().createPoint(new Coordinate(1, -1)));
        feature.setPropertyValue("b",bigDecimal);
        feature.setPropertyValue("c",bigInteger);

        // store features
        File tmpFile = getTempFile();
        tmpFile.createNewFile();
        ShapefileFeatureStore s = new ShapefileFeatureStore(tmpFile.toURI());
        s.createFeatureType(type);

//        // was failing in GEOT-2427
//        FeatureWriter<SimpleFeatureType, SimpleFeature> writer = s.getFeatureWriter(s.getNames().iterator().next(), Filter.include());
//        SimpleFeature feature1 = writer.next();
//        writer.close();

    }

    @Test
    public void testReadQPJ() throws Exception {
        final URL shpUrl = this.getClass().getResource("/org/geotoolkit/test-data/shapes/utf8.shp");
        final FeatureType ft = new ShapefileFeatureStore(shpUrl.toURI()).getFeatureType();
        assertNotNull("No feature type loaded !", ft);
        final CoordinateReferenceSystem crs = FeatureExt.getCRS(ft);
        assertNotNull("No CRS loaded !", crs);
        final URL qpjUrl = this.getClass().getResource("/org/geotoolkit/test-data/shapes/utf8.qpj");
        assertEquals("CRS loaded by shapefile store is not the one contained in qpj !", crs, PrjFiles.read(qpjUrl));
    }

    /**
     * Creates feature collection with all the stuff we care about from simple
     * types, to Geometry and date.
     * <p>
     * As we care about supporting more stuff please add on to the end of this
     * list...
     *
     * @return FeatureCollection<SimpleFeature> For use in testing.
     * @throws Exception
     */
    private Collection<Feature> createFeatureCollection() throws Exception {
        FeatureType featureType = createExampleSchema();

        Collection<Feature> features = new ArrayList<>();
        for (int i = 0, ii = 20; i < ii; i++) {
            final Feature feature = featureType.newInstance();
            feature.setPropertyValue("a",org.geotoolkit.geometry.jts.JTS.getFactory().createPoint(new Coordinate(1, -1)));
            feature.setPropertyValue("b",new Byte((byte) i));
            feature.setPropertyValue("c",new Short((short) i));
            feature.setPropertyValue("d",new Double(i));
            feature.setPropertyValue("e",new Float(i));
            feature.setPropertyValue("f",new String(i + " "));
            feature.setPropertyValue("g",new Date(i));
            feature.setPropertyValue("h",new Boolean(true));
            feature.setPropertyValue("i",new Integer(22));
            feature.setPropertyValue("j",new Long(1234567890123456789L));
            feature.setPropertyValue("k",new BigDecimal(new BigInteger("12345678901234567890123456789"), 2));
            feature.setPropertyValue("l",new BigInteger("12345678901234567890123456789"));
            features.add(feature);
        }
        return features;
    }

    private FeatureType createExampleSchema() {
        FeatureTypeBuilder build = new FeatureTypeBuilder();
        build.setName("junk");
        build.addAttribute(Point.class).setName("a").setCRS(CommonCRS.WGS84.normalizedGeographic());
        build.addAttribute(Byte.class).setName("b");
        build.addAttribute(Short.class).setName("c");
        build.addAttribute(Double.class).setName("d");
        build.addAttribute(Float.class).setName("e");
        build.addAttribute(String.class).setName("f");
        build.addAttribute(Date.class).setName("g");
        build.addAttribute(Boolean.class).setName("h");
        build.addAttribute(Number.class).setName("i");
        build.addAttribute(Long.class).setName("j");
        build.addAttribute(BigDecimal.class).setName("k");
        build.addAttribute(BigInteger.class).setName("l");
        return build.build();
    }

    private void make3D(final Geometry g) {
        Coordinate[] c = g.getCoordinates();
        for (int i = 0, ii = c.length; i < ii; i++) {
            c[i].z = 42 + i;
        }
    }

    private void writeFeatures(final ShapefileFeatureStore s, final Collection<Feature> fc)
            throws Exception {

        final FeatureType sft = fc.iterator().next().getType();

        s.createFeatureType(sft);
        try (FeatureWriter fw = s.getFeatureWriter(new Query(sft.getName()))) {
            Iterator<Feature> it = fc.iterator();
            while (it.hasNext()) {
                Feature feature = it.next();
                Feature newFeature = fw.next();
                FeatureExt.copy(feature, newFeature, false);
                fw.write();
            }
        }
    }

    private void runWriteReadTest(final Geometry geom, final boolean d3) throws Exception {
        // make features

        final FeatureTypeBuilder ftb = new FeatureTypeBuilder();
        ftb.setName("Junk");
        ftb.addAttribute(geom.getClass()).setName("a").setCRS(CommonCRS.WGS84.normalizedGeographic()).addRole(AttributeRole.DEFAULT_GEOMETRY);
        final FeatureType type = ftb.build();

        Collection<Feature> features = new ArrayList<>();
        for (int i = 0, ii = 20; i < ii; i++) {
            final Feature feature = type.newInstance();
            feature.setPropertyValue("a", (Geometry) geom.clone());
            features.add(feature);
        }

        // set up file
        File tmpFile = getTempFile();
        tmpFile.delete();

        // write features
        ShapefileFeatureStore shapeFeatureStore = new ShapefileFeatureStore(tmpFile.toURI());
        shapeFeatureStore.createFeatureType(type);
        writeFeatures(shapeFeatureStore, features);

        // read features
        shapeFeatureStore = new ShapefileFeatureStore(tmpFile.toURI());
        FeatureCollection fc = loadFeatures(shapeFeatureStore);
        // verify
        try (FeatureIterator fci = fc.iterator()) {
            while (fci.hasNext()) {
                Feature f = fci.next();
                Geometry fromShape = FeatureExt.getDefaultGeometryValue(f)
                        .filter(Geometry.class::isInstance)
                        .map(Geometry.class::cast)
                        .orElseThrow(() -> new IllegalArgumentException("No geometry found in feature "+f));

                if (fromShape instanceof GeometryCollection) {
                    if (!(geom instanceof GeometryCollection)) {
                        fromShape = ((GeometryCollection) fromShape)
                                .getGeometryN(0);
                    }
                }
                try {
                    Coordinate[] c1 = geom.getCoordinates();
                    Coordinate[] c2 = fromShape.getCoordinates();
                    for (int cc = 0, ccc = c1.length; cc < ccc; cc++) {
                        if (d3)
                            assertTrue(c1[cc].equals3D(c2[cc]));
                        else
                            assertTrue(c1[cc].equals2D(c2[cc]));
                    }
                } catch (Throwable t) {
                    fail("Bogus : " + Arrays.asList(geom.getCoordinates()) + " : "
                            + Arrays.asList(fromShape.getCoordinates()));
                }
            }
        }
        tmpFile.delete();
    }

    private ShapefileFeatureStore createDataStore(final File f) throws Exception {
        Collection<Feature> fc = createFeatureCollection();
        ShapefileFeatureStore sds = new ShapefileFeatureStore(f.toURI());
        writeFeatures(sds, fc);
        return sds;
    }

    private ShapefileFeatureStore createDataStore() throws Exception {
        return createDataStore(getTempFile());
    }
}
