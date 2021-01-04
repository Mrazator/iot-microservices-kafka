package com.randakm.pv217.iotservices.datagenerator.main;

import com.randakm.pv217.iotservices.datagenerator.core.Report;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class KafkaEndpoint {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEndpoint.class);

  private final ReportService reportService;
  private final Emitter<Report> channel;
  private boolean running = true;

  /**
   * @param reportService
   */
  public KafkaEndpoint(ReportService reportService, @Channel("measurements-generated-out") Emitter<Report> channel) {
    super();
    this.reportService = reportService;
    this.channel = channel;
  }

  public void generate() {
    LOGGER.debug("Kafka endpoint generate called");
    channel.send(reportService.generateReport());
  }

  @Scheduled(every = "1s")
  public void scheduledGenerate() {
    if (running) {
      generate();
    }
  }

  /**
   * Getter for running.
   *
   * @return the running
   */
  public boolean isRunning() {
    return running;
  }

  /**
   * Setter for running.
   *
   * @param running - the running to set
   */
  public void setRunning(boolean running) {
    this.running = running;
  }

}
