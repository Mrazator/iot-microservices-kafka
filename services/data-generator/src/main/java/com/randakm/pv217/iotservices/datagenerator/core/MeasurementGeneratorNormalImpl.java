package com.randakm.pv217.iotservices.datagenerator.core;

import java.util.Random;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.RandomGeneratorFactory;

public class MeasurementGeneratorNormalImpl implements MeasurementGenerator {

  private static final int MAX_TRIES = 1000;
  private final MeasurementGeneratorNormalImplConfig config;
  private final Random random;
  private final NormalDistribution dist;

  /**
   * @param mean
   * @param deviation
   * @param min
   * @param max
   * @param errorRate
   */
  public MeasurementGeneratorNormalImpl(long seed, MeasurementGeneratorNormalImplConfig config) {
    if(config == null) throw new IllegalArgumentException("Config muse provided!");
    if(config.getMin() > config.getMax()) throw new IllegalArgumentException("Config min must be lower then max!");
    
    this.config = config;
    this.random = new Random(seed);
    this.dist = new NormalDistribution(RandomGeneratorFactory.createRandomGenerator(random), config.getMean(),
        config.getDeviation());

  }

  @Override
  public Measurement generate() {
    float f = random.nextFloat();
    if (f < config.getErrorRate()) {
      throw new RuntimeException("Measurement error simulation!");
    }

    Float value = null;
    int tries = 0;
    while (!isValid(value)) {
      if (tries > MAX_TRIES) {
        throw new RuntimeException("Could not generate valid value in specified limit");
      }
      value = Float.valueOf((float) dist.sample());
      tries++;
    }

    Measurement m = new Measurement();
    m.setName(config.getName());
    m.setValue(value);
    m.setTimestamp(System.currentTimeMillis());
    
    return m;
  }

  private boolean isValid(Float value) {
    if (value == null)
      return false;
    if (value < config.getMin())
      return false;
    if (value > config.getMax())
      return false;

    return true;
  }

}
