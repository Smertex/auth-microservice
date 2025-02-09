package by.smertex.api.controller.impl;

import by.smertex.api.controller.AuthController;
import by.smertex.core.dto.input.SecurityEmailCode;
import by.smertex.core.dto.input.SecurityUserDto;
import by.smertex.core.service.SendCodeService;
import by.smertex.core.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
@Validated
public class AuthControllerImpl implements AuthController {

    private final SendCodeService sendCodeService;

    private final VerificationService verificationService;

    @PostMapping("/get-access-code")
    public ResponseEntity<String> sendCodeForAccess(@RequestBody SecurityUserDto dto) {
        return ResponseEntity.ok()
                .body(sendCodeService.redirect(dto));
    }

    @PostMapping("/{hiddenEmail}")
    public ResponseEntity<String> generateToken(@PathVariable String hiddenEmail,
                                                @RequestBody SecurityEmailCode dto) {
        return ResponseEntity.ok()
                .body(verificationService.generateToken(hiddenEmail, dto));
    }
}

