package com.randakm.pv217.iotservices.datagenerator.main;

import com.randakm.pv217.iotservices.datagenerator.core.Report;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.JsonbBuilder;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.arc.profile.IfBuildProfile;

@ApplicationScoped
@IfBuildProfile("dev")
public class KafkaDebugEndpoint {


  @Incoming("measurements-generated-in")
  public void process(String reportJson) {
    var jsonb = JsonbBuilder.create();
    var report = jsonb.fromJson(reportJson, Report.class);
    System.out.println("received report" + report);
  }
}
