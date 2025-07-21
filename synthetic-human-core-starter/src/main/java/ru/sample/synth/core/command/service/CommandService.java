package ru.sample.synth.core.command.service;

import ru.sample.synth.core.command.model.SynthCommand;
import ru.sample.synth.core.command.service.exception.ExecutionQueueIsFullException;

public interface CommandService {

    void processCommand(SynthCommand command) throws ExecutionQueueIsFullException;

}
