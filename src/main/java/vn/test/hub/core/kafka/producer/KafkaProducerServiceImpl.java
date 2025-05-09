package vn.test.hub.core.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vn.test.hub.core.kafka.EventMessage;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final KafkaTemplate<String, EventMessage> kafkaTemplate;


    @Override
    public void sendMessage(String topic, EventMessage message) {
        try {
            kafkaTemplate.send(topic, message);
            log.info("Sent message to topic: {}, value: {}", topic, message);

        } catch (Exception e) {
            log.error("Failed to send message", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMessage(String topic, String key, EventMessage message) {
        try {
            kafkaTemplate.send(topic, key, message);
            log.info("Sent message to topic: {}, key: {}, value: {}", topic, key, message);
        } catch (Exception e) {
            log.error("Failed to send message", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMessage(String topic, String key, EventMessage message, int partition) {
        try {
            kafkaTemplate.send(topic, partition, key, message);
            log.info("Sent message to topic: {}, key: {}, value: {}, partition: {}", topic, key, message, partition);
        } catch (Exception e) {
            log.error("Failed to send message", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendMessage(String topic, String key, EventMessage message, int partition, long timestamp) {
        try {
            kafkaTemplate.send(topic, partition, timestamp, key, message);
            log.info("Sent message to topic: {}, key: {}, value: {}, partition: {}, timestamp: {}", topic, key, message, partition, timestamp);
        } catch (Exception e) {
            log.error("Failed to send message", e);
            throw new RuntimeException(e);
        }
    }
}
