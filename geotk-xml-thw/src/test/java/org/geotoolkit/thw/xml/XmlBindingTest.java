
package org.geotoolkit.thw.xml;

import jakarta.xml.bind.JAXBException;
import org.junit.Test;

/**
 *
 * @author Guilhem Legal
 */
public class XmlBindingTest {

    @Test
    public void contextCreationTest() throws JAXBException {

        THSMarshallerPool.getInstance().acquireMarshaller();
    }
}
