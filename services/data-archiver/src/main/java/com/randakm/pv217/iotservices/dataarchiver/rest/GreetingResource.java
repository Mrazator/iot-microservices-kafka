package com.randakm.pv217.iotservices.dataarchiver.rest;

import com.randakm.pv217.iotservices.dataarchiver.core.ArchiveService;
import com.randakm.pv217.iotservices.dataarchiver.core.Report;
import com.randakm.pv217.iotservices.dataarchiver.data.Measurement;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello-resteasy")
public class GreetingResource {

  @Inject
  public ArchiveService archiveService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Transactional
  public String hello() {
    var m = new Measurement();
    m.setName("Frequency");
    m.setValue(50.5f);
    m.setMeasuredAt(Instant.now().minusMillis(500));
    m.setCollectedAt(Instant.now());
    
    var m2 = new Measurement();
    m2.setName("ActivePower");
    m2.setValue(100.551f);
    m2.setMeasuredAt(Instant.now().minusMillis(500));
    m2.setCollectedAt(Instant.now());
    
    var report = new Report();
    report.setControlCenterId("TEST_CC");
    report.setMeasurements(List.of(m, m2));
    
    archiveService.archiveMeasurementsReport(report);
    
    return "Hello RESTEasy";
  }
}