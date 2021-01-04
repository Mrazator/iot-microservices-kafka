package com.randakm.pv217.iotservices.datagenerator.rest;

import com.randakm.pv217.iotservices.datagenerator.core.Report;
import com.randakm.pv217.iotservices.datagenerator.main.ReportService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("hello")
public class GreetingController {

  @Inject
  public ReportService reportService;

  @Inject
  @Channel("measurements-generated-out")
  public Emitter<Report> channel;
  
  @GET
  @Path("generate")
  @Produces(MediaType.TEXT_PLAIN)
  public String generateManually() {
    var rep = reportService.generateReport();
    channel.send(rep);
    return "Generated report";
  }
}
