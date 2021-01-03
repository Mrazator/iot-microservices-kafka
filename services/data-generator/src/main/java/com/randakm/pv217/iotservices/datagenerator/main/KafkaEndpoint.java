package com.randakm.pv217.iotservices.datagenerator.main;

import com.randakm.pv217.iotservices.datagenerator.core.Report;

import java.time.Duration;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class KafkaEndpoint {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEndpoint.class);
  private static final int GENERATE_INTERVAL = 1;

  private final ReportService reportService;

  /**
   * @param reportService
   */
  public KafkaEndpoint(ReportService reportService) {
    super();
    this.reportService = reportService;
  }

  @Outgoing("measurements-generated-out")
  public Multi<Report> generate() {
    return Multi.createFrom().ticks().every(Duration.ofSeconds(GENERATE_INTERVAL)).onOverflow().drop().map(tick -> {
      LOGGER.debug("Kafka endpoint generate called");
      return reportService.generateReport();
    });
  }
}
