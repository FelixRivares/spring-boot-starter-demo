package ru.sample.synth.core.audit.service;

import ru.sample.synth.core.audit.model.AuditEvent;

public interface AuditService {

    void audit(AuditEvent event);

}
