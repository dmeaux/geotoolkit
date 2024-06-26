/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2023, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */

package org.geotoolkit.kml.xml.v230;

import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import jakarta.xml.bind.annotation.adapters.HexBinaryAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the net.opengis.kml._2 package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LineString_QNAME = new QName("http://www.opengis.net/kml/2.2", "LineString");
    private final static QName _AbstractStyleSelectorGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractStyleSelectorGroup");
    private final static QName _Location_QNAME = new QName("http://www.opengis.net/kml/2.2", "Location");
    private final static QName _LatLonBoxObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LatLonBoxObjectExtensionGroup");
    private final static QName _FolderSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "FolderSimpleExtensionGroup");
    private final static QName _StyleMap_QNAME = new QName("http://www.opengis.net/kml/2.2", "StyleMap");
    private final static QName _Rotation_QNAME = new QName("http://www.opengis.net/kml/2.2", "rotation");
    private final static QName _AbstractContainerGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractContainerGroup");
    private final static QName _LocationObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LocationObjectExtensionGroup");
    private final static QName _AliasSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AliasSimpleExtensionGroup");
    private final static QName _Message_QNAME = new QName("http://www.opengis.net/kml/2.2", "message");
    private final static QName _StyleObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "StyleObjectExtensionGroup");
    private final static QName _StyleUrl_QNAME = new QName("http://www.opengis.net/kml/2.2", "styleUrl");
    private final static QName _MultiGeometrySimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "MultiGeometrySimpleExtensionGroup");
    private final static QName _LabelStyle_QNAME = new QName("http://www.opengis.net/kml/2.2", "LabelStyle");
    private final static QName _ScreenOverlayObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ScreenOverlayObjectExtensionGroup");
    private final static QName _PlaylistSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PlaylistSimpleExtensionGroup");
    private final static QName _TourObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "TourObjectExtensionGroup");
    private final static QName _Duration_QNAME = new QName("http://www.opengis.net/kml/2.2", "duration");
    private final static QName _OrientationSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "OrientationSimpleExtensionGroup");
    private final static QName _HttpQuery_QNAME = new QName("http://www.opengis.net/kml/2.2", "httpQuery");
    private final static QName _AbstractFeatureObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractFeatureObjectExtensionGroup");
    private final static QName _Pair_QNAME = new QName("http://www.opengis.net/kml/2.2", "Pair");
    private final static QName _NetworkLinkControl_QNAME = new QName("http://www.opengis.net/kml/2.2", "NetworkLinkControl");
    private final static QName _AltitudeMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "altitudeMode");
    private final static QName _LineStyleSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LineStyleSimpleExtensionGroup");
    private final static QName _AbstractLatLonBoxGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractLatLonBoxGroup");
    private final static QName _End_QNAME = new QName("http://www.opengis.net/kml/2.2", "end");
    private final static QName _ViewBoundScale_QNAME = new QName("http://www.opengis.net/kml/2.2", "viewBoundScale");
    private final static QName _Color_QNAME = new QName("http://www.opengis.net/kml/2.2", "color");
    private final static QName _AbstractLatLonBoxSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractLatLonBoxSimpleExtensionGroup");
    private final static QName _NetworkLinkSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "NetworkLinkSimpleExtensionGroup");
    private final static QName _InnerBoundaryIs_QNAME = new QName("http://www.opengis.net/kml/2.2", "innerBoundaryIs");
    private final static QName _Expires_QNAME = new QName("http://www.opengis.net/kml/2.2", "expires");
    private final static QName _Description_QNAME = new QName("http://www.opengis.net/kml/2.2", "description");
    private final static QName _ScreenOverlay_QNAME = new QName("http://www.opengis.net/kml/2.2", "ScreenOverlay");
    private final static QName _LinkSnippet_QNAME = new QName("http://www.opengis.net/kml/2.2", "linkSnippet");
    private final static QName _AbstractTourPrimitiveObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractTourPrimitiveObjectExtensionGroup");
    private final static QName _AnimatedUpdate_QNAME = new QName("http://www.opengis.net/kml/2.2", "AnimatedUpdate");
    private final static QName _ResourceMap_QNAME = new QName("http://www.opengis.net/kml/2.2", "ResourceMap");
    private final static QName _PhoneNumber_QNAME = new QName("http://www.opengis.net/kml/2.2", "phoneNumber");
    private final static QName _AbstractObjectGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractObjectGroup");
    private final static QName _AbstractGeometryGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractGeometryGroup");
    private final static QName _CameraSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "CameraSimpleExtensionGroup");
    private final static QName _Region_QNAME = new QName("http://www.opengis.net/kml/2.2", "Region");
    private final static QName _Name_QNAME = new QName("http://www.opengis.net/kml/2.2", "name");
    private final static QName _AbstractExtentObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractExtentObjectExtensionGroup");
    private final static QName _Icon_QNAME = new QName("http://www.opengis.net/kml/2.2", "Icon");
    private final static QName _ImagePyramidObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ImagePyramidObjectExtensionGroup");
    private final static QName _RefreshInterval_QNAME = new QName("http://www.opengis.net/kml/2.2", "refreshInterval");
    private final static QName _Visibility_QNAME = new QName("http://www.opengis.net/kml/2.2", "visibility");
    private final static QName _TargetHref_QNAME = new QName("http://www.opengis.net/kml/2.2", "targetHref");
    private final static QName _MaxAltitude_QNAME = new QName("http://www.opengis.net/kml/2.2", "maxAltitude");
    private final static QName _FlyToView_QNAME = new QName("http://www.opengis.net/kml/2.2", "flyToView");
    private final static QName _PolyStyleSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PolyStyleSimpleExtensionGroup");
    private final static QName _TextColor_QNAME = new QName("http://www.opengis.net/kml/2.2", "textColor");
    private final static QName _AbstractShape_QNAME = new QName("http://www.opengis.net/kml/2.2", "abstractShape");
    private final static QName _AbstractGridOrigin_QNAME = new QName("http://www.opengis.net/kml/2.2", "abstractGridOrigin");
    private final static QName _ItemIconObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ItemIconObjectExtensionGroup");
    private final static QName _ViewVolume_QNAME = new QName("http://www.opengis.net/kml/2.2", "ViewVolume");
    private final static QName _ScaleObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ScaleObjectExtensionGroup");
    private final static QName _NetworkLink_QNAME = new QName("http://www.opengis.net/kml/2.2", "NetworkLink");
    private final static QName _AnimatedUpdateObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AnimatedUpdateObjectExtensionGroup");
    private final static QName _SoundCueSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "SoundCueSimpleExtensionGroup");
    private final static QName _Polygon_QNAME = new QName("http://www.opengis.net/kml/2.2", "Polygon");
    private final static QName _TimeStampSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "TimeStampSimpleExtensionGroup");
    private final static QName _AbstractGeometryObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractGeometryObjectExtensionGroup");
    private final static QName _WaitSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "WaitSimpleExtensionGroup");
    private final static QName _GridOrigin_QNAME = new QName("http://www.opengis.net/kml/2.2", "gridOrigin");
    private final static QName _Camera_QNAME = new QName("http://www.opengis.net/kml/2.2", "Camera");
    private final static QName _TrackSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "TrackSimpleExtensionGroup");
    private final static QName _Schema_QNAME = new QName("http://www.opengis.net/kml/2.2", "Schema");
    private final static QName _FlyToSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "FlyToSimpleExtensionGroup");
    private final static QName _NetworkLinkControlSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "NetworkLinkControlSimpleExtensionGroup");
    private final static QName _LinkName_QNAME = new QName("http://www.opengis.net/kml/2.2", "linkName");
    private final static QName _ImagePyramid_QNAME = new QName("http://www.opengis.net/kml/2.2", "ImagePyramid");
    private final static QName _Tilt_QNAME = new QName("http://www.opengis.net/kml/2.2", "tilt");
    private final static QName _ViewFormat_QNAME = new QName("http://www.opengis.net/kml/2.2", "viewFormat");
    private final static QName _RegionSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "RegionSimpleExtensionGroup");
    private final static QName _PlacemarkObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PlacemarkObjectExtensionGroup");
    private final static QName _LinearRing_QNAME = new QName("http://www.opengis.net/kml/2.2", "LinearRing");
    private final static QName _SimpleArrayData_QNAME = new QName("http://www.opengis.net/kml/2.2", "SimpleArrayData");
    private final static QName _Open_QNAME = new QName("http://www.opengis.net/kml/2.2", "open");
    private final static QName _MinLodPixels_QNAME = new QName("http://www.opengis.net/kml/2.2", "minLodPixels");
    private final static QName _Y_QNAME = new QName("http://www.opengis.net/kml/2.2", "y");
    private final static QName _X_QNAME = new QName("http://www.opengis.net/kml/2.2", "x");
    private final static QName _LookAtSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LookAtSimpleExtensionGroup");
    private final static QName _LodObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LodObjectExtensionGroup");
    private final static QName _AbstractPlayMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "abstractPlayMode");
    private final static QName _Z_QNAME = new QName("http://www.opengis.net/kml/2.2", "z");
    private final static QName _LinearRingObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LinearRingObjectExtensionGroup");
    private final static QName _LatLonQuadObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LatLonQuadObjectExtensionGroup");
    private final static QName _Placemark_QNAME = new QName("http://www.opengis.net/kml/2.2", "Placemark");
    private final static QName _Address_QNAME = new QName("http://www.opengis.net/kml/2.2", "address");
    private final static QName _AbstractStyleSelectorSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractStyleSelectorSimpleExtensionGroup");
    private final static QName _PointSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PointSimpleExtensionGroup");
    private final static QName _TimeSpanSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "TimeSpanSimpleExtensionGroup");
    private final static QName _Coordinates_QNAME = new QName("http://www.opengis.net/kml/2.2", "coordinates");
    private final static QName _MaxHeight_QNAME = new QName("http://www.opengis.net/kml/2.2", "maxHeight");
    private final static QName _LatLonAltBox_QNAME = new QName("http://www.opengis.net/kml/2.2", "LatLonAltBox");
    private final static QName _Outline_QNAME = new QName("http://www.opengis.net/kml/2.2", "outline");
    private final static QName _RefreshMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "refreshMode");
    private final static QName _SourceHref_QNAME = new QName("http://www.opengis.net/kml/2.2", "sourceHref");
    private final static QName _PairObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PairObjectExtensionGroup");
    private final static QName _IconStyleSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "IconStyleSimpleExtensionGroup");
    private final static QName _Tour_QNAME = new QName("http://www.opengis.net/kml/2.2", "Tour");
    private final static QName _Key_QNAME = new QName("http://www.opengis.net/kml/2.2", "key");
    private final static QName _SchemaDataExtension_QNAME = new QName("http://www.opengis.net/kml/2.2", "SchemaDataExtension");
    private final static QName _LineStringObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LineStringObjectExtensionGroup");
    private final static QName _DocumentSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "DocumentSimpleExtensionGroup");
    private final static QName _Latitude_QNAME = new QName("http://www.opengis.net/kml/2.2", "latitude");
    private final static QName _DisplayName_QNAME = new QName("http://www.opengis.net/kml/2.2", "displayName");
    private final static QName _LineStyle_QNAME = new QName("http://www.opengis.net/kml/2.2", "LineStyle");
    private final static QName _ListStyle_QNAME = new QName("http://www.opengis.net/kml/2.2", "ListStyle");
    private final static QName _AbstractGeometrySimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractGeometrySimpleExtensionGroup");
    private final static QName _ListStyleObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ListStyleObjectExtensionGroup");
    private final static QName _UpdateExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "UpdateExtensionGroup");
    private final static QName _AbstractContainerObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractContainerObjectExtensionGroup");
    private final static QName _KmlObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "KmlObjectExtensionGroup");
    private final static QName _TourControlSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "TourControlSimpleExtensionGroup");
    private final static QName _AbstractSubStyleGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractSubStyleGroup");
    private final static QName _AbstractSubStyleSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractSubStyleSimpleExtensionGroup");
    private final static QName _GroundOverlayObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "GroundOverlayObjectExtensionGroup");
    private final static QName _PhotoOverlayObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PhotoOverlayObjectExtensionGroup");
    private final static QName _Roll_QNAME = new QName("http://www.opengis.net/kml/2.2", "roll");
    private final static QName _Metadata_QNAME = new QName("http://www.opengis.net/kml/2.2", "Metadata");
    private final static QName _ItemIcon_QNAME = new QName("http://www.opengis.net/kml/2.2", "ItemIcon");
    private final static QName _AbstractOverlayObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractOverlayObjectExtensionGroup");
    private final static QName _BalloonStyleObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "BalloonStyleObjectExtensionGroup");
    private final static QName _AbstractLinkGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractLinkGroup");
    private final static QName _Size_QNAME = new QName("http://www.opengis.net/kml/2.2", "size");
    private final static QName _AbstractOverlaySimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractOverlaySimpleExtensionGroup");
    private final static QName _Link_QNAME = new QName("http://www.opengis.net/kml/2.2", "Link");
    private final static QName _ObjectSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ObjectSimpleExtensionGroup");
    private final static QName _AbstractStyleSelectorObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractStyleSelectorObjectExtensionGroup");
    private final static QName _PlacemarkSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PlacemarkSimpleExtensionGroup");
    private final static QName _FlyTo_QNAME = new QName("http://www.opengis.net/kml/2.2", "FlyTo");
    private final static QName _SchemaExtension_QNAME = new QName("http://www.opengis.net/kml/2.2", "SchemaExtension");
    private final static QName _LinearRingSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LinearRingSimpleExtensionGroup");
    private final static QName _RegionObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "RegionObjectExtensionGroup");
    private final static QName _Orientation_QNAME = new QName("http://www.opengis.net/kml/2.2", "Orientation");
    private final static QName _ViewRefreshMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "viewRefreshMode");
    private final static QName _TimeStamp_QNAME = new QName("http://www.opengis.net/kml/2.2", "TimeStamp");
    private final static QName _LatLonBox_QNAME = new QName("http://www.opengis.net/kml/2.2", "LatLonBox");
    private final static QName _DataExtension_QNAME = new QName("http://www.opengis.net/kml/2.2", "DataExtension");
    private final static QName _AbstractExtentSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractExtentSimpleExtensionGroup");
    private final static QName _AbstractViewRefreshMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "abstractViewRefreshMode");
    private final static QName _Playlist_QNAME = new QName("http://www.opengis.net/kml/2.2", "Playlist");
    private final static QName _RefreshVisibility_QNAME = new QName("http://www.opengis.net/kml/2.2", "refreshVisibility");
    private final static QName _MaxSnippetLines_QNAME = new QName("http://www.opengis.net/kml/2.2", "maxSnippetLines");
    private final static QName _DelayedStart_QNAME = new QName("http://www.opengis.net/kml/2.2", "delayedStart");
    private final static QName _Near_QNAME = new QName("http://www.opengis.net/kml/2.2", "near");
    private final static QName _State_QNAME = new QName("http://www.opengis.net/kml/2.2", "state");
    private final static QName _TourControlObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "TourControlObjectExtensionGroup");
    private final static QName _AbstractSubStyleObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractSubStyleObjectExtensionGroup");
    private final static QName _KmlSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "KmlSimpleExtensionGroup");
    private final static QName _Snippet_QNAME = new QName("http://www.opengis.net/kml/2.2", "snippet");
    private final static QName _LodSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LodSimpleExtensionGroup");
    private final static QName _Delete_QNAME = new QName("http://www.opengis.net/kml/2.2", "Delete");
    private final static QName _GroundOverlaySimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "GroundOverlaySimpleExtensionGroup");
    private final static QName _LineStyleObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LineStyleObjectExtensionGroup");
    private final static QName _ListStyleSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ListStyleSimpleExtensionGroup");
    private final static QName _Document_QNAME = new QName("http://www.opengis.net/kml/2.2", "Document");
    private final static QName _AbstractTourPrimitiveSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractTourPrimitiveSimpleExtensionGroup");
    private final static QName _IconStyleObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "IconStyleObjectExtensionGroup");
    private final static QName _LeftFov_QNAME = new QName("http://www.opengis.net/kml/2.2", "leftFov");
    private final static QName _DocumentObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "DocumentObjectExtensionGroup");
    private final static QName _AbstractLatLonBoxObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractLatLonBoxObjectExtensionGroup");
    private final static QName _MinRefreshPeriod_QNAME = new QName("http://www.opengis.net/kml/2.2", "minRefreshPeriod");
    private final static QName _WaitObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "WaitObjectExtensionGroup");
    private final static QName _Alias_QNAME = new QName("http://www.opengis.net/kml/2.2", "Alias");
    private final static QName _MinFadeExtent_QNAME = new QName("http://www.opengis.net/kml/2.2", "minFadeExtent");
    private final static QName _ScaleSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ScaleSimpleExtensionGroup");
    private final static QName _AbstractRefreshMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "abstractRefreshMode");
    private final static QName _Track_QNAME = new QName("http://www.opengis.net/kml/2.2", "Track");
    private final static QName _MultiGeometry_QNAME = new QName("http://www.opengis.net/kml/2.2", "MultiGeometry");
    private final static QName _ImagePyramidSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ImagePyramidSimpleExtensionGroup");
    private final static QName _AbstractOverlayGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractOverlayGroup");
    private final static QName _MaxFadeExtent_QNAME = new QName("http://www.opengis.net/kml/2.2", "maxFadeExtent");
    private final static QName _LineStringSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LineStringSimpleExtensionGroup");
    private final static QName _AbstractTimePrimitiveSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractTimePrimitiveSimpleExtensionGroup");
    private final static QName _Point_QNAME = new QName("http://www.opengis.net/kml/2.2", "Point");
    private final static QName _Change_QNAME = new QName("http://www.opengis.net/kml/2.2", "Change");
    private final static QName _SimpleData_QNAME = new QName("http://www.opengis.net/kml/2.2", "SimpleData");
    private final static QName _Data_QNAME = new QName("http://www.opengis.net/kml/2.2", "Data");
    private final static QName _Scale_QNAME = new QName("http://www.opengis.net/kml/2.2", "Scale");
    private final static QName _BgColor_QNAME = new QName("http://www.opengis.net/kml/2.2", "bgColor");
    private final static QName _LookAtObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LookAtObjectExtensionGroup");
    private final static QName _Create_QNAME = new QName("http://www.opengis.net/kml/2.2", "Create");
    private final static QName _TimeSpan_QNAME = new QName("http://www.opengis.net/kml/2.2", "TimeSpan");
    private final static QName _East_QNAME = new QName("http://www.opengis.net/kml/2.2", "east");
    private final static QName _Value_QNAME = new QName("http://www.opengis.net/kml/2.2", "value");
    private final static QName _PolyStyleObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PolyStyleObjectExtensionGroup");
    private final static QName _LatLonAltBoxObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LatLonAltBoxObjectExtensionGroup");
    private final static QName _BalloonStyleSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "BalloonStyleSimpleExtensionGroup");
    private final static QName _LatLonQuadSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LatLonQuadSimpleExtensionGroup");
    private final static QName _FlyToObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "FlyToObjectExtensionGroup");
    private final static QName _Angles_QNAME = new QName("http://www.opengis.net/kml/2.2", "angles");
    private final static QName _NetworkLinkControlObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "NetworkLinkControlObjectExtensionGroup");
    private final static QName _Tessellate_QNAME = new QName("http://www.opengis.net/kml/2.2", "tessellate");
    private final static QName _Altitude_QNAME = new QName("http://www.opengis.net/kml/2.2", "altitude");
    private final static QName _AbstractViewSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractViewSimpleExtensionGroup");
    private final static QName _SimpleArrayField_QNAME = new QName("http://www.opengis.net/kml/2.2", "SimpleArrayField");
    private final static QName _ModelObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ModelObjectExtensionGroup");
    private final static QName _AbstractExtendedDataGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractExtendedDataGroup");
    private final static QName _TrackObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "TrackObjectExtensionGroup");
    private final static QName _AbstractFlyToMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "abstractFlyToMode");
    private final static QName _PhotoOverlay_QNAME = new QName("http://www.opengis.net/kml/2.2", "PhotoOverlay");
    private final static QName _BottomFov_QNAME = new QName("http://www.opengis.net/kml/2.2", "bottomFov");
    private final static QName _AbstractTimePrimitiveGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractTimePrimitiveGroup");
    private final static QName _ResourceMapSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ResourceMapSimpleExtensionGroup");
    private final static QName _AbstractState_QNAME = new QName("http://www.opengis.net/kml/2.2", "abstractState");
    private final static QName _DrawOrder_QNAME = new QName("http://www.opengis.net/kml/2.2", "drawOrder");
    private final static QName _TileSize_QNAME = new QName("http://www.opengis.net/kml/2.2", "tileSize");
    private final static QName _BalloonVisibility_QNAME = new QName("http://www.opengis.net/kml/2.2", "balloonVisibility");
    private final static QName _PairSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PairSimpleExtensionGroup");
    private final static QName _StyleSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "StyleSimpleExtensionGroup");
    private final static QName _FlyToMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "flyToMode");
    private final static QName _Wait_QNAME = new QName("http://www.opengis.net/kml/2.2", "Wait");
    private final static QName _OuterBoundaryIs_QNAME = new QName("http://www.opengis.net/kml/2.2", "outerBoundaryIs");
    private final static QName _Scalep_QNAME = new QName("http://www.opengis.net/kml/2.2", "scale");
    private final static QName _ScreenOverlaySimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ScreenOverlaySimpleExtensionGroup");
    private final static QName _TimeSpanObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "TimeSpanObjectExtensionGroup");
    private final static QName _OverlayXY_QNAME = new QName("http://www.opengis.net/kml/2.2", "overlayXY");
    private final static QName _ExtendedData_QNAME = new QName("http://www.opengis.net/kml/2.2", "ExtendedData");
    private final static QName _AliasObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AliasObjectExtensionGroup");
    private final static QName _HorizFov_QNAME = new QName("http://www.opengis.net/kml/2.2", "horizFov");
    private final static QName _AltitudeModeObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AltitudeModeObjectExtensionGroup");
    private final static QName _PointObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PointObjectExtensionGroup");
    private final static QName _FolderObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "FolderObjectExtensionGroup");
    private final static QName _AbstractSnippetGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractSnippetGroup");
    private final static QName _SimpleFieldExtension_QNAME = new QName("http://www.opengis.net/kml/2.2", "SimpleFieldExtension");
    private final static QName _BalloonStyle_QNAME = new QName("http://www.opengis.net/kml/2.2", "BalloonStyle");
    private final static QName _Fill_QNAME = new QName("http://www.opengis.net/kml/2.2", "fill");
    private final static QName _DisplayMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "displayMode");
    private final static QName _ColorMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "colorMode");
    private final static QName _AbstractColorStyleObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractColorStyleObjectExtensionGroup");
    private final static QName _StyleMapSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "StyleMapSimpleExtensionGroup");
    private final static QName _MultiGeometryObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "MultiGeometryObjectExtensionGroup");
    private final static QName _OrientationObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "OrientationObjectExtensionGroup");
    private final static QName _Extrude_QNAME = new QName("http://www.opengis.net/kml/2.2", "extrude");
    private final static QName _HotSpot_QNAME = new QName("http://www.opengis.net/kml/2.2", "hotSpot");
    private final static QName _AbstractKey_QNAME = new QName("http://www.opengis.net/kml/2.2", "abstractKey");
    private final static QName _AbstractColorStyleSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractColorStyleSimpleExtensionGroup");
    private final static QName _PhotoOverlaySimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PhotoOverlaySimpleExtensionGroup");
    private final static QName _Longitude_QNAME = new QName("http://www.opengis.net/kml/2.2", "longitude");
    private final static QName _AbstractContainerSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractContainerSimpleExtensionGroup");
    private final static QName _West_QNAME = new QName("http://www.opengis.net/kml/2.2", "west");
    private final static QName _TourControl_QNAME = new QName("http://www.opengis.net/kml/2.2", "TourControl");
    private final static QName _ModelSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ModelSimpleExtensionGroup");
    private final static QName _NetworkLinkObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "NetworkLinkObjectExtensionGroup");
    private final static QName _AbstractExtentGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractExtentGroup");
    private final static QName _AbstractColorMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "abstractColorMode");
    private final static QName _MaxSessionLength_QNAME = new QName("http://www.opengis.net/kml/2.2", "maxSessionLength");
    private final static QName _Update_QNAME = new QName("http://www.opengis.net/kml/2.2", "Update");
    private final static QName _RightFov_QNAME = new QName("http://www.opengis.net/kml/2.2", "rightFov");
    private final static QName _ResourceMapObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ResourceMapObjectExtensionGroup");
    private final static QName _IconStyle_QNAME = new QName("http://www.opengis.net/kml/2.2", "IconStyle");
    private final static QName _AnimatedUpdateSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AnimatedUpdateSimpleExtensionGroup");
    private final static QName _SoundCue_QNAME = new QName("http://www.opengis.net/kml/2.2", "SoundCue");
    private final static QName _AbstractViewGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractViewGroup");
    private final static QName _PolygonSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PolygonSimpleExtensionGroup");
    private final static QName _Width_QNAME = new QName("http://www.opengis.net/kml/2.2", "width");
    private final static QName _Model_QNAME = new QName("http://www.opengis.net/kml/2.2", "Model");
    private final static QName _AbstractViewObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractViewObjectExtensionGroup");
    private final static QName _TopFov_QNAME = new QName("http://www.opengis.net/kml/2.2", "topFov");
    private final static QName _ViewRefreshTime_QNAME = new QName("http://www.opengis.net/kml/2.2", "viewRefreshTime");
    private final static QName _Heading_QNAME = new QName("http://www.opengis.net/kml/2.2", "heading");
    private final static QName _ListItemType_QNAME = new QName("http://www.opengis.net/kml/2.2", "listItemType");
    private final static QName _RotationXY_QNAME = new QName("http://www.opengis.net/kml/2.2", "rotationXY");
    private final static QName _MaxLodPixels_QNAME = new QName("http://www.opengis.net/kml/2.2", "maxLodPixels");
    private final static QName _SimpleArrayFieldExtension_QNAME = new QName("http://www.opengis.net/kml/2.2", "SimpleArrayFieldExtension");
    private final static QName _MultiTrackObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "MultiTrackObjectExtensionGroup");
    private final static QName _Coord_QNAME = new QName("http://www.opengis.net/kml/2.2", "coord");
    private final static QName _LookAt_QNAME = new QName("http://www.opengis.net/kml/2.2", "LookAt");
    private final static QName _LinkSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LinkSimpleExtensionGroup");
    private final static QName _ViewVolumeObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ViewVolumeObjectExtensionGroup");
    private final static QName _SeaFloorAltitudeMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "seaFloorAltitudeMode");
    private final static QName _AbstractBgColorGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractBgColorGroup");
    private final static QName _Style_QNAME = new QName("http://www.opengis.net/kml/2.2", "Style");
    private final static QName _South_QNAME = new QName("http://www.opengis.net/kml/2.2", "south");
    private final static QName _AltitudeOffset_QNAME = new QName("http://www.opengis.net/kml/2.2", "altitudeOffset");
    private final static QName _North_QNAME = new QName("http://www.opengis.net/kml/2.2", "north");
    private final static QName _LabelStyleSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LabelStyleSimpleExtensionGroup");
    private final static QName _BoundarySimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "BoundarySimpleExtensionGroup");
    private final static QName _BasicLinkSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "BasicLinkSimpleExtensionGroup");
    private final static QName _PolyStyle_QNAME = new QName("http://www.opengis.net/kml/2.2", "PolyStyle");
    private final static QName _Folder_QNAME = new QName("http://www.opengis.net/kml/2.2", "Folder");
    private final static QName _AbstractDisplayMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "abstractDisplayMode");
    private final static QName _Url_QNAME = new QName("http://www.opengis.net/kml/2.2", "Url");
    private final static QName _Range_QNAME = new QName("http://www.opengis.net/kml/2.2", "range");
    private final static QName _Kml_QNAME = new QName("http://www.opengis.net/kml/2.2", "kml");
    private final static QName _TimeStampObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "TimeStampObjectExtensionGroup");
    private final static QName _SoundCueObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "SoundCueObjectExtensionGroup");
    private final static QName _TourSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "TourSimpleExtensionGroup");
    private final static QName _AbstractColorStyleGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractColorStyleGroup");
    private final static QName _AbstractTourPrimitiveGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractTourPrimitiveGroup");
    private final static QName _AbstractListItemType_QNAME = new QName("http://www.opengis.net/kml/2.2", "abstractListItemType");
    private final static QName _MinAltitude_QNAME = new QName("http://www.opengis.net/kml/2.2", "minAltitude");
    private final static QName _Begin_QNAME = new QName("http://www.opengis.net/kml/2.2", "begin");
    private final static QName _AbstractTimePrimitiveObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractTimePrimitiveObjectExtensionGroup");
    private final static QName _MultiTrackSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "MultiTrackSimpleExtensionGroup");
    private final static QName _SimpleField_QNAME = new QName("http://www.opengis.net/kml/2.2", "SimpleField");
    private final static QName _Cookie_QNAME = new QName("http://www.opengis.net/kml/2.2", "cookie");
    private final static QName _Shape_QNAME = new QName("http://www.opengis.net/kml/2.2", "shape");
    private final static QName _PolygonObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PolygonObjectExtensionGroup");
    private final static QName _ItemIconSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ItemIconSimpleExtensionGroup");
    private final static QName _Lod_QNAME = new QName("http://www.opengis.net/kml/2.2", "Lod");
    private final static QName _AltitudeModeSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AltitudeModeSimpleExtensionGroup");
    private final static QName _Interpolate_QNAME = new QName("http://www.opengis.net/kml/2.2", "interpolate");
    private final static QName _LocationSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LocationSimpleExtensionGroup");
    private final static QName _ViewVolumeSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "ViewVolumeSimpleExtensionGroup");
    private final static QName _CameraObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "CameraObjectExtensionGroup");
    private final static QName _StyleMapObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "StyleMapObjectExtensionGroup");
    private final static QName _LinkDescription_QNAME = new QName("http://www.opengis.net/kml/2.2", "linkDescription");
    private final static QName _AbstractUpdateOptionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractUpdateOptionGroup");
    private final static QName _AbstractFeatureGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractFeatureGroup");
    private final static QName _MaxWidth_QNAME = new QName("http://www.opengis.net/kml/2.2", "maxWidth");
    private final static QName _SimpleArrayDataExtension_QNAME = new QName("http://www.opengis.net/kml/2.2", "SimpleArrayDataExtension");
    private final static QName _GroundOverlay_QNAME = new QName("http://www.opengis.net/kml/2.2", "GroundOverlay");
    private final static QName _Href_QNAME = new QName("http://www.opengis.net/kml/2.2", "href");
    private final static QName _Text_QNAME = new QName("http://www.opengis.net/kml/2.2", "text");
    private final static QName _ScreenXY_QNAME = new QName("http://www.opengis.net/kml/2.2", "screenXY");
    private final static QName _SchemaData_QNAME = new QName("http://www.opengis.net/kml/2.2", "SchemaData");
    private final static QName _BasicLinkObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "BasicLinkObjectExtensionGroup");
    private final static QName _LinkObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LinkObjectExtensionGroup");
    private final static QName _MultiTrack_QNAME = new QName("http://www.opengis.net/kml/2.2", "MultiTrack");
    private final static QName _AbstractFeatureSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "AbstractFeatureSimpleExtensionGroup");
    private final static QName _When_QNAME = new QName("http://www.opengis.net/kml/2.2", "when");
    private final static QName _LatLonAltBoxSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LatLonAltBoxSimpleExtensionGroup");
    private final static QName _BoundaryObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "BoundaryObjectExtensionGroup");
    private final static QName _LabelStyleObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LabelStyleObjectExtensionGroup");
    private final static QName _PlayMode_QNAME = new QName("http://www.opengis.net/kml/2.2", "playMode");
    private final static QName _LatLonQuad_QNAME = new QName("http://www.opengis.net/kml/2.2", "LatLonQuad");
    private final static QName _LatLonBoxSimpleExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "LatLonBoxSimpleExtensionGroup");
    private final static QName _PlaylistObjectExtensionGroup_QNAME = new QName("http://www.opengis.net/kml/2.2", "PlaylistObjectExtensionGroup");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.opengis.kml._2
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteType }
     *
     */
    public DeleteType createDeleteType() {
        return new DeleteType();
    }

    /**
     * Create an instance of {@link DocumentType }
     *
     */
    public DocumentType createDocumentType() {
        return new DocumentType();
    }

    /**
     * Create an instance of {@link PlaylistType }
     *
     */
    public PlaylistType createPlaylistType() {
        return new PlaylistType();
    }

    /**
     * Create an instance of {@link FlyToType }
     *
     */
    public FlyToType createFlyToType() {
        return new FlyToType();
    }

    /**
     * Create an instance of {@link AbstractTourPrimitiveType }
     *
     */
    public AbstractTourPrimitiveType createAbstractTourPrimitiveType() {
        return new AbstractTourPrimitiveType();
    }

    /**
     * Create an instance of {@link LatLonBoxType }
     *
     */
    public LatLonBoxType createLatLonBoxType() {
        return new LatLonBoxType();
    }

    /**
     * Create an instance of {@link OrientationType }
     *
     */
    public OrientationType createOrientationType() {
        return new OrientationType();
    }

    /**
     * Create an instance of {@link TimeStampType }
     *
     */
    public TimeStampType createTimeStampType() {
        return new TimeStampType();
    }

    /**
     * Create an instance of {@link Vec2Type }
     *
     */
    public Vec2Type createVec2Type() {
        return new Vec2Type();
    }

    /**
     * Create an instance of {@link LinkType }
     *
     */
    public LinkType createLinkType() {
        return new LinkType();
    }

    /**
     * Create an instance of {@link ListStyleType }
     *
     */
    public ListStyleType createListStyleType() {
        return new ListStyleType();
    }

    /**
     * Create an instance of {@link LineStyleType }
     *
     */
    public LineStyleType createLineStyleType() {
        return new LineStyleType();
    }

    /**
     * Create an instance of {@link MetadataType }
     *
     */
    public MetadataType createMetadataType() {
        return new MetadataType();
    }

    /**
     * Create an instance of {@link ItemIconType }
     *
     */
    public ItemIconType createItemIconType() {
        return new ItemIconType();
    }

    /**
     * Create an instance of {@link LatLonAltBoxType }
     *
     */
    public LatLonAltBoxType createLatLonAltBoxType() {
        return new LatLonAltBoxType();
    }

    /**
     * Create an instance of {@link TourType }
     *
     */
    public TourType createTourType() {
        return new TourType();
    }

    /**
     * Create an instance of {@link PlacemarkType }
     *
     */
    public PlacemarkType createPlacemarkType() {
        return new PlacemarkType();
    }

    /**
     * Create an instance of {@link LinearRingType }
     *
     */
    public LinearRingType createLinearRingType() {
        return new LinearRingType();
    }

    /**
     * Create an instance of {@link SimpleArrayDataType }
     *
     */
    public SimpleArrayDataType createSimpleArrayDataType() {
        return new SimpleArrayDataType();
    }

    /**
     * Create an instance of {@link SchemaType }
     *
     */
    public SchemaType createSchemaType() {
        return new SchemaType();
    }

    /**
     * Create an instance of {@link ImagePyramidType }
     *
     */
    public ImagePyramidType createImagePyramidType() {
        return new ImagePyramidType();
    }

    /**
     * Create an instance of {@link NetworkLinkType }
     *
     */
    public NetworkLinkType createNetworkLinkType() {
        return new NetworkLinkType();
    }

    /**
     * Create an instance of {@link ViewVolumeType }
     *
     */
    public ViewVolumeType createViewVolumeType() {
        return new ViewVolumeType();
    }

    /**
     * Create an instance of {@link CameraType }
     *
     */
    public CameraType createCameraType() {
        return new CameraType();
    }

    /**
     * Create an instance of {@link PolygonType }
     *
     */
    public PolygonType createPolygonType() {
        return new PolygonType();
    }

    /**
     * Create an instance of {@link ResourceMapType }
     *
     */
    public ResourceMapType createResourceMapType() {
        return new ResourceMapType();
    }

    /**
     * Create an instance of {@link SnippetType }
     *
     */
    public SnippetType createSnippetType() {
        return new SnippetType();
    }

    /**
     * Create an instance of {@link AnimatedUpdateType }
     *
     */
    public AnimatedUpdateType createAnimatedUpdateType() {
        return new AnimatedUpdateType();
    }

    /**
     * Create an instance of {@link RegionType }
     *
     */
    public RegionType createRegionType() {
        return new RegionType();
    }

    /**
     * Create an instance of {@link BoundaryType }
     *
     */
    public BoundaryType createBoundaryType() {
        return new BoundaryType();
    }

    /**
     * Create an instance of {@link ScreenOverlayType }
     *
     */
    public ScreenOverlayType createScreenOverlayType() {
        return new ScreenOverlayType();
    }

    /**
     * Create an instance of {@link NetworkLinkControlType }
     *
     */
    public NetworkLinkControlType createNetworkLinkControlType() {
        return new NetworkLinkControlType();
    }

    /**
     * Create an instance of {@link PairType }
     *
     */
    public PairType createPairType() {
        return new PairType();
    }

    /**
     * Create an instance of {@link StyleMapType }
     *
     */
    public StyleMapType createStyleMapType() {
        return new StyleMapType();
    }

    /**
     * Create an instance of {@link LabelStyleType }
     *
     */
    public LabelStyleType createLabelStyleType() {
        return new LabelStyleType();
    }

    /**
     * Create an instance of {@link LineStringType }
     *
     */
    public LineStringType createLineStringType() {
        return new LineStringType();
    }

    /**
     * Create an instance of {@link LocationType }
     *
     */
    public LocationType createLocationType() {
        return new LocationType();
    }

    /**
     * Create an instance of {@link MultiTrackType }
     *
     */
    public MultiTrackType createMultiTrackType() {
        return new MultiTrackType();
    }

    /**
     * Create an instance of {@link SchemaDataType }
     *
     */
    public SchemaDataType createSchemaDataType() {
        return new SchemaDataType();
    }

    /**
     * Create an instance of {@link LatLonQuadType }
     *
     */
    public LatLonQuadType createLatLonQuadType() {
        return new LatLonQuadType();
    }

    /**
     * Create an instance of {@link GroundOverlayType }
     *
     */
    public GroundOverlayType createGroundOverlayType() {
        return new GroundOverlayType();
    }

    /**
     * Create an instance of {@link LodType }
     *
     */
    public LodType createLodType() {
        return new LodType();
    }

    /**
     * Create an instance of {@link SimpleFieldType }
     *
     */
    public SimpleFieldType createSimpleFieldType() {
        return new SimpleFieldType();
    }

    /**
     * Create an instance of {@link KmlType }
     *
     */
    public KmlType createKmlType() {
        return new KmlType();
    }

    /**
     * Create an instance of {@link FolderType }
     *
     */
    public FolderType createFolderType() {
        return new FolderType();
    }

    /**
     * Create an instance of {@link PolyStyleType }
     *
     */
    public PolyStyleType createPolyStyleType() {
        return new PolyStyleType();
    }

    /**
     * Create an instance of {@link LookAtType }
     *
     */
    public LookAtType createLookAtType() {
        return new LookAtType();
    }

    /**
     * Create an instance of {@link StyleType }
     *
     */
    public StyleType createStyleType() {
        return new StyleType();
    }

    /**
     * Create an instance of {@link IconStyleType }
     *
     */
    public IconStyleType createIconStyleType() {
        return new IconStyleType();
    }

    /**
     * Create an instance of {@link ModelType }
     *
     */
    public ModelType createModelType() {
        return new ModelType();
    }

    /**
     * Create an instance of {@link SoundCueType }
     *
     */
    public SoundCueType createSoundCueType() {
        return new SoundCueType();
    }

    /**
     * Create an instance of {@link UpdateType }
     *
     */
    public UpdateType createUpdateType() {
        return new UpdateType();
    }

    /**
     * Create an instance of {@link TourControlType }
     *
     */
    public TourControlType createTourControlType() {
        return new TourControlType();
    }

    /**
     * Create an instance of {@link BalloonStyleType }
     *
     */
    public BalloonStyleType createBalloonStyleType() {
        return new BalloonStyleType();
    }

    /**
     * Create an instance of {@link ExtendedDataType }
     *
     */
    public ExtendedDataType createExtendedDataType() {
        return new ExtendedDataType();
    }

    /**
     * Create an instance of {@link WaitType }
     *
     */
    public WaitType createWaitType() {
        return new WaitType();
    }

    /**
     * Create an instance of {@link PhotoOverlayType }
     *
     */
    public PhotoOverlayType createPhotoOverlayType() {
        return new PhotoOverlayType();
    }

    /**
     * Create an instance of {@link SimpleArrayFieldType }
     *
     */
    public SimpleArrayFieldType createSimpleArrayFieldType() {
        return new SimpleArrayFieldType();
    }

    /**
     * Create an instance of {@link TimeSpanType }
     *
     */
    public TimeSpanType createTimeSpanType() {
        return new TimeSpanType();
    }

    /**
     * Create an instance of {@link CreateType }
     *
     */
    public CreateType createCreateType() {
        return new CreateType();
    }

    /**
     * Create an instance of {@link SimpleDataType }
     *
     */
    public SimpleDataType createSimpleDataType() {
        return new SimpleDataType();
    }

    /**
     * Create an instance of {@link DataType }
     *
     */
    public DataType createDataType() {
        return new DataType();
    }

    /**
     * Create an instance of {@link ScaleType }
     *
     */
    public ScaleType createScaleType() {
        return new ScaleType();
    }

    /**
     * Create an instance of {@link PointType }
     *
     */
    public PointType createPointType() {
        return new PointType();
    }

    /**
     * Create an instance of {@link ChangeType }
     *
     */
    public ChangeType createChangeType() {
        return new ChangeType();
    }

    /**
     * Create an instance of {@link AliasType }
     *
     */
    public AliasType createAliasType() {
        return new AliasType();
    }

    /**
     * Create an instance of {@link MultiGeometryType }
     *
     */
    public MultiGeometryType createMultiGeometryType() {
        return new MultiGeometryType();
    }

    /**
     * Create an instance of {@link TrackType }
     *
     */
    public TrackType createTrackType() {
        return new TrackType();
    }

    /**
     * Create an instance of {@link BasicLinkType }
     *
     */
    public BasicLinkType createBasicLinkType() {
        return new BasicLinkType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LineStringType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LineString", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractGeometryGroup")
    public JAXBElement<LineStringType> createLineString(LineStringType value) {
        return new JAXBElement<LineStringType>(_LineString_QNAME, LineStringType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractStyleSelectorType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractStyleSelectorGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractStyleSelectorType> createAbstractStyleSelectorGroup(AbstractStyleSelectorType value) {
        return new JAXBElement<AbstractStyleSelectorType>(_AbstractStyleSelectorGroup_QNAME, AbstractStyleSelectorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocationType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Location", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<LocationType> createLocation(LocationType value) {
        return new JAXBElement<LocationType>(_Location_QNAME, LocationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LatLonBoxObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createLatLonBoxObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_LatLonBoxObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "FolderSimpleExtensionGroup")
    public JAXBElement<Object> createFolderSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_FolderSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleMapType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "StyleMap", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractStyleSelectorGroup")
    public JAXBElement<StyleMapType> createStyleMap(StyleMapType value) {
        return new JAXBElement<StyleMapType>(_StyleMap_QNAME, StyleMapType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "rotation", defaultValue = "0.0")
    public JAXBElement<Double> createRotation(Double value) {
        return new JAXBElement<Double>(_Rotation_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractContainerType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractContainerGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractFeatureGroup")
    public JAXBElement<AbstractContainerType> createAbstractContainerGroup(AbstractContainerType value) {
        return new JAXBElement<AbstractContainerType>(_AbstractContainerGroup_QNAME, AbstractContainerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LocationObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createLocationObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_LocationObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AliasSimpleExtensionGroup")
    public JAXBElement<Object> createAliasSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AliasSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "message")
    public JAXBElement<String> createMessage(String value) {
        return new JAXBElement<String>(_Message_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "StyleObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createStyleObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_StyleObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "styleUrl")
    public JAXBElement<String> createStyleUrl(String value) {
        return new JAXBElement<String>(_StyleUrl_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "MultiGeometrySimpleExtensionGroup")
    public JAXBElement<Object> createMultiGeometrySimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_MultiGeometrySimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LabelStyleType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LabelStyle", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractColorStyleGroup")
    public JAXBElement<LabelStyleType> createLabelStyle(LabelStyleType value) {
        return new JAXBElement<LabelStyleType>(_LabelStyle_QNAME, LabelStyleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ScreenOverlayObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createScreenOverlayObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_ScreenOverlayObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PlaylistSimpleExtensionGroup")
    public JAXBElement<Object> createPlaylistSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_PlaylistSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TourObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createTourObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_TourObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "duration", defaultValue = "0.0")
    public JAXBElement<Double> createDuration(Double value) {
        return new JAXBElement<Double>(_Duration_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "OrientationSimpleExtensionGroup")
    public JAXBElement<Object> createOrientationSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_OrientationSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "httpQuery")
    public JAXBElement<String> createHttpQuery(String value) {
        return new JAXBElement<String>(_HttpQuery_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractFeatureObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractFeatureObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractFeatureObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PairType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Pair", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<PairType> createPair(PairType value) {
        return new JAXBElement<PairType>(_Pair_QNAME, PairType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NetworkLinkControlType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "NetworkLinkControl")
    public JAXBElement<NetworkLinkControlType> createNetworkLinkControl(NetworkLinkControlType value) {
        return new JAXBElement<NetworkLinkControlType>(_NetworkLinkControl_QNAME, NetworkLinkControlType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltitudeModeEnumType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "altitudeMode", defaultValue = "clampToGround")
    public JAXBElement<AltitudeModeEnumType> createAltitudeMode(AltitudeModeEnumType value) {
        return new JAXBElement<AltitudeModeEnumType>(_AltitudeMode_QNAME, AltitudeModeEnumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LineStyleSimpleExtensionGroup")
    public JAXBElement<Object> createLineStyleSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_LineStyleSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractLatLonBoxType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractLatLonBoxGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractExtentGroup")
    public JAXBElement<AbstractLatLonBoxType> createAbstractLatLonBoxGroup(AbstractLatLonBoxType value) {
        return new JAXBElement<AbstractLatLonBoxType>(_AbstractLatLonBoxGroup_QNAME, AbstractLatLonBoxType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "end")
    public JAXBElement<String> createEnd(String value) {
        return new JAXBElement<String>(_End_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "viewBoundScale", defaultValue = "1.0")
    public JAXBElement<Double> createViewBoundScale(Double value) {
        return new JAXBElement<Double>(_ViewBoundScale_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "color", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractBgColorGroup", defaultValue = "ffffffff")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    public JAXBElement<byte[]> createColor(byte[] value) {
        return new JAXBElement<byte[]>(_Color_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractLatLonBoxSimpleExtensionGroup")
    public JAXBElement<Object> createAbstractLatLonBoxSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractLatLonBoxSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "NetworkLinkSimpleExtensionGroup")
    public JAXBElement<Object> createNetworkLinkSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_NetworkLinkSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoundaryType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "innerBoundaryIs")
    public JAXBElement<BoundaryType> createInnerBoundaryIs(BoundaryType value) {
        return new JAXBElement<BoundaryType>(_InnerBoundaryIs_QNAME, BoundaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "expires")
    public JAXBElement<String> createExpires(String value) {
        return new JAXBElement<String>(_Expires_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScreenOverlayType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ScreenOverlay", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractOverlayGroup")
    public JAXBElement<ScreenOverlayType> createScreenOverlay(ScreenOverlayType value) {
        return new JAXBElement<ScreenOverlayType>(_ScreenOverlay_QNAME, ScreenOverlayType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SnippetType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "linkSnippet")
    public JAXBElement<SnippetType> createLinkSnippet(SnippetType value) {
        return new JAXBElement<SnippetType>(_LinkSnippet_QNAME, SnippetType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractTourPrimitiveObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractTourPrimitiveObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractTourPrimitiveObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnimatedUpdateType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AnimatedUpdate", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractTourPrimitiveGroup")
    public JAXBElement<AnimatedUpdateType> createAnimatedUpdate(AnimatedUpdateType value) {
        return new JAXBElement<AnimatedUpdateType>(_AnimatedUpdate_QNAME, AnimatedUpdateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceMapType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ResourceMap", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<ResourceMapType> createResourceMap(ResourceMapType value) {
        return new JAXBElement<ResourceMapType>(_ResourceMap_QNAME, ResourceMapType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "phoneNumber")
    public JAXBElement<String> createPhoneNumber(String value) {
        return new JAXBElement<String>(_PhoneNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractObjectGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractObjectGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeometryType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractGeometryGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractGeometryType> createAbstractGeometryGroup(AbstractGeometryType value) {
        return new JAXBElement<AbstractGeometryType>(_AbstractGeometryGroup_QNAME, AbstractGeometryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "CameraSimpleExtensionGroup")
    public JAXBElement<Object> createCameraSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_CameraSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegionType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Region", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<RegionType> createRegion(RegionType value) {
        return new JAXBElement<RegionType>(_Region_QNAME, RegionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractExtentObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractExtentObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractExtentObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Icon", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<LinkType> createIcon(LinkType value) {
        return new JAXBElement<LinkType>(_Icon_QNAME, LinkType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ImagePyramidObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createImagePyramidObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_ImagePyramidObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "refreshInterval", defaultValue = "4.0")
    public JAXBElement<Double> createRefreshInterval(Double value) {
        return new JAXBElement<Double>(_RefreshInterval_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "visibility", defaultValue = "1")
    public JAXBElement<Boolean> createVisibility(Boolean value) {
        return new JAXBElement<Boolean>(_Visibility_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "targetHref")
    public JAXBElement<String> createTargetHref(String value) {
        return new JAXBElement<String>(_TargetHref_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "maxAltitude", defaultValue = "0.0")
    public JAXBElement<Double> createMaxAltitude(Double value) {
        return new JAXBElement<Double>(_MaxAltitude_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "flyToView", defaultValue = "0")
    public JAXBElement<Boolean> createFlyToView(Boolean value) {
        return new JAXBElement<Boolean>(_FlyToView_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PolyStyleSimpleExtensionGroup")
    public JAXBElement<Object> createPolyStyleSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_PolyStyleSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "textColor", defaultValue = "ff000000")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    public JAXBElement<byte[]> createTextColor(byte[] value) {
        return new JAXBElement<byte[]>(_TextColor_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "abstractShape")
    public JAXBElement<String> createAbstractShape(String value) {
        return new JAXBElement<String>(_AbstractShape_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "abstractGridOrigin")
    public JAXBElement<String> createAbstractGridOrigin(String value) {
        return new JAXBElement<String>(_AbstractGridOrigin_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ItemIconObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createItemIconObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_ItemIconObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewVolumeType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ViewVolume", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<ViewVolumeType> createViewVolume(ViewVolumeType value) {
        return new JAXBElement<ViewVolumeType>(_ViewVolume_QNAME, ViewVolumeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ScaleObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createScaleObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_ScaleObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NetworkLinkType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "NetworkLink", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractFeatureGroup")
    public JAXBElement<NetworkLinkType> createNetworkLink(NetworkLinkType value) {
        return new JAXBElement<NetworkLinkType>(_NetworkLink_QNAME, NetworkLinkType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AnimatedUpdateObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAnimatedUpdateObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AnimatedUpdateObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SoundCueSimpleExtensionGroup")
    public JAXBElement<Object> createSoundCueSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_SoundCueSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolygonType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Polygon", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractGeometryGroup")
    public JAXBElement<PolygonType> createPolygon(PolygonType value) {
        return new JAXBElement<PolygonType>(_Polygon_QNAME, PolygonType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TimeStampSimpleExtensionGroup")
    public JAXBElement<Object> createTimeStampSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_TimeStampSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractGeometryObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractGeometryObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractGeometryObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "WaitSimpleExtensionGroup")
    public JAXBElement<Object> createWaitSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_WaitSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridOriginEnumType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "gridOrigin", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "abstractGridOrigin", defaultValue = "lowerLeft")
    public JAXBElement<GridOriginEnumType> createGridOrigin(GridOriginEnumType value) {
        return new JAXBElement<GridOriginEnumType>(_GridOrigin_QNAME, GridOriginEnumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CameraType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Camera", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractViewGroup")
    public JAXBElement<CameraType> createCamera(CameraType value) {
        return new JAXBElement<CameraType>(_Camera_QNAME, CameraType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TrackSimpleExtensionGroup")
    public JAXBElement<Object> createTrackSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_TrackSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchemaType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Schema")
    public JAXBElement<SchemaType> createSchema(SchemaType value) {
        return new JAXBElement<SchemaType>(_Schema_QNAME, SchemaType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "FlyToSimpleExtensionGroup")
    public JAXBElement<Object> createFlyToSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_FlyToSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "NetworkLinkControlSimpleExtensionGroup")
    public JAXBElement<Object> createNetworkLinkControlSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_NetworkLinkControlSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "linkName")
    public JAXBElement<String> createLinkName(String value) {
        return new JAXBElement<String>(_LinkName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImagePyramidType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ImagePyramid", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<ImagePyramidType> createImagePyramid(ImagePyramidType value) {
        return new JAXBElement<ImagePyramidType>(_ImagePyramid_QNAME, ImagePyramidType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "tilt", defaultValue = "0.0")
    public JAXBElement<Double> createTilt(Double value) {
        return new JAXBElement<Double>(_Tilt_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "viewFormat")
    public JAXBElement<String> createViewFormat(String value) {
        return new JAXBElement<String>(_ViewFormat_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "RegionSimpleExtensionGroup")
    public JAXBElement<Object> createRegionSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_RegionSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PlacemarkObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createPlacemarkObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_PlacemarkObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinearRingType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LinearRing", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractGeometryGroup")
    public JAXBElement<LinearRingType> createLinearRing(LinearRingType value) {
        return new JAXBElement<LinearRingType>(_LinearRing_QNAME, LinearRingType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleArrayDataType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SimpleArrayData")
    public JAXBElement<SimpleArrayDataType> createSimpleArrayData(SimpleArrayDataType value) {
        return new JAXBElement<SimpleArrayDataType>(_SimpleArrayData_QNAME, SimpleArrayDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "open", defaultValue = "0")
    public JAXBElement<Boolean> createOpen(Boolean value) {
        return new JAXBElement<Boolean>(_Open_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "minLodPixels", defaultValue = "0.0")
    public JAXBElement<Double> createMinLodPixels(Double value) {
        return new JAXBElement<Double>(_MinLodPixels_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "y", defaultValue = "1.0")
    public JAXBElement<Double> createY(Double value) {
        return new JAXBElement<Double>(_Y_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "x", defaultValue = "1.0")
    public JAXBElement<Double> createX(Double value) {
        return new JAXBElement<Double>(_X_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LookAtSimpleExtensionGroup")
    public JAXBElement<Object> createLookAtSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_LookAtSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LodObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createLodObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_LodObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "abstractPlayMode")
    public JAXBElement<String> createAbstractPlayMode(String value) {
        return new JAXBElement<String>(_AbstractPlayMode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "z", defaultValue = "1.0")
    public JAXBElement<Double> createZ(Double value) {
        return new JAXBElement<Double>(_Z_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LinearRingObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createLinearRingObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_LinearRingObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LatLonQuadObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createLatLonQuadObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_LatLonQuadObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlacemarkType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Placemark", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractFeatureGroup")
    public JAXBElement<PlacemarkType> createPlacemark(PlacemarkType value) {
        return new JAXBElement<PlacemarkType>(_Placemark_QNAME, PlacemarkType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "address")
    public JAXBElement<String> createAddress(String value) {
        return new JAXBElement<String>(_Address_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractStyleSelectorSimpleExtensionGroup")
    public JAXBElement<Object> createAbstractStyleSelectorSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractStyleSelectorSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PointSimpleExtensionGroup")
    public JAXBElement<Object> createPointSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_PointSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TimeSpanSimpleExtensionGroup")
    public JAXBElement<Object> createTimeSpanSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_TimeSpanSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "coordinates")
    public JAXBElement<List<String>> createCoordinates(List<String> value) {
        return new JAXBElement<List<String>>(_Coordinates_QNAME, ((Class) List.class), null, ((List<String> ) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "maxHeight", defaultValue = "0")
    public JAXBElement<Integer> createMaxHeight(Integer value) {
        return new JAXBElement<Integer>(_MaxHeight_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LatLonAltBoxType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LatLonAltBox", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractLatLonBoxGroup")
    public JAXBElement<LatLonAltBoxType> createLatLonAltBox(LatLonAltBoxType value) {
        return new JAXBElement<LatLonAltBoxType>(_LatLonAltBox_QNAME, LatLonAltBoxType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "outline", defaultValue = "1")
    public JAXBElement<Boolean> createOutline(Boolean value) {
        return new JAXBElement<Boolean>(_Outline_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefreshModeEnumType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "refreshMode", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "abstractRefreshMode", defaultValue = "onChange")
    public JAXBElement<RefreshModeEnumType> createRefreshMode(RefreshModeEnumType value) {
        return new JAXBElement<RefreshModeEnumType>(_RefreshMode_QNAME, RefreshModeEnumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "sourceHref")
    public JAXBElement<String> createSourceHref(String value) {
        return new JAXBElement<String>(_SourceHref_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PairObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createPairObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_PairObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "IconStyleSimpleExtensionGroup")
    public JAXBElement<Object> createIconStyleSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_IconStyleSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TourType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Tour", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractFeatureGroup")
    public JAXBElement<TourType> createTour(TourType value) {
        return new JAXBElement<TourType>(_Tour_QNAME, TourType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleStateEnumType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "key", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "abstractKey", defaultValue = "normal")
    public JAXBElement<StyleStateEnumType> createKey(StyleStateEnumType value) {
        return new JAXBElement<StyleStateEnumType>(_Key_QNAME, StyleStateEnumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SchemaDataExtension")
    public JAXBElement<Object> createSchemaDataExtension(Object value) {
        return new JAXBElement<Object>(_SchemaDataExtension_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LineStringObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createLineStringObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_LineStringObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "DocumentSimpleExtensionGroup")
    public JAXBElement<Object> createDocumentSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_DocumentSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "latitude", defaultValue = "0.0")
    public JAXBElement<Double> createLatitude(Double value) {
        return new JAXBElement<Double>(_Latitude_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "displayName")
    public JAXBElement<String> createDisplayName(String value) {
        return new JAXBElement<String>(_DisplayName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LineStyleType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LineStyle", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractColorStyleGroup")
    public JAXBElement<LineStyleType> createLineStyle(LineStyleType value) {
        return new JAXBElement<LineStyleType>(_LineStyle_QNAME, LineStyleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListStyleType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ListStyle", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractSubStyleGroup")
    public JAXBElement<ListStyleType> createListStyle(ListStyleType value) {
        return new JAXBElement<ListStyleType>(_ListStyle_QNAME, ListStyleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractGeometrySimpleExtensionGroup")
    public JAXBElement<Object> createAbstractGeometrySimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractGeometrySimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ListStyleObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createListStyleObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_ListStyleObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "UpdateExtensionGroup")
    public JAXBElement<Object> createUpdateExtensionGroup(Object value) {
        return new JAXBElement<Object>(_UpdateExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractContainerObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractContainerObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractContainerObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "KmlObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createKmlObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_KmlObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TourControlSimpleExtensionGroup")
    public JAXBElement<Object> createTourControlSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_TourControlSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractSubStyleType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractSubStyleGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractSubStyleType> createAbstractSubStyleGroup(AbstractSubStyleType value) {
        return new JAXBElement<AbstractSubStyleType>(_AbstractSubStyleGroup_QNAME, AbstractSubStyleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractSubStyleSimpleExtensionGroup")
    public JAXBElement<Object> createAbstractSubStyleSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractSubStyleSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "GroundOverlayObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createGroundOverlayObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_GroundOverlayObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PhotoOverlayObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createPhotoOverlayObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_PhotoOverlayObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "roll", defaultValue = "0.0")
    public JAXBElement<Double> createRoll(Double value) {
        return new JAXBElement<Double>(_Roll_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetadataType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Metadata", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractExtendedDataGroup")
    public JAXBElement<MetadataType> createMetadata(MetadataType value) {
        return new JAXBElement<MetadataType>(_Metadata_QNAME, MetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ItemIconType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ItemIcon", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<ItemIconType> createItemIcon(ItemIconType value) {
        return new JAXBElement<ItemIconType>(_ItemIcon_QNAME, ItemIconType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractOverlayObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractOverlayObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractOverlayObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "BalloonStyleObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createBalloonStyleObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_BalloonStyleObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractLinkGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractLinkGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractLinkGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vec2Type }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "size")
    public JAXBElement<Vec2Type> createSize(Vec2Type value) {
        return new JAXBElement<Vec2Type>(_Size_QNAME, Vec2Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractOverlaySimpleExtensionGroup")
    public JAXBElement<Object> createAbstractOverlaySimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractOverlaySimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Link", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractLinkGroup")
    public JAXBElement<LinkType> createLink(LinkType value) {
        return new JAXBElement<LinkType>(_Link_QNAME, LinkType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ObjectSimpleExtensionGroup")
    public JAXBElement<Object> createObjectSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_ObjectSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractStyleSelectorObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractStyleSelectorObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractStyleSelectorObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PlacemarkSimpleExtensionGroup")
    public JAXBElement<Object> createPlacemarkSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_PlacemarkSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlyToType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "FlyTo", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractTourPrimitiveGroup")
    public JAXBElement<FlyToType> createFlyTo(FlyToType value) {
        return new JAXBElement<FlyToType>(_FlyTo_QNAME, FlyToType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SchemaExtension")
    public JAXBElement<Object> createSchemaExtension(Object value) {
        return new JAXBElement<Object>(_SchemaExtension_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LinearRingSimpleExtensionGroup")
    public JAXBElement<Object> createLinearRingSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_LinearRingSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "RegionObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createRegionObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_RegionObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrientationType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Orientation", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<OrientationType> createOrientation(OrientationType value) {
        return new JAXBElement<OrientationType>(_Orientation_QNAME, OrientationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewRefreshModeEnumType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "viewRefreshMode", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "abstractViewRefreshMode", defaultValue = "never")
    public JAXBElement<ViewRefreshModeEnumType> createViewRefreshMode(ViewRefreshModeEnumType value) {
        return new JAXBElement<ViewRefreshModeEnumType>(_ViewRefreshMode_QNAME, ViewRefreshModeEnumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeStampType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TimeStamp", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractTimePrimitiveGroup")
    public JAXBElement<TimeStampType> createTimeStamp(TimeStampType value) {
        return new JAXBElement<TimeStampType>(_TimeStamp_QNAME, TimeStampType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LatLonBoxType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LatLonBox", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractLatLonBoxGroup")
    public JAXBElement<LatLonBoxType> createLatLonBox(LatLonBoxType value) {
        return new JAXBElement<LatLonBoxType>(_LatLonBox_QNAME, LatLonBoxType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "DataExtension")
    public JAXBElement<Object> createDataExtension(Object value) {
        return new JAXBElement<Object>(_DataExtension_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractExtentSimpleExtensionGroup")
    public JAXBElement<Object> createAbstractExtentSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractExtentSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "abstractViewRefreshMode")
    public JAXBElement<String> createAbstractViewRefreshMode(String value) {
        return new JAXBElement<String>(_AbstractViewRefreshMode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlaylistType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Playlist", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<PlaylistType> createPlaylist(PlaylistType value) {
        return new JAXBElement<PlaylistType>(_Playlist_QNAME, PlaylistType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "refreshVisibility", defaultValue = "0")
    public JAXBElement<Boolean> createRefreshVisibility(Boolean value) {
        return new JAXBElement<Boolean>(_RefreshVisibility_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "maxSnippetLines", defaultValue = "2")
    public JAXBElement<Integer> createMaxSnippetLines(Integer value) {
        return new JAXBElement<Integer>(_MaxSnippetLines_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "delayedStart", defaultValue = "0.0")
    public JAXBElement<Double> createDelayedStart(Double value) {
        return new JAXBElement<Double>(_DelayedStart_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "near", defaultValue = "0.0")
    public JAXBElement<Double> createNear(Double value) {
        return new JAXBElement<Double>(_Near_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link ItemIconStateEnumType }{@code >}{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "state", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "abstractState")
    public JAXBElement<List<ItemIconStateEnumType>> createState(List<ItemIconStateEnumType> value) {
        return new JAXBElement<List<ItemIconStateEnumType>>(_State_QNAME, ((Class) List.class), null, ((List<ItemIconStateEnumType> ) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TourControlObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createTourControlObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_TourControlObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractSubStyleObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractSubStyleObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractSubStyleObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "KmlSimpleExtensionGroup")
    public JAXBElement<Object> createKmlSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_KmlSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "snippet", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractSnippetGroup")
    public JAXBElement<String> createSnippet(String value) {
        return new JAXBElement<String>(_Snippet_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LodSimpleExtensionGroup")
    public JAXBElement<Object> createLodSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_LodSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Delete", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractUpdateOptionGroup")
    public JAXBElement<DeleteType> createDelete(DeleteType value) {
        return new JAXBElement<DeleteType>(_Delete_QNAME, DeleteType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "GroundOverlaySimpleExtensionGroup")
    public JAXBElement<Object> createGroundOverlaySimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_GroundOverlaySimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LineStyleObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createLineStyleObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_LineStyleObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ListStyleSimpleExtensionGroup")
    public JAXBElement<Object> createListStyleSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_ListStyleSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Document", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractContainerGroup")
    public JAXBElement<DocumentType> createDocument(DocumentType value) {
        return new JAXBElement<DocumentType>(_Document_QNAME, DocumentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractTourPrimitiveSimpleExtensionGroup")
    public JAXBElement<Object> createAbstractTourPrimitiveSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractTourPrimitiveSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "IconStyleObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createIconStyleObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_IconStyleObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "leftFov", defaultValue = "0.0")
    public JAXBElement<Double> createLeftFov(Double value) {
        return new JAXBElement<Double>(_LeftFov_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "DocumentObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createDocumentObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_DocumentObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractLatLonBoxObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractLatLonBoxObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractLatLonBoxObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "minRefreshPeriod", defaultValue = "0.0")
    public JAXBElement<Double> createMinRefreshPeriod(Double value) {
        return new JAXBElement<Double>(_MinRefreshPeriod_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "WaitObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createWaitObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_WaitObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AliasType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Alias", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AliasType> createAlias(AliasType value) {
        return new JAXBElement<AliasType>(_Alias_QNAME, AliasType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "minFadeExtent", defaultValue = "0.0")
    public JAXBElement<Double> createMinFadeExtent(Double value) {
        return new JAXBElement<Double>(_MinFadeExtent_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ScaleSimpleExtensionGroup")
    public JAXBElement<Object> createScaleSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_ScaleSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "abstractRefreshMode")
    public JAXBElement<String> createAbstractRefreshMode(String value) {
        return new JAXBElement<String>(_AbstractRefreshMode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrackType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Track", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractGeometryGroup")
    public JAXBElement<TrackType> createTrack(TrackType value) {
        return new JAXBElement<TrackType>(_Track_QNAME, TrackType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiGeometryType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "MultiGeometry", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractGeometryGroup")
    public JAXBElement<MultiGeometryType> createMultiGeometry(MultiGeometryType value) {
        return new JAXBElement<MultiGeometryType>(_MultiGeometry_QNAME, MultiGeometryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ImagePyramidSimpleExtensionGroup")
    public JAXBElement<Object> createImagePyramidSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_ImagePyramidSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractOverlayType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractOverlayGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractFeatureGroup")
    public JAXBElement<AbstractOverlayType> createAbstractOverlayGroup(AbstractOverlayType value) {
        return new JAXBElement<AbstractOverlayType>(_AbstractOverlayGroup_QNAME, AbstractOverlayType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "maxFadeExtent", defaultValue = "0.0")
    public JAXBElement<Double> createMaxFadeExtent(Double value) {
        return new JAXBElement<Double>(_MaxFadeExtent_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LineStringSimpleExtensionGroup")
    public JAXBElement<Object> createLineStringSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_LineStringSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractTimePrimitiveSimpleExtensionGroup")
    public JAXBElement<Object> createAbstractTimePrimitiveSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractTimePrimitiveSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Point", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractGeometryGroup")
    public JAXBElement<PointType> createPoint(PointType value) {
        return new JAXBElement<PointType>(_Point_QNAME, PointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Change", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractUpdateOptionGroup")
    public JAXBElement<ChangeType> createChange(ChangeType value) {
        return new JAXBElement<ChangeType>(_Change_QNAME, ChangeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleDataType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SimpleData")
    public JAXBElement<SimpleDataType> createSimpleData(SimpleDataType value) {
        return new JAXBElement<SimpleDataType>(_SimpleData_QNAME, SimpleDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Data", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<DataType> createData(DataType value) {
        return new JAXBElement<DataType>(_Data_QNAME, DataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScaleType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Scale", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<ScaleType> createScale(ScaleType value) {
        return new JAXBElement<ScaleType>(_Scale_QNAME, ScaleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "bgColor", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractBgColorGroup", defaultValue = "ffffffff")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    public JAXBElement<byte[]> createBgColor(byte[] value) {
        return new JAXBElement<byte[]>(_BgColor_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LookAtObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createLookAtObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_LookAtObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Create", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractUpdateOptionGroup")
    public JAXBElement<CreateType> createCreate(CreateType value) {
        return new JAXBElement<CreateType>(_Create_QNAME, CreateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeSpanType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TimeSpan", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractTimePrimitiveGroup")
    public JAXBElement<TimeSpanType> createTimeSpan(TimeSpanType value) {
        return new JAXBElement<TimeSpanType>(_TimeSpan_QNAME, TimeSpanType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "east", defaultValue = "180.0")
    public JAXBElement<Double> createEast(Double value) {
        return new JAXBElement<Double>(_East_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "value")
    public JAXBElement<Object> createValue(Object value) {
        return new JAXBElement<Object>(_Value_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PolyStyleObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createPolyStyleObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_PolyStyleObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LatLonAltBoxObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createLatLonAltBoxObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_LatLonAltBoxObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "BalloonStyleSimpleExtensionGroup")
    public JAXBElement<Object> createBalloonStyleSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_BalloonStyleSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LatLonQuadSimpleExtensionGroup")
    public JAXBElement<Object> createLatLonQuadSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_LatLonQuadSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "FlyToObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createFlyToObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_FlyToObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "angles")
    public JAXBElement<String> createAngles(String value) {
        return new JAXBElement<String>(_Angles_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "NetworkLinkControlObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createNetworkLinkControlObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_NetworkLinkControlObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "tessellate", defaultValue = "0")
    public JAXBElement<Boolean> createTessellate(Boolean value) {
        return new JAXBElement<Boolean>(_Tessellate_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "altitude", defaultValue = "0.0")
    public JAXBElement<Double> createAltitude(Double value) {
        return new JAXBElement<Double>(_Altitude_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractViewSimpleExtensionGroup")
    public JAXBElement<Object> createAbstractViewSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractViewSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleArrayFieldType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SimpleArrayField")
    public JAXBElement<SimpleArrayFieldType> createSimpleArrayField(SimpleArrayFieldType value) {
        return new JAXBElement<SimpleArrayFieldType>(_SimpleArrayField_QNAME, SimpleArrayFieldType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ModelObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createModelObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_ModelObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractExtendedDataGroup")
    public JAXBElement<Object> createAbstractExtendedDataGroup(Object value) {
        return new JAXBElement<Object>(_AbstractExtendedDataGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TrackObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createTrackObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_TrackObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "abstractFlyToMode")
    public JAXBElement<String> createAbstractFlyToMode(String value) {
        return new JAXBElement<String>(_AbstractFlyToMode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhotoOverlayType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PhotoOverlay", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractOverlayGroup")
    public JAXBElement<PhotoOverlayType> createPhotoOverlay(PhotoOverlayType value) {
        return new JAXBElement<PhotoOverlayType>(_PhotoOverlay_QNAME, PhotoOverlayType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "bottomFov", defaultValue = "0.0")
    public JAXBElement<Double> createBottomFov(Double value) {
        return new JAXBElement<Double>(_BottomFov_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractTimePrimitiveType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractTimePrimitiveGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractTimePrimitiveType> createAbstractTimePrimitiveGroup(AbstractTimePrimitiveType value) {
        return new JAXBElement<AbstractTimePrimitiveType>(_AbstractTimePrimitiveGroup_QNAME, AbstractTimePrimitiveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ResourceMapSimpleExtensionGroup")
    public JAXBElement<Object> createResourceMapSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_ResourceMapSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "abstractState")
    public JAXBElement<Object> createAbstractState(Object value) {
        return new JAXBElement<Object>(_AbstractState_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "drawOrder", defaultValue = "0")
    public JAXBElement<Integer> createDrawOrder(Integer value) {
        return new JAXBElement<Integer>(_DrawOrder_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "tileSize", defaultValue = "256")
    public JAXBElement<Integer> createTileSize(Integer value) {
        return new JAXBElement<Integer>(_TileSize_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "balloonVisibility", defaultValue = "true")
    public JAXBElement<Boolean> createBalloonVisibility(Boolean value) {
        return new JAXBElement<Boolean>(_BalloonVisibility_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PairSimpleExtensionGroup")
    public JAXBElement<Object> createPairSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_PairSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "StyleSimpleExtensionGroup")
    public JAXBElement<Object> createStyleSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_StyleSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlyToModeEnumType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "flyToMode", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "abstractFlyToMode", defaultValue = "bounce")
    public JAXBElement<FlyToModeEnumType> createFlyToMode(FlyToModeEnumType value) {
        return new JAXBElement<FlyToModeEnumType>(_FlyToMode_QNAME, FlyToModeEnumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WaitType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Wait", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractTourPrimitiveGroup")
    public JAXBElement<WaitType> createWait(WaitType value) {
        return new JAXBElement<WaitType>(_Wait_QNAME, WaitType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoundaryType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "outerBoundaryIs")
    public JAXBElement<BoundaryType> createOuterBoundaryIs(BoundaryType value) {
        return new JAXBElement<BoundaryType>(_OuterBoundaryIs_QNAME, BoundaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "scale", defaultValue = "1.0")
    public JAXBElement<Double> createScalep(Double value) {
        return new JAXBElement<Double>(_Scalep_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ScreenOverlaySimpleExtensionGroup")
    public JAXBElement<Object> createScreenOverlaySimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_ScreenOverlaySimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TimeSpanObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createTimeSpanObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_TimeSpanObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vec2Type }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "overlayXY")
    public JAXBElement<Vec2Type> createOverlayXY(Vec2Type value) {
        return new JAXBElement<Vec2Type>(_OverlayXY_QNAME, Vec2Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtendedDataType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ExtendedData", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractExtendedDataGroup")
    public JAXBElement<ExtendedDataType> createExtendedData(ExtendedDataType value) {
        return new JAXBElement<ExtendedDataType>(_ExtendedData_QNAME, ExtendedDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AliasObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAliasObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AliasObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "horizFov")
    public JAXBElement<Double> createHorizFov(Double value) {
        return new JAXBElement<Double>(_HorizFov_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AltitudeModeObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAltitudeModeObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AltitudeModeObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PointObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createPointObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_PointObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "FolderObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createFolderObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_FolderObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractSnippetGroup")
    public JAXBElement<Object> createAbstractSnippetGroup(Object value) {
        return new JAXBElement<Object>(_AbstractSnippetGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SimpleFieldExtension")
    public JAXBElement<Object> createSimpleFieldExtension(Object value) {
        return new JAXBElement<Object>(_SimpleFieldExtension_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BalloonStyleType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "BalloonStyle", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractSubStyleGroup")
    public JAXBElement<BalloonStyleType> createBalloonStyle(BalloonStyleType value) {
        return new JAXBElement<BalloonStyleType>(_BalloonStyle_QNAME, BalloonStyleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "fill", defaultValue = "1")
    public JAXBElement<Boolean> createFill(Boolean value) {
        return new JAXBElement<Boolean>(_Fill_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DisplayModeEnumType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "displayMode", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "abstractDisplayMode", defaultValue = "default")
    public JAXBElement<DisplayModeEnumType> createDisplayMode(DisplayModeEnumType value) {
        return new JAXBElement<DisplayModeEnumType>(_DisplayMode_QNAME, DisplayModeEnumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ColorModeEnumType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "colorMode", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "abstractColorMode", defaultValue = "normal")
    public JAXBElement<ColorModeEnumType> createColorMode(ColorModeEnumType value) {
        return new JAXBElement<ColorModeEnumType>(_ColorMode_QNAME, ColorModeEnumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractColorStyleObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractColorStyleObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractColorStyleObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "StyleMapSimpleExtensionGroup")
    public JAXBElement<Object> createStyleMapSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_StyleMapSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "MultiGeometryObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createMultiGeometryObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_MultiGeometryObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "OrientationObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createOrientationObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_OrientationObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "extrude", defaultValue = "0")
    public JAXBElement<Boolean> createExtrude(Boolean value) {
        return new JAXBElement<Boolean>(_Extrude_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vec2Type }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "hotSpot")
    public JAXBElement<Vec2Type> createHotSpot(Vec2Type value) {
        return new JAXBElement<Vec2Type>(_HotSpot_QNAME, Vec2Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "abstractKey")
    public JAXBElement<String> createAbstractKey(String value) {
        return new JAXBElement<String>(_AbstractKey_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractColorStyleSimpleExtensionGroup")
    public JAXBElement<Object> createAbstractColorStyleSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractColorStyleSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PhotoOverlaySimpleExtensionGroup")
    public JAXBElement<Object> createPhotoOverlaySimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_PhotoOverlaySimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "longitude", defaultValue = "0.0")
    public JAXBElement<Double> createLongitude(Double value) {
        return new JAXBElement<Double>(_Longitude_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractContainerSimpleExtensionGroup")
    public JAXBElement<Object> createAbstractContainerSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractContainerSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "west", defaultValue = "-180.0")
    public JAXBElement<Double> createWest(Double value) {
        return new JAXBElement<Double>(_West_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TourControlType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TourControl", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractTourPrimitiveGroup")
    public JAXBElement<TourControlType> createTourControl(TourControlType value) {
        return new JAXBElement<TourControlType>(_TourControl_QNAME, TourControlType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ModelSimpleExtensionGroup")
    public JAXBElement<Object> createModelSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_ModelSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "NetworkLinkObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createNetworkLinkObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_NetworkLinkObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractExtentType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractExtentGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractExtentType> createAbstractExtentGroup(AbstractExtentType value) {
        return new JAXBElement<AbstractExtentType>(_AbstractExtentGroup_QNAME, AbstractExtentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "abstractColorMode")
    public JAXBElement<String> createAbstractColorMode(String value) {
        return new JAXBElement<String>(_AbstractColorMode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "maxSessionLength", defaultValue = "-1.0")
    public JAXBElement<Double> createMaxSessionLength(Double value) {
        return new JAXBElement<Double>(_MaxSessionLength_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Update")
    public JAXBElement<UpdateType> createUpdate(UpdateType value) {
        return new JAXBElement<UpdateType>(_Update_QNAME, UpdateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "rightFov", defaultValue = "0.0")
    public JAXBElement<Double> createRightFov(Double value) {
        return new JAXBElement<Double>(_RightFov_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ResourceMapObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createResourceMapObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_ResourceMapObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IconStyleType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "IconStyle", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractColorStyleGroup")
    public JAXBElement<IconStyleType> createIconStyle(IconStyleType value) {
        return new JAXBElement<IconStyleType>(_IconStyle_QNAME, IconStyleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AnimatedUpdateSimpleExtensionGroup")
    public JAXBElement<Object> createAnimatedUpdateSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AnimatedUpdateSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoundCueType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SoundCue", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractTourPrimitiveGroup")
    public JAXBElement<SoundCueType> createSoundCue(SoundCueType value) {
        return new JAXBElement<SoundCueType>(_SoundCue_QNAME, SoundCueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractViewType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractViewGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractViewType> createAbstractViewGroup(AbstractViewType value) {
        return new JAXBElement<AbstractViewType>(_AbstractViewGroup_QNAME, AbstractViewType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PolygonSimpleExtensionGroup")
    public JAXBElement<Object> createPolygonSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_PolygonSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "width", defaultValue = "1.0")
    public JAXBElement<Double> createWidth(Double value) {
        return new JAXBElement<Double>(_Width_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModelType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Model", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractGeometryGroup")
    public JAXBElement<ModelType> createModel(ModelType value) {
        return new JAXBElement<ModelType>(_Model_QNAME, ModelType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractViewObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractViewObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractViewObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "topFov", defaultValue = "0.0")
    public JAXBElement<Double> createTopFov(Double value) {
        return new JAXBElement<Double>(_TopFov_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "viewRefreshTime", defaultValue = "4.0")
    public JAXBElement<Double> createViewRefreshTime(Double value) {
        return new JAXBElement<Double>(_ViewRefreshTime_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "heading", defaultValue = "0.0")
    public JAXBElement<Double> createHeading(Double value) {
        return new JAXBElement<Double>(_Heading_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListItemTypeEnumType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "listItemType", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "abstractListItemType", defaultValue = "check")
    public JAXBElement<ListItemTypeEnumType> createListItemType(ListItemTypeEnumType value) {
        return new JAXBElement<ListItemTypeEnumType>(_ListItemType_QNAME, ListItemTypeEnumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vec2Type }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "rotationXY")
    public JAXBElement<Vec2Type> createRotationXY(Vec2Type value) {
        return new JAXBElement<Vec2Type>(_RotationXY_QNAME, Vec2Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "maxLodPixels", defaultValue = "-1.0")
    public JAXBElement<Double> createMaxLodPixels(Double value) {
        return new JAXBElement<Double>(_MaxLodPixels_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SimpleArrayFieldExtension")
    public JAXBElement<Object> createSimpleArrayFieldExtension(Object value) {
        return new JAXBElement<Object>(_SimpleArrayFieldExtension_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "MultiTrackObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createMultiTrackObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_MultiTrackObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "coord")
    public JAXBElement<String> createCoord(String value) {
        return new JAXBElement<String>(_Coord_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LookAtType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LookAt", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractViewGroup")
    public JAXBElement<LookAtType> createLookAt(LookAtType value) {
        return new JAXBElement<LookAtType>(_LookAt_QNAME, LookAtType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LinkSimpleExtensionGroup")
    public JAXBElement<Object> createLinkSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_LinkSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ViewVolumeObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createViewVolumeObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_ViewVolumeObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SeaFloorAltitudeModeEnumType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "seaFloorAltitudeMode")
    public JAXBElement<SeaFloorAltitudeModeEnumType> createSeaFloorAltitudeMode(SeaFloorAltitudeModeEnumType value) {
        return new JAXBElement<SeaFloorAltitudeModeEnumType>(_SeaFloorAltitudeMode_QNAME, SeaFloorAltitudeModeEnumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractBgColorGroup")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    public JAXBElement<byte[]> createAbstractBgColorGroup(byte[] value) {
        return new JAXBElement<byte[]>(_AbstractBgColorGroup_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StyleType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Style", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractStyleSelectorGroup")
    public JAXBElement<StyleType> createStyle(StyleType value) {
        return new JAXBElement<StyleType>(_Style_QNAME, StyleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "south", defaultValue = "-90.0")
    public JAXBElement<Double> createSouth(Double value) {
        return new JAXBElement<Double>(_South_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "altitudeOffset", defaultValue = "0.0")
    public JAXBElement<Double> createAltitudeOffset(Double value) {
        return new JAXBElement<Double>(_AltitudeOffset_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "north", defaultValue = "90.0")
    public JAXBElement<Double> createNorth(Double value) {
        return new JAXBElement<Double>(_North_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LabelStyleSimpleExtensionGroup")
    public JAXBElement<Object> createLabelStyleSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_LabelStyleSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "BoundarySimpleExtensionGroup")
    public JAXBElement<Object> createBoundarySimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_BoundarySimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "BasicLinkSimpleExtensionGroup")
    public JAXBElement<Object> createBasicLinkSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_BasicLinkSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolyStyleType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PolyStyle", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractColorStyleGroup")
    public JAXBElement<PolyStyleType> createPolyStyle(PolyStyleType value) {
        return new JAXBElement<PolyStyleType>(_PolyStyle_QNAME, PolyStyleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FolderType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Folder", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractContainerGroup")
    public JAXBElement<FolderType> createFolder(FolderType value) {
        return new JAXBElement<FolderType>(_Folder_QNAME, FolderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "abstractDisplayMode")
    public JAXBElement<String> createAbstractDisplayMode(String value) {
        return new JAXBElement<String>(_AbstractDisplayMode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinkType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Url", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractLinkGroup")
    public JAXBElement<LinkType> createUrl(LinkType value) {
        return new JAXBElement<LinkType>(_Url_QNAME, LinkType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "range", defaultValue = "0.0")
    public JAXBElement<Double> createRange(Double value) {
        return new JAXBElement<Double>(_Range_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmlType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "kml")
    public JAXBElement<KmlType> createKml(KmlType value) {
        return new JAXBElement<KmlType>(_Kml_QNAME, KmlType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TimeStampObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createTimeStampObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_TimeStampObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SoundCueObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createSoundCueObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_SoundCueObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "TourSimpleExtensionGroup")
    public JAXBElement<Object> createTourSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_TourSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractColorStyleType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractColorStyleGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractSubStyleGroup")
    public JAXBElement<AbstractColorStyleType> createAbstractColorStyleGroup(AbstractColorStyleType value) {
        return new JAXBElement<AbstractColorStyleType>(_AbstractColorStyleGroup_QNAME, AbstractColorStyleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractTourPrimitiveType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractTourPrimitiveGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractTourPrimitiveType> createAbstractTourPrimitiveGroup(AbstractTourPrimitiveType value) {
        return new JAXBElement<AbstractTourPrimitiveType>(_AbstractTourPrimitiveGroup_QNAME, AbstractTourPrimitiveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "abstractListItemType")
    public JAXBElement<String> createAbstractListItemType(String value) {
        return new JAXBElement<String>(_AbstractListItemType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "minAltitude", defaultValue = "0.0")
    public JAXBElement<Double> createMinAltitude(Double value) {
        return new JAXBElement<Double>(_MinAltitude_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "begin")
    public JAXBElement<String> createBegin(String value) {
        return new JAXBElement<String>(_Begin_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractTimePrimitiveObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createAbstractTimePrimitiveObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_AbstractTimePrimitiveObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "MultiTrackSimpleExtensionGroup")
    public JAXBElement<Object> createMultiTrackSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_MultiTrackSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleFieldType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SimpleField")
    public JAXBElement<SimpleFieldType> createSimpleField(SimpleFieldType value) {
        return new JAXBElement<SimpleFieldType>(_SimpleField_QNAME, SimpleFieldType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "cookie")
    public JAXBElement<String> createCookie(String value) {
        return new JAXBElement<String>(_Cookie_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShapeEnumType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "shape", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "abstractShape", defaultValue = "rectangle")
    public JAXBElement<ShapeEnumType> createShape(ShapeEnumType value) {
        return new JAXBElement<ShapeEnumType>(_Shape_QNAME, ShapeEnumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PolygonObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createPolygonObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_PolygonObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ItemIconSimpleExtensionGroup")
    public JAXBElement<Object> createItemIconSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_ItemIconSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LodType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "Lod", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<LodType> createLod(LodType value) {
        return new JAXBElement<LodType>(_Lod_QNAME, LodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AltitudeModeSimpleExtensionGroup")
    public JAXBElement<Object> createAltitudeModeSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AltitudeModeSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "interpolate", defaultValue = "false")
    public JAXBElement<Boolean> createInterpolate(Boolean value) {
        return new JAXBElement<Boolean>(_Interpolate_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LocationSimpleExtensionGroup")
    public JAXBElement<Object> createLocationSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_LocationSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "ViewVolumeSimpleExtensionGroup")
    public JAXBElement<Object> createViewVolumeSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_ViewVolumeSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "CameraObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createCameraObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_CameraObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "StyleMapObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createStyleMapObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_StyleMapObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "linkDescription")
    public JAXBElement<String> createLinkDescription(String value) {
        return new JAXBElement<String>(_LinkDescription_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractUpdateOptionGroup")
    public JAXBElement<Object> createAbstractUpdateOptionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractUpdateOptionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractFeatureType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractFeatureGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractFeatureType> createAbstractFeatureGroup(AbstractFeatureType value) {
        return new JAXBElement<AbstractFeatureType>(_AbstractFeatureGroup_QNAME, AbstractFeatureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "maxWidth", defaultValue = "0")
    public JAXBElement<Integer> createMaxWidth(Integer value) {
        return new JAXBElement<Integer>(_MaxWidth_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SimpleArrayDataExtension")
    public JAXBElement<Object> createSimpleArrayDataExtension(Object value) {
        return new JAXBElement<Object>(_SimpleArrayDataExtension_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GroundOverlayType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "GroundOverlay", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractOverlayGroup")
    public JAXBElement<GroundOverlayType> createGroundOverlay(GroundOverlayType value) {
        return new JAXBElement<GroundOverlayType>(_GroundOverlay_QNAME, GroundOverlayType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "href")
    public JAXBElement<String> createHref(String value) {
        return new JAXBElement<String>(_Href_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "text")
    public JAXBElement<String> createText(String value) {
        return new JAXBElement<String>(_Text_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vec2Type }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "screenXY")
    public JAXBElement<Vec2Type> createScreenXY(Vec2Type value) {
        return new JAXBElement<Vec2Type>(_ScreenXY_QNAME, Vec2Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchemaDataType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "SchemaData", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<SchemaDataType> createSchemaData(SchemaDataType value) {
        return new JAXBElement<SchemaDataType>(_SchemaData_QNAME, SchemaDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "BasicLinkObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createBasicLinkObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_BasicLinkObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LinkObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createLinkObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_LinkObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiTrackType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "MultiTrack", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractGeometryGroup")
    public JAXBElement<MultiTrackType> createMultiTrack(MultiTrackType value) {
        return new JAXBElement<MultiTrackType>(_MultiTrack_QNAME, MultiTrackType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "AbstractFeatureSimpleExtensionGroup")
    public JAXBElement<Object> createAbstractFeatureSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_AbstractFeatureSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "when")
    public JAXBElement<String> createWhen(String value) {
        return new JAXBElement<String>(_When_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LatLonAltBoxSimpleExtensionGroup")
    public JAXBElement<Object> createLatLonAltBoxSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_LatLonAltBoxSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "BoundaryObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createBoundaryObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_BoundaryObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LabelStyleObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createLabelStyleObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_LabelStyleObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlayModeEnumType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "playMode", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "abstractPlayMode", defaultValue = "pause")
    public JAXBElement<PlayModeEnumType> createPlayMode(PlayModeEnumType value) {
        return new JAXBElement<PlayModeEnumType>(_PlayMode_QNAME, PlayModeEnumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LatLonQuadType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LatLonQuad", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractExtentGroup")
    public JAXBElement<LatLonQuadType> createLatLonQuad(LatLonQuadType value) {
        return new JAXBElement<LatLonQuadType>(_LatLonQuad_QNAME, LatLonQuadType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "LatLonBoxSimpleExtensionGroup")
    public JAXBElement<Object> createLatLonBoxSimpleExtensionGroup(Object value) {
        return new JAXBElement<Object>(_LatLonBoxSimpleExtensionGroup_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/kml/2.2", name = "PlaylistObjectExtensionGroup", substitutionHeadNamespace = "http://www.opengis.net/kml/2.2", substitutionHeadName = "AbstractObjectGroup")
    public JAXBElement<AbstractObjectType> createPlaylistObjectExtensionGroup(AbstractObjectType value) {
        return new JAXBElement<AbstractObjectType>(_PlaylistObjectExtensionGroup_QNAME, AbstractObjectType.class, null, value);
    }

}
