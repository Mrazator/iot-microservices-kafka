package com.randakm.pv217.iotservices.datagenerator.main;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class MeasuringScheduler {

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

    // TODO implement kafka push
    System.out.println("pushing to kafka ");

  }
}
