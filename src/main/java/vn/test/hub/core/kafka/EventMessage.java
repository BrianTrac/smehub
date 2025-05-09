package vn.test.hub.core.kafka;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EventMessage {
    private String eventType;
    private String eventId;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private String source;
    private Object payload;
    private Metadata metadata;

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metadata {
        private String correlationId;
        private Map<String, String> headers;
    }
}
