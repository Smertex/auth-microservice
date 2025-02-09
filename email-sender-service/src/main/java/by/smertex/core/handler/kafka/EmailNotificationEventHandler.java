package by.smertex.core.handler.kafka;

import by.smertex.core.dto.event.EmailNotificationEvent;
import by.smertex.core.service.SendCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailNotificationEventHandler {

    private final SendCodeService sendCodeService;

    @KafkaListener(topics = "email-notification-event-topic")
    public void receive(EmailNotificationEvent event){
        sendCodeService.sendToEmail(event);
    }
}
