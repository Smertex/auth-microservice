package by.smertex.core.service.impl;

import by.smertex.core.database.repository.EmailCodeRepository;
import by.smertex.core.dto.input.SecurityEmailCode;
import by.smertex.core.exception.VerificationException;
import by.smertex.core.service.VerificationService;
import by.smertex.core.util.Cryptographer;
import by.smertex.core.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final EmailCodeRepository emailCodeRepository;

    private final Cryptographer cryptographer;

    private final JwtUtil jwtUtil;

    @Override
    public String generateToken(String hiddenEmail, SecurityEmailCode dto) {
        return emailCodeRepository.findByEmail(cryptographer.decrypt(hiddenEmail))
                .filter(emailCode ->
                        emailCode.getCode().equals(dto.code())
                )
                .map(emailCode -> {
                    emailCodeRepository.remove(emailCode.getEmail());
                    return jwtUtil.generateToken(emailCode.getEmail());
                })
                .orElseThrow(() -> new VerificationException("Email incorrect or code not found"));
    }
}
