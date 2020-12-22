package com.randakm.pv217.iotservices.datagenerator.main;

import com.randakm.pv217.iotservices.datagenerator.core.Report;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class MeasuringScheduler {
  private static final Logger LOGGER = LoggerFactory.getLogger(MeasuringScheduler.class);
  
  private final DataGeneratorService dataGenerator;
  
  @ConfigProperty(name = "controlcenter.id") 
  private String controlCenterId;

  /**
   * @param dataGenerator
   */
  @Inject
  public MeasuringScheduler(DataGeneratorService dataGenerator) {
    super();
    this.dataGenerator = dataGenerator;
  }

  
  @Scheduled(every = "1s")
  @Counted(name = "measureGenerateCount", description = "Number of measurements generation done")
  @Timed(name = "measureGenerateTime", description = "A measure of how long it takes to perform measurements generation", unit = MetricUnits.MILLISECONDS)
  public void measure() {
    Report report = new Report();
    report.setControlCenterId(controlCenterId);
    report.setMeasurements(dataGenerator.generateMeasurements());
    
    Jsonb builder = JsonbBuilder.create();
    String json = builder.toJson(report);
    
    // TODO implement kafka push
    LOGGER.info("pushing to kafka \n"+json);

  }
}
