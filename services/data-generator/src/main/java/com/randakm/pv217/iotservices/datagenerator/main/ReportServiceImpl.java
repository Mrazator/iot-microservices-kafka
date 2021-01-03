package com.randakm.pv217.iotservices.datagenerator.main;

import com.randakm.pv217.iotservices.datagenerator.core.Report;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ReportServiceImpl implements ReportService {

  private final String controlCenterId;
  private final MeasurementsService dataGenerator;

  /**
   * @param controlCenterId
   * @param dataGenerator
   */
  public ReportServiceImpl(@ConfigProperty(name = "controlcenter.id") String controlCenterId,
      MeasurementsService dataGenerator) {
    super();
    this.controlCenterId = controlCenterId;
    this.dataGenerator = dataGenerator;
  }

  @Override
  public Report generateReport() {
    var list = dataGenerator.generateMeasurements();
    Report rep = new Report();
    rep.setControlCenterId(controlCenterId);
    rep.setMeasurements(list);
    return rep;
  }

}
