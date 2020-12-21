package com.randakm.pv217.iotservices.dataarchiver.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ControlCenter {
  @Id
  private String id;
  private String description;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "controlCenter")
  private Set<Measurement> measurements = new HashSet<>();

  /**
   * Getter for id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Setter for id.
   *
   * @param id - the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Getter for description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Setter for description.
   *
   * @param description - the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Getter for measurements.
   *
   * @return the measurements
   */
  public Set<Measurement> getMeasurements() {
    return measurements;
  }

  /**
   * Setter for measurements.
   *
   * @param measurements - the measurements to set
   */
  public void setMeasurements(Set<Measurement> measurements) {
    this.measurements = measurements;
  }

}
