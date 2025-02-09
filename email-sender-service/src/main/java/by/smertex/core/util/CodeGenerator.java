package by.smertex.core.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class CodeGenerator {

    @Value("${email-code.pattern}")
    private String pattern;

    @Value("${email-code.length}")
    private int length;

    private final Random random;

    public String generate() {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(pattern.charAt(random.nextInt(pattern.length())));
        }
        return sb.toString();
    }
}
