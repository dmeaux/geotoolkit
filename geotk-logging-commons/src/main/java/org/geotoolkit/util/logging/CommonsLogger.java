/*
 *    Geotoolkit.org - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2006-2012, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2009-2012, Geomatys
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
package org.geotoolkit.util.logging;

import java.util.logging.Level;
import org.apache.commons.logging.Log;
import org.apache.sis.util.logging.LoggerAdapter;


/**
 * An adapter that redirect all Java logging events to the Apache's
 * <A HREF="http://jakarta.apache.org/commons/logging/">Commons-logging</A> framework.
 *
 * @author Martin Desruisseaux (IRD)
 * @author Saul Farber (MassGIS)
 * @version 3.00
 *
 * @since 2.4
 * @module
 *
 * @see CommonsLoggerFactory
 * @see Logger
 */
final class CommonsLogger extends LoggerAdapter {
    /**
     * The Apache logger to use.
     */
    final Log logger;

    /**
     * Creates a new logger.
     *
     * @param name   The logger name.
     * @param logger The result of {@code LogFactory.getLog(name)}.
     */
    public CommonsLogger(final String name, final Log logger) {
        super(name);
        this.logger = logger;
    }

    /**
     * Do nothing since Commons-Logger doesn't support programmatic change of logging level.
     */
    @Override
    public void setLevel(Level level) {
    }

    /**
     * Returns the level for this logger.
     */
    @Override
    public Level getLevel() {
        if (logger.isTraceEnabled()) return Level.FINEST;
        if (logger.isDebugEnabled()) return Level.FINE;
        if (logger.isInfoEnabled ()) return Level.CONFIG;
        if (logger.isWarnEnabled ()) return Level.WARNING;
        if (logger.isErrorEnabled()) return Level.SEVERE;
        if (logger.isFatalEnabled()) return Level.SEVERE;
        return Level.OFF;
    }

    /**
     * Returns {@code true} if the specified level is loggable.
     */
    @Override
    @SuppressWarnings("fallthrough")
    public boolean isLoggable(final Level level) {
        final int n = level.intValue();
        switch (n / 100) {
            default: {
                switch (n) { // Special cases (should not occur often).
                    case Integer.MIN_VALUE: return true;  // ALL
                    case Integer.MAX_VALUE: return false; // OFF
                    default: return n>=0 && logger.isFatalEnabled();
                }
            }
            case 10: return logger.isErrorEnabled();    // SEVERE
            case  9: return logger.isWarnEnabled();     // WARNING
            case  8:                                    // INFO
            case  7: return logger.isInfoEnabled();     // CONFIG
            case  6:                                    // (not allocated)
            case  5: return logger.isDebugEnabled();    // FINE
            case  4:                                    // FINER
            case  3:                                    // FINEST
            case  2:                                    // (not allocated)
            case  1:                                    // (not allocated)
            case  0: return logger.isTraceEnabled();    // ALL
        }
    }

    /**
     * Logs a record at the specified level.
     */
    @Override
    @SuppressWarnings("fallthrough")
    public void log(final Level level, final String message, final Throwable thrown) {
        final int n = level.intValue();
        switch (n / 100) {
            default: {
                // MAX_VALUE is a special value for Level.OFF. Otherwise and
                // if positive, log to fatal since we are greater than SEVERE.
                if (n != Integer.MAX_VALUE || n >= 0) {
                    logger.fatal(message, thrown);
                }
                break;
            }
            case 10: logger.error(message, thrown); break;  // SEVERE
            case  9: logger.warn (message, thrown); break;  // WARNING
            case  8:                                        // INFO
            case  7: logger.info (message, thrown); break;  // CONFIG
            case  6:                                        // (not allocated)
            case  5: logger.debug(message, thrown); break;  // FINE
            case  4:                                        // FINER
            case  3:                                        // FINEST
            case  2:                                        // (not allocated)
            case  1:                                        // (not allocated)
            case  0: logger.trace(message, thrown); break;  // ALL
        }
    }

    @Override public void severe (String message) {logger.error(message);}
    @Override public void warning(String message) {logger.warn (message);}
    @Override public void info   (String message) {logger.info (message);}
    @Override public void config (String message) {logger.info (message);}
    @Override public void fine   (String message) {logger.debug(message);}
    @Override public void finer  (String message) {logger.debug(message);}
    @Override public void finest (String message) {logger.trace(message);}
}
