/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2020, Geomatys
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
package org.geotoolkit.storage;

import org.apache.sis.storage.DataStoreException;

/**
 * Raised by resources when a thread interruption happens,is catched and rethrown
 * as a DataStoreException.
 *
 * @author Johann Sorel (Geomatys)
 */
public class InterruptedStoreException extends DataStoreException {

    public InterruptedStoreException(Throwable cause) {
        super(cause);
    }

    public InterruptedStoreException(String message, Throwable cause) {
        super(message, cause);
    }

}
