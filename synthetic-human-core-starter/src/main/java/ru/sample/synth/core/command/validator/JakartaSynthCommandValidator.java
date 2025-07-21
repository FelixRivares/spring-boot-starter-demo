package ru.sample.synth.core.command.validator;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sample.synth.core.command.model.SynthCommand;

@Component
@RequiredArgsConstructor
public class JakartaSynthCommandValidator implements SynthCommandValidator {

    private final Validator validator;

    @Override
    public void validate(SynthCommand command) {
        final var violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
