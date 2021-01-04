package com.randakm.pv217.iotservices.datagenerator.health;

import com.randakm.pv217.iotservices.datagenerator.main.MeasurementsService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class DBConnectionCheck implements HealthCheck {

  @Inject
  private MeasurementsService generatorService;

  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse.builder().name("Measurements generator initialized")
        .state(generatorService.isInitialized()).build();
  }
}
