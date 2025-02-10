package by.smertex.api.controller;

import by.smertex.core.dto.input.SecurityEmailCode;
import by.smertex.core.dto.input.SecurityUserDto;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<String> sendCodeForAccess(SecurityUserDto dto);

    ResponseEntity<String> generateToken(String secret, SecurityEmailCode dto);
}
