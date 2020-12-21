package com.randakm.pv217.iotservices.dataarchiver.core;

import com.randakm.pv217.iotservices.dataarchiver.data.Measurement;

import java.time.Instant;
import java.util.List;

public interface MeasurementRepo {

  void addMeasurement(Measurement m);

  List<Measurement> findMeasurements();

  List<Measurement> findMeasurements(String name, Instant from, Instant to);

}
