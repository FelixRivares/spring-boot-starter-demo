package ru.sample.synth.core.audit.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class AuditEvent {

    private String time;
    private String method;
    private Map<String, Param> params;
    private MethodExecutionState state;

    public record Param(String type, Object value) {
    }

    public enum MethodExecutionState {

        SUCCESS, EXCEPTION

    }

}
