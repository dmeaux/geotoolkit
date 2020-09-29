
package org.geotoolkit.storage.coverage;

import org.apache.sis.measure.NumberRange;
import org.apache.sis.storage.DataStoreException;
import org.geotoolkit.process.ProcessListener;
import org.geotoolkit.storage.multires.MultiResolutionResource;
import org.opengis.geometry.Envelope;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public interface IProgressiveCoverageResource extends MultiResolutionResource {

    void clear(Envelope env, NumberRange resolutions) throws DataStoreException;

    void generate(Envelope env, NumberRange resolutions, ProcessListener listener) throws DataStoreException;
}
