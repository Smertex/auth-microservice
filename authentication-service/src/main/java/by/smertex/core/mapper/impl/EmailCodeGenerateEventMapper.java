package by.smertex.core.mapper.impl;

import by.smertex.core.database.model.impl.EmailCode;
import by.smertex.core.dto.event.EmailCodeGenerateEvent;
import by.smertex.core.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class EmailCodeGenerateEventMapper implements Mapper<EmailCode, EmailCodeGenerateEvent> {
    @Override
    public EmailCode map(EmailCodeGenerateEvent from) {
        return EmailCode.builder()
                .code(from.code())
                .email(from.email())
                .build();
    }
}
