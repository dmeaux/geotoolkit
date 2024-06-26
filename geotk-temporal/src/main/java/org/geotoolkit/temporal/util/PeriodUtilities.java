/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2010, Open Source Geospatial Foundation (OSGeo)
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
package org.geotoolkit.temporal.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
import org.opengis.filter.TemporalOperatorName;
import org.opengis.temporal.Period;
import org.opengis.temporal.TemporalPrimitive;
import org.geotoolkit.temporal.object.ISODateParser;

import static org.geotoolkit.temporal.object.TemporalConstants.*;
import org.geotoolkit.temporal.object.TemporalUtilities;


/**
 * Not thread safe.
 *
 * @author Guilhem Legal
 * @author Mehdi Sidhoum
 */
public class PeriodUtilities {

    /**
     * The format of the dates.
     */
    private DateFormat dateFormat;

    /**
     * The date parser.
     */
    private ISODateParser dateParser = new ISODateParser();


    /**
     * Build a new period worker with the specified DateFormat
     */
    public PeriodUtilities(final DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Transform the original date with the specified date format.
     * Sometimes the original date is more precise than the specified date format.
     * This will lead to strange period computation.
     *
     * Example: if we want to get rid of the milliseconds we must calculate the periods
     * without the millisecond in each date.
     *
     * @param d The original date.
     * @return A new date conforming to the date format.
     */
    private long transDate(Date d) {
        String view = dateFormat.format(d);
        try {
            Date transformed = dateFormat.parse(view);
            return transformed.getTime();
        } catch (ParseException ex) {
            // should not happen
            throw new IllegalArgumentException("date can not be parsed!");
        }
    }

    public String getDatesRespresentation(final SortedSet<Date> dates) {
        return getDatesRespresentation(dates, false);
    }

    /**
     * Evaluate the periodical gap between the different available time.
     * Return a String concatening periods and isolated date.
     *
     * @param dates a sorted set of date (ordered by time).
     */
    public String getDatesRespresentation(final SortedSet<Date> dates, boolean useMonthAndYear) {
        if (dates.comparator() != null) {
            throw new IllegalArgumentException("Dates should be sorted naturaly without any custom comparator.");
        }

        if (dates.isEmpty()) {
            return "";
        }

        final StringBuffer response = new StringBuffer();

        Date first          = dates.first();
        Date previousDate   = first;
        long previousGap    = 0;
        long gap            = 0;
        int nbDataInGap     = 0;

        for (Date d : dates) {
            previousGap = gap;
            gap = transDate(d) - transDate(previousDate);

            if (previousGap != gap) {
                if (nbDataInGap >= 2) {
                    final String firstDate = dateFormat.format(first);
                    if (response.indexOf(firstDate + ',') != -1) {
                        final int pos = response.indexOf(firstDate);
                        response.delete(pos, pos + firstDate.length() + 1);
                    }
                    response.append(getPeriodDescription(dates.subSet(first, d), previousGap, useMonthAndYear)).append(',');
                    nbDataInGap = 1;
                    first = d;
                } else {
                    if (nbDataInGap > 0) {
                        dateFormat.format(previousDate, response, new FieldPosition(0)).append(',');
                        nbDataInGap = 1;
                    }
                    first = previousDate;
                }

            } else {
                nbDataInGap++;
            }

            previousDate = d;
        }

        if (nbDataInGap > 0) {
            if (nbDataInGap >= 2) {
                final String firstDate = dateFormat.format(first);
                if (response.indexOf(firstDate + ',') != -1) {
                    final int pos = response.indexOf(firstDate);
                    response.delete(pos, pos + firstDate.length() + 1);
                }
                response.append(getPeriodDescription(dates.tailSet(first), gap, useMonthAndYear));
                nbDataInGap = 1;
            } else {
                if (nbDataInGap > 0) {
                    dateFormat.format(previousDate, response, new FieldPosition(0));
                    nbDataInGap = 1;
                }
           }
        }

        return response.toString();
    }

    public String getPeriodDescription(final SortedSet<Date> dates, long gap) {
        return getPeriodDescription(dates, gap, false);
    }

    /**
     * Return a String for a range of date (or just one)
     */
    public String getPeriodDescription(final SortedSet<Date> dates, long gap, boolean useMonthAndYear) {
        final StringBuffer response = new StringBuffer();
        dateFormat.format(dates.first(), response, new FieldPosition(0));
        response.append('/');

        dateFormat.format(dates.last(), response, new FieldPosition(0));
        response.append("/P");

        if (useMonthAndYear) {
            //we look if the gap is more than one year (31536000000 ms)
            long temp = gap / YEAR_MS;
            if (temp > 1) {
                response.append(temp).append('Y');
                gap -= temp * YEAR_MS;
            }

            //we look if the gap is more than one month (2628000000 ms)
            temp = gap / MONTH_MS;
            if (temp >= 1) {
                response.append(temp).append('M');
                gap -= temp * MONTH_MS;
            }
        }

        //we look if the gap is more than one week (604800000 ms)
        long temp = gap / WEEK_MS;
        if (temp >= 1) {
            response.append(temp).append('W');
            gap -= temp * WEEK_MS;
        }

        //we look if the gap is more than one day (86400000 ms)
        temp = gap / DAY_MS;
        if (temp >= 1) {
            response.append(temp).append('D');
            gap -= temp * DAY_MS;
        }

        //if the gap is not over we pass to the hours by adding 'T'
        if (gap != 0) {
            response.append('T');
        }

        //we look if the gap is more than one hour (3600000 ms)
        temp = gap / HOUR_MS;
        if (temp >= 1) {
            response.append(temp).append('H');
            gap -= temp * HOUR_MS;
        }

        //we look if the gap is more than one min (60000 ms)
        temp = gap / MINUTE_MS;
        if (temp >= 1) {
            response.append(temp).append('M');
            gap -= temp * MINUTE_MS;
        }

        //we look if the gap is more than one second (1000 ms)
        temp = gap / SECOND_MS;
        if (temp >= 1) {
            gap -= temp * SECOND_MS;
            response.append(temp);
            if (gap != 0) {
               response.append(".").append(gap);
            }
            response.append('S');
        }
        return response.toString();
    }

    /**
     * Return a sorted set from a string description.
     */
    public SortedSet<Date> getDatesFromPeriodDescription(final String periods) throws ParseException {
        final SortedSet<Date> response = new TreeSet<Date>();
        final StringTokenizer tokens = new StringTokenizer(periods, ",");
        while (tokens.hasMoreTokens()) {
            String dates = tokens.nextToken().trim();

            int slash = dates.indexOf('/');

            if (slash == -1) {
                response.add((dateFormat == null) ? dateParser.parseToDate(dates) :  dateFormat.parse(dates));
            } else {

                //we get the begin position
                final String begin = dates.substring(0, slash);
                final Date first = (dateFormat == null) ? dateParser.parseToDate(begin) :  dateFormat.parse(begin);
                dates = dates.substring(slash+1);

                //we get the end position
                slash = dates.indexOf('/');
                final String end = dates.substring(0, slash);
                final Date last = (dateFormat == null) ? dateParser.parseToDate(end) :  dateFormat.parse(end);
                    dates = dates.substring(slash+1);

                //then we get the period Description
                final long gap = getTimeFromPeriodDescription(dates);

                Date currentDate = first;
                while (currentDate.getTime() <= last.getTime()) {
                    response.add(currentDate);
                    currentDate = new Date(currentDate.getTime() + gap);
                }
                response.add(last);
            }
        }
        return response;
    }

    /**
     * Return a Date (long time) from a String description
     */
    public long getTimeFromPeriodDescription(String periodDescription) {

        long time = 0;
        //we remove the 'P'
        periodDescription = periodDescription.substring(1);

        //we look if the period contains years (31536000000 ms)
        if (periodDescription.indexOf('Y') != -1) {
            final int nbYear = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('Y')));
            time += nbYear * YEAR_MS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('Y') + 1);
        }

        //we look if the period contains months (2628000000 ms)
        if (    periodDescription.indexOf('M') != -1 &&
                (periodDescription.indexOf('T') == -1 || periodDescription.indexOf('T') > periodDescription.indexOf('M')) ) {
            final int nbMonth = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('M')));
            time += nbMonth * MONTH_MS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('M') + 1);
        }

        //we look if the period contains weeks (604800000 ms)
        if (periodDescription.indexOf('W') != -1) {
            final int nbWeek = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('W')));
            time += nbWeek * WEEK_MS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('W') + 1);
        }

        //we look if the period contains days (86400000 ms)
        if (periodDescription.indexOf('D') != -1) {
            final int nbDay = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('D')));
            time += nbDay * DAY_MS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('D') + 1);
        }

        //if the periodDescription is not over we pass to the hours by removing 'T'
        if (periodDescription.indexOf('T') != -1) {
            periodDescription = periodDescription.substring(1);
        }

        //we look if the period contains hours (3600000 ms)
        if (periodDescription.indexOf('H') != -1) {
            final int nbHour = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('H')));
            time += nbHour * HOUR_MS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('H') + 1);
        }

        //we look if the period contains minutes (60000 ms)
        if (periodDescription.indexOf('M') != -1) {
            final int nbMin = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('M')));
            time += nbMin * MINUTE_MS;
            periodDescription = periodDescription.substring(periodDescription.indexOf('M') + 1);
        }

        //we look if the period contains seconds (1000 ms)
        if (periodDescription.indexOf('S') != -1) {
            if (periodDescription.indexOf('.') != -1) {
                final int nbSec = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('.')));
                time += nbSec * SECOND_MS;
                periodDescription = periodDescription.substring(periodDescription.indexOf('.') + 1);
                // millsecond
                final int nbMSec = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('S')));
                time += nbMSec;
                periodDescription = periodDescription.substring(periodDescription.indexOf('S') + 1);
            } else {
                final int nbSec = Integer.parseInt(periodDescription.substring(0, periodDescription.indexOf('S')));
                time += nbSec * SECOND_MS;
                periodDescription = periodDescription.substring(periodDescription.indexOf('S') + 1);
            }
        }

        if (periodDescription.length() != 0) {
            throw new IllegalArgumentException("The period descritpion is malformed");
        }
        return time;
    }

    public static TemporalOperatorName relativePosition(final Period self, final TemporalPrimitive other) {
        final Instant start = TemporalUtilities.toInstant(self.getBeginning());
        final Instant end = TemporalUtilities.toInstant(self.getEnding());
        if (other instanceof org.opengis.temporal.Instant instantarg) {
            Instant t = TemporalUtilities.toInstant(instantarg);
            if (end.isBefore(t)) {
                return TemporalOperatorName.BEFORE;
            } else if (end.compareTo(t) == 0) {
                return TemporalOperatorName.ENDED_BY;
            } else if (start.isBefore(t) &&
                end.isAfter(t)) {
                return TemporalOperatorName.CONTAINS;
            } else {
                 return (start.compareTo(t) == 0) ? TemporalOperatorName.BEGUN_BY : TemporalOperatorName.AFTER;
            }
        } else if (other instanceof Period instantarg) {
            final var otherStart = TemporalUtilities.toInstant(instantarg.getBeginning());
            final var otherEnd = TemporalUtilities.toInstant(instantarg.getEnding());
            if (end.isBefore(otherStart)) {
                return TemporalOperatorName.BEFORE;
            } else if (end.compareTo(otherStart) == 0) {
                return TemporalOperatorName.MEETS;
            } else if (start.isBefore(otherStart) && end.isAfter(otherStart) && end.isBefore(otherEnd)) {
                return TemporalOperatorName.OVERLAPS;
            } else if (start.compareTo(otherStart) == 0 && end.isBefore(otherEnd)) {
                return TemporalOperatorName.BEGINS;
            } else if (start.compareTo(otherStart) == 0 && end.isAfter(otherEnd)) {
                return TemporalOperatorName.BEGUN_BY;
            } else if (start.isAfter(otherStart) && end.isBefore(otherEnd)) {
                return TemporalOperatorName.DURING;
            } else if (start.isBefore(otherStart) && end.isAfter(otherEnd)) {
                return TemporalOperatorName.CONTAINS;
            } else if (start.compareTo(otherStart) == 0 && end.compareTo(otherEnd) == 0) {
                return TemporalOperatorName.EQUALS;
            } else if (start.isAfter(otherStart) && start.isBefore(otherEnd) && end.isAfter(otherEnd)) {
                return TemporalOperatorName.OVERLAPPED_BY;
            } else if (start.isAfter(otherStart) && end.compareTo(otherEnd) == 0) {
                return TemporalOperatorName.ENDS;
            } else if (start.isBefore(otherStart) && end.compareTo(otherEnd) == 0) {
                return TemporalOperatorName.ENDED_BY;
            } else {
                return (start.compareTo(otherEnd) == 0) ? TemporalOperatorName.MET_BY : TemporalOperatorName.AFTER;
            }
        } else {
            return null;
        }
    }
}
