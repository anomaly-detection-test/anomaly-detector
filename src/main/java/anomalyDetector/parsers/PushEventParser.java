package anomalyDetector.parsers;

import anomalyDetector.events.Event;
import anomalyDetector.events.EventType;
import anomalyDetector.events.PushEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static anomalyDetector.events.EventType.PUSH;

@AllArgsConstructor
@Component
public class PushEventParser implements Parsable {
    private final ObjectMapper objectMapper;

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