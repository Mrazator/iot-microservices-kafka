package com.randakm.pv217.iotservices.datagenerator.core;

public class Measurement {
  private String name;
  private Float value;
  private Long timestamp;

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
   * Getter for timestamp.
   *
   * @return the timestamp
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * Setter for timestamp.
   *
   * @param timestamp - the timestamp to set
   */
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

}
