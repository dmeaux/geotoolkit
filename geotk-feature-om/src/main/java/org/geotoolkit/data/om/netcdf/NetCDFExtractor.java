/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2014, Geomatys
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

package org.geotoolkit.data.om.netcdf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.sis.measure.Longitude;
import org.geotoolkit.gml.xml.AbstractGeometry;
import org.geotoolkit.observation.xml.Process;
import org.geotoolkit.sampling.xml.SamplingFeature;
import org.geotoolkit.sos.MeasureStringBuilder;
import org.geotoolkit.observation.model.ObservationDataset;
import org.geotoolkit.observation.model.ProcedureDataset;
import org.geotoolkit.observation.model.GeoSpatialBound;
import org.geotoolkit.observation.OMUtils;
import static org.geotoolkit.observation.model.FeatureType.*;
import static org.geotoolkit.data.om.netcdf.NetCDFUtils.*;
import static org.geotoolkit.observation.model.ObservationTransformUtils.*;
import org.geotoolkit.sos.xml.SOSXmlFactory;
import org.geotoolkit.swe.xml.AbstractDataRecord;
import org.geotoolkit.swe.xml.Phenomenon;
import org.opengis.geometry.DirectPosition;
import ucar.ma2.Array;
import ucar.ma2.ArrayChar;
import ucar.ma2.ArrayInt;
import ucar.nc2.Attribute;
import ucar.nc2.Dimension;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

/**
 *
 * @author Guilhem Legal (Geomatys)
 */
public class NetCDFExtractor {

    private static final Logger LOGGER = Logger.getLogger("org.geotoolkit.sos.netcdf");
    private static final long LIMIT = (Integer.MIN_VALUE * -1l) + 1l;

    public static ObservationDataset getObservationFromNetCDF(final Path netCDFFile, final String procedureID) throws NetCDFParsingException {
        final NCFieldAnalyze analyze = analyzeResult(netCDFFile, null);
        switch (analyze.featureType) {
            case TIMESERIES :
            return parseDataBlockTS(analyze, procedureID, null, new HashSet<>());
            case PROFILE :
            return parseDataBlockXY(analyze, procedureID, null, new HashSet<>());
            case TRAJECTORY :
            return parseDataBlockTraj(analyze, procedureID, null, new HashSet<>());
            case GRID :
            return parseDataBlockGrid(analyze, procedureID, null, new HashSet<>());
            default : return null;
        }
    }

    public static ObservationDataset getObservationFromNetCDF(final NCFieldAnalyze analyze, final String procedureID, final List<String> acceptedProcedureIDs, Set<org.opengis.observation.Phenomenon> phenomenons) throws NetCDFParsingException {
        switch (analyze.featureType) {
            case TIMESERIES :
            return parseDataBlockTS(analyze, procedureID, acceptedProcedureIDs, phenomenons);
            case PROFILE :
            return parseDataBlockXY(analyze, procedureID, acceptedProcedureIDs, phenomenons);
            case TRAJECTORY :
            return parseDataBlockTraj(analyze, procedureID, acceptedProcedureIDs, phenomenons);
            case GRID :
            return parseDataBlockGrid(analyze, procedureID, acceptedProcedureIDs, phenomenons);
            default : return null;
        }
    }

    public static List<ProcedureDataset> getProcedures(final NCFieldAnalyze analyze, final String procedureID, final List<String> acceptedProcedureIDs) throws NetCDFParsingException {
        switch (analyze.featureType) {
            case TIMESERIES :
            return getProcedureTS(analyze, procedureID, acceptedProcedureIDs);
            case PROFILE :
            return getProcedureXY(analyze, procedureID, acceptedProcedureIDs);
            case TRAJECTORY :
            return getProcedureTraj(analyze, procedureID, acceptedProcedureIDs);
            case GRID :
            return getProcedureGrid(analyze, procedureID, acceptedProcedureIDs);
            default : return null;
        }
    }

    /**
     * @deprecated use {@link #analyzeResult(Path, String)} instead
     */
    public static NCFieldAnalyze analyzeResult(final File netCDFFile, final String selectedBand) {
        return analyzeResult(netCDFFile.toPath(), selectedBand);
    }

    /**
     * Analyse NetCDF file.
     *
     * @param netCDFFile
     * @param selectedBand
     * @return
     */
    public static NCFieldAnalyze analyzeResult(final Path netCDFFile, final String selectedBand) {
        final NCFieldAnalyze analyze = new NCFieldAnalyze();
        try {

            //TODO can fail if input path is not on local filesystem
            final NetcdfFile file = NetcdfFile.open(netCDFFile.toString());
            analyze.file = file;

            final Attribute ftAtt = file.findGlobalAttribute("featureType");
            if (ftAtt != null) {
                final String value = ftAtt.getStringValue();
                if ("timeSeries".equalsIgnoreCase(value)) {
                    analyze.featureType = TIMESERIES;
                } else if ("profile".equalsIgnoreCase(value)) {
                    analyze.featureType = PROFILE;
                } else if ("trajectory".equalsIgnoreCase(value)) {
                    analyze.featureType = TRAJECTORY;
                } else {
                    analyze.featureType = GRID;
                }
            } else {
                analyze.featureType = GRID;
            }

            final Attribute titAtt = file.findGlobalAttribute("title");
            if (titAtt != null) {
                final String value = titAtt.getStringValue();
                analyze.title = value;
            }

            final List<NCField> all = new ArrayList<>();

            for (Variable variable : file.getVariables()) {
                final String name = variable.getFullName();
                if (name != null) {
                    final int dimension = variable.getDimensions().size();
                    final String dimensionLabel = getDimensionString(variable);
                    final String description = variable.getDescription();

                    // try to get units
                    String uom = null;
                    final Attribute att = variable.findAttribute("units");
                    if (att != null) {
                        uom = att.getStringValue();
                    }
                    Number fillValue = null;
                    final Attribute attFill = variable.findAttribute("_FillValue");
                    if (attFill != null) {
                        fillValue = attFill.getNumericValue();
                    }

                    analyze.vars.put(name, variable);
                    if (name.equalsIgnoreCase("Time")) {
                        Type dataType = Type.DATE;
                        final NCField currentField = new NCField(name, name, dataType, dimension, dimensionLabel, fillValue, uom);
                        all.add(currentField);
                        if (analyze.featureType == TIMESERIES || analyze.featureType == TRAJECTORY || analyze.featureType == GRID) {
                            analyze.mainField = currentField;
                        } else {
                            analyze.skippedFields.add(currentField);
                        }
                        analyze.timeField = currentField;

                    } else if (name.equalsIgnoreCase("Latitude") || name.equalsIgnoreCase("lat")) {
                        Type dataType = Type.DOUBLE;
                        final NCField currentField = new NCField(name, name, dataType, dimension, dimensionLabel, fillValue, uom);
                        analyze.skippedFields.add(currentField);
                        analyze.latField = currentField;

                    } else if (name.equalsIgnoreCase("Longitude") || name.equalsIgnoreCase("long") || name.equalsIgnoreCase("lon")) {
                        Type dataType = Type.DOUBLE;
                        final NCField currentField = new NCField(name, name, dataType, dimension, dimensionLabel, fillValue, uom);
                        analyze.skippedFields.add(currentField);
                        analyze.lonField = currentField;


                    } else if (name.equalsIgnoreCase("pression") || name.equalsIgnoreCase("pres") || name.equalsIgnoreCase("depth") || name.equalsIgnoreCase("zLevel") || name.equalsIgnoreCase("z")) {
                        Type dataType = Type.DOUBLE;
                        final NCField currentField = new NCField(name, name, dataType, dimension, dimensionLabel, fillValue, uom);
                        if (analyze.featureType == PROFILE) {
                            analyze.mainField = currentField;
                        } else if (dimension > 1 && (selectedBand == null || name.equals(selectedBand))) {
                            analyze.phenfields.add(currentField);
                        } else {
                            analyze.skippedFields.add(currentField);
                        }

                    } else if (name.equalsIgnoreCase("timeserie") || name.equalsIgnoreCase("trajectory") || name.equalsIgnoreCase("profile")) {
                        Type dataType = Type.STRING;
                        final NCField currentField = new NCField(name, name, dataType, dimension, dimensionLabel, fillValue, uom);
                        analyze.separatorField = currentField;

                    } else  {
                        Type dataType = getTypeFromDataType(variable.getDataType());
                        final NCField currentField = new NCField(name, name, dataType, dimension, dimensionLabel, fillValue, uom);
                        if ((dataType == Type.DOUBLE || dataType == Type.INT) && dimension != 0 && (selectedBand == null || name.equals(selectedBand))) {
                            analyze.phenfields.add(currentField);
                        } else {
                            analyze.skippedFields.add(currentField);
                        }
                    }
                }
            }

            // another round to try to find a main field
            if (analyze.mainField == null) {
                for (NCField field : all) {
                    // try to find a time field
                    if ((analyze.featureType == TIMESERIES || analyze.featureType == TRAJECTORY || analyze.featureType == GRID) && field.name.toLowerCase().contains("time")) {
                        analyze.mainField = field;
                        analyze.phenfields.remove(field);
                        break;
                    }
                }
            }

            // post analyze
            if (analyze.mainField != null) {
                for (NCField f : all) {
                    final String mainDimension = analyze.mainField.dimensionLabel;
                    // dimension order
                    if (!f.dimensionLabel.startsWith(mainDimension)) {
                        f.mainVariableFirst = false;
                    }

                    // exclude phenomenon field not related to main
                    if (analyze.phenfields.contains(f)) {
                        if (!f.dimensionLabel.contains(mainDimension)) {
                            analyze.phenfields.remove(f);
                            analyze.skippedFields.add(f);
                        }
                    }
                }

                //look for invisible separator
                if (analyze.featureType != TRAJECTORY && analyze.separatorField == null) {
                    for (NCField phenField : analyze.phenfields) {
                        if (phenField.dimension > 1) {
                            final String separatorDim = phenField.dimensionLabel.replace(analyze.mainField.name, "").trim();
                            final Dimension dim = file.findDimension(separatorDim);
                            if (dim != null) {
                                analyze.dimensionSeparator = separatorDim;
                            }
                        }
                    }
                }

                if (analyze.featureType == TRAJECTORY && analyze.separatorField == null) {
                    for (NCField phenField : analyze.phenfields) {
                        if (phenField.dimension > 1) {
                            final Dimension dim = file.findDimension("trajectory");
                            if (dim != null) {
                                final String separatorDim = "trajectory";
                                analyze.dimensionSeparator = separatorDim;
                            }
                        }
                    }
                } else if (analyze.featureType == TIMESERIES && analyze.separatorField == null && analyze.dimensionSeparator == null) {
                    for (NCField phenField : analyze.phenfields) {
                        if (phenField.dimension > 1) {
                            final Dimension dim = file.findDimension("timeseries");
                            if (dim != null) {
                                final String separatorDim = "timeseries";
                                analyze.dimensionSeparator = separatorDim;
                            }
                        }
                    }
                }

                if (analyze.dimensionSeparator != null) {
                    for (NCField f : all) {
                        // dimension order
                        if (f.dimensionLabel.startsWith(analyze.dimensionSeparator)) {
                            f.mainVariableFirst = false;
                        }
                    }
                }
            }


        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return analyze;
    }

    public static boolean isObservationFile(final String nfilePath) {
        try (NetcdfFile file = NetcdfFile.open(nfilePath)) {
            final Attribute ftAtt = file.findGlobalAttribute("featureType");
            if (ftAtt != null) {
                final String value = ftAtt.getStringValue();
                if ("timeSeries".equalsIgnoreCase(value) ||
                    "profile".equalsIgnoreCase(value)   ||
                    "trajectory".equalsIgnoreCase(value)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "IO exception while opening netCDF file", ex);
        }
        return false;
    }

    private static ObservationDataset parseDataBlockTS(final NCFieldAnalyze analyze, final String procedureID, final List<String> acceptedSensorID, Set<org.opengis.observation.Phenomenon> phenomenons) throws NetCDFParsingException {
        final ObservationDataset results = new ObservationDataset();
        if (analyze.mainField == null) {
            LOGGER.warning("No main field found");
            return results;
        }
        LOGGER.info("parsing netCDF TS");

        try {

            final List<String> separators = parseSeparatorValues(analyze);
            final boolean single          = separators.isEmpty();

            Array latArray  = null;
            Array lonArray  = null;
            if (analyze.hasSpatial()) {
                latArray        = analyze.getArrayFromField(analyze.latField);
                lonArray        = analyze.getArrayFromField(analyze.lonField);
            }

            final Variable timeVar  = analyze.vars.get(analyze.mainField.name);
            final String timeUnits  = analyze.mainField.uom;
            final Array timeArray   = analyze.file.readArrays(Arrays.asList(timeVar)).get(0);
            final boolean constantT = analyze.mainField.dimension == 1;
            final boolean timeFirst = analyze.mainField.mainVariableFirst;


            final Map<String, Array> phenArrays = analyze.getPhenomenonArrayMap();
            final List<String> fields           = new ArrayList(phenArrays.keySet());
            final AbstractDataRecord datarecord = OMUtils.getDataRecordTimeSeries("2.0.0", analyze.phenfields);
            final Phenomenon phenomenon         = OMUtils.getPhenomenon("2.0.0", analyze.phenfields, phenomenons);
            results.phenomenons.add(toModel(phenomenon));

            if (single) {
                if (acceptedSensorID == null || acceptedSensorID.contains(procedureID)) {
                    final Process proc =  (Process) OMUtils.buildProcess(procedureID);
                    final ProcedureDataset compo = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getDescription(), "Component", "timeseries", fields, null);
                    results.procedures.add(compo);

                    final MeasureStringBuilder sb = new MeasureStringBuilder();
                    final int count               = timeVar.getDimension(0).getLength();
                    final GeoSpatialBound gb      = new GeoSpatialBound();
                    final String identifier       = UUID.randomUUID().toString();
                    //read geometry (assume point)
                    SamplingFeature sp = null;
                    if (analyze.hasSpatial()) {
                        final double latitude         = getDoubleValue(latArray, analyze.latField.fillValue);
                        final double longitude        = Longitude.normalize(getDoubleValue(lonArray, analyze.lonField.fillValue));
                        if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                            sp = OMUtils.buildSamplingPoint(identifier, latitude, longitude);
                            results.featureOfInterest.add(toModel(sp));
                            gb.addXYCoordinate(longitude, latitude);
                            gb.addGeometry((AbstractGeometry)sp.getGeometry());
                        }
                    }

                    // iterating over time
                    for (int i = 0; i < count; i++) {

                        final long millis = getTimeValue(timeUnits, timeArray, i);

                        if (millis == 0 || millis == LIMIT) {
                            continue;
                        }
                        gb.addDate(millis);
                        sb.appendDate(millis);

                        for (NCField field : analyze.phenfields) {
                            final Array phenArray = phenArrays.get(field.name);
                            final Double value    = getDoubleValue(phenArray, i, field.fillValue);
                            sb.appendValue(value);
                        }
                        sb.closeBlock();
                    }
                    results.observations.add(toModel(OMUtils.buildObservation(identifier,                    // id
                                                                      sp,                            // foi
                                                                      phenomenon,                    // phenomenon
                                                                      proc,                          // procedure
                                                                      count,                         // result
                                                                      datarecord,                    // result
                                                                      sb,                            // result
                                                                      gb.getTimeObject("2.0.0"))));   // time
                    results.spatialBound.merge(gb);
                    compo.spatialBound.merge(gb);
                }

            } else {
                final Process proc =  (Process) OMUtils.buildProcess(procedureID);
                final ProcedureDataset system = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getDescription(), "System", "timeseries", fields, null);
                results.procedures.add(system);
                for (int j = 0; j < separators.size(); j++) {

                    final String identifier       = separators.get(j);
                    final MeasureStringBuilder sb = new MeasureStringBuilder();
                    final int count               = getGoodTimeDimension(timeVar, analyze.dimensionSeparator).getLength();
                    final GeoSpatialBound gb      = new GeoSpatialBound();
                    final String currentProcID    = procedureID + '-' + identifier;
                    final Process currentProc     =  (Process) OMUtils.buildProcess(currentProcID);
                    final ProcedureDataset compo     = new ProcedureDataset(currentProc.getHref(), currentProc.getName(), currentProc.getDescription(), "Component", "timeseries", fields, null);

                    if (acceptedSensorID == null || acceptedSensorID.contains(currentProcID)) {

                        //read geometry (assume point)
                        SamplingFeature sp = null;
                        if (analyze.hasSpatial()) {
                            final double latitude         = getDoubleValue(latArray, j, analyze.latField.fillValue);
                            final double longitude        = Longitude.normalize(getDoubleValue(lonArray, j, analyze.lonField.fillValue));
                            if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                                sp = OMUtils.buildSamplingPoint(identifier, latitude, longitude);
                                results.featureOfInterest.add(toModel(sp));
                                gb.addXYCoordinate(longitude, latitude);
                                gb.addGeometry((AbstractGeometry)sp.getGeometry());
                            }
                        }

                        for (int i = 0; i < count; i++) {

                            final long millis = getTimeValue(timeUnits, timeFirst, constantT, timeArray, i, j);

                            if (millis == 0 || millis == LIMIT) {
                                continue;
                            }
                            gb.addDate(millis);
                            sb.appendDate(millis);
                            for (NCField field : analyze.phenfields) {
                                final Array phenArray   = phenArrays.get(field.name);
                                final boolean mainFirst = field.mainVariableFirst;
                                final Double value      = getDoubleValue(mainFirst, phenArray, i, j, field.fillValue);
                                sb.appendValue(value);
                            }
                            // remove the last token separator
                            sb.closeBlock();
                        }

                        compo.spatialBound.merge(gb);
                        system.children.add(compo);

                        final String obsid = UUID.randomUUID().toString();
                        results.observations.add(toModel(OMUtils.buildObservation(obsid,                         // id
                                                                          sp,                            // foi
                                                                          phenomenon,                    // phenomenon
                                                                          currentProc,                   // procedure
                                                                          count,                         // result
                                                                          datarecord,                    // result
                                                                          sb,                            // result
                                                                          gb.getTimeObject("2.0.0"))));   // time
                        results.spatialBound.merge(gb);
                    }
                }
            }

        } catch (IOException | IllegalArgumentException ex) {
            throw new NetCDFParsingException("error while parsing netcdf timeserie", ex);
        }

        LOGGER.info("datablock parsed");
        return results;
    }

    private static List<ProcedureDataset> getProcedureTS(final NCFieldAnalyze analyze, final String procedureID, final List<String> acceptedSensorID) throws NetCDFParsingException {
        final List<ProcedureDataset> results = new ArrayList<>();
        if (analyze.mainField == null) {
            LOGGER.warning("No main field found");
            return results;
        }
        LOGGER.info("parsing netCDF TS");

        try {

            final List<String> separators = parseSeparatorValues(analyze);
            final boolean single          = separators.isEmpty();

            Array latArray  = null;
            Array lonArray  = null;
            if (analyze.hasSpatial()) {
                latArray        = analyze.getArrayFromField(analyze.latField);
                lonArray        = analyze.getArrayFromField(analyze.lonField);
            }

            final Variable timeVar  = analyze.vars.get(analyze.mainField.name);
            final String timeUnits  = analyze.mainField.uom;
            final Array timeArray   = analyze.file.readArrays(Arrays.asList(timeVar)).get(0);
            final boolean constantT = analyze.mainField.dimension == 1;
            final boolean timeFirst = analyze.mainField.mainVariableFirst;

            final Set<String> fields = analyze.getPhenomenonArrayMap().keySet();

            if (single) {
                if (acceptedSensorID == null || acceptedSensorID.contains(procedureID)) {
                    final Process proc =  (Process) OMUtils.buildProcess(procedureID);
                    final ProcedureDataset compo = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getHref(), "Component", "timeseries", fields, null);
                    results.add(compo);

                    final int count               = timeVar.getDimension(0).getLength();
                    final GeoSpatialBound gb      = new GeoSpatialBound();

                    if (analyze.hasSpatial()) {
                        final double latitude         = getDoubleValue(latArray, analyze.latField.fillValue);
                        final double longitude        = Longitude.normalize(getDoubleValue(lonArray, analyze.lonField.fillValue));
                        if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                            gb.addXYCoordinate(longitude, latitude);
                        }
                    }

                    // iterating over time
                    for (int i = 0; i < count; i++) {
                        final long millis = getTimeValue(timeUnits, timeArray, i);
                        if (millis == 0 || millis == LIMIT) {
                            continue;
                        }
                        gb.addDate(millis);
                    }
                    compo.spatialBound.merge(gb);
                }

            } else {
                final Process proc =  (Process) OMUtils.buildProcess(procedureID);
                final ProcedureDataset system = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getHref(), "System", "timeseries", fields, null);
                results.add(system);
                for (int j = 0; j < separators.size(); j++) {

                    final String identifier       = separators.get(j);
                    final int count               = getGoodTimeDimension(timeVar, analyze.dimensionSeparator).getLength();
                    final GeoSpatialBound gb      = new GeoSpatialBound();
                    final String currentProcID    = procedureID + '-' + identifier;
                    final Process currentProc     =  (Process) OMUtils.buildProcess(currentProcID);
                    final ProcedureDataset compo     = new ProcedureDataset(currentProc.getHref(), currentProc.getName(), currentProc.getDescription(), "Component", "timeseries", fields, null);

                    if (acceptedSensorID == null || acceptedSensorID.contains(currentProcID)) {

                        if (analyze.hasSpatial()) {
                            final double latitude         = getDoubleValue(latArray, j, analyze.latField.fillValue);
                            final double longitude        = Longitude.normalize(getDoubleValue(lonArray, j, analyze.lonField.fillValue));
                            if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                                gb.addXYCoordinate(longitude, latitude);
                            }
                        }

                        for (int i = 0; i < count; i++) {

                            final long millis = getTimeValue(timeUnits, timeFirst, constantT, timeArray, i, j);

                            if (millis == 0 || millis == LIMIT) {
                                continue;
                            }
                            gb.addDate(millis);
                        }

                        compo.spatialBound.merge(gb);
                        system.children.add(compo);
                    }
                }
            }

        } catch (IOException | IllegalArgumentException ex) {
            throw new NetCDFParsingException("error while parsing netcdf timeserie", ex);
        }

        LOGGER.info("datablock parsed");
        return results;
    }

    private static ObservationDataset parseDataBlockXY(final NCFieldAnalyze analyze, final String procedureID, final List<String> acceptedSensorID, Set<org.opengis.observation.Phenomenon> phenomenons) throws NetCDFParsingException {
        final ObservationDataset results = new ObservationDataset();
        if (analyze.mainField == null) {
            LOGGER.warning("No main field found");
            return results;
        }
        LOGGER.info("parsing datablock XY");

        try {
            final List<String> separators = parseSeparatorValues(analyze);
            final boolean single          = separators.isEmpty();

            Array latArray  = null;
            Array lonArray  = null;
            if (analyze.hasSpatial()) {
                latArray        = analyze.getArrayFromField(analyze.latField);
                lonArray        = analyze.getArrayFromField(analyze.lonField);
            }

            Array timeArray  = null;
            String timeUnits = null;
            if (analyze.hasTime()) {
                timeUnits        = analyze.timeField.uom;
                timeArray        = analyze.getArrayFromField(analyze.timeField);
            }

            final Variable zVar     = analyze.vars.get(analyze.mainField.name);
            final Array zArray      = analyze.file.readArrays(Arrays.asList(zVar)).get(0);
            final boolean constantZ = analyze.mainField.dimension == 1;
            final boolean Zfirst    = analyze.mainField.mainVariableFirst;

            final Map<String, Array> phenArrays = analyze.getPhenomenonArrayMap();
            final List<String> fields           = new ArrayList(phenArrays.keySet());
            final AbstractDataRecord datarecord = OMUtils.getDataRecordProfile("2.0.0", analyze.phenfields);
            final Phenomenon phenomenon         = OMUtils.getPhenomenon("2.0.0", analyze.phenfields, phenomenons);
            results.phenomenons.add(toModel(phenomenon));

            if (single) {
                if (acceptedSensorID == null || acceptedSensorID.contains(procedureID)) {
                    final Process proc =  (Process) OMUtils.buildProcess(procedureID);
                    final ProcedureDataset compo = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getDescription(), "profile", "Component", fields, null);
                    results.procedures.add(compo);

                    final MeasureStringBuilder sb = new MeasureStringBuilder();
                    final int count               = zVar.getDimension(0).getLength();
                    final GeoSpatialBound gb      = new GeoSpatialBound();
                    final String identifier       = UUID.randomUUID().toString();

                    //read geometry (assume point)
                    SamplingFeature sp = null;
                    if (analyze.hasSpatial()) {
                        final double latitude         = getDoubleValue(latArray, 0, analyze.latField.fillValue);
                        final double longitude        = Longitude.normalize(getDoubleValue(lonArray, 0, analyze.lonField.fillValue));
                        if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                            sp = OMUtils.buildSamplingPoint(identifier, latitude, longitude);
                            results.featureOfInterest.add(toModel(sp));
                            gb.addXYCoordinate(longitude, latitude);
                            gb.addGeometry((AbstractGeometry) sp.getGeometry());
                        }
                    }
                    if (analyze.hasTime()) {
                        final long millis = getTimeValue(timeUnits, timeArray, 0);

                        if (millis != 0 && millis != LIMIT) {
                            gb.addDate(millis);
                        }
                    }

                    for (int zIndex = 0; zIndex < zVar.getDimension(0).getLength(); zIndex++) {

                        double zLevel = getDoubleValue(zArray, zIndex, analyze.mainField.fillValue);
                        if (zLevel == 0 || zLevel == FILL_VALUE) {
                            continue;
                        }
                        sb.appendValue(zLevel);

                        for (NCField field : analyze.phenfields) {
                            final Array phenArray = phenArrays.get(field.name);
                            final double value    = getDoubleValue(phenArray, zIndex, field.fillValue);
                            sb.appendValue(value);
                        }
                        sb.closeBlock();
                    }
                    results.observations.add(toModel(OMUtils.buildObservation(identifier,                    // id
                                                                      sp,                            // foi
                                                                      phenomenon,                    // phenomenon
                                                                      proc,                          // procedure
                                                                      count,                         // result
                                                                      datarecord,                    // result
                                                                      sb,                            // result
                                                                      gb.getTimeObject("2.0.0"))));   // time
                    results.spatialBound.merge(gb);
                    compo.spatialBound.merge(gb);
                }

            } else {
                final Process proc =  (Process) OMUtils.buildProcess(procedureID);
                final ProcedureDataset system = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getDescription(), "profile", "System", fields, null);
                results.procedures.add(system);

                for (int profileIndex = 0; profileIndex < separators.size(); profileIndex++) {

                    final String identifier    = separators.get(profileIndex);
                    final int count            = zVar.getDimension(0).getLength();
                    final GeoSpatialBound gb   = new GeoSpatialBound();
                    final String currentProcID = procedureID + '-' + identifier;
                    final Process currentProc  =  (Process) OMUtils.buildProcess(currentProcID);
                    final ProcedureDataset compo  = new ProcedureDataset(currentProc.getHref(), currentProc.getName(), currentProc.getDescription(), "profile", "Component", fields, null);

                    if (acceptedSensorID == null || acceptedSensorID.contains(currentProcID)) {
                        //read geometry (assume point)
                        SamplingFeature sp = null;
                        if (analyze.hasSpatial()) {
                            final double latitude         = getDoubleValue(latArray, 0, analyze.latField.fillValue);
                            final double longitude        = Longitude.normalize(getDoubleValue(lonArray, 0, analyze.lonField.fillValue));
                            if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                                sp = OMUtils.buildSamplingPoint(identifier, latitude, longitude);
                                results.featureOfInterest.add(toModel(sp));
                                gb.addXYCoordinate(longitude, latitude);
                                gb.addGeometry((AbstractGeometry) sp.getGeometry());
                            }
                        }
                        if (analyze.hasTime()) {
                            final long millis = getTimeValue(timeUnits, timeArray, 0);

                            if (millis != 0 && millis != LIMIT) {
                                gb.addDate(millis);
                            }
                        }

                        final MeasureStringBuilder sb = new MeasureStringBuilder();
                        for (int zIndex = 0; zIndex < zVar.getDimension(0).getLength(); zIndex++) {

                            double zLevel = getZValue(Zfirst, constantZ, zArray, zIndex, profileIndex, analyze.mainField.fillValue);
                            if (zLevel == 0 || zLevel == FILL_VALUE) {
                                continue;
                            }
                            sb.appendValue(zLevel);

                            for (NCField field : analyze.phenfields) {
                                final Array phenArray   = phenArrays.get(field.name);
                                final boolean mainFirst = field.mainVariableFirst;
                                final double value      = getDoubleValue(mainFirst, phenArray, zIndex, profileIndex, field.fillValue);
                                sb.appendValue(value);
                            }
                            sb.closeBlock();
                        }
                        compo.spatialBound.merge(gb);
                        system.children.add(compo);

                        final String obsid = UUID.randomUUID().toString();
                        results.observations.add(toModel(OMUtils.buildObservation(obsid,                         // id
                                                                          sp,                            // foi
                                                                          phenomenon,                    // phenomenon
                                                                          currentProc,                   // procedure
                                                                          count,                         // result
                                                                          datarecord,                    // result
                                                                          sb,                            // result
                                                                          gb.getTimeObject("2.0.0"))));   // time
                        results.spatialBound.merge(gb);
                    }
                }
            }

        } catch (IOException | IllegalArgumentException ex) {
            throw new NetCDFParsingException("error while parsing netcdf profile", ex);
        }
        LOGGER.info("datablock parsed");
        return results;
    }

    private static List<ProcedureDataset> getProcedureXY(final NCFieldAnalyze analyze, final String procedureID, final List<String> acceptedSensorID) throws NetCDFParsingException {
        final List<ProcedureDataset> results = new ArrayList<>();
        if (analyze.mainField == null) {
            LOGGER.warning("No main field found");
            return results;
        }
        LOGGER.info("parsing datablock XY");

        try {
            final List<String> separators = parseSeparatorValues(analyze);
            final boolean single          = separators.isEmpty();

            Array latArray  = null;
            Array lonArray  = null;
            if (analyze.hasSpatial()) {
                latArray        = analyze.getArrayFromField(analyze.latField);
                lonArray        = analyze.getArrayFromField(analyze.lonField);
            }

            Array timeArray  = null;
            String timeUnits = null;
            if (analyze.hasTime()) {
                timeUnits        = analyze.timeField.uom;
                timeArray        = analyze.getArrayFromField(analyze.timeField);
            }

            final Set<String> fields = analyze.getPhenomenonArrayMap().keySet();

            if (single) {
                if (acceptedSensorID == null || acceptedSensorID.contains(procedureID)) {
                    final Process proc =  (Process) OMUtils.buildProcess(procedureID);
                    final ProcedureDataset compo = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getDescription(), "Component", "profile", fields, null);
                    results.add(compo);

                    final GeoSpatialBound gb      = new GeoSpatialBound();

                    if (analyze.hasSpatial()) {
                        final double latitude         = getDoubleValue(latArray, 0, analyze.latField.fillValue);
                        final double longitude        = Longitude.normalize(getDoubleValue(lonArray, 0, analyze.lonField.fillValue));
                        if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                            gb.addXYCoordinate(longitude, latitude);
                        }
                    }
                    if (analyze.hasTime()) {
                        final long millis = getTimeValue(timeUnits, timeArray, 0);

                        if (millis != 0 && millis != LIMIT) {
                            gb.addDate(millis);
                        }
                    }
                    compo.spatialBound.merge(gb);
                }

            } else {
                final Process proc =  (Process) OMUtils.buildProcess(procedureID);
                final ProcedureDataset system = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getDescription(), "System", "profile", fields, null);
                results.add(system);

                for (int profileIndex = 0; profileIndex < separators.size(); profileIndex++) {

                    final String identifier    = separators.get(profileIndex);
                    final GeoSpatialBound gb   = new GeoSpatialBound();
                    final String currentProcID = procedureID + '-' + identifier;
                    final Process currentProc  =  (Process) OMUtils.buildProcess(currentProcID);
                    final ProcedureDataset compo  = new ProcedureDataset(currentProc.getHref(), currentProc.getName(), currentProc.getDescription(), "Component", "profile", fields, null);

                    if (acceptedSensorID == null || acceptedSensorID.contains(currentProcID)) {
                        if (analyze.hasSpatial()) {
                            final double latitude         = getDoubleValue(latArray, 0, analyze.latField.fillValue);
                            final double longitude        = Longitude.normalize(getDoubleValue(lonArray, 0, analyze.lonField.fillValue));
                            if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                                gb.addXYCoordinate(longitude, latitude);
                            }
                        }
                        if (analyze.hasTime()) {
                            final long millis = getTimeValue(timeUnits, timeArray, 0);

                            if (millis != 0 && millis != LIMIT) {
                                gb.addDate(millis);
                            }
                        }

                        compo.spatialBound.merge(gb);
                        system.children.add(compo);
                    }
                }
            }

        } catch (IOException | IllegalArgumentException ex) {
            throw new NetCDFParsingException("error while parsing netcdf profile", ex);
        }
        LOGGER.info("datablock parsed");
        return results;
    }

    private static ObservationDataset parseDataBlockTraj(final NCFieldAnalyze analyze, final String procedureID, final List<String> acceptedSensorID, Set<org.opengis.observation.Phenomenon> phenomenons) throws NetCDFParsingException {
        final ObservationDataset results = new ObservationDataset();
        if (analyze.mainField == null) {
            LOGGER.warning("No main field found");
            return results;
        }
        LOGGER.info("parsing netCDF Traj");

        try {

            final List<String> separators = parseSeparatorValues(analyze);
            final boolean single          = separators.isEmpty();

            Array latArray  = null;
            Array lonArray  = null;
            if (analyze.hasSpatial()) {
                latArray        = analyze.getArrayFromField(analyze.latField);
                lonArray        = analyze.getArrayFromField(analyze.lonField);
            }

            final Variable timeVar  = analyze.vars.get(analyze.mainField.name);
            final String timeUnits  = analyze.mainField.uom;
            final Array timeArray   = analyze.file.readArrays(Arrays.asList(timeVar)).get(0);
            final boolean constantT = analyze.mainField.dimension == 1;
            final boolean timeFirst = analyze.mainField.mainVariableFirst;


            final Map<String, Array> phenArrays = analyze.getPhenomenonArrayMap();
            final List<String> fields           = new ArrayList(phenArrays.keySet());
            final AbstractDataRecord datarecord = OMUtils.getDataRecordTrajectory("2.0.0", analyze.phenfields);
            final Phenomenon phenomenon         = OMUtils.getPhenomenon("2.0.0", analyze.phenfields, phenomenons);
            results.phenomenons.add(toModel(phenomenon));

            if (single) {
                if (acceptedSensorID == null || acceptedSensorID.contains(procedureID)) {
                    final Process proc =  (Process) OMUtils.buildProcess(procedureID);
                    final ProcedureDataset compo = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getDescription(), "trajectory", "Component", fields, null);
                    results.procedures.add(compo);

                    final MeasureStringBuilder sb = new MeasureStringBuilder();
                    final int count               = timeVar.getDimension(0).getLength();
                    final GeoSpatialBound gb      = new GeoSpatialBound();
                    final String identifier       = UUID.randomUUID().toString();

                    final List<DirectPosition> positions = new ArrayList<>();
                    DirectPosition previousPosition = null;

                    // iterating over time
                    for (int i = 0; i < count; i++) {

                        final long millis = getTimeValue(timeUnits, timeArray, i);

                        if (millis == 0 || millis == LIMIT) {
                            continue;
                        }
                        gb.addDate(millis);
                        sb.appendDate(millis);

                        final double latitude         = getDoubleValue(latArray, i, analyze.latField.fillValue);
                        sb.appendValue(latitude);
                        final double longitude        = Longitude.normalize(getDoubleValue(lonArray, i, analyze.lonField.fillValue));
                        sb.appendValue(longitude);
                        if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                            final DirectPosition position = SOSXmlFactory.buildDirectPosition("2.0.0", null, 2, Arrays.asList(latitude, longitude));
                            if (!position.equals(previousPosition)) {
                                positions.add(position);
                            }
                            previousPosition = position;
                            gb.addXYCoordinate(longitude, latitude);
                        }

                        for (NCField field : analyze.phenfields) {
                            final Array phenArray = phenArrays.get(field.name);
                            final Double value    = getDoubleValue(phenArray, i, field.fillValue);
                            sb.appendValue(value);
                        }
                        sb.closeBlock();
                    }

                    final SamplingFeature sp = OMUtils.buildSamplingCurve("foi-" + identifier, positions);
                    results.featureOfInterest.add(toModel(sp));
                    gb.addGeometry((AbstractGeometry) sp.getGeometry());

                    results.observations.add(toModel(OMUtils.buildObservation("obs-" + identifier,           // id
                                                                      sp,                            // foi
                                                                      phenomenon,                    // phenomenon
                                                                      proc,                          // procedure
                                                                      count,                         // result
                                                                      datarecord,                    // result
                                                                      sb,                            // result
                                                                      gb.getTimeObject("2.0.0"))));   // time
                    results.spatialBound.merge(gb);
                    compo.spatialBound.merge(gb);
                }

            } else {
                final Process proc =  (Process) OMUtils.buildProcess(procedureID);
                final ProcedureDataset system = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getDescription(), "trajectory", "System", fields, null);
                results.procedures.add(system);

                for (int j = 0; j < separators.size(); j++) {

                    final String identifier       = separators.get(j);
                    final MeasureStringBuilder sb = new MeasureStringBuilder();
                    int count                     = timeVar.getDimension(0).getLength();
                    final GeoSpatialBound gb      = new GeoSpatialBound();
                    final String currentProcID    = procedureID + '-' + identifier;
                    final Process currentProc      =  (Process) OMUtils.buildProcess(currentProcID);
                    final ProcedureDataset compo     = new ProcedureDataset(currentProc.getHref(), currentProc.getName(), currentProc.getDescription(), "trajectory", "Component", fields, null);

                    if (acceptedSensorID == null || acceptedSensorID.contains(currentProcID)) {
                        final List<DirectPosition> positions = new ArrayList<>();
                        DirectPosition previousPosition = null;

                        for (int i = 0; i < count; i++) {

                            final long millis = getTimeValue(timeUnits, timeFirst, constantT, timeArray, i, j);

                            if (millis == 0 || millis == LIMIT) {
                                continue;
                            }
                            gb.addDate(millis);
                            sb.appendDate(millis);

                            final double latitude  = getDoubleValue(true, latArray, i, j, analyze.latField.fillValue);
                            final double longitude = Longitude.normalize(getDoubleValue(true, lonArray, i, j, analyze.lonField.fillValue));
                            sb.appendValue(latitude);
                            sb.appendValue(longitude);
                            if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                                final DirectPosition position = SOSXmlFactory.buildDirectPosition("2.0.0", null, 2, Arrays.asList(latitude, longitude));
                                if (!position.equals(previousPosition)) {
                                    positions.add(position);
                                }
                                previousPosition = position;
                                gb.addXYCoordinate(longitude, latitude);
                            }

                            for (NCField field : analyze.phenfields) {
                                final Array phenArray   = phenArrays.get(field.name);
                                final boolean mainFirst = field.mainVariableFirst;
                                final Double value      = getDoubleValue(mainFirst, phenArray, i, j, field.fillValue);
                                sb.appendValue(value);
                            }
                            sb.closeBlock();
                        }

                        final SamplingFeature sp      = OMUtils.buildSamplingCurve(identifier, positions);
                        results.featureOfInterest.add(toModel(sp));
                        gb.addGeometry((AbstractGeometry) sp.getGeometry());

                        compo.spatialBound.merge(gb);
                        system.children.add(compo);

                        final String obsid = UUID.randomUUID().toString();
                        results.observations.add(toModel(OMUtils.buildObservation(obsid,                         // id
                                                                          sp,                            // foi
                                                                          phenomenon,                    // phenomenon
                                                                          currentProc,                   // procedure
                                                                          count,                         // result
                                                                          datarecord,                    // result
                                                                          sb,                            // result
                                                                          gb.getTimeObject("2.0.0"))));   // time
                        results.spatialBound.merge(gb);
                    }
                }
            }

        } catch (IOException | IllegalArgumentException ex) {
            throw new NetCDFParsingException("error while parsing netcdf trajectory", ex);
        }

        LOGGER.info("datablock parsed");
        return results;
    }

    private static List<ProcedureDataset> getProcedureTraj(final NCFieldAnalyze analyze, final String procedureID, final List<String> acceptedSensorID) throws NetCDFParsingException {
        final List<ProcedureDataset> results = new ArrayList<>();
        if (analyze.mainField == null) {
            LOGGER.warning("No main field found");
            return results;
        }
        LOGGER.info("parsing netCDF Traj");

        try {

            final List<String> separators = parseSeparatorValues(analyze);
            final boolean single          = separators.isEmpty();

            Array latArray  = null;
            Array lonArray  = null;
            if (analyze.hasSpatial()) {
                latArray        = analyze.getArrayFromField(analyze.latField);
                lonArray        = analyze.getArrayFromField(analyze.lonField);
            }

            final Variable timeVar  = analyze.vars.get(analyze.mainField.name);
            final String timeUnits  = analyze.mainField.uom;
            final Array timeArray   = analyze.file.readArrays(Arrays.asList(timeVar)).get(0);
            final boolean constantT = analyze.mainField.dimension == 1;
            final boolean timeFirst = analyze.mainField.mainVariableFirst;

            final Set<String> fields = analyze.getPhenomenonArrayMap().keySet();

            if (single) {
                if (acceptedSensorID == null || acceptedSensorID.contains(procedureID)) {
                    final Process proc =  (Process) OMUtils.buildProcess(procedureID);
                    final ProcedureDataset compo = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getDescription(), "Component", "trajectory", fields, null);
                    results.add(compo);

                    final int count               = timeVar.getDimension(0).getLength();
                    final GeoSpatialBound gb      = new GeoSpatialBound();

                    // iterating over time
                    for (int i = 0; i < count; i++) {

                        final long millis = getTimeValue(timeUnits, timeArray, i);

                        if (millis == 0 || millis == LIMIT) {
                            continue;
                        }
                        gb.addDate(millis);

                        final double latitude         = getDoubleValue(latArray, i, analyze.latField.fillValue);
                        final double longitude        = Longitude.normalize(getDoubleValue(lonArray, i, analyze.lonField.fillValue));
                        if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                            gb.addXYCoordinate(longitude, latitude);
                        }
                    }
                    compo.spatialBound.merge(gb);
                }

            } else {
                final Process proc =  (Process) OMUtils.buildProcess(procedureID);
                final ProcedureDataset system = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getDescription(), "System", "trajectory", fields, null);
                results.add(system);

                for (int j = 0; j < separators.size(); j++) {

                    final String identifier       = separators.get(j);
                    int count                     = timeVar.getDimension(0).getLength();
                    final GeoSpatialBound gb      = new GeoSpatialBound();
                    final String currentProcID    = procedureID + '-' + identifier;
                    final Process currentProc      =  (Process) OMUtils.buildProcess(currentProcID);
                    final ProcedureDataset compo     = new ProcedureDataset(currentProc.getHref(), currentProc.getName(), currentProc.getDescription(), "Component", "trajectory", fields, null);

                    if (acceptedSensorID == null || acceptedSensorID.contains(currentProcID)) {

                        for (int i = 0; i < count; i++) {

                            final long millis = getTimeValue(timeUnits, timeFirst, constantT, timeArray, i, j);

                            if (millis == 0 || millis == LIMIT) {
                                continue;
                            }
                            gb.addDate(millis);

                            final double latitude  = getDoubleValue(true, latArray, i, j, analyze.latField.fillValue);
                            final double longitude = Longitude.normalize(getDoubleValue(true, lonArray, i, j, analyze.lonField.fillValue));
                            if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                                gb.addXYCoordinate(longitude, latitude);
                            }
                        }

                        compo.spatialBound.merge(gb);
                        system.children.add(compo);
                    }
                }
            }

        } catch (IOException | IllegalArgumentException ex) {
            throw new NetCDFParsingException("error while parsing netcdf trajectory", ex);
        }

        LOGGER.info("datablock parsed");
        return results;
    }

    private static ObservationDataset parseDataBlockGrid(final NCFieldAnalyze analyze, final String procedureID, final List<String> acceptedSensorID, Set<org.opengis.observation.Phenomenon> phenomenons) throws NetCDFParsingException {
        final ObservationDataset results = new ObservationDataset();
        if (acceptedSensorID == null || acceptedSensorID.contains(procedureID)) {
            final Process proc =  (Process) OMUtils.buildProcess(procedureID);
            final ProcedureDataset compo = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getDescription(), "grid", "Component", new ArrayList<>(), null);
            results.procedures.add(compo);

            if (analyze.mainField == null) {
                LOGGER.warning("No main field found");
                return results;
            }
            LOGGER.info("parsing netCDF GRID");

            try {

                final Variable latVar = analyze.vars.get(analyze.latField.name);
                final Variable lonVar = analyze.vars.get(analyze.lonField.name);
                final Array latArray  = analyze.file.readArrays(Arrays.asList(latVar)).get(0);
                final Array lonArray  = analyze.file.readArrays(Arrays.asList(lonVar)).get(0);

                final Variable timeVar  = analyze.vars.get(analyze.mainField.name);
                final String timeUnits  = analyze.mainField.uom;
                final Array timeArray   = analyze.file.readArrays(Arrays.asList(timeVar)).get(0);


                final Map<String, Array> phenArrays = analyze.getPhenomenonArrayMap();
                final List<String> fields           = new ArrayList(phenArrays.keySet());
                final AbstractDataRecord datarecord = OMUtils.getDataRecordTimeSeries("2.0.0", analyze.phenfields);
                final Phenomenon phenomenon         = OMUtils.getPhenomenon("2.0.0", analyze.phenfields, phenomenons);
                results.phenomenons.add(toModel(phenomenon));
                compo.fields.addAll(fields);

                final int latSize = latVar.getDimension(0).getLength();
                for (int latIndex = 0; latIndex < latSize; latIndex++) {

                    final int lonSize = lonVar.getDimension(0).getLength();
                    for (int lonIndex = 0; lonIndex < lonSize; lonIndex++) {

                        final String identifier       = UUID.randomUUID().toString();
                        final MeasureStringBuilder sb = new MeasureStringBuilder();
                        final int count               = timeVar.getDimension(0).getLength();
                        final GeoSpatialBound gb      = new GeoSpatialBound();

                        SamplingFeature sp = null;
                        final double latitude         = getDoubleValue(latArray, latIndex, analyze.latField.fillValue);
                        final double longitude        = Longitude.normalize(getDoubleValue(lonArray, lonIndex, analyze.lonField.fillValue));
                        if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                            sp      = OMUtils.buildSamplingPoint(identifier, latitude, longitude);
                            results.featureOfInterest.add(toModel(sp));
                            gb.addXYCoordinate(longitude, latitude);
                        }

                        for (int i = 0; i < count; i++) {

                            final long millis = getTimeValue(timeUnits, timeArray, i);

                            if (millis == 0 || millis == LIMIT) {
                                continue;
                            }
                            gb.addDate(millis);
                            sb.appendDate(millis);

                            for (NCField field : analyze.phenfields) {
                                final Array phenArray   = phenArrays.get(field.name);
                                final Double value      = getDoubleValue(phenArray, i, latIndex, lonIndex, field.fillValue);
                                sb.appendValue(value);
                            }
                            sb.closeBlock();
                        }

                        results.observations.add(toModel(OMUtils.buildObservation(identifier,                    // id
                                                                          sp,                            // foi
                                                                          phenomenon,                    // phenomenon
                                                                          proc,                          // procedure
                                                                          count,                         // result
                                                                          datarecord,                    // result
                                                                          sb,                            // result
                                                                          gb.getTimeObject("2.0.0"))));   // time
                        results.spatialBound.merge(gb);
                    }
                }

                results.spatialBound.addGeometry(results.spatialBound.getPolyGonBounds("2.0.0"));
                compo.spatialBound.addGeometry(results.spatialBound.getPolyGonBounds("2.0.0"));

            } catch (IOException | IllegalArgumentException ex) {
                throw new NetCDFParsingException("error while parsing netcdf grid", ex);
            }
        }

        LOGGER.info("datablock parsed");
        return results;
    }

    private static List<ProcedureDataset> getProcedureGrid(final NCFieldAnalyze analyze, final String procedureID, final List<String> acceptedSensorID) throws NetCDFParsingException {
        final List<ProcedureDataset> results = new ArrayList<>();
        if (acceptedSensorID == null || acceptedSensorID.contains(procedureID)) {
            final Process proc =  (Process) OMUtils.buildProcess(procedureID);
            final ProcedureDataset compo = new ProcedureDataset(proc.getHref(), proc.getName(), proc.getDescription(), "grid", "Component", new ArrayList<>(), null);
            results.add(compo);

            if (analyze.mainField == null) {
                LOGGER.warning("No main field identified");
                return results;
            }
            LOGGER.info("parsing netCDF GRID");

            try {
                if (analyze.latField == null || analyze.lonField == null) {
                    LOGGER.warning("No lat/lon field identified");
                    return results;
                }

                final Variable latVar = analyze.vars.get(analyze.latField.name);
                final Variable lonVar = analyze.vars.get(analyze.lonField.name);
                final Array latArray  = analyze.file.readArrays(Arrays.asList(latVar)).get(0);
                final Array lonArray  = analyze.file.readArrays(Arrays.asList(lonVar)).get(0);

                final Variable timeVar  = analyze.vars.get(analyze.mainField.name);
                final String timeUnits  = analyze.mainField.uom;
                final Array timeArray   = analyze.file.readArrays(Arrays.asList(timeVar)).get(0);

                compo.fields.addAll(analyze.getPhenomenonArrayMap().keySet());

                final int latSize = latVar.getDimension(0).getLength();
                for (int latIndex = 0; latIndex < latSize; latIndex++) {

                    final int lonSize = lonVar.getDimension(0).getLength();
                    for (int lonIndex = 0; lonIndex < lonSize; lonIndex++) {

                        final int count               = timeVar.getDimension(0).getLength();
                        final GeoSpatialBound gb      = new GeoSpatialBound();

                        final double latitude         = getDoubleValue(latArray, latIndex, analyze.latField.fillValue);
                        final double longitude        = Longitude.normalize(getDoubleValue(lonArray, lonIndex, analyze.lonField.fillValue));
                        if (!Double.isNaN(latitude) && !Double.isNaN(longitude)) {
                            gb.addXYCoordinate(longitude, latitude);
                        }

                        for (int i = 0; i < count; i++) {

                            final long millis = getTimeValue(timeUnits, timeArray, i);

                            if (millis == 0 || millis == LIMIT) {
                                continue;
                            }
                            gb.addDate(millis);
                        }
                        compo.spatialBound.merge(gb);
                    }
                }

            } catch (IOException | IllegalArgumentException ex) {
                throw new NetCDFParsingException("error while parsing netcdf grid", ex);
            }
        }

        LOGGER.info("datablock parsed");
        return results;
    }

    private static List<String> parseSeparatorValues(final NCFieldAnalyze analyze) throws IOException {
        final List<String> separators = new ArrayList<>();
        if (analyze.separatorField != null) {
            final Variable separatorVar = analyze.vars.get(analyze.separatorField.name);
            final Array array = analyze.getArrayFromField(analyze.separatorField);
            if (array instanceof ArrayChar.D2) {
                final ArrayChar.D2 separatorArray = (ArrayChar.D2) array;
                final int separatorsSize = separatorVar.getDimension(0).getLength();
                for (int j = 0; j < separatorsSize; j++) {
                    final int size = separatorVar.getDimension(1).getLength();
                    final char[] id = new char[size];
                    for (int i = 0; i < size; i++) {
                        id[i] = separatorArray.get(j, i);
                    }
                    final String identifier = new String(id).trim() + '-';
                    separators.add(identifier);
                }
            } else if (array instanceof ArrayInt.D1) {
                final ArrayInt.D1 separatorArray = (ArrayInt.D1) array;
                final int separatorsSize = separatorVar.getDimension(0).getLength();
                for (int j = 0; j < separatorsSize; j++) {
                    final int id = separatorArray.get(j);
                    final String identifier = Integer.toString(id).trim() + '-';
                    separators.add(identifier);
                }
            }
        } else if (analyze.dimensionSeparator != null) {
            final Dimension dim = analyze.file.findDimension(analyze.dimensionSeparator);
            if (dim != null) {
                for (int i = 0; i < dim.getLength(); i++) {
                    separators.add(Integer.toString(i));
                }
            }
        }
        return separators;
    }
}
