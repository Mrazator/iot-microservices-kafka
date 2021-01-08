package com.nabiyev.pv217.iotservices.dataanalyzer.rest;

import com.nabiyev.pv217.iotservices.dataanalyzer.core.AggregationService;
import com.nabiyev.pv217.iotservices.dataanalyzer.data.AggregatedMeasurement;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Path("/statistics")
public class AggregationResource {

  @Inject
  public AggregationService service;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<AggregatedMeasurement> getAggregatedMeasurements(@QueryParam("name") String name,
      @QueryParam("from") String fromStr, @QueryParam("to") String toStr,
      @QueryParam("controlCenterId") String controlCenterId, @QueryParam("unit") ChronoUnit unit) {

    Instant from = fromStr != null ? Instant.parse(fromStr) : Instant.ofEpochMilli(0);
    Instant to = toStr != null ? Instant.parse(toStr) : Instant.now();
    if (unit == null)
      unit = ChronoUnit.HOURS;

    return service.calculateAvarages(name, controlCenterId, from, to, unit);
  }
}
