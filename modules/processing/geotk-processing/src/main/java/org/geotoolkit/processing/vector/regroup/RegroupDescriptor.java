/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2011, Geomatys
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
package org.geotoolkit.processing.vector.regroup;

import org.apache.sis.parameter.ParameterBuilder;
import org.geotoolkit.process.ProcessDescriptor;
import org.geotoolkit.process.Process;
import org.geotoolkit.processing.vector.VectorDescriptor;

import org.opengis.parameter.ParameterDescriptor;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.parameter.ParameterValueGroup;

/**
 * Parameters description of Regroup process.
 * name of the process : "regroup"
 * inputs :
 * <ul>
 *     <li>FEATURE_IN "feature_in" FeatureCollection source</li>
 *     <li>REGROUP_ATTRIBUTE "regroup_attribute" Attribute name to apply the regroup process</li>
 *     <li>GEOMETRY_NAME "regroup_attribute" Geometry property name. Optional</li>
 * </ul>
 * outputs :
 * <ul>
 *     <li>FEATURE_OUT "feature_out" resulting FeatureCollection</li>
 * </ul>
 * @author Quentin Boileau
 * @module
 */
public final class RegroupDescriptor extends VectorDescriptor {

    /**Process name : regroup */
    public static final String NAME = "regroup";

    /**
     * Optional - Attribute name to apply the regroup process
     */
    public static final ParameterDescriptor<String> REGROUP_ATTRIBUTE = new ParameterBuilder()
            .addName("regroup_attribute")
            .setRemarks("Attribute name used for the regroup process")
            .setRequired(false)
            .create(String.class, null);

    /**
     * Optional - Geometry property name. Refer to the geometry used for the regroup process
     */
    public static final ParameterDescriptor<String> GEOMETRY_NAME = new ParameterBuilder()
            .addName("geometry_name")
            .setRemarks("Geometry property name")
            .setRequired(false)
            .create(String.class, null);

    /** Input Parameters */
    public static final ParameterDescriptorGroup INPUT_DESC =
            new ParameterBuilder().addName("InputParameters").createGroup(FEATURE_IN, REGROUP_ATTRIBUTE,GEOMETRY_NAME);

    /** Ouput Parameters */
    public static final ParameterDescriptorGroup OUTPUT_DESC =
            new ParameterBuilder().addName("OutputParameters").createGroup(FEATURE_OUT);
    
    /** Instance */
    public static final ProcessDescriptor INSTANCE = new RegroupDescriptor();

    /**
     * Default constructor
     */
    private RegroupDescriptor() {
        super(NAME, "Return a Featrue Collection generated by the Regroup process"
                + "Each different value of the given attribute name return a Feature.", INPUT_DESC, OUTPUT_DESC);
    }

    /**
     *  {@inheritDoc }
     */
    @Override
    public Process createProcess(final ParameterValueGroup input) {
        return new RegroupProcess(input);
    }
}
