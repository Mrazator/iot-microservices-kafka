package com.randakm.pv217.iotservices.dataarchiver.core;

import com.randakm.pv217.iotservices.dataarchiver.data.Measurement;

import java.time.Instant;
import java.util.List;

public interface ArchiveService {

  public void archiveMeasurementsReport(Report report);
  
  public List<Measurement> findMeasurements(String name, Instant from, Instant to);
}
