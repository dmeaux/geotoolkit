/*
 *    Geotoolkit.org - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2001-2012, Open Source Geospatial Foundation (OSGeo)
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

/**
 * Extensions to {@link javax.imageio.ImageReader} and {@link javax.imageio.ImageWriter} for binary
 * files and text files. This module declares service providers for the following formats:
 * <p>
 * <table border="1" cellspacing="0" cellpadding="4">
 *   <tr bgcolor="lightblue">
 *     <th>{@link javax.imageio.ImageReader} subclass</th>
 *     <th>{@link javax.imageio.ImageWriter} subclass</th>
 *     <th>Name</th>
 *     <th>MIME type</th>
 *     <th>Comments</th>
 *   </tr><tr>
 *     <td>&nbsp;{@link org.geotoolkit.image.io.plugin.TextMatrixImageReader}&nbsp;</td>
 *     <td>&nbsp;{@link org.geotoolkit.image.io.plugin.TextMatrixImageWriter}&nbsp;</td>
 *     <td>&nbsp;{@code matrix}&nbsp;</td>
 *     <td>&nbsp;{@code text/plain}&nbsp;</td>
 *     <td>&nbsp;Locale sensitive.&nbsp;</td>
 *   </tr><tr>
 *     <td>&nbsp;{@link org.geotoolkit.image.io.plugin.TextRecordImageReader}&nbsp;</td>
 *     <td>&nbsp;</td>
 *     <td>&nbsp;{@code records}&nbsp;</td>
 *     <td>&nbsp;{@code text/plain}&nbsp;</td>
 *     <td>&nbsp;Locale sensitive.&nbsp;</td>
 *   </tr>
 * </table>
 *
 * {@section Note about text formats}
 * In the case of text files, the {@link java.nio.charset.Charset} used for decoding/encoding
 * streams and the {@link java.util.Locale} used for parsing/formatting numbers are plugin-specific.
 * Some plugins use the {@linkplain java.util.Locale#getDefault() system default}, which make them
 * locale-dependent (see the table above). However it is possible to derive a locale-insensitive
 * format from a locale sensitive one. See the
 * {@link org.geotoolkit.image.io.plugin.TextMatrixImageReader.Spi} javadoc for an example.
 *
 * {@section System initialization}
 * <b>Remainder:</b> while not mandatory, it is recommended to invoke some initialization methods
 * at least once before to use the Geotk library. See {@link org.geotoolkit.image.io} for more
 * information.
 *
 * @author Martin Desruisseaux (IRD, Geomatys)
 * @author Antoine Hnawia (IRD)
 * @version 3.20
 *
 * @since 3.07 (derived from 1.2)
 * @module
 */
package org.geotoolkit.image.io.plugin;
