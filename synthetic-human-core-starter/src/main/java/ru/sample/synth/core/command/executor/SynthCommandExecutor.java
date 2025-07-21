package ru.sample.synth.core.command.executor;

import ru.sample.synth.core.command.model.SynthCommand;

public interface SynthCommandExecutor {

    void execute(SynthCommand synthCommand);

}
