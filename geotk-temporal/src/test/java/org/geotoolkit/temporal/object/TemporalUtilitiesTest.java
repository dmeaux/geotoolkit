/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2010, Geomatys
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

package org.geotoolkit.temporal.object;

import java.text.ParseException;
import java.util.Calendar;
import java.util.TimeZone;
import org.junit.Test;

import static org.junit.Assert.*;
import static java.util.Calendar.*;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class TemporalUtilitiesTest {

    @Test
    public void dateErrorParsingTest() {
        try {
            TemporalUtilities.parseDateCal("fsdfsdfs");
            fail("Parsing should have raise a parse exception.");
        } catch (ParseException ex) {
            // ok
        }
        try {
            TemporalUtilities.parseDateCal(null);
            fail("Parsing should have raise a null pointer exception.");
        } catch (NullPointerException ex) {
            // ok
        } catch (ParseException ex) {
            fail("Parsing should have raise a null pointer exception.");
        }
    }

    @Test
    public void dateParsingTest() throws ParseException {
        String str;
        final Calendar date = Calendar.getInstance();
        int year = 1995;
        int month = 10; // starts at 0
        int day = 23;
        int hour = 16;
        int min = 41;
        int sec = 36;
        int mil = 512;

        str = "11/1995";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(1, date.get(DAY_OF_MONTH));
        assertEquals(0, date.get(HOUR_OF_DAY));
        assertEquals(0, date.get(MINUTE));
        assertEquals(0, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "23/11/1995";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(0, date.get(HOUR_OF_DAY));
        assertEquals(0, date.get(MINUTE));
        assertEquals(0, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "23 novembre 1995";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(0, date.get(HOUR_OF_DAY));
        assertEquals(0, date.get(MINUTE));
        assertEquals(0, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "1995-11-23 16:41:36";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(hour, date.get(HOUR_OF_DAY));
        assertEquals(min, date.get(MINUTE));
        assertEquals(sec, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "Novembre 1995";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(1, date.get(DAY_OF_MONTH));
        assertEquals(0, date.get(HOUR_OF_DAY));
        assertEquals(0, date.get(MINUTE));
        assertEquals(0, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "11-1995";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(1, date.get(DAY_OF_MONTH));
        assertEquals(0, date.get(HOUR_OF_DAY));
        assertEquals(0, date.get(MINUTE));
        assertEquals(0, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "1995-11-23T16:41:36";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(hour, date.get(HOUR_OF_DAY));
        assertEquals(min, date.get(MINUTE));
        assertEquals(sec, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "1995-11-23Z";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(0, date.get(HOUR_OF_DAY));
        assertEquals(0, date.get(MINUTE));
        assertEquals(0, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "1995";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(0, date.get(MONTH));
        assertEquals(1, date.get(DAY_OF_MONTH));
        assertEquals(0, date.get(HOUR_OF_DAY));
        assertEquals(0, date.get(MINUTE));
        assertEquals(0, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "1995-11";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(1, date.get(DAY_OF_MONTH));
        assertEquals(0, date.get(HOUR_OF_DAY));
        assertEquals(0, date.get(MINUTE));
        assertEquals(0, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "1995:11:23 16:41:36";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(hour, date.get(HOUR_OF_DAY));
        assertEquals(min, date.get(MINUTE));
        assertEquals(sec, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        // ISO 8601
        // dates--------------------------------------------------------

        str = "1995-11-23T16:41:36";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(hour, date.get(HOUR_OF_DAY));
        assertEquals(min, date.get(MINUTE));
        assertEquals(sec, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "1995-11-23T16:41:36.512";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(hour, date.get(HOUR_OF_DAY));
        assertEquals(min, date.get(MINUTE));
        assertEquals(sec, date.get(SECOND));
        assertEquals(mil, date.get(MILLISECOND));

        str = "1995-11-23T16:41:36Z";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(hour, date.get(HOUR_OF_DAY));
        assertEquals(min, date.get(MINUTE));
        assertEquals(sec, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "1995-11-23T16:41:36.512Z";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(hour, date.get(HOUR_OF_DAY));
        assertEquals(min, date.get(MINUTE));
        assertEquals(sec, date.get(SECOND));
        assertEquals(512, date.get(MILLISECOND));

        str = "1995-11-23"; //no Z, we are in local time zone
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getDefault());
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(0, date.get(HOUR_OF_DAY));
        assertEquals(0, date.get(MINUTE));
        assertEquals(0, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "1995-11-23T16:41:36+04";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(hour - 4, date.get(HOUR_OF_DAY));
        assertEquals(min, date.get(MINUTE));
        assertEquals(sec, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "1995-11-23T16:41:36+04:00";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(hour - 4, date.get(HOUR_OF_DAY));
        assertEquals(min, date.get(MINUTE));
        assertEquals(sec, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "1995-11-23T16:41:36-04";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(hour + 4, date.get(HOUR_OF_DAY));
        assertEquals(min, date.get(MINUTE));
        assertEquals(sec, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));

        str = "1995-11-23T16:41:36-04:00";
        date.setTime(TemporalUtilities.parseDateCal(str).getTime());
        date.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        assertEquals(year, date.get(YEAR));
        assertEquals(month, date.get(MONTH));
        assertEquals(day, date.get(DAY_OF_MONTH));
        assertEquals(hour + 4, date.get(HOUR_OF_DAY));
        assertEquals(min, date.get(MINUTE));
        assertEquals(sec, date.get(SECOND));
        assertEquals(0, date.get(MILLISECOND));
    }

    @Test
    public void durationFormatingTest() {
        assertEquals("0ms", TemporalUtilities.durationToString(0));
        assertEquals("15ms", TemporalUtilities.durationToString(15));
        assertEquals("1s 25ms", TemporalUtilities.durationToString(1025));
        assertEquals("1min 35ms", TemporalUtilities.durationToString(60035));
    }
}
