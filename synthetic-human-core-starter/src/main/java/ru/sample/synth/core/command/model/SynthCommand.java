package ru.sample.synth.core.command.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SynthCommand {

    @Size(max = 1000)
    @NotBlank
    private String description;
    private SynthCommandPriority priority;
    @Size(max=100)
    @NotBlank
    private String author;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}(T\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?(Z|[+-]\\d{2}:\\d{2})?)?$")
    private String time;

}
