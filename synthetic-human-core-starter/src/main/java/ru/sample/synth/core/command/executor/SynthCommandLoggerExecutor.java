package ru.sample.synth.core.command.executor;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.sample.synth.core.command.model.SynthCommand;
import ru.sample.synth.core.metrics.SyntheticHumanMetricService;

@Slf4j
@Component
@RequiredArgsConstructor
public class SynthCommandLoggerExecutor implements SynthCommandExecutor {

  private final SyntheticHumanMetricService metricService;

  @Override
  @SneakyThrows
  public void execute(SynthCommand synthCommand) {
    Thread.sleep(1_000);
    log.info("""
            Start executing command...
            \tCommand from - {}
            \tregistered at - {}
            \tdescription - {}
            \tpriority - {}
            End executing command...""",
        synthCommand.getAuthor(),
        synthCommand.getTime(),
        synthCommand.getDescription(),
        synthCommand.getPriority());
    metricService.publishProceedTasksMetric(synthCommand.getAuthor());
  }

}
