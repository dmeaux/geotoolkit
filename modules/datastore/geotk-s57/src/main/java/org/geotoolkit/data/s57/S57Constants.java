/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2013, Geomatys
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
package org.geotoolkit.data.s57;

/**
 * S-57 constants.
 * 
 * @author Johann Sorel (Geomatys)
 */
public final class S57Constants {

    private S57Constants() {}
    
    /**
     * Constants can be expressed both as text or integer in S-57.
     */
    public static class SBConstant{
        private final String text;
        private final int binary;
        public SBConstant(String text, int binary) {
            this.text = text;
            this.binary = binary;            
        }
        @Override
        public boolean equals(Object candidate){
            if(candidate instanceof Number){
                return binary == ((Number)candidate).intValue();
            }
            return text.equals(candidate);
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    // TOPOLOGY (3.1) //////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    /** 
     * Only one vector data structure may be used within a data set. The data structure used must be explicitly
     * encoded in the “Data Structure” [DSTR] subfield of the “Data Set Structure Information” [DSSI] field. This
     * field is part of the “Data Set General Information” record (see clause 7.3.1).
     * 
     * {1} Cartographic spaghetti (see part 2, clause 2.2.1.1) */
    public static final SBConstant TOPOLOGY_SPAGHETTI = new SBConstant("CS",1); 
    /** {2} Chain-node (see part 2, clause 2.2.1.2) */
    public static final SBConstant TOPOLOGY_CHAINNODE = new SBConstant("CN",2); 
    /** {3} Planar graph (see part 2, clause 2.2.1.3) */
    public static final SBConstant TOPOLOGY_GRAPH = new SBConstant("PG",3); 
    /** {4} Full topology (see part 2, clause 2.2.1.4) */
    public static final SBConstant TOPOLOGY_FULL = new SBConstant("FT",4); 
    /** {255} Topology is not relevant */
    public static final SBConstant TOPOLOGY_NONDE = new SBConstant("NO",255); 

    ////////////////////////////////////////////////////////////////////////////
    // UNITS (3.2.1 ////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
            
    /** 
     * Coordinates can be encoded in three different ways. Only one type of units is allowed within a data set.
     * The type of unit is encoded in the “Coordinate Unit” [COUN] subfield of the “Data set Parameter” [DSPM] field.
     * 
     * LL {1} Latitude and longitude : Degrees of arc */
    public static final SBConstant COORDUNIT_LATLON = new SBConstant("LL",1);
    /** EN {2} Easting/Northing : Meters */
    public static final SBConstant COORDUNIT_EASTNORTH = new SBConstant("EN",2);
    /** UC {3} Units on chart/map : Milimeters */
    public static final SBConstant COORDUNIT_MAPUNIT = new SBConstant("UC",3);
    
    ////////////////////////////////////////////////////////////////////////////
    // COORDINATES (3.2.2) /////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * When transforming units, other than latitude and longitude, into geographical positions (referenced to the
     * earth’ surface), the following data must be available:
     * • the chart/map projection employed, including the necessary parameters;
     * • a sufficient number of registration points (points for which both the unit coordinates and the
     * geographical position are known).
     * The data indicated above must be encoded in “Data Set Projection” [DSPR] and “Data Set Registration Control” [DSRC] fields.
     * Up to 4 parameters can be specified in the “Data Set Projection” field.
     * 
     * Albert equal area ALA {1} :
     * - Central meridian 
     * - Std. parallel nearer to equator
     * - Std. parallel farther from equator 
     * - Parallel of origin
     */
    public static final SBConstant PROJECTION_ALBERT_EQUAL_AREA = new SBConstant("ALA",1);
    /**
     * Azimuthal equal area {2} :
     * - Longitude of tangency 
     * - Latitude of tangency 
     */
    public static final SBConstant PROJECTION_AZIMUTHAL_EQUAL_AREA = new SBConstant("AZA",2);
    /**
     * Azimuthal equal distance {3} :
     * - Longitude of tangency 
     * - Latitude of tangency 
     */
    public static final SBConstant PROJECTION_AZIMUTHAL_EQUAL_DISTANCE = new SBConstant("AZD",3);
    /**
     * Gnonomic {4} :
     * - Longitude of tangency 
     * - Latitude of tangency 
     */
    public static final SBConstant PROJECTION_GNONOMIC = new SBConstant("GNO",4);
    /**
     * Hotline oblique Mercator (rectified skew orthomorphic) {5} :
     * - Longitude of projection origin 
     * - Latitude of projection origin 
     * - Azimuth of skew X-axis at projection origin 
     * - Scale factor at projection origin
     */
    public static final SBConstant PROJECTION_HOTINE_OBLIQUE_MERCATOR = new SBConstant("HOM",5);
    /**
     * Lambert conformal conic {6} :
     * - Central meridian 
     * - Std. parallel nearer to equator 
     * - Std. parallel farther from equator 
     * - Parallel of origin
     */
    public static final SBConstant PROJECTION_LAMBER_CONFORMAL_CONIC = new SBConstant("LCC",6);
    /**
     * Lambert equal area {7} :
     * - Central meridian 
     */
    public static final SBConstant PROJECTION_LAMBERT_EQUAL_AREA = new SBConstant("LEA",7);
    /**
     * Mercator {8} :
     * - Central meridian 
     * - Latitude of true scale 
     * - Parallel of origin 
     */
    public static final SBConstant PROJECTION_MERCATOR = new SBConstant("MER",8);
    /**
     * Oblique Mercator {9} :
     * - Longitude of reference point on great circle 
     * - Latitude reference point of great circle 
     * - Azimuth of great circle at ref. point 
     */
    public static final SBConstant PROJECTION_OBLIQUE_MERCATOR = new SBConstant("OME",9);
    /**
     * Orthographic {10} :
     * - Longitude of tangency 
     * - Latitude of tangency 
     */
    public static final SBConstant PROJECTION_ORTHOGRAPHIC = new SBConstant("ORT",10);
    /**
     * Polar stereo graphic {11} :
     * - Central meridian 
     * - Latitude of true scale 
     */
    public static final SBConstant PROJECTION_POLAR_STEREO_GRAPHIC = new SBConstant("PST",11);
    /**
     * Azimuthal equal area {12} :
     * - Central meridian 
     */
    public static final SBConstant PROJECTION_POLYCONIC = new SBConstant("POL",12);
    /**
     * Transverse Mercator {13} :
     * - Central meridian 
     * - Central scale factor
     * - Parallel of origin
     */
    public static final SBConstant PROJECTION_TRANSVERSE_MERCATOR = new SBConstant("TME",13);
    /**
     * Oblique stereographic {14} :
     * - Longitude of origin 
     * - Latitude of origin 
     * - Scale factor at origin 
     */
    public static final SBConstant PROJECTION_OBLIQUE_STEREOGRAPHIC = new SBConstant("OST",14);
    
    ////////////////////////////////////////////////////////////////////////////
    // VARIOUS OTHER ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    /** 
     * In the binary implementation, 3-D sounding values are encoded as integers. In order to convert
     * floating-point 3-D (sounding) values to integers (and vice-versa) a multiplication factor is used. The factor
     * is defined by the encoder and held in the “3-D (sounding) Multiplication Factor” [SOMF] subfield. The
     * SOMF subfield applies to the “3-D (sounding) Value” [VE3D] subfield of the “3-D Coordinate” [SG3D] field.
     * The conversion algorithm is defined in clause 2.6.
     */
    public static final String SOUNDING_VALUE = "VE3D";
    public static final String SOUNDING_FACTOR = "SOMF";
    
    /**
     * A “Cyclic Redundancy Check” (CRC) algorithm can be used to ensure that the data has not been
     * corrupted during the exchange process. Different CRC algorithms can be used for different applications.
     * The algorithm used is, therefore, described in the relevant product specification (see Appendix B –
     * Product Specifications).
     * A CRC value for every file in an exchange set can be encoded in the “Catalogue Directory” [CATD] field,
     * CRCS subfield.
     */
    public static final String CHECKSUM = "CRCS";
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Feature record identifier field (4.2) ///////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    /** record identifier */
    public static final String FRI_ID1 = "RCNM";
    public static final String FRI_ID2 = "RCID";
    /** object geometric primitive*/
    public static final String FRI_PRIMITIVE = "PRIM";
    /** The “Group” [GRUP] subfield is used to separate feature objects into groups. The definition of groups is
     * dependent on the product specification (see Appendix B – Product Specifications). If a feature object does
     * not belong to a group, the subfield must be left empty (see clause 2.1). */
    public static final String FRI_GROUP = "GRUP";
    /** The numeric object label/code of the object class from the IHO Object Catalogue is encoded in the “Object
     * Label/Code” [OBJL] subfield. */
    public static final String FRI_LABEL = "OBJL";
    /** record version */
    public static final String FRI_VERSION = "RVER";
    /** record update instruction */
    public static final String FRI_UPDATE_INSTRUCTION = "RUIN";
    
    /** The “Object Geometric Primitive” [PRIM] subfield is used to specify the geometric primitive of the encoded object. 
     * 
     * Point */
    public static final SBConstant PRIMITIVE_POINT = new SBConstant("P",1);
    /** Line */
    public static final SBConstant PRIMITIVE_LINE = new SBConstant("L",2);
    /** Area */
    public static final SBConstant PRIMITIVE_AREA = new SBConstant("A",3);
    /** Object does not directly reference any geometry */
    public static final SBConstant PRIMITIVE_NONE = new SBConstant("N",255);
    
    ////////////////////////////////////////////////////////////////////////////
    // Feature object identifier field (4.3) ///////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    /** The allowable values for the “Producing Agency” [AGEN] subfield are defined in the IHO Object
     * Catalogue. The IHO Object Catalogue contains a 2-character acronym and a corresponding integer value
     * for each agency. If the producing agency is not listed, the AGEN subfield must be encoded as a missing
     * subfield value (see clause 2.1). */
    public static final String FOI_PRODUCING_AGENCY = "AGEN";
    /** The “Feature Object Identification Number” ranges from 1 to (2^32)-2. The “Feature Object Identification
     * Subdivision” ranges from 1 to (2^16)-2. Both subfields are used to create an unique key for a feature object
     * produced by the agency encoded in the AGEN subfield. The usage of the FIDN and FIDS subfields is not
     * constrained and must be defined by the encoder. */
    public static final String FOI_NUMBER = "FIDN";
    public static final String FOI_SUBDIVISION = "FIDS";
    
}
