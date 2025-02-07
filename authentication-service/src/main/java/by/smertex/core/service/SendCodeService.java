package by.smertex.core.service;

import by.smertex.core.dto.input.SecurityUserDto;

public interface SendCodeService {
    String redirect(SecurityUserDto securityUserDto);

    void sendCode(SecurityUserDto dto);
}
