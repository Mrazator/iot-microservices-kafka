package com.randakm.pv217.iotservices.dataarchiver.core;

import com.randakm.pv217.iotservices.dataarchiver.data.ControlCenter;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ControlCenterRepoImpl implements ControlCenterRepo {
  private final EntityManager entityManager;

  /**
   * @param entityManager
   */
  public ControlCenterRepoImpl(EntityManager entityManager) {
    super();
    this.entityManager = entityManager;
  }

  @Override
  public void addControlCenter(ControlCenter cc) {
    entityManager.persist(cc);    
  }

  @Override
  public ControlCenter findById(String id) {
    return entityManager.find(ControlCenter.class, id);    
  }
  
  
  
}
