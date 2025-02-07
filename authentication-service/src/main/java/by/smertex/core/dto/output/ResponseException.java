package by.smertex.core.dto.output;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResponseException(int status,
                                String message,
                                LocalDateTime errorTime) {
}
