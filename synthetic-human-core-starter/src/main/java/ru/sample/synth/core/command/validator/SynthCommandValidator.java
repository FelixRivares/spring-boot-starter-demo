package ru.sample.synth.core.command.validator;

import ru.sample.synth.core.command.model.SynthCommand;

public interface SynthCommandValidator {

    void validate(SynthCommand command);

}
