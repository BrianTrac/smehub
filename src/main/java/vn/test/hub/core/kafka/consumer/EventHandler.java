package vn.test.hub.core.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import vn.test.hub.core.kafka.EventMessage;

public interface EventHandler {
    void handle(ConsumerRecord<String, EventMessage> record);
}
