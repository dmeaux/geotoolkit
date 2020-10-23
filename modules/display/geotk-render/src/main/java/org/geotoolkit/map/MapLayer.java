/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2008 - 2009, Geomatys
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
package org.geotoolkit.map;

import java.beans.PropertyChangeEvent;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import org.apache.sis.geometry.ImmutableEnvelope;
import org.apache.sis.internal.system.DefaultFactories;
import org.apache.sis.referencing.CommonCRS;
import org.apache.sis.storage.DataSet;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.Query;
import org.apache.sis.storage.Resource;
import static org.apache.sis.util.ArgumentChecks.ensureNonNull;
import org.geotoolkit.renderer.Presentation;
import org.geotoolkit.storage.feature.FeatureStoreUtilities;
import org.geotoolkit.style.MutableFeatureTypeStyle;
import org.geotoolkit.style.MutableStyle;
import org.geotoolkit.style.MutableStyleFactory;
import org.geotoolkit.style.StyleConstants;
import org.geotoolkit.style.StyleListener;
import org.geotoolkit.util.collection.CheckedArrayList;
import org.geotoolkit.util.collection.CollectionChangeEvent;
import org.opengis.geometry.Envelope;
import org.opengis.style.StyleFactory;

/**
 * A layer to be rendered. A layer is an aggregation of both a
 * data source : {@link FeatureCollection} or {@ CoverageReference}
 * with a given {@link MutableStyle}.
 *
 * @author Johann Sorel (Geomatys)
 * @module
 */
public class MapLayer extends MapItem implements StyleListener {

    public static final String STYLE_PROPERTY = "style";
    public static final String OPACITY_PROPERTY = "opacity";
    public static final String SELECTABLE_PROPERTY = "selectable";
    public static final String QUERY_PROPERTY = "query";
    public static final String BOUNDS_PROPERTY = "bounds";
    public static final String SELECTION_FILTER_PROPERTY = "selection_filter";
    public static final String SELECTION_STYLE_PROPERTY = "selection_style";

    /**
     * Use this key in the User map properties and add a Boolean.TRUE
     * to indicate if features store their own Symbolizer.
     * Symbolizer should be stored in the user map of each feature with this key.
     *
     * TODO make a special feature and feature collection implementation to define this case.
     */
    public static final String USERKEY_STYLED_FEATURE = "styled_feature";

    private static final ImmutableEnvelope INFINITE = new ImmutableEnvelope(
            new double[] {-180, -90}, new double[] {180, 90}, CommonCRS.WGS84.normalizedGeographic());

    private final List<GraphicBuilder> builders = new CheckedArrayList<GraphicBuilder>(GraphicBuilder.class);

    private final StyleListener.Weak styleListener = new StyleListener.Weak(null, this);

    protected Resource resource;
    protected Query query;
    protected MutableStyle style;
    protected MutableStyle selectionStyle;
    protected boolean selectable = false;
    private double opacity = 1d;

    public MapLayer(final Resource resource){
        this.resource = resource;
        this.desc = StyleConstants.DEFAULT_DESCRIPTION;
        this.selectionStyle = null;
        final MutableStyleFactory factory = (MutableStyleFactory) DefaultFactories.forBuildin(StyleFactory.class);
        setStyle(factory.style());
    }

    /**
     * Get layer resource.
     *
     * @return Resource, can be null.
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Returns the query, may be {@code null}.
     */
    public Query getQuery() {
        return query;
    }

    /**
     * Sets a filter query for this layer.
     *
     * <p>
     * Query filters should be used to reduce searched or displayed feature
     * when rendering or analyzing this layer.
     * </p>
     *
     * @param query the full filter for this layer. can be null.
     */
    public void setQuery(final Query query) {

        final Query oldQuery;
        synchronized (this) {
            oldQuery = getQuery();
            if (Objects.equals(query, oldQuery)) {
                return;
            }
            this.query = query;
        }
        firePropertyChange(QUERY_PROPERTY, oldQuery, this.query);
    }

    /**
     * Get the style for this layer.  If style has not been set, then null is
     * returned.
     *
     * @return The style.
     */
    public MutableStyle getStyle() {
        return this.style;
    }

    /**
     * Sets the style for this layer. If a style has not been defined a default
     * one shall be used.
     *
     * @param style The new style
     */
    public void setStyle(final MutableStyle style) {
        ensureNonNull("style", style);

        final MutableStyle oldStyle;
        synchronized (this) {
            oldStyle = this.style;
            if(style.equals(oldStyle)){
                return;
            }

            if(oldStyle != null){
                styleListener.unregisterSource(oldStyle);
            }
            this.style = style;
            styleListener.registerSource(style);
        }
        firePropertyChange(STYLE_PROPERTY, oldStyle, this.style);
    }

    /**
     *
     * @return double layer opacity between 0 and 1
     */
    public double getOpacity(){
        return opacity;
    }

    /**
     * Specify the global opacity of this layer.
     * 0 is fully translucent and 1 is opaque.
     * A {@link PropertyChangeEvent} is fired if the opacity changed.
     *
     * @param opacity : value between 0 and 1
     */
    public void setOpacity(final double opacity){
        final double oldOpacity;
        synchronized (this) {
            oldOpacity = this.opacity;
            if(oldOpacity == opacity){
                return;
            }
            this.opacity = opacity;
        }
        firePropertyChange(OPACITY_PROPERTY, oldOpacity, this.opacity);
    }

    /**
     * Determine whether this layer is selectable on a map pane or whether the
     * layer is static.
     *
     * @return <code>true</code> if the layer is selectable.
     */
    public boolean isSelectable() {
        return this.selectable;
    }

    /**
     * Specify whether this layer is selectable on a map pane.
     * A {@link PropertyChangeEvent} is fired if the selectable changed.
     *
     * @param selectable Show the layer if <code>true</code>.
     */
    public void setSelectable(final boolean selectable) {
        final boolean oldSelectable;
        synchronized (this) {
            oldSelectable = this.selectable;
            if(oldSelectable == selectable){
                return;
            }
            this.selectable = selectable;
        }
        firePropertyChange(SELECTABLE_PROPERTY, oldSelectable, this.selectable);
    }

    /**
     *
     * @return Style associated for the selected datas
     */
    public MutableStyle getSelectionStyle(){
        return selectionStyle;
    }

    /**
     * Set the style associated to selected datas.
     *
     * @param style : can be null, the default selection style should used by
     * rendering engine if this vaue is null.
     */
    public void setSelectionStyle(final MutableStyle style){

        final MutableStyle oldStyle;
        synchronized (this) {
            oldStyle = this.selectionStyle;
            if(oldStyle == style){
                return;
            }
            this.selectionStyle = style;
        }
        firePropertyChange(SELECTION_STYLE_PROPERTY, oldStyle, this.selectionStyle);
    }

    /**
     * find out the bounds of the layer
     * This method should never return null,
     * if the features envelope could not calculated then the crs valide envelope
     * if possible and in last case an infinitee envelope.
     * @return - the layer's bounds
     */
    public Envelope getBounds() {
        final Resource ref = getResource();

        Envelope env = null;
        if (ref instanceof DataSet) {
            try {
                env = FeatureStoreUtilities.getEnvelope((DataSet) ref);
            } catch (DataStoreException e) {
                LOGGER.log(Level.WARNING, "Cannot access resource envelope. " + e.getMessage(), e);
            }
        }

        if (env == null) {
            //same strategy as coverage map layer
            //this approach is removed in Apache SIS
            env = INFINITE;
        }

        return env;
    }

    /**
     * Returns the living list of all graphic builders linked to this
     * map layer.
     *
     * @return living list of graphic builders
     */
    public List<GraphicBuilder> graphicBuilders(){
        return builders;
    }

    /**
     * A layer may provide a graphic builder, this enable
     * special representations, like wind arrows for coverages.
     * A layer may have different builder for each kind of Graphic implementation.
     * This enable the possibility to have custom made graphic representation
     * and several builder, for 2D, 3D or else...
     *
     * @param type : the graphic type wanted
     * @return graphicBuilder<? extends type> or null
     */
    public <T extends Presentation> GraphicBuilder<? extends T> getGraphicBuilder( final Class<T> type ){

        for(GraphicBuilder builder : builders){
            if(type.isAssignableFrom(builder.getGraphicType())){
                return builder;
            }
        }

        return null;
    }

    /**
     * Register a layer listener, this listener will be registered
     * also as an item listener.
     * @param listener Layer listener to register
     */
    public void addLayerListener(final LayerListener listener){
        synchronized(listeners){
            listeners.add(LayerListener.class, listener);
            addPropertyChangeListener(listener);
        }
    }

    /**
     * Unregister a layer listener, this listener will be unregistered
     * also as an item listener.
     * @param listener Layer listener to unregister
     */
    public void removeLayerListener(final LayerListener listener){
        synchronized(listeners){
            listeners.remove(LayerListener.class, listener);
            removePropertyChangeListener(listener);
        }
    }

    //--------------------------------------------------------------------------
    // listeners management ----------------------------------------------------
    //--------------------------------------------------------------------------

    protected void fireStyleChange(final EventObject event){
        //TODO make fire property change thread safe, preserve fire order

        final LayerListener[] lists = listeners.getListeners(LayerListener.class);

        for(LayerListener listener : lists){
            listener.styleChange(this, event);
        }

        //fire a property change for others
        firePropertyChange(STYLE_PROPERTY, this.getStyle(), this.getStyle());

    }

    //--------------------------------------------------------------------------
    // style listener-----------------------------------------------------------
    //--------------------------------------------------------------------------

    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        fireStyleChange(event);
    }

    @Override
    public void featureTypeStyleChange(final CollectionChangeEvent<MutableFeatureTypeStyle> event) {
        fireStyleChange(event);
    }

}
