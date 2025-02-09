package by.smertex.core.service.impl;

import by.smertex.core.dto.event.EmailNotificationEvent;
import by.smertex.core.dto.input.SecurityUserDto;
import by.smertex.core.exception.KafkaResponseException;
import by.smertex.core.mapper.Mapper;
import by.smertex.core.service.SendCodeService;
import by.smertex.core.util.Cryptographer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class SendCodeServiceImpl implements SendCodeService {

    private final KafkaTemplate<String, EmailNotificationEvent> kafkaTemplate;

    private final Mapper<EmailNotificationEvent, SecurityUserDto> emailNotificationEventSecurityUserDtoMapper;

    private final Cryptographer cryptographer;

    @Override
    public String redirect(SecurityUserDto securityUserDto) {
        sendCode(securityUserDto);
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/security/{secret}")
                .buildAndExpand(cryptographer.encrypt(
                        securityUserDto.email())
                )
                .toUriString();
    }

    @Override
    public void sendCode(SecurityUserDto dto) {
        kafkaTemplate.send("email-notification-event-topic", null, emailNotificationEventSecurityUserDtoMapper.map(dto))
                .exceptionally(exception -> {
                    throw new KafkaResponseException(exception.getMessage());
                });
    }
}
