package by.smertex.core.service.impl;

import by.smertex.core.dto.event.EmailCodeGenerateEvent;
import by.smertex.core.dto.event.EmailNotificationEvent;
import by.smertex.core.exception.KafkaResponseException;
import by.smertex.core.service.SendCodeService;
import by.smertex.core.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendCodeServiceImpl implements SendCodeService {

    private final KafkaTemplate<String, EmailCodeGenerateEvent> kafkaTemplate;

    private final CodeGenerator codeGenerator;

    @Lazy
    private SendCodeService sendCodeService;

    @Override
    public void sendToEmail(EmailNotificationEvent event) {
        String code = codeGenerator.generate();
        log.info("Sending {} code {}", event.email(), code);
        sendCodeService.sendToSecurityService(new EmailCodeGenerateEvent(event.email(), code));
    }

    @Override
    @Transactional
    public void sendToSecurityService(EmailCodeGenerateEvent event) {
        kafkaTemplate.send("email-code-generate-events-topic", null, event)
                .exceptionally(exception -> {
                    throw new KafkaResponseException(exception.getMessage());
                });
    }
}
