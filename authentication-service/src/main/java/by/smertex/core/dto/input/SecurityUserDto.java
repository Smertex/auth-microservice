package by.smertex.core.dto.input;

import by.smertex.core.util.Templates;
import jakarta.validation.constraints.Pattern;

public record SecurityUserDto(
        @Pattern(regexp = Templates.EMAIL_PATTERN) String email
) {
}
