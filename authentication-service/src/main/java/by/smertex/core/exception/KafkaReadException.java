package by.smertex.core.exception;

import org.springframework.kafka.KafkaException;

public class KafkaReadException extends KafkaException {
    public KafkaReadException(String message) {
        super(message);
    }
}
