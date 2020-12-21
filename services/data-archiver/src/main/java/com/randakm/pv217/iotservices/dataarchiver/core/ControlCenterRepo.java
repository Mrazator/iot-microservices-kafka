package com.randakm.pv217.iotservices.dataarchiver.core;

import com.randakm.pv217.iotservices.dataarchiver.data.ControlCenter;

public interface ControlCenterRepo {
  void addControlCenter(ControlCenter cc);

  ControlCenter findById(String id);
}
