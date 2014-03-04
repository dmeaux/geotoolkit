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
package org.geotoolkit.coverage.filestore;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import org.apache.sis.io.wkt.Convention;
import org.apache.sis.io.wkt.FormattableObject;
import org.apache.sis.storage.DataStoreException;
import org.geotoolkit.coverage.AbstractPyramidSet;
import org.geotoolkit.coverage.Pyramid;
import org.geotoolkit.gui.swing.tree.Trees;
import org.geotoolkit.image.io.XImageIO;
import org.geotoolkit.referencing.IdentifiedObjects;
import org.apache.sis.util.Classes;
import org.apache.sis.xml.MarshallerPool;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import javax.xml.bind.JAXBContext;

/**
 *
 * @author Johann Sorel (Geomatys)
 * @module pending
 */
@XmlRootElement(name="PyramidSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLPyramidSet extends AbstractPyramidSet{

    @XmlTransient
    private static MarshallerPool POOL;

    private static synchronized MarshallerPool getPoolInstance() throws JAXBException{
        if(POOL == null){
            POOL = new MarshallerPool(JAXBContext.newInstance(XMLPyramidSet.class), null);
        }
        return POOL;
    }


    @XmlElement(name="Pyramid")
    private List<XMLPyramid> pyramids;
    @XmlElement(name="FormatName")
    private String formatName;
    @XmlElement(name="SampleDimension")
    private List<XMLSampleDimension> sampleDimensions;

    @XmlTransient
    private String id;
    @XmlTransient
    private File mainfile;
    @XmlTransient
    private ImageReaderSpi spi;

    public XMLPyramidSet() {
    }

    public XMLPyramidSet(String formatName){
        this.formatName = formatName;
    }

    void initialize(File mainFile){
        this.mainfile = mainFile;
        //calculate id based on file name
        id = mainfile.getName();
        int index = id.lastIndexOf('.');
        if(index > 0){
            id = id.substring(0,index);
        }

        for(XMLPyramid pyramid : pyramids()){
            pyramid.initialize(this);
        }
    }

    public String getFormatName() {
        return formatName;
    }

    public ImageReaderSpi getReaderSpi() throws DataStoreException{
        if(spi == null){
            try {
                final ImageReader reader = XImageIO.getReaderByFormatName(formatName, null, Boolean.TRUE, Boolean.TRUE);
                spi = reader.getOriginatingProvider();
                reader.dispose();
            } catch (IOException ex) {
                throw new DataStoreException(ex.getMessage(), ex);
            }
        }
        return spi;
    }

    public List<XMLPyramid> pyramids() {
        if(pyramids == null){
            pyramids = new ArrayList<>();
        }
        return pyramids;
    }

    public List<XMLSampleDimension> getSampleDimensions() {
        if(sampleDimensions==null){
            sampleDimensions = new ArrayList<>();
        }
        return sampleDimensions;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * @return xml file where the pyramid set definition is stored.
     */
    public File getMainfile() {
        return mainfile;
    }

    /**
     * @return Folder where each pyramid is stored.
     */
    public File getFolder(){
        return new File(mainfile.getParentFile(),getId());
    }

    @Override
    public Collection<Pyramid> getPyramids() {
        return (Collection)pyramids();
    }

    @Override
    public List<String> getFormats() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Envelope getEnvelope() {
        for(XMLPyramid pyramid : pyramids()){
            final List<XMLMosaic> mosaics = pyramid.mosaics();
            if(!mosaics.isEmpty()){
                return mosaics.get(mosaics.size()-1).getEnvelope();
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return Trees.toString(Classes.getShortClassName(this)+" "+getId(), getPyramids());
    }

    /**
     * Create and register a new pyramid in the set.
     *
     * @param crs
     * @return
     */
    Pyramid createPyramid(CoordinateReferenceSystem crs) {
        final XMLPyramid pyramid = new XMLPyramid();
        pyramid.crs = ((FormattableObject)crs).toString(Convention.WKT1);
        try {
            pyramid.id = URLEncoder.encode(IdentifiedObjects.getIdentifier(crs),"UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getMessage(),ex);
        }
        pyramid.initialize(this);
        pyramids().add(pyramid);
        return pyramid;
    }

    /**
     * Write this pyramid set in it's main file.
     * @throws JAXBException
     */
    public void write() throws JAXBException{
        final MarshallerPool pool = getPoolInstance();
        final Marshaller marshaller = pool.acquireMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(this, getMainfile());
        pool.recycle(marshaller);
    }

    /**
     * Read the given file and return an XMLPyramidSet.
     *
     * @param file
     * @return
     * @throws JAXBException
     */
    public static XMLPyramidSet read(File file) throws JAXBException{
        final MarshallerPool pool = getPoolInstance();
        final Unmarshaller unmarshaller = pool.acquireUnmarshaller();
        final XMLPyramidSet set;
        set = (XMLPyramidSet) unmarshaller.unmarshal(file);
        pool.recycle(unmarshaller);
        set.initialize(file);
        return set;
    }

}