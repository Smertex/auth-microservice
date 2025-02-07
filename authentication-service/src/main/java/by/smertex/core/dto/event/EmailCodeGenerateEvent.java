package by.smertex.core.dto.event;

public record EmailCodeGenerateEvent(String email,
                                     String code) {
}
