package com.randakm.pv217.iotservices.datagenerator.core;

public class ReactivePowerCalculator {

  private static final double POWER_FACTOR = 0.85;

  public Float measureReactivepower(Float activePower) {
    if(activePower == null) throw new IllegalArgumentException("Active power is null!");
    if(activePower.isNaN()) throw new IllegalArgumentException("Active power is NaN!");
    if(activePower.isInfinite()) throw new IllegalArgumentException("Active power is Infinite!");
    
    var value = (float) (activePower * Math.tan(Math.acos(POWER_FACTOR)));
    return value;
  }
}
