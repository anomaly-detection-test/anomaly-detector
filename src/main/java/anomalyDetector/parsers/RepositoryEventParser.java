package anomalyDetector.parsers;

import anomalyDetector.events.Event;
import anomalyDetector.events.EventType;
import anomalyDetector.events.RepositoryEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static anomalyDetector.events.EventType.REPOSITORY;

@AllArgsConstructor
@Component
public class RepositoryEventParser implements Parsable {
    private final ObjectMapper objectMapper;

    @Override
    public Event parse(String payload, Instant eventCreatedTime) throws Exception {
        RepositoryEvent event = objectMapper.readValue(payload, RepositoryEvent.class);
        event.setType(REPOSITORY);
        event.setReceivedAt(eventCreatedTime);
        return event;
    }

    @Override
    public EventType getSupportedType() {
        return REPOSITORY;
    }
}