package com.nabiyev.pv217.iotservices.datacollector.data;

import java.util.List;

public class ReportCollect {
    private String controlCenterId;
    private List<MeasurementCollect> measurements;

    public String getControlCenterId() {
        return controlCenterId;
    }

    public void setControlCenterId(String controlCenterId) {
        this.controlCenterId = controlCenterId;
    }

    public List<MeasurementCollect> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementCollect> measurements) {
        this.measurements = measurements;
    }
}
