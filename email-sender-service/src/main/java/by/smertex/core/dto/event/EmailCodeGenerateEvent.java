package by.smertex.core.dto.event;

import lombok.Builder;

@Builder
public record EmailCodeGenerateEvent(String email,
                                     String code) {
}
