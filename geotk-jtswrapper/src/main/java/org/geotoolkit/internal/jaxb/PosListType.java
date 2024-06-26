

package org.geotoolkit.internal.jaxb;

import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlValue;

/**
 *
 * @author Guilhem Legal (Geomatys)
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PosListType {

    @XmlValue
    private List<Double> value;

    public PosListType() {
    }

    public PosListType(final List<Double> value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public List<Double> getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(final List<Double> value) {
        this.value = value;
    }
}
