package com.nabiyev.pv217.iotservices.dataanalyzer.kafka;

import com.nabiyev.pv217.iotservices.dataanalyzer.data.AggregatedMeasurement;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEndpoint {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEndpoint.class);

  @Channel("measurements-analyzed-out")
  public Emitter<List<AggregatedMeasurement>> channel;

  public void pushToKafka(List<AggregatedMeasurement> analyzed) {
    LOGGER.debug("Sending analyzed measurements to kakfa!");
    channel.send(analyzed);
  }

}
