package com.randakm.pv217.iotservices.datagenerator.core;

public class MeasurementGeneratorNormalImplConfig {
  private String name;
  private Float mean;
  private Float deviation;
  private Float min = Float.NEGATIVE_INFINITY;
  private Float max = Float.POSITIVE_INFINITY;
  private Float errorRate = 0f;

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
   * Getter for mean.
   *
   * @return the mean
   */
  public Float getMean() {
    return mean;
  }

  /**
   * Setter for mean.
   *
   * @param mean - the mean to set
   */
  public void setMean(Float mean) {
    this.mean = mean;
  }

  /**
   * Getter for deviation.
   *
   * @return the deviation
   */
  public Float getDeviation() {
    return deviation;
  }

  /**
   * Setter for deviation.
   *
   * @param deviation - the deviation to set
   */
  public void setDeviation(Float deviation) {
    this.deviation = deviation;
  }

  /**
   * Getter for min.
   *
   * @return the min
   */
  public Float getMin() {
    return min;
  }

  /**
   * Setter for min.
   *
   * @param min - the min to set
   */
  public void setMin(Float min) {
    this.min = min;
  }

  /**
   * Getter for max.
   *
   * @return the max
   */
  public Float getMax() {
    return max;
  }

  /**
   * Setter for max.
   *
   * @param max - the max to set
   */
  public void setMax(Float max) {
    this.max = max;
  }

  /**
   * Getter for errorRate.
   *
   * @return the errorRate
   */
  public Float getErrorRate() {
    return errorRate;
  }

  /**
   * Setter for errorRate.
   *
   * @param errorRate - the errorRate to set
   */
  public void setErrorRate(Float errorRate) {
    this.errorRate = errorRate;
  }

}
