package com.randakm.pv217.iotservices.datagenerator.main;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class MeasuringScheduler {
  private static final Logger LOGGER = LoggerFactory.getLogger(MeasuringScheduler.class);
  
  private final DataGeneratorService dataGenerator;

  /**
   * @param dataGenerator
   */
  @Inject
  public MeasuringScheduler(DataGeneratorService dataGenerator) {
    super();
    this.dataGenerator = dataGenerator;
  }

  @Scheduled(every = "1s")
  public void measure() {
    var measurements = dataGenerator.generateMeasurements();

    Jsonb builder = JsonbBuilder.create();
    String json = builder.toJson(measurements);
    
    // TODO implement kafka push
    LOGGER.info("pushing to kafka \n"+json);

  }
}
