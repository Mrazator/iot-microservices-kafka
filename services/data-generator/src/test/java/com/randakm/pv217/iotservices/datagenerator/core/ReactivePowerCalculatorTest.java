package com.randakm.pv217.iotservices.datagenerator.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ReactivePowerCalculatorTest {

  @Test
  public void testIllegalInput() {
    var calculator = new ReactivePowerCalculator();
    Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.measureReactivepower(null));
    Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.measureReactivepower(Float.NaN));
    Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.measureReactivepower(Float.POSITIVE_INFINITY));
    Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.measureReactivepower(Float.NEGATIVE_INFINITY));
  }
  
  @Test
  public void testNormalInput() {
    var calculator = new ReactivePowerCalculator();
    Assertions.assertEquals(61.974434f, calculator.measureReactivepower(100f));
  }
}
