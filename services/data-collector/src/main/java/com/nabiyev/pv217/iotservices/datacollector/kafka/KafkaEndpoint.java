package com.nabiyev.pv217.iotservices.datacollector.kafka;

import com.nabiyev.pv217.iotservices.datacollector.data.Report;
import com.nabiyev.pv217.iotservices.datacollector.data.ReportCollect;
import com.nabiyev.pv217.iotservices.datacollector.services.CollectorService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEndpoint.class);

    @Inject
    public CollectorService service;

    @Incoming("measurements-generated-in")
    @Outgoing("measurements-collected-out")
    public ReportCollect processReport(String reportJson) {
        LOGGER.debug("incoming kafka message "+reportJson);
        Jsonb jsonb = JsonbBuilder.create();
        Report input = jsonb.fromJson(reportJson, Report.class);

        ReportCollect result = service.someMethod(input);
        return result;
    }
}
