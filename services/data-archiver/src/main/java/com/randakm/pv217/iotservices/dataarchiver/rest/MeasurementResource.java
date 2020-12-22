package com.randakm.pv217.iotservices.dataarchiver.rest;

import com.randakm.pv217.iotservices.dataarchiver.core.ArchiveService;
import com.randakm.pv217.iotservices.dataarchiver.data.Measurement;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/measurements")
public class MeasurementResource {

  private final ArchiveService archiveService;

  /**
   * @param archiveService
   */
  public MeasurementResource(ArchiveService archiveService) {
    super();
    this.archiveService = archiveService;
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Measurement> getMeasurements(@QueryParam(value = "name") String name, @QueryParam(value="from") Date fromdate, @QueryParam(value="to") Date todate) {
    Instant from = null;
    Instant to = null;
    
    if(fromdate != null) from = Instant.ofEpochMilli(fromdate.getTime());
    if(todate != null) to = Instant.ofEpochMilli(todate.getTime());
    if(from == null) from = Instant.ofEpochMilli(0);
    if(to == null) to = Instant.now();
    
    var result = archiveService.findMeasurements(name, from, to);
    return result;
  }
  
}
