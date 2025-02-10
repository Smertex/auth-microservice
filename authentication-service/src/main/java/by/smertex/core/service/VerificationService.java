package by.smertex.core.service;

import by.smertex.core.dto.input.SecurityEmailCode;

public interface VerificationService {
    String generateToken(String hiddenEmail, SecurityEmailCode dto);
}
