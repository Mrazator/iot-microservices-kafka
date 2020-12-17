package com.randakm.pv217.iotservices.datagenerator.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MeasurementGeneratorNormalImplTest {

  @BeforeEach
  public void init() {

  }

  @Test
  public void testNullConfig() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> new MeasurementGeneratorNormalImpl(1l, null));
  }

  @Test
  public void testInvalidConfig() {
    var cfg = new MeasurementGeneratorNormalImplConfig();
    cfg.setMin(50f);
    cfg.setMax(40f);

    Assertions.assertThrows(IllegalArgumentException.class, () -> new MeasurementGeneratorNormalImpl(1l, cfg));
  }

  @Test
  public void testRange() {
    var cfg = new MeasurementGeneratorNormalImplConfig();
    cfg.setMean(0f);
    cfg.setDeviation(1000f);
    cfg.setMin(-10f);
    cfg.setMax(10f);

    var generator = new MeasurementGeneratorNormalImpl(1l, cfg);

    for (int i = 0; i < 1000; i++) {
      var measurement = generator.generate();
      Assertions.assertTrue(measurement.getValue() >= cfg.getMin() && measurement.getValue() <= cfg.getMax());
    }
  }
  
  @Test
  public void testErrorRate() {
    var cfg = new MeasurementGeneratorNormalImplConfig();
    cfg.setMean(0f);
    cfg.setDeviation(10f);
    cfg.setErrorRate(0.2f);

    var generator = new MeasurementGeneratorNormalImpl(1l, cfg);

    int errs = 0;
    int tries = 10000;
    for (int i = 0; i < tries; i++) {
      try {
        generator.generate();
      } catch(Exception e) {
        errs++;
      }
    }
    
    float errRate = errs*1f/tries;
    Assertions.assertEquals(cfg.getErrorRate(), errRate, 0.001f);    
  }
}
