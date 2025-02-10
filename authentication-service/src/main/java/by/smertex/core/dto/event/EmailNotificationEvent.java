package by.smertex.core.dto.event;

import lombok.Builder;

@Builder
public record EmailNotificationEvent(String email) {
}
