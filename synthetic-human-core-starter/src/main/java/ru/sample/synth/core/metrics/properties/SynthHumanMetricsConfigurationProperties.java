package ru.sample.synth.core.metrics.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ConfigurationProperties("synth.core.metrics")
@Component("synthHumanProperties")
@Data
public class SynthHumanMetricsConfigurationProperties {

  @NotNull
  @Valid
  private BusynessMetric busyness = new BusynessMetric();

  @Data
  public static class BusynessMetric {

    @NotNull
    private Duration initialDelay = Duration.of(1, ChronoUnit.SECONDS);
    @NotNull
    private Duration fixedDelay = Duration.of(1, ChronoUnit.SECONDS);

  }

}
