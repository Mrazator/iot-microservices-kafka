package com.nabiyev.pv217.iotservices.dataanalyzer.rest;

import com.nabiyev.pv217.iotservices.dataanalyzer.core.AggregationService;
import com.nabiyev.pv217.iotservices.dataanalyzer.data.AggregatedMeasurement;
import com.nabiyev.pv217.iotservices.dataanalyzer.kafka.KafkaEndpoint;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Path("/statistics")
public class AggregationResource {

  @Inject
  public AggregationService service;

  @Inject
  public KafkaEndpoint kafkaEndpoint;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<AggregatedMeasurement> getAggregatedMeasurements(@QueryParam("name") String name,
      @QueryParam("from") String fromStr, @QueryParam("to") String toStr,
      @QueryParam("controlCenterId") String controlCenterId, @QueryParam("unit") ChronoUnit unit) {

    if (name == null) {
      throw new WebApplicationException(
          Response.status(Response.Status.BAD_REQUEST).entity("name parameter is mandatory").type(MediaType.TEXT_PLAIN).build());
    }
    
    Instant from = fromStr != null ? Instant.parse(fromStr) : Instant.ofEpochMilli(0);
    Instant to = toStr != null ? Instant.parse(toStr) : Instant.now();
    if (unit == null)
      unit = ChronoUnit.HOURS;

    List<AggregatedMeasurement> result = service.calculateAvarages(name, controlCenterId, from, to, unit);
    kafkaEndpoint.pushToKafka(result);
    return result;
  }

}
