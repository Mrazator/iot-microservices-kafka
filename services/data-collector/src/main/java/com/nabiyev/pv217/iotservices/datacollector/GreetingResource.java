package com.nabiyev.pv217.iotservices.datacollector;

import com.nabiyev.pv217.iotservices.datacollector.data.Report;
import com.nabiyev.pv217.iotservices.datacollector.data.ReportCollect;
import com.nabiyev.pv217.iotservices.datacollector.services.CollectorService;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Path("/hello")
public class GreetingResource {

    @Inject
    public CollectorService colsrv;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ReportCollect hello() {
        Report rep = loadReportExample();
        System.out.println(rep.measurements.size());
        return colsrv.someMethod(rep);
    }


    private Report loadReportExample() {
        try {
            Jsonb jsonDeserializer = JsonbBuilder.create();
            InputStream is = getClass().getResourceAsStream("/example.json");
            return jsonDeserializer.fromJson(is, Report.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}