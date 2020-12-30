package com.nabiyev.pv217.iotservices.datacollector.data;

import java.util.List;

public class Report {
    private String controlCenterId;
    private List<Measurement> measurements;

    public String getControlCenterId() {
        return controlCenterId;
    }

    public void setControlCenterId(String controlCenterId) {
        this.controlCenterId = controlCenterId;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}

