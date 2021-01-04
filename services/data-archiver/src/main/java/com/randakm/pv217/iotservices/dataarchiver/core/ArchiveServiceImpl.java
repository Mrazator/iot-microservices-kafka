package com.randakm.pv217.iotservices.dataarchiver.core;

import com.randakm.pv217.iotservices.dataarchiver.data.ControlCenter;
import com.randakm.pv217.iotservices.dataarchiver.data.Measurement;

import java.time.Instant;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@ApplicationScoped
@Transactional
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

  @Counted(name = "archiveReportCount", description = "Number of reports archived")
  @Timed(name = "archiverReportTime", description = "A measure of how long it takes to archive one report", unit = MetricUnits.MILLISECONDS)
  @Override
  public void archiveMeasurementsReport(Report report) {
    var controlCenter = controlCenterRepo.findById(report.getControlCenterId());
    if (controlCenter == null) {
      controlCenter = new ControlCenter();
      controlCenter.setId(report.getControlCenterId());
      controlCenterRepo.addControlCenter(controlCenter);
    }

    for (var in : report.getMeasurements()) {
      var m = new Measurement();
      m.setName(in.getName());
      m.setValue(in.getValue());
      m.setMeasuredAt(in.getMeasuredAt());
      m.setCollectedAt(in.getCollectedAt());
      m.setArchivedAt(Instant.now());
      m.setControlCenter(controlCenter);
      measurementRepo.addMeasurement(m);
    }
  }

  @Override
  public List<Measurement> findMeasurements(String name, Instant from, Instant to) {
    return measurementRepo.findMeasurements(name, from, to);
  }

}
