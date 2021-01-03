package com.randakm.pv217.iotservices.dataarchiver.kafka;

import com.randakm.pv217.iotservices.dataarchiver.core.ArchiveService;
import com.randakm.pv217.iotservices.dataarchiver.core.Report;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class KafkaEndpoint {

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
  public Report process(Report report) {
    archiveService.archiveMeasurementsReport(report);
    return report;
  }

}
