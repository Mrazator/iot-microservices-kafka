package com.nabiyev.pv217.iotservices.datacollector.kafka;

import com.nabiyev.pv217.iotservices.datacollector.data.Report;
import com.nabiyev.pv217.iotservices.datacollector.data.ReportCollect;
import com.nabiyev.pv217.iotservices.datacollector.services.CollectorService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@ApplicationScoped
public class KafkaEndpoint {

    @Inject
    public CollectorService service;

    @Incoming("measurements-generated-in")
    @Outgoing("measurements-collected-out")
    public ReportCollect processReport(String reportJson) {
        Jsonb jsonb = JsonbBuilder.create();
        Report input = jsonb.fromJson(reportJson, Report.class);

        ReportCollect result = service.someMethod(input);
        return result;
    }
}
