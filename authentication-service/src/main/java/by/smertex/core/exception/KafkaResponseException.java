package by.smertex.core.exception;

import org.apache.kafka.common.KafkaException;

public class KafkaResponseException extends KafkaException {
    public KafkaResponseException(String message) {
        super(message);
    }
}
