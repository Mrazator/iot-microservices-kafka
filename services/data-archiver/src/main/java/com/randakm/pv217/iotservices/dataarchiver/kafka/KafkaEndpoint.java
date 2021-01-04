package com.randakm.pv217.iotservices.dataarchiver.kafka;

import com.randakm.pv217.iotservices.dataarchiver.core.ArchiveService;
import com.randakm.pv217.iotservices.dataarchiver.core.Report;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.reactive.messaging.annotations.Blocking;

//@ApplicationScoped
public class KafkaEndpoint {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEndpoint.class);

  private ArchiveService archiveService;

  /**
   * @param archiveService
   */
  public KafkaEndpoint(ArchiveService archiveService) {
    super();
    this.archiveService = archiveService;
  }
  
  @Incoming("measurements-collected-in")
  @Outgoing("measurements-archived-out")
  @Blocking
  public Report process(String reportJson) {
    LOGGER.debug("incoming kafka message "+reportJson);
    Jsonb jsonb = JsonbBuilder.create();
    Report report = jsonb.fromJson(reportJson, Report.class);
    
    archiveService.archiveMeasurementsReport(report);
    return report;
  }

}
