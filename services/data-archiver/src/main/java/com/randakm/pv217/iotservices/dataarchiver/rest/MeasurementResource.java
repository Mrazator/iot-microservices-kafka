package com.randakm.pv217.iotservices.dataarchiver.rest;

import com.randakm.pv217.iotservices.dataarchiver.core.ArchiveService;
import com.randakm.pv217.iotservices.dataarchiver.data.Measurement;
import com.randakm.pv217.iotservices.dataarchiver.kafka.KafkaEndpoint;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/measurements")
public class MeasurementResource {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEndpoint.class);

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
  public List<Measurement> getMeasurements(@QueryParam("name") String name, @QueryParam("from") String fromStr, @QueryParam("to") String toStr) {
    LOGGER.info("Called getMeasurements rest method! name="+name+", from="+fromStr+", to="+toStr);

    Instant from = null;
    Instant to = null;
    
    if(fromStr != null) from = Instant.parse(fromStr);
    if(toStr != null) to = Instant.parse(toStr);

    if(from == null) from = Instant.ofEpochMilli(0);
    if(to == null) to = Instant.now();
        
    var result = archiveService.findMeasurements(name, from, to);
    
    return result;
  }
  
}
