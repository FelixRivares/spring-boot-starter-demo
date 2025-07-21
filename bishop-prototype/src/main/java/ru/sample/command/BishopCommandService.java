package ru.sample.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sample.api.model.CommandType;
import ru.sample.api.model.Initiator;
import ru.sample.command.exception.UnavailableCommandException;
import ru.sample.synth.core.audit.aspect.WaylandWatchingYou;
import ru.sample.synth.core.command.model.SynthCommand;
import ru.sample.synth.core.command.model.SynthCommandPriority;
import ru.sample.synth.core.command.service.CommandService;
import ru.sample.synth.core.command.service.exception.ExecutionQueueIsFullException;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class BishopCommandService {

  private final CommandService commandService;

  @WaylandWatchingYou
  public void runCommand(CommandType commandType, Initiator initiator) throws UnavailableCommandException, ExecutionQueueIsFullException {
    final var synthCommandBuilder = SynthCommand.builder();
    synthCommandBuilder.description(chooseDescription(commandType));
    synthCommandBuilder.author(initiator.name());
    synthCommandBuilder.priority(choosePriority(initiator));
//    synthCommandBuilder.time("не время");
    synthCommandBuilder.time(DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
    commandService.processCommand(synthCommandBuilder.build());
  }

  private String chooseDescription(CommandType commandType) throws UnavailableCommandException {
    return switch (commandType) {
      case ALERT -> "ALERT! ALERT! ALERT!";
      case HELP -> "Try to help...";
      case KILL -> throw new UnavailableCommandException("I cannot killed yet... May be another time...");
      case ENGINE -> "Processing engine... Try to fix...";
    };
  }

  private SynthCommandPriority choosePriority(Initiator initiator) {
    return switch (initiator) {
      case REGULAR_HUMAN -> SynthCommandPriority.COMMON;
      case WEYLAND_YUTANI_OFFICER -> SynthCommandPriority.CRITICAL;
    };
  }

}
