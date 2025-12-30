package anomalyDetector.parsers;

import anomalyDetector.events.Event;
import anomalyDetector.events.EventType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static anomalyDetector.events.EventType.UNKNOWN;

@Component
public class DefaultEventParser implements Parsable {
    private final ObjectMapper objectMapper;

    public DefaultEventParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Event parse(String payload, Instant eventCreatedTime) throws Exception {
        Event event = objectMapper.readValue(payload, Event.class);
        event.setType(UNKNOWN);
        event.setReceivedAt(eventCreatedTime);
        return event;
    }

    @Override
    public EventType getSupportedType() {
        return UNKNOWN;
    }
}
