package by.smertex.core.service;

import by.smertex.core.dto.event.EmailCodeGenerateEvent;
import by.smertex.core.dto.event.EmailNotificationEvent;

public interface SendCodeService {
    void sendToEmail(EmailNotificationEvent event);

    void sendToSecurityService(EmailCodeGenerateEvent event);
}
