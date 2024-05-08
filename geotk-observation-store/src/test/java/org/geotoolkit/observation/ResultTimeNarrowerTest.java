/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2020, Geomatys
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
package org.geotoolkit.observation;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.geotoolkit.filter.FilterUtilities.FF;
import org.geotoolkit.observation.model.ComplexResult;
import org.geotoolkit.observation.model.Field;
import org.geotoolkit.observation.model.FieldType;
import static org.geotoolkit.observation.model.TextEncoderProperties.DEFAULT_ENCODING;
import org.geotoolkit.observation.result.ResultTimeNarrower;
import org.geotoolkit.temporal.object.DefaultInstant;
import org.geotoolkit.temporal.object.DefaultPeriod;
import org.junit.Assert;
import org.junit.Test;
import org.opengis.filter.Filter;
import org.opengis.referencing.IdentifiedObject;

/**
 *
 * @author Guilhem Legal (Geomatys)
 */
public class ResultTimeNarrowerTest {

    @Test
    public void applyTimeConstraintTest() throws Exception {

        String values = "2007-05-01T02:59:00.0,6.56@@" +
                        "2007-05-01T03:59:00.0,6.56@@" +
                        "2007-05-01T04:59:00.0,6.56@@" +
                        "2007-05-01T05:59:00.0,6.56@@" +
                        "2007-05-01T06:59:00.0,6.56@@" +
                        "2007-05-01T07:59:00.0,6.56@@" +
                        "2007-05-01T08:59:00.0,6.56@@" +
                        "2007-05-01T09:59:00.0,6.56@@" +
                        "2007-05-01T10:59:00.0,6.56@@" +
                        "2007-05-01T11:59:00.0,6.56@@" +
                        "2007-05-01T17:59:00.0,6.56@@" +
                        "2007-05-01T18:59:00.0,6.55@@" +
                        "2007-05-01T19:59:00.0,6.55@@" +
                        "2007-05-01T20:59:00.0,6.55@@" +
                        "2007-05-01T21:59:00.0,6.55@@";

        List<Field> fields = new ArrayList<>();
        fields.add(OMUtils.TIME_FIELD);
        fields.add(new Field(2, FieldType.QUANTITY, "Temperature", "urn:ogc:temperature", "°C", null));
        ComplexResult array = new ComplexResult(fields, DEFAULT_ENCODING, values, 15);

        List<Filter> eventTimes = new ArrayList<>();
        Timestamp obsBegin = new Timestamp(Instant.parse("2007-05-01T01:59:00.0Z").toEpochMilli());
        Timestamp obsEnd   = new Timestamp(Instant.parse("2007-05-01T20:59:00.0Z").toEpochMilli());

        // no filter
        ResultTimeNarrower.applyTimeConstraint(obsBegin, obsEnd, array, eventTimes);

        // no changes applied
        Assert.assertEquals(values, array.getValues());

       /*
        * time after
        */
        String expected = "2007-05-01T10:59:00.0,6.56@@" +
                          "2007-05-01T11:59:00.0,6.56@@" +
                          "2007-05-01T17:59:00.0,6.56@@" +
                          "2007-05-01T18:59:00.0,6.55@@" +
                          "2007-05-01T19:59:00.0,6.55@@" +
                          "2007-05-01T20:59:00.0,6.55@@" +
                          "2007-05-01T21:59:00.0,6.55@@";

        eventTimes.add(FF.after(FF.property("result_time"), FF.literal(
                new DefaultInstant(Collections.singletonMap(IdentifiedObject.NAME_KEY, "name"),
                        Instant.parse("2007-05-01T07:59:00.0Z")))));
        ResultTimeNarrower.applyTimeConstraint(obsBegin, obsEnd, array, eventTimes);

        Assert.assertEquals(expected, array.getValues());

        /*
        * time before
        */
        expected = "2007-05-01T02:59:00.0,6.56@@" +
                   "2007-05-01T03:59:00.0,6.56@@" +
                   "2007-05-01T04:59:00.0,6.56@@" +
                   "2007-05-01T05:59:00.0,6.56@@" +
                   "2007-05-01T06:59:00.0,6.56@@" +
                   "2007-05-01T07:59:00.0,6.56@@" +
                   "2007-05-01T08:59:00.0,6.56@@" +
                   "2007-05-01T09:59:00.0,6.56@@";

        eventTimes.clear();
        eventTimes.add(FF.before(FF.property("result_time"), FF.literal(
                new DefaultInstant(Collections.singletonMap(IdentifiedObject.NAME_KEY, "name"),
                        Instant.parse("2007-05-01T08:59:00.0Z")))));
        array = new ComplexResult(fields, DEFAULT_ENCODING, values, 15);
        ResultTimeNarrower.applyTimeConstraint(obsBegin, obsEnd, array, eventTimes);

        Assert.assertEquals(expected, array.getValues());

       /*
        * time during
        */
       expected = "2007-05-01T07:59:00.0,6.56@@" +
                  "2007-05-01T08:59:00.0,6.56@@" +
                  "2007-05-01T09:59:00.0,6.56@@";

        eventTimes.clear();
        eventTimes.add(FF.during(FF.property("result_time"),
                FF.literal(new DefaultPeriod(Collections.singletonMap(IdentifiedObject.NAME_KEY, "name"),
                        new DefaultInstant(Collections.singletonMap(IdentifiedObject.NAME_KEY, "name"), Instant.parse("2007-05-01T04:59:00.0Z")),
                        new DefaultInstant(Collections.singletonMap(IdentifiedObject.NAME_KEY, "name"), Instant.parse("2007-05-01T08:59:00.0Z"))))));
        array = new ComplexResult(fields, DEFAULT_ENCODING, values, 15);
        ResultTimeNarrower.applyTimeConstraint(obsBegin, obsEnd, array, eventTimes);

        Assert.assertEquals(expected, array.getValues());
    }
}
