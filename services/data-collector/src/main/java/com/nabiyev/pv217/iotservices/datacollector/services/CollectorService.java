package com.nabiyev.pv217.iotservices.datacollector.services;

import com.nabiyev.pv217.iotservices.datacollector.data.Measurement;
import com.nabiyev.pv217.iotservices.datacollector.data.MeasurementCollect;
import com.nabiyev.pv217.iotservices.datacollector.data.Report;
import com.nabiyev.pv217.iotservices.datacollector.data.ReportCollect;
import sun.misc.FloatingDecimal;

import javax.enterprise.context.ApplicationScoped;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class CollectorService {
    Map<String, String> namesMap = new HashMap<>();

    public CollectorService() {
        namesMap.put("MW", "Active Power");
        namesMap.put("freq", "Frequency");
        namesMap.put("TemperFahr", "Temperature");
    }

    public ReportCollect someMethod (Report input) {
        ReportCollect repCollect = new ReportCollect();
        repCollect.controlCenterId = input.controlCenterId;
        repCollect.measurements = new ArrayList<>();

        for(Measurement m : input.measurements) {
            if(!namesMap.containsKey(m.name)) {
                continue;
            }

            MeasurementCollect mc = convertMeasurement(m);
            repCollect.measurements.add(mc);
        }

        return repCollect;
    }

    private MeasurementCollect convertMeasurement (Measurement input) {
        MeasurementCollect mesCollect = new MeasurementCollect();

        if(input.name.equals("TemperFahr")) {
            input.value = (input.value - 32)*(5.0f/9);
        }

        mesCollect.name = namesMap.get(input.name);
        mesCollect.measuredAt = Instant.ofEpochMilli(input.timestamp);
        mesCollect.collectedAt = Instant.now();
        mesCollect.value = Math.round(input.value * 1000.0) / 1000.0;
        return mesCollect;
    }

}

