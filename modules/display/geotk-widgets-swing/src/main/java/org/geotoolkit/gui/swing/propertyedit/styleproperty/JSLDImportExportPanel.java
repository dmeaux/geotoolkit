/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2009-2011, Johann Sorel
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

package org.geotoolkit.gui.swing.propertyedit.styleproperty;

import java.awt.Component;
import java.awt.Image;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.xml.bind.JAXBException;

import org.geotoolkit.gui.swing.propertyedit.PropertyPane;
import org.geotoolkit.gui.swing.resource.IconBundle;
import org.geotoolkit.gui.swing.resource.MessageBundle;
import org.geotoolkit.map.MapLayer;
import org.geotoolkit.sld.MutableLayer;
import org.geotoolkit.sld.MutableStyledLayerDescriptor;
import org.geotoolkit.sld.xml.Specification;
import org.geotoolkit.sld.xml.Specification.StyledLayerDescriptor;
import org.geotoolkit.sld.xml.StyleXmlIO;
import org.geotoolkit.style.MutableStyle;
import org.apache.sis.util.logging.Logging;
import org.jdesktop.swingx.combobox.EnumComboBoxModel;

import org.opengis.sld.LayerStyle;
import org.opengis.sld.NamedLayer;
import org.opengis.sld.UserLayer;
import org.opengis.style.Style;
import org.opengis.util.FactoryException;


/**
 * Panel to export styles as SLD.
 *
 * @author Johann Sorel (Puzzle-GIS)
 */
public class JSLDImportExportPanel extends javax.swing.JPanel implements PropertyPane{

    private MapLayer layer = null;

    /** Creates new form JSLDExportPanel */
    public JSLDImportExportPanel() {
        initComponents();
        guiVersion.setModel(new EnumComboBoxModel(StyledLayerDescriptor.class));
        guiVersion.setSelectedItem(StyledLayerDescriptor.V_1_1_0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        guiVersion = new javax.swing.JComboBox();

        jButton1.setText("export sld");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp100(evt);
            }
        });

        jButton3.setText("import sld");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guiVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guiVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exp100(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp100
        write();
    }//GEN-LAST:event_exp100

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        read();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void write(){
        final StyledLayerDescriptor version = (StyledLayerDescriptor)guiVersion.getSelectedItem();
        if(layer != null && layer.getStyle() != null){
            final MutableStyle style = layer.getStyle();
            final JFileChooser chooser = new JFileChooser();
            final int result = chooser.showSaveDialog(this);

            if(result == JFileChooser.APPROVE_OPTION){
                final StyleXmlIO tool = new StyleXmlIO();
                try {
                    tool.writeStyle(chooser.getSelectedFile(), style, version);
                } catch (JAXBException ex) {
                    Logging.getLogger(JSLDImportExportPanel.class).log(Level.WARNING,ex.getMessage(),ex);
                }
            }
        }
    }

    private void read(){
        final StyledLayerDescriptor version = (StyledLayerDescriptor)guiVersion.getSelectedItem();
        if(layer != null){
            final JFileChooser chooser = new JFileChooser();
            final int result = chooser.showOpenDialog(this);

            parse:
            if(result == JFileChooser.APPROVE_OPTION){
                final StyleXmlIO tool = new StyleXmlIO();
                try {
                    final MutableStyledLayerDescriptor sld = tool.readSLD(chooser.getSelectedFile(), version);

                    if(sld != null ){
                        for(MutableLayer sldLayer : sld.layers()){
                            if(sldLayer instanceof NamedLayer){
                                final NamedLayer nl = (NamedLayer) sldLayer;
                                for(LayerStyle ls : nl.styles()){
                                    if(ls instanceof MutableStyle){
                                        layer.setStyle((MutableStyle) ls);
                                    }
                                }
                            }else if(sldLayer instanceof UserLayer){
                                final UserLayer ul = (UserLayer) sldLayer;
                                for(Style ls : ul.styles()){
                                    if(ls instanceof MutableStyle){
                                        layer.setStyle((MutableStyle) ls);
                                    }
                                }
                            }
                        }
                    }
                    break parse;
                } catch (JAXBException ex) {
                    Logging.getLogger(JSLDImportExportPanel.class).log(Level.FINEST,ex.getMessage(),ex);
                } catch (FactoryException ex) {
                    Logging.getLogger(JSLDImportExportPanel.class).log(Level.FINEST,ex.getMessage(),ex);
                }

                try {
                    final MutableStyle style = tool.readStyle(chooser.getSelectedFile(),
                            (version==StyledLayerDescriptor.V_1_0_0) ?
                            Specification.SymbologyEncoding.SLD_1_0_0 :
                            Specification.SymbologyEncoding.V_1_1_0);

                    layer.setStyle(style);

                    break parse;
                } catch (JAXBException ex) {
                    Logging.getLogger(JSLDImportExportPanel.class).log(Level.FINEST,ex.getMessage(),ex);
                } catch (FactoryException ex) {
                    Logging.getLogger(JSLDImportExportPanel.class).log(Level.FINEST,ex.getMessage(),ex);
                }

            }
        }
    }

    @Override
    public boolean canHandle(Object target) {
        return target instanceof MapLayer;
    }

    @Override
    public void setTarget(Object candidate) {
        if(candidate instanceof MapLayer){
            this.layer = (MapLayer) candidate;
        }
    }

    @Override
    public void apply() {
    }

    @Override
    public void reset() {
    }

    @Override
    public String getTitle() {
        return MessageBundle.getString("import_export");
    }

    @Override
    public ImageIcon getIcon() {
        return IconBundle.getIcon("16_advanced_style");
    }

    @Override
    public Image getPreview() {
        return null;
    }

    @Override
    public String getToolTip() {
        return "";
    }

    @Override
    public Component getComponent() {
        return this;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox guiVersion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables

}