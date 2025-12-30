package anomalyDetector.parsers;

import anomalyDetector.events.Event;
import anomalyDetector.events.EventType;
import anomalyDetector.events.PushEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static anomalyDetector.events.EventType.PUSH;

@Component
public class PushEventParser implements Parsable {
    private final ObjectMapper objectMapper;

    public PushEventParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Event parse(String payload, Instant eventCreatedTime) throws Exception {
        PushEvent event = objectMapper.readValue(payload, PushEvent.class);
        event.setType(PUSH);
        event.setReceivedAt(eventCreatedTime);
        return event;
    }

    @Override
    public EventType getSupportedType() {
        return PUSH;
    }
}