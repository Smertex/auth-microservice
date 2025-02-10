package by.smertex.core.mapper.impl;

import by.smertex.core.dto.event.EmailNotificationEvent;
import by.smertex.core.dto.input.SecurityUserDto;
import by.smertex.core.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SecurityEmailCodeMapper implements Mapper<EmailNotificationEvent, SecurityUserDto> {
    @Override
    public EmailNotificationEvent map(SecurityUserDto from) {
        return EmailNotificationEvent.builder()
                .email(from.email())
                .build();
    }
}
