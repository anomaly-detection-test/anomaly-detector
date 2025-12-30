package anomalyDetector.parsers;

import anomalyDetector.events.Event;
import anomalyDetector.events.EventType;
import anomalyDetector.events.RepositoryEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import static anomalyDetector.events.EventType.REPOSITORY;

@Component
public class RepositoryEventParser implements Parsable {

    private final ObjectMapper objectMapper;

    public RepositoryEventParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Event parse(String payload) throws Exception {
        RepositoryEvent event = objectMapper.readValue(payload, RepositoryEvent.class);
        event.setType(REPOSITORY);
        return event;
    }

    @Override
    public EventType getSupportedType() {
        return REPOSITORY;
    }
}