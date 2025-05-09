package vn.test.hub.core.kafka.producer;

import vn.test.hub.core.kafka.EventMessage;

public interface KafkaProducerService {
    void sendMessage(String topic, EventMessage message);

    void sendMessage(String topic, String key, EventMessage message);

    void sendMessage(String topic, String key, EventMessage message, int partition);

    void sendMessage(String topic, String key, EventMessage message, int partition, long timestamp);
}
