package by.smertex.core.dto.input;

import jakarta.validation.constraints.Size;

public record SecurityEmailCode(
        @Size(min = 6, max = 6) String code
) {
}
