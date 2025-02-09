package by.smertex.core.handler.kafka;

import by.smertex.core.database.model.impl.EmailCode;
import by.smertex.core.database.repository.EmailCodeRepository;
import by.smertex.core.dto.event.EmailCodeGenerateEvent;
import by.smertex.core.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailCodeGenerateEventHandler {

    private final EmailCodeRepository emailCodeRepository;

    private final Mapper<EmailCode, EmailCodeGenerateEvent> emailCodeGenerateEventMapper;

    @KafkaListener(topics = "email-code-generate-events-topic")
    public void receive(EmailCodeGenerateEvent event){
        emailCodeRepository.save(
                emailCodeGenerateEventMapper.map(event)
        );
        log.info("Message with code saved");
    }
}
