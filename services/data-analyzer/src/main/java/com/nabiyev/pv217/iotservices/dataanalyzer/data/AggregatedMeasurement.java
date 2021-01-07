package com.nabiyev.pv217.iotservices.dataanalyzer.data;

import java.time.Instant;

public class AggregatedMeasurement {
    public Double value;
    public String name;
    public String controlCenterId;
    public Instant intervalStart;
    public Instant intervalEnd;
}
