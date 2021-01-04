package com.randakm.pv217.iotservices.dataarchiver.core;

import java.time.Instant;

public class MeasurementIn {
  
  private String name;
  private Float value;
  private Instant measuredAt;
  private Instant collectedAt;

  /**
   * Getter for name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for name.
   *
   * @param name - the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter for value.
   *
   * @return the value
   */
  public Float getValue() {
    return value;
  }

  /**
   * Setter for value.
   *
   * @param value - the value to set
   */
  public void setValue(Float value) {
    this.value = value;
  }

  /**
   * Getter for measuredAt.
   *
   * @return the measuredAt
   */
  public Instant getMeasuredAt() {
    return measuredAt;
  }

  /**
   * Setter for measuredAt.
   *
   * @param measuredAt - the measuredAt to set
   */
  public void setMeasuredAt(Instant measuredAt) {
    this.measuredAt = measuredAt;
  }

  /**
   * Getter for collectedAt.
   *
   * @return the collectedAt
   */
  public Instant getCollectedAt() {
    return collectedAt;
  }

  /**
   * Setter for collectedAt.
   *
   * @param collectedAt - the collectedAt to set
   */
  public void setCollectedAt(Instant collectedAt) {
    this.collectedAt = collectedAt;
  }

}
