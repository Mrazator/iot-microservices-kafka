package com.randakm.pv217.iotservices.dataarchiver.core;

import java.util.List;

public class Report {
  private String controlCenterId;
  private List<MeasurementIn> measurements;

  /**
   * Getter for controlCenterId.
   *
   * @return the controlCenterId
   */
  public String getControlCenterId() {
    return controlCenterId;
  }

  /**
   * Setter for controlCenterId.
   *
   * @param controlCenterId - the controlCenterId to set
   */
  public void setControlCenterId(String controlCenterId) {
    this.controlCenterId = controlCenterId;
  }

  /**
   * Getter for measurements.
   *
   * @return the measurements
   */
  public List<MeasurementIn> getMeasurements() {
    return measurements;
  }

  /**
   * Setter for measurements.
   *
   * @param measurements - the measurements to set
   */
  public void setMeasurements(List<MeasurementIn> measurements) {
    this.measurements = measurements;
  }

}
