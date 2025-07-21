package ru.sample.synth.core.audit.service.console;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.sample.synth.core.audit.model.AuditEvent;
import ru.sample.synth.core.audit.service.AuditService;

@Slf4j
@Service
@ConditionalOnProperty(name = "synth.core.audit.mode", havingValue = "CONSOLE", matchIfMissing = true)
public class ConsoleAuditService implements AuditService {

    @Override
    public void audit(AuditEvent event) {
        log.info("""
                        Audit event:
                        \tTime - {}
                        \tMethod- {}
                        \tParameters - {}
                        \tState - {}""",
                event.getTime(),
                event.getMethod(),
                event.getParams(),
                event.getState());
    }

}
