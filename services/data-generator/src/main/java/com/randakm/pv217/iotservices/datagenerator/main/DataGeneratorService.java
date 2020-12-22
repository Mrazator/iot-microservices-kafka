package com.randakm.pv217.iotservices.datagenerator.main;

import com.randakm.pv217.iotservices.datagenerator.core.Measurement;

import java.util.List;

public interface DataGeneratorService {

  List<Measurement> generateMeasurements();
  boolean isInitialized();
  
}
