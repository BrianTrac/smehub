package vn.test.hub.core.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import vn.test.hub.core.kafka.EventMessage;

import java.io.DataInput;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final EventHandlerRegistry registry;

    public void consumeMessage(ConsumerRecord<String, EventMessage> record) {
        try {
            EventHandler handler = registry.getHandler(record.value().getEventType());

            if (handler != null) {
                handler.handle(record);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
