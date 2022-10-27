/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2022, Geomatys
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
package org.geotoolkit.hdf.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.sis.storage.AbstractResource;
import org.apache.sis.storage.Aggregate;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.util.iso.Names;
import org.geotoolkit.hdf.IOStructure;
import org.geotoolkit.hdf.ObjectHeader;
import org.geotoolkit.hdf.SymbolTableEntry;
import org.geotoolkit.hdf.SymbolTableNode;
import org.geotoolkit.hdf.btree.BTreeV1;
import org.geotoolkit.hdf.heap.LocalHeap;
import org.geotoolkit.hdf.io.Connector;
import org.geotoolkit.hdf.io.HDF5DataInput;
import org.geotoolkit.hdf.message.AttributeMessage;
import org.geotoolkit.hdf.message.BogusMessage;
import org.geotoolkit.hdf.message.Message;
import org.geotoolkit.hdf.message.NillMessage;
import org.geotoolkit.hdf.message.ObjectHeaderContinuationMessage;
import org.geotoolkit.hdf.message.SymbolTableMessage;
import org.geotoolkit.util.StringUtilities;
import org.opengis.util.GenericName;

/**
 * A group is an object header that contains a message that points to a local
 * heap (for storing the links to objects in the group) and to a B-tree
 * (which indexes the links).
 *
 * @author Johann Sorel (Geomatys)
 */
public final class DefaultGroup extends AbstractResource implements Group, Aggregate {

    private final Group parent;
    private final Connector connector;
    private final SymbolTableEntry entry;

    private final String name;
    private final BTreeV1 btree;
    private final LocalHeap localHeap;
    private final List<Node> components = new ArrayList<>();

    //parsed values
    private final Map<String,Object> attributes = new HashMap<>();

    public DefaultGroup(Group parent, Connector connector, SymbolTableEntry entry, String name) throws IOException, DataStoreException {
        super(null, false);
        this.parent = parent;
        this.name = name;
        this.connector = connector;
        this.entry = entry;

        final ObjectHeader header;
        try (final HDF5DataInput channel = connector.createChannel()) {
            header = entry.getHeader(channel);
            btree = entry.getBTree(channel).orElse(null);
            localHeap = entry.getLocalHeap(channel).orElse(null);

            //extract properties
            for (Message message : header.getMessages()) {
                if (message instanceof AttributeMessage cdt) {
                    attributes.put(cdt.getName(), cdt.getValue());
                } else if (message instanceof SymbolTableMessage stm) {
                    buildComponents(channel, stm);
                } else if (message instanceof NillMessage
                        || message instanceof BogusMessage
                        || message instanceof ObjectHeaderContinuationMessage) {
                //ignore those
            } else {
                    throw new IOException("Unhandled message " + message.getClass().getSimpleName());
                }
            }
        }
    }

    @Override
    public Group getParent() {
        return parent;
    }

    @Override
    public Optional<GenericName> getIdentifier() throws DataStoreException {
        return Optional.of(Names.createLocalName(null, null, name));
    }

    private void buildComponents(final HDF5DataInput channel, SymbolTableMessage stm) throws IOException, DataStoreException {
        channel.mark();
        channel.seek(stm.localHeapAddress);
        final LocalHeap localHeap = (LocalHeap) IOStructure.loadIdentifiedObject(channel);

        channel.seek(stm.v1BtreeAddress);
        final BTreeV1 btree = (BTreeV1) IOStructure.loadIdentifiedObject(channel);
        channel.reset();

        final BTreeV1.GroupNode node = (BTreeV1.GroupNode) btree.root;
        for (long adr : node.groupChildAddresses) {
            channel.seek(adr);
            IOStructure struct = IOStructure.loadIdentifiedObject(channel);
            if (struct instanceof SymbolTableNode stn) {
                buildComponents(channel, localHeap, stn);
            } else {
                throw new IOException("Unexpected " + struct);
            }
        }
    }

    private void buildComponents(final HDF5DataInput channel, LocalHeap localHeap, SymbolTableNode stn) throws IOException, DataStoreException {
        for (SymbolTableEntry entry : stn.entries) {
            final String componentName = entry.getName(channel, localHeap).orElse("");
            final int cacheType = entry.getCacheType();
            if (cacheType == 0) {
                components.add(new Dataset(this, connector, entry, componentName));
            } else if (cacheType == 1) {
                components.add(new DefaultGroup(this, connector, entry, componentName));
            } else if (cacheType == 2) {
                throw new IOException("Not supported yet");
            } else {
                throw new IOException("Unexpected symbol table entry cache type " + cacheType);
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public Node getComponent(String name) {
        for (Node n : components()) {
            if (name.equals(n.getName())) {
                return n;
            }
        }
        return null;
    }

    @Override
    public List<Node> components() {
        return Collections.unmodifiableList(components);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    @Override
    public String toString() {
        final String name = getName();
        final StringBuilder sb = new StringBuilder(name);
        sb.append("[HDF5-Group]");
        for (Map.Entry entry : getAttributes().entrySet()) {
            Object v = entry.getValue();
            if (v instanceof CharSequence) {
                v = "\"" + v + "\"";
                v = ((String)v).replace('\n', ' ');
            }
            sb.append('\n').append("@").append(entry.getKey()).append(" = ").append(v);
        }
        return StringUtilities.toStringTree(sb.toString(), components());
    }
}
