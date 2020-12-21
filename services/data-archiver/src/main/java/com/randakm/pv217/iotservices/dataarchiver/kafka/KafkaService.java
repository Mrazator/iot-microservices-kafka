package com.randakm.pv217.iotservices.dataarchiver.kafka;

import com.randakm.pv217.iotservices.dataarchiver.core.ArchiveService;
import com.randakm.pv217.iotservices.dataarchiver.core.Report;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class KafkaService {

  private ArchiveService archiveService;

  /**
   * @param archiveService
   */
  public KafkaService(ArchiveService archiveService) {
    super();
    this.archiveService = archiveService;
  }
  
  @Incoming("collected_report")                         
  public void process(Report report) {
    archiveService.archiveMeasurementsReport(report);
  }

}
