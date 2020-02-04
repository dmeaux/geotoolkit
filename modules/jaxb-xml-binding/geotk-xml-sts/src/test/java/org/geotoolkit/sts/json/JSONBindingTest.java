/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2015, Geomatys
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
package org.geotoolkit.sts.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.geotoolkit.nio.IOUtilities;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author guilhem
 */
public class JSONBindingTest extends org.geotoolkit.test.TestBase {

    @Test
    public void testMarshallingDataArray() throws JAXBException, IOException, URISyntaxException {

        final DataArray dataArray = new DataArray();
        dataArray.setIotCount(new BigDecimal(3));
        dataArray.setComponents(Arrays.asList("id", "phenomenonTime", "resultTime", "result"));

        List<Object> ls1 = new ArrayList<>();
        ls1.add(1);
        ls1.add("2005-08-05T12:21:13Z");
        ls1.add("2005-08-05T12:21:13Z");
        ls1.add(20);
        List<Object> ls2 = new ArrayList<>();
        ls2.add(2);
        ls2.add("2005-08-05T12:22:08Z");
        ls2.add("2005-08-05T12:21:13Z");
        ls2.add(30);

        dataArray.setDataArray(Arrays.asList(ls1, ls2));

        ObjectMapper m = new ObjectMapper();
        m.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        StringWriter sw = new StringWriter();
        m.writeValue(sw, dataArray);

        String expResult = IOUtilities.toString(IOUtilities.getResourceAsPath("json/dataArray1.json"));
        expResult = expResult.replace(" ", "");
        expResult = expResult.replace("\n", "");
        String result = sw.toString().replace(" ", "");

        assertEquals(expResult, result);

    }

    @Test
    public void testMarshallingMultiDataArray() throws JAXBException, IOException, URISyntaxException {

        final DataArray dataArray = new DataArray();
        dataArray.setIotCount(new BigDecimal(3));
        dataArray.setComponents(Arrays.asList("id", "phenomenonTime", "resultTime", "result"));

        List<Object> rls1 = new ArrayList<>();
        rls1.add(10.2);
        rls1.add(65);
        rls1.add("clear");
        List<Object> ls1 = new ArrayList<>();
        ls1.add(1);
        ls1.add("2005-08-05T12:21:13Z");
        ls1.add("2005-08-05T12:21:13Z");
        ls1.add(rls1);

        List<Object> rls2 = new ArrayList<>();
        rls2.add(9.8);
        rls2.add(62);
        rls2.add("unclear");
        List<Object> ls2 = new ArrayList<>();
        ls2.add(2);
        ls2.add("2005-08-05T12:22:08Z");
        ls2.add("2005-08-05T12:21:13Z");
        ls2.add(rls2);

        dataArray.setDataArray(Arrays.asList(ls1, ls2));

        ObjectMapper m = new ObjectMapper();
        m.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        StringWriter sw = new StringWriter();
        m.writeValue(sw, dataArray);

        String expResult = IOUtilities.toString(IOUtilities.getResourceAsPath("json/dataArray2.json"));
        expResult = expResult.replace(" ", "");
        expResult = expResult.replace("\n", "");
        String result = sw.toString().replace(" ", "");

        assertEquals(expResult, result);

    }
}
