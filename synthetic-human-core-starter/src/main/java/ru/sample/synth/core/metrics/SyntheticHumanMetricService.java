package ru.sample.synth.core.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class SyntheticHumanMetricService {

  private static final String CURRENT_ANDROID_BUSYNESS_METRIC_NAME = "current_android_busyness";
  private static final String CURRENT_ANDROID_BUSYNESS_METRIC_DESCRIPTION = "current android task queue size";
  private static final String CURRENT_ANDROID_PROCEED_TASKS_METRIC_NAME = "current_android_proceed_tasks";
  private static final String CURRENT_ANDROID_PROCEED_TASKS_METRIC_DESCRIPTION = "current android proceed tasks by author";
  private static final String CURRENT_ANDROID_PROCEED_TASKS_METRIC_LABEL = "author";

  private final AtomicLong currentAndroidBusynessConnectedValue = new AtomicLong();
  private final MeterRegistry meterRegistry;
  private final Map<String, Counter> counterMap = new HashMap<>();

  @PostConstruct
  public void registerMetrics() {
    Gauge.builder(CURRENT_ANDROID_BUSYNESS_METRIC_NAME, currentAndroidBusynessConnectedValue::get)
        .description(CURRENT_ANDROID_BUSYNESS_METRIC_DESCRIPTION)
        .register(meterRegistry);
  }

  public void publishQueueSizeMetric(int queueSize) {
    currentAndroidBusynessConnectedValue.set(queueSize);
  }

  public void publishProceedTasksMetric(String author) {
    counterMap.computeIfAbsent(author, counter ->
            Counter.builder(CURRENT_ANDROID_PROCEED_TASKS_METRIC_NAME)
                .description(CURRENT_ANDROID_PROCEED_TASKS_METRIC_DESCRIPTION)
                .tag(CURRENT_ANDROID_PROCEED_TASKS_METRIC_LABEL, author)
                .register(meterRegistry))
        .increment();
  }

}
