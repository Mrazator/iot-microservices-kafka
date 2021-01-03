package com.randakm.pv217.iotservices.datagenerator.main;

import com.randakm.pv217.iotservices.datagenerator.core.Measurement;

import java.util.List;

public interface MeasurementsService {

  List<Measurement> generateMeasurements();
  boolean isInitialized();
  
}
