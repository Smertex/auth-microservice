package by.smertex.api.controller.impl;

import by.smertex.api.controller.TestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-controller")
@RequiredArgsConstructor
public class TestControllerImpl implements TestController {
    @GetMapping
    public ResponseEntity<String> verificationAuthentication() {
        return ResponseEntity.ok("Authentication successful. User in system");
    }
}
