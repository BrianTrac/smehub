package vn.test.hub.core.kafka.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import vn.test.hub.core.kafka.EventMessage;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "kafka.consumer.enabled", havingValue = "true", matchIfMissing = false)
public class KafkaConsumerConfig implements InitializingBean {
    private final KafkaConsumerProperties kafkaConsumerProperties;
    private final KafkaConsumerService kafkaConsumerService;

    @Value("${kafka.bootstrap-servers:localhost:9092}")
    private String bootstrapServers;

    @Override
    public void afterPropertiesSet() {
        kafkaConsumerProperties.getConsumers().forEach(consumerConfig -> {
            // Create factory with configuration
            ConcurrentKafkaListenerContainerFactory<String, EventMessage> factory =
                    new ConcurrentKafkaListenerContainerFactory<>();

            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
            props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerConfig.getGroupId());

            // Configure JsonDeserializer to trust specific package(s)
            JsonDeserializer<EventMessage> deserializer = new JsonDeserializer<>(EventMessage.class);
            deserializer.addTrustedPackages("*");

            // Update consumer factory to use the configured deserializer
            factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
                    props,
                    new StringDeserializer(),
                    deserializer
            ));

            factory.setConcurrency(consumerConfig.getConcurrency());

            // Create and start container
            ConcurrentMessageListenerContainer<String, EventMessage> container =
                    factory.createContainer(consumerConfig.getTopic());
            container.getContainerProperties().setMessageListener(
                    (MessageListener<String, EventMessage>) kafkaConsumerService::consumeMessage);
            container.start();
        });
    }
}
