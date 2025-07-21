package ru.sample.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sample.api.model.CommandType;
import ru.sample.api.model.Initiator;
import ru.sample.command.BishopCommandService;
import ru.sample.command.exception.UnavailableCommandException;
import ru.sample.synth.core.command.service.exception.ExecutionQueueIsFullException;

@RestController
@RequestMapping("/command")
@RequiredArgsConstructor
public class BishopApiV1 {

  private final BishopCommandService commandService;

  @PostMapping
  public void processCommand(
      @RequestParam CommandType commandType,
      @RequestParam Initiator initiator) throws UnavailableCommandException, ExecutionQueueIsFullException {

    commandService.runCommand(commandType, initiator);
  }

}
