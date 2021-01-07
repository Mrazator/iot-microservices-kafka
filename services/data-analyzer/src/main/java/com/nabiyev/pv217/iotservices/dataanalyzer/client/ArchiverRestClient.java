package com.nabiyev.pv217.iotservices.dataanalyzer.client;

import com.nabiyev.pv217.iotservices.dataanalyzer.data.Measurement;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/measurements")
@RegisterRestClient
public interface ArchiverRestClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Measurement> getMeasurements(@QueryParam("name") String name, @QueryParam("from") String fromStr,
                                             @QueryParam("to") String toStr, @QueryParam("controlCenterId") String controlCenterId);
}
