package com.randakm.pv217.iotservices.dataarchiver.data;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Measurement {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;

  private Float value;

  private Instant measuredAt;

  private Instant collectedAt;

  @ManyToOne(fetch = FetchType.EAGER)
  private ControlCenter controlCenter;

  /**
   * Getter for id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Setter for id.
   *
   * @param id - the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

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

  /**
   * Getter for controlCenter.
   *
   * @return the controlCenter
   */
  public ControlCenter getControlCenter() {
    return controlCenter;
  }

  /**
   * Setter for controlCenter.
   *
   * @param controlCenter - the controlCenter to set
   */
  public void setControlCenter(ControlCenter controlCenter) {
    this.controlCenter = controlCenter;
  }

}
