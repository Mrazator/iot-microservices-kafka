package com.randakm.pv217.iotservices.dataarchiver.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class DBConnectionCheck implements HealthCheck {

  @Inject
  private EntityManager entityManager;

  @Override
  public HealthCheckResponse call() {
    boolean state = true;
  if (entityManager == null || !entityManager.isOpen()) {
    state = false;
  }

  return HealthCheckResponse.builder().name("Custom DB Connection").state(state).build();
  }
}
