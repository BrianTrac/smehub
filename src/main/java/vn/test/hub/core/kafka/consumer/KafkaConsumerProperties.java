package vn.test.hub.core.kafka.consumer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@ConditionalOnProperty(name = "kafka.consumer.enabled", havingValue = "true", matchIfMissing = false)
@ConfigurationProperties(prefix = "kafka.consumer")
@Getter
@Setter
public class KafkaConsumerProperties {
    private List<ConsumerConfig> consumers = new ArrayList<>();

    @Getter
    @Setter
    @Builder
    public static class ConsumerConfig {
        @NotBlank
        private String topic;

        @NotBlank
        private String groupId;

        @Min(1)
        private int concurrency = 1;
    }
}
