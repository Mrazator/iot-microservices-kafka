package com.randakm.pv217.iotservices.dataarchiver.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class KafkaConnectionCheck implements HealthCheck {
  
  @Override
  public HealthCheckResponse call() {
    boolean state = false;
    return HealthCheckResponse.builder().name("Custom Kafka Connection").state(state).build();
  }
}
