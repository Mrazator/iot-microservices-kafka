package com.randakm.pv217.iotservices.datagenerator.main;

import com.randakm.pv217.iotservices.datagenerator.core.Measurement;
import com.randakm.pv217.iotservices.datagenerator.core.MeasurementGenerator;
import com.randakm.pv217.iotservices.datagenerator.core.MeasurementGeneratorNormalImpl;
import com.randakm.pv217.iotservices.datagenerator.core.MeasurementGeneratorNormalImplConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DataGeneratorImpl implements DataGeneratorService {

  private List<MeasurementGenerator> generators;
  private long seed;
  
  @PostConstruct
  public void init() {
    System.out.println("Initializing!");
    generators = new ArrayList<>();
    
    seed = new Random().nextLong();
    
    addGenerator("Frequency", 60f, 10f, 0f, 200f, 0.2f);
    addGenerator("ActivePower", 100f, 100f, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, 0.01f);
    addGenerator("Reactive", 100f, 100f, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, 0.01f);
    addGenerator("DeviceTemperatureFahr", 77f, 10f, -459f, Float.POSITIVE_INFINITY, 0.05f);
  }
  
  private void addGenerator(String name, float mean, float dev, float min, float max, float err) {
    MeasurementGeneratorNormalImplConfig cfg = new MeasurementGeneratorNormalImplConfig();
    cfg.setName(name);
    cfg.setMean(mean);
    cfg.setDeviation(dev);
    cfg.setMin(min);
    cfg.setMax(max);
    cfg.setErrorRate(err);
    
    generators.add(new MeasurementGeneratorNormalImpl(seed, cfg));
    
  }

  @Override
  public List<Measurement> generateMeasurements() {
    List<Measurement> list = new ArrayList<>();
    for(var mg : generators) {
      try {
        list.add(mg.generate());
      } catch(Exception e) {
        System.out.println("Failed generating value");
        e.printStackTrace();//TODO logging
      }
    }
    
    return list;
  }

}
