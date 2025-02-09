package by.smertex.core.util;

import by.smertex.core.exception.HideEmailDecryptException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class Cryptographer {

    @Value("${url.secret}")
    private String secret;

    public String encrypt(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ secret.charAt(i % secret.length())));
        }
        return Base64.getUrlEncoder().withoutPadding().encodeToString(output.toString().getBytes());
    }

    public String decrypt(String input) {
        try {
            byte[] decodedBytes = Base64.getUrlDecoder().decode(input);
            String decodedString = new String(decodedBytes);

            StringBuilder output = new StringBuilder();
            for (int i = 0; i < decodedString.length(); i++) {
                output.append((char) (decodedString.charAt(i) ^ secret.charAt(i % secret.length())));
            }
            return output.toString();
        } catch (Exception e) {
            throw new HideEmailDecryptException(e.getMessage());
        }
    }
}
