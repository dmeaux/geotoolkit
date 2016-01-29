/*
 *    Geotoolkit.org - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2016, Geomatys
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
package org.geotoolkit.internal.io;

import java.util.Hashtable;
import java.util.Properties;
import java.io.IOException;
import javax.sql.DataSource;
import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import org.apache.sis.internal.metadata.sql.Initializer;
import org.geotoolkit.internal.sql.DefaultDataSource;
import org.geotoolkit.internal.sql.AuthenticatedDataSource;
import javax.naming.InitialContext;


/**
 * Tiny JNDI context, used only for specifying an EPSG data source to Apache SIS.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @module
 */
public final class JNDI implements Context, InitialContextFactory {
    /**
     * Invoked from {@link org.geotoolkit.lang.Setup} for setting a pseudo-JNDI environment.
     */
    public static void install() {
        if (!Initializer.hasJNDI()) {
            System.setProperty(INITIAL_CONTEXT_FACTORY, JNDI.class.getName());
        }
    }

    /**
     * Invoked from {@link org.geotoolkit.lang.Setup} for removing our pseudo-JNDI environment.
     */
    public static void uninstall() {
        if (JNDI.class.getName().equals(System.getProperty(INITIAL_CONTEXT_FACTORY))) {
            System.clearProperty(INITIAL_CONTEXT_FACTORY);
        }
    }

    /**
     * Data source for the EPSG database, or {@code null} if none.
     */
    private static DataSource EPSG;

    /**
     * Sets the data source for the EPSG database.
     *
     * @param source Data source for the EPSG database, or {@code null} if none.
     */
    public static synchronized void setEPSG(final DataSource source) throws NamingException {
        EPSG = source;
        if (Initializer.hasJNDI()) {
            final Context env = (Context) InitialContext.doLookup("java:comp/env");
            if (!(env instanceof JNDI)) {
                env.bind("jdbc/SpatialMetadata", source);
            }
        }
    }

    /**
     * Returns the EPSG data source.
     */
    private static synchronized DataSource getEPSG() throws IOException {
        if (EPSG == null) {
            final Properties properties = Installation.EPSG.getDataSource();
            if (properties != null) {
                final String url = properties.getProperty("URL");
                if (url != null) {
                    EPSG = new DefaultDataSource(url);
                    final String user = properties.getProperty("user");
                    if (user != null) {
                        EPSG = new AuthenticatedDataSource(EPSG, user, properties.getProperty("password"), true);
                    }
                }
            }
        }
        return EPSG;
    }

    /**
     * Invoked by JNDI through reflection for creating the {@link InitialContextFactory}.
     */
    public JNDI() {
    }

    /**
     * Invoked by JNDI for creating the {@link Context}.
     *
     * @param environment ignored.
     * @return The context.
     */
    @Override
    public Context getInitialContext(Hashtable<?,?> environment) {
        return this;
    }

    /**
     * Returns the object for the given name.
     *
     * @param  name The object name.
     * @return Value associated to the given name.
     * @throws NamingException if the value can not be obtained.
     */
    @Override
    public Object lookup(final String name) throws NamingException {
        switch (name) {
            case "java:comp/env": return this;
            case "jdbc/SpatialMetadata": {
                final DataSource ds;
                try {
                    ds = getEPSG();
                } catch (IOException e) {
                    throw (NamingException) new NamingException(e.getMessage()).initCause(e);
                }
                if (ds != null) {
                    return ds;
                }
                throw new NameNotFoundException("EPSG DataSource not set.");
            }
            default: throw new NameNotFoundException(name);
        }
    }

    @Override
    public void bind(String name, Object obj) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public void rebind(String name, Object obj) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public void unbind(String name) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public void rename(String oldName, String newName) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public NamingEnumeration<NameClassPair> list(String name) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public NamingEnumeration<Binding> listBindings(String name) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public void destroySubcontext(String name) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public Context createSubcontext(String name) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public Object lookupLink(String name) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public NameParser getNameParser(String name) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public String composeName(String name, String prefix) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public Object addToEnvironment(String propName, Object propVal) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public Object removeFromEnvironment(String propName) throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public Hashtable<?, ?> getEnvironment() throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public String getNameInNamespace() throws NamingException {
        throw new NamingException("Not supported yet.");
    }

    @Override
    public void close() throws NamingException {
    }

    /**
     * Delegates to the methods working on {@link String}.
     *
     * @param  name The name to convert to string.
     * @throws NamingException if the string-based method failed.
     */
    @Override public void                               bind           (Name name, Object obj)   throws NamingException {         bind           (name.toString(), obj);}
    @Override public void                             unbind           (Name name)               throws NamingException {       unbind           (name.toString());}
    @Override public void                             rebind           (Name name, Object obj)   throws NamingException {       rebind           (name.toString(), obj);}
    @Override public void                             rename           (Name name, Name newName) throws NamingException {       rename           (name.toString(), newName.toString());}
    @Override public Object                           lookup           (Name name)               throws NamingException {return lookup           (name.toString());}
    @Override public Object                           lookupLink       (Name name)               throws NamingException {return lookupLink       (name.toString());}
    @Override public NamingEnumeration<NameClassPair> list             (Name name)               throws NamingException {return list             (name.toString());}
    @Override public NamingEnumeration<Binding>       listBindings     (Name name)               throws NamingException {return listBindings     (name.toString());}
    @Override public NameParser                       getNameParser    (Name name)               throws NamingException {return getNameParser    (name.toString());}
    @Override public Context                          createSubcontext (Name name)               throws NamingException {return createSubcontext (name.toString());}
    @Override public void                             destroySubcontext(Name name)               throws NamingException {       destroySubcontext(name.toString());}
    @Override public Name                             composeName      (Name name, Name prefix)  throws NamingException {
        return getNameParser(name).parse(composeName(name.toString(), prefix.toString()));
    }
}
