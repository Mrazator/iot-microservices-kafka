package com.randakm.pv217.iotservices.dataarchiver.rest;

import com.randakm.pv217.iotservices.dataarchiver.core.ArchiveService;
import com.randakm.pv217.iotservices.dataarchiver.core.Report;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/reports")
public class ReportResource {
  private final ArchiveService archiveService;

  /**
   * @param archiveService
   */
  public ReportResource(ArchiveService archiveService) {
    super();
    this.archiveService = archiveService;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void getMeasurements(Report report) {
    archiveService.archiveMeasurementsReport(report);
  }
}
