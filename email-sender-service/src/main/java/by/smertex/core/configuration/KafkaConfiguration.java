package by.smertex.core.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfiguration {
    NewTopic createTopic() {
        return TopicBuilder
                .name("email-code-generate-events-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of(
                        "min.insync.replicas", "2"
                ))
                .build();
    }
}
