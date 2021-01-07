package com.nabiyev.pv217.iotservices.dataanalyzer.core;

import com.nabiyev.pv217.iotservices.dataanalyzer.client.ArchiverRestClient;
import com.nabiyev.pv217.iotservices.dataanalyzer.data.AggregatedMeasurement;
import com.nabiyev.pv217.iotservices.dataanalyzer.data.Measurement;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class AggregationService {

    @Inject
    @RestClient
    public ArchiverRestClient archiveClient;

    @Retry(maxRetries = 1)
    public List<AggregatedMeasurement> calculateAvarages(String name, String controlCenterId,
                                                         Instant from, Instant to, ChronoUnit unit) {

        // get the source data
        List<Measurement> measurements = getMeasurements(name, controlCenterId, from, to);

        // clean interval range
        from = from.truncatedTo(unit);
        to = to.truncatedTo(unit).plus(1, unit);

        // divide measurements inside time-frame buckets
        Map<Instant, List<Measurement>> map = new HashMap<>();
        for(Measurement m : measurements) {
            Instant intervalStart = m.measuredAt.truncatedTo(unit);
            if(!map.containsKey(intervalStart)) {
                map.put(intervalStart, new ArrayList<>());
            }
            map.get(intervalStart).add(m);
        }

        // create aggregated objects
        List<AggregatedMeasurement> aggregated = new ArrayList<>();
        for(Instant inst : map.keySet()) {
            AggregatedMeasurement agg = createAvgOf(name, inst, unit, controlCenterId, map.get(inst));
            aggregated.add(agg);
        }

        return aggregated;
    }

    private AggregatedMeasurement createAvgOf(String name, Instant intervalStart, ChronoUnit unit,
                                              String controlCenterId, List<Measurement> measurements) {
        AggregatedMeasurement agg = new AggregatedMeasurement();
        agg.intervalStart = intervalStart;
        agg.intervalEnd = intervalStart.plus(1, unit);
        agg.name = name;
        agg.controlCenterId = controlCenterId;
        agg.value = measurements.stream().mapToDouble((m) -> m.value).average().getAsDouble();
        return agg;
    }

    private List<Measurement> getMeasurements(String name, String controlCenterId, Instant from, Instant to) {
        return archiveClient.getMeasurements(name, from.toString(), to.toString(), controlCenterId);
    }
}
