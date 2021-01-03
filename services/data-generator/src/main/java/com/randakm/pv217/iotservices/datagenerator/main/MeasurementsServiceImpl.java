package com.randakm.pv217.iotservices.datagenerator.main;

import com.randakm.pv217.iotservices.datagenerator.core.Measurement;
import com.randakm.pv217.iotservices.datagenerator.core.MeasurementGenerator;
import com.randakm.pv217.iotservices.datagenerator.core.MeasurementGeneratorNormalImpl;
import com.randakm.pv217.iotservices.datagenerator.core.MeasurementGeneratorNormalImplConfig;
import com.randakm.pv217.iotservices.datagenerator.core.ReactivePowerCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MeasurementsServiceImpl implements MeasurementsService {
  private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementsServiceImpl.class);

  private List<MeasurementGenerator> generators;
  private long seed;
  private boolean initialized = false;

  @PostConstruct
  public void init() {
    LOGGER.debug("Initializing!");
    generators = new ArrayList<>();

    seed = new Random().nextLong();

    addGenerator("freq", 0f, 200f);
    addGenerator("MW", Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
    addGenerator("TemperFahr", -459f, Float.POSITIVE_INFINITY);

    initialized = true;
  }

  private void addGenerator(String name, float min, float max) {
    Config cfg = ConfigProvider.getConfig();

    String keyBase = "measurement." + name.toLowerCase() + ".";

    addGenerator(name, cfg.getValue(keyBase + "mean", Float.class), cfg.getValue(keyBase + "dev", Float.class), min,
        max, cfg.getValue(keyBase + "err", Float.class));

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
  @Counted(name = "generateMeasurementsCount", description = "Number of measurements generation done")
  @Timed(name = "generateMeasurementsTime", description = "A measure of how long it takes to perform measurements generation", unit = MetricUnits.MILLISECONDS)
  public List<Measurement> generateMeasurements() {
    List<Measurement> list = new ArrayList<>();
    for (var mg : generators) {
      try {
        list.add(mg.generate());
      } catch (Exception e) {
        LOGGER.debug("Failed generating value");
      }
    }

    addDependentMeasurements(list);

    return list;
  }

  private void addDependentMeasurements(List<Measurement> list) {
    var mw = list.stream().filter((m) -> m.getName().equals("MW")).findFirst();
    if (mw.isPresent()) {
      var mvar = new Measurement();
      mvar.setName("MVAR");
      mvar.setTimestamp(mw.get().getTimestamp());
      mvar.setValue(new ReactivePowerCalculator().measureReactivepower(mw.get().getValue()));
      list.add(mvar);
    }
  }

  @Override
  public boolean isInitialized() {
    return initialized;
  }

}
