package com.randakm.pv217.iotservices.dataarchiver.core;

import com.randakm.pv217.iotservices.dataarchiver.data.ControlCenter;
import com.randakm.pv217.iotservices.dataarchiver.data.Measurement;
import com.randakm.pv217.iotservices.dataarchiver.kafka.KafkaEndpoint;

import java.time.Instant;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Transactional
public class ArchiveServiceImpl implements ArchiveService {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEndpoint.class);

  private final ControlCenterRepo controlCenterRepo;
  private final MeasurementRepo measurementRepo;
  private final int simulatedExceptionRatePercent;

  /**
   * @param controlCenterRepo
   * @param measurementRepo
   */
  public ArchiveServiceImpl(ControlCenterRepo controlCenterRepo, MeasurementRepo measurementRepo,
      @ConfigProperty(name = "archiveService.simulatedExceptionRatePercent") int simErrRate) {
    super();
    this.controlCenterRepo = controlCenterRepo;
    this.measurementRepo = measurementRepo;
    this.simulatedExceptionRatePercent = simErrRate;
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

  @Counted(name = "findMeasurementsCount", description = "Number of measurements search done")
  @Timed(name = "findMeasurementsTime", description = "A measure of how long it takes to perform measurements searching", unit = MetricUnits.MILLISECONDS)
  @Override
  public List<Measurement> findMeasurements(String name, Instant from, Instant to, String controlCenterId) {
    LOGGER.debug("find measurements for measurement '" + name + "' of controlcenter '" + controlCenterId + "', between "
        + from + " and " + to);
    simulateError();
    return measurementRepo.findMeasurements(name, from, to, controlCenterId);
  }

  private void simulateError() {
    if (new Random().nextInt(100) < simulatedExceptionRatePercent) {
      throw new SimulatedException("Simulated DB exception!");
    }
  }

}
