package vn.test.hub.core.kafka.consumer;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventHandlerRegistry {
    private final Map<String, EventHandler> handlers = new HashMap<>();


    public void registerHandler(String eventType, EventHandler handler) {
        handlers.put(eventType, handler);
    }

    public EventHandler getHandler(String eventType) {
        return handlers.get(eventType);
    }

}
