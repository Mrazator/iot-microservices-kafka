package com.randakm.pv217.iotservices.dataarchiver.core;

import com.randakm.pv217.iotservices.dataarchiver.data.Measurement;

import java.time.Instant;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class MeasurementRepoImpl implements MeasurementRepo {

  private final EntityManager entityManager;

  /**
   * @param entityManager
   */
  public MeasurementRepoImpl(EntityManager entityManager) {
    super();
    this.entityManager = entityManager;
  }

  @Override
  public void addMeasurement(Measurement m) {
    entityManager.persist(m);
  }

  @Override
  public List<Measurement> findMeasurements() {
    return entityManager.createQuery("SELECT m FROM ", Measurement.class).getResultList();
  }

  @Override
  public List<Measurement> findMeasurements(String name, Instant from, Instant to) {
    TypedQuery<Measurement> query = entityManager.createQuery(
        "FROM Measurement WHERE name LIKE :name AND measuredAt <= :from AND measuredAt < :to", Measurement.class);
    query.setParameter("name", name);
    query.setParameter("from", from);
    query.setParameter("to", to);

    return query.getResultList();
  }

}
