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

package org.geotoolkit.display2d.container;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.EventObject;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Stream;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridCoverageBuilder;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.event.StoreEvent;
import org.apache.sis.storage.event.StoreListener;
import org.geotoolkit.display.PortrayalException;
import org.geotoolkit.display.SearchArea;
import org.geotoolkit.display.canvas.RenderingContext;
import org.geotoolkit.display2d.GO2Utilities;
import org.geotoolkit.display2d.canvas.J2DCanvas;
import org.geotoolkit.display2d.canvas.RenderingContext2D;
import org.geotoolkit.display2d.presentation.RasterPresentation;
import org.geotoolkit.display2d.primitive.GraphicJ2D;
import org.geotoolkit.display2d.service.DefaultPortrayalService;
import org.geotoolkit.map.GraphicBuilder;
import org.geotoolkit.map.LayerListener;
import org.geotoolkit.map.MapItem;
import org.geotoolkit.map.MapLayer;
import org.geotoolkit.renderer.Presentation;
import org.geotoolkit.storage.event.StorageListener;
import org.geotoolkit.util.collection.CollectionChangeEvent;
import org.opengis.display.primitive.Graphic;


/**
 *
 * @author Johann Sorel (Geomatys)
 * @module
 */
public class MapLayerJ2D extends MapItemJ2D<MapLayer> implements StoreListener<StoreEvent> {

    private final LayerListener ll = new LayerListener() {

        @Override
        public void styleChange(MapLayer source, EventObject event) {
            //event is catch in the property change
        }

        @Override
        public void itemChange(CollectionChangeEvent<MapItem> event) {
            //handle by parent
        }

        @Override
        public void propertyChange(PropertyChangeEvent event) {
            if(getCanvas().isAutoRepaint()){
                final String propName = event.getPropertyName();
                if(MapLayer.VISIBLE_PROPERTY.equals(propName)){
                    //handle in StatelessMapItemJ2D
                } else if (item.isVisible() &&
                   (  MapLayer.STYLE_PROPERTY.equals(propName)
                   || MapLayer.SELECTION_FILTER_PROPERTY.equals(propName)
                   || MapLayer.OPACITY_PROPERTY.equals(propName)
                   || MapLayer.QUERY_PROPERTY.equals(propName) )){
                    //TODO should call a repaint only on this graphic
                    getCanvas().repaint();
                }
            }
        }
    };

    private final LayerListener.Weak weakLayerListener = new LayerListener.Weak(ll);
    private final StorageListener.Weak weakResourceListener = new StorageListener.Weak(this);


    public MapLayerJ2D(final J2DCanvas canvas, final MapLayer layer){
        //do not use layer crs here, to long to calculate
        super(canvas, layer, false);
        try {
            weakLayerListener.registerSource(layer);
            weakResourceListener.registerSource(layer.getResource());
        } catch (Exception e) {
            GO2Utilities.LOGGER.log(Level.WARNING, "Cannot observe layer for changes.", e);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        weakLayerListener.dispose();
        weakResourceListener.dispose();
    }

    /**
     * Render layer, will only be painted if an appropriate graphic builder is attached
     * to it.
     */
    public Stream<Presentation> paintLayer(final RenderingContext2D context) throws PortrayalException, DataStoreException {

        //we abort painting if the layer is not visible.
        if (!item.isVisible()) return Stream.empty();

        //we abort if opacity is to low
        final double opacity = item.getOpacity();
        if (opacity < 1e-6) return Stream.empty();

        if (1-opacity < 1e-6) {
            //we are very close to opacity one, no need to create a intermediate image
            return streamPresentations(context);
        } else {
            //create an intermediate layer which will be painted on the main context
            //after with the given opacity
            final Rectangle rect = context.getCanvasDisplayBounds();
            final BufferedImage inter = createBufferedImage(ColorModel.getRGBdefault(),
                    ColorModel.getRGBdefault().createCompatibleSampleModel(rect.width, rect.height));
            final Graphics2D g2d = inter.createGraphics();
            final RenderingContext2D interContext = context.create(g2d);

            final J2DPainter painter = new J2DPainter();
            try (Stream<Presentation> stream = streamPresentations(interContext)) {
                painter.paint(interContext, stream, true);
            }

            final GridCoverageBuilder gcb = new GridCoverageBuilder();
            gcb.setDomain(interContext.getGridGeometry());
            gcb.setValues(inter);
            final GridCoverage coverage = gcb.build();

            final RasterPresentation rp = new RasterPresentation(layer, coverage);
            rp.forGrid(context);
            rp.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) opacity);

            return Stream.of(rp);
        }
    }

    private Stream<Presentation> streamPresentations(final RenderingContext2D context) throws DataStoreException, PortrayalException {

        final GraphicBuilder<? extends GraphicJ2D> builder = item.getGraphicBuilder(GraphicJ2D.class);
        if (builder != null) {
            final Collection<? extends GraphicJ2D> graphics = builder.createGraphics(item, canvas);
            return (Stream) graphics.stream();
        } else {
            return DefaultPortrayalService.present(item, item.getResource(), context);
        }
    }

    /**
     * pick layer, will only be picked if an appropriate graphic builder is attached
     * to it.
     */
    @Override
    public List<Graphic> getGraphicAt(final RenderingContext context, final SearchArea mask, List<Graphic> graphics) {

        final GraphicBuilder<GraphicJ2D> builder = (GraphicBuilder<GraphicJ2D>) item.getGraphicBuilder(GraphicJ2D.class);
        if (builder != null) {
            //this layer hasa special graphic rendering, use it instead of normal rendering
            final Collection<GraphicJ2D> gras = builder.createGraphics(item, canvas);
            for (final GraphicJ2D gra : gras) {
                graphics = gra.getGraphicAt(context, mask, graphics);
            }
            return graphics;
        } else {
            //since this is a custom layer, we have no way to find a child graphic.
            graphics.add(this);
            return graphics;
        }
    }

    @Override
    public void eventOccured(StoreEvent event) {
        if (item.isVisible() && getCanvas().isAutoRepaint()) {
            //TODO should call a repaint only on this graphic
            getCanvas().repaint();
        }
    }

}
