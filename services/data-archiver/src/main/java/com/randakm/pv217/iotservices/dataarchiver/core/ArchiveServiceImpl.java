package com.randakm.pv217.iotservices.dataarchiver.core;

import com.randakm.pv217.iotservices.dataarchiver.data.ControlCenter;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class ArchiveServiceImpl implements ArchiveService {

  private final ControlCenterRepo controlCenterRepo;
  private final MeasurementRepo measurementRepo;
    
  /**
   * @param controlCenterRepo
   * @param measurementRepo
   */
  public ArchiveServiceImpl(ControlCenterRepo controlCenterRepo, MeasurementRepo measurementRepo) {
    super();
    this.controlCenterRepo = controlCenterRepo;
    this.measurementRepo = measurementRepo;
  }

  @Transactional
  @Override
  public void archiveMeasurementsReport(Report report) {
    var controlCenter = controlCenterRepo.findById(report.getControlCenterId());
    if(controlCenter == null) {
      controlCenter = new ControlCenter();
      controlCenter.setId(report.getControlCenterId());
      controlCenterRepo.addControlCenter(controlCenter);
    }
    
    for(var m : report.getMeasurements()) {
      m.setControlCenter(controlCenter);
      measurementRepo.addMeasurement(m);
    }
  }

}
