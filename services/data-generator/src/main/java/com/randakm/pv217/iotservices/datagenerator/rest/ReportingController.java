package com.randakm.pv217.iotservices.datagenerator.rest;

import com.randakm.pv217.iotservices.datagenerator.main.KafkaEndpoint;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("reporting")
public class ReportingController {

  @Inject
  public KafkaEndpoint kafkaEndpoint;

  @GET
  @Path("generate")
  @Produces(MediaType.TEXT_PLAIN)
  public String generateManually() {
    kafkaEndpoint.generate();
    return "generated one report";
  }
  
  @GET
  @Path("state")
  @Produces(MediaType.TEXT_PLAIN)
  public String state() {
    return "Running: "+kafkaEndpoint.isRunning();
  }

  @GET
  @Path("start")
  @Produces(MediaType.TEXT_PLAIN)
  public String startGenerating() {
    kafkaEndpoint.setRunning(true);
    return "Started scheduled report generating";
  }

  @GET
  @Path("stop")
  @Produces(MediaType.TEXT_PLAIN)
  public String stopGenerating() {
    kafkaEndpoint.setRunning(false);
    return "Stopped scheduled report generating";
  }
}
