package anomalyDetector.parsers;

import anomalyDetector.events.Event;
import anomalyDetector.events.EventType;
import anomalyDetector.events.TeamEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import static anomalyDetector.events.EventType.TEAM;

@Component
public class TeamEventParser implements Parsable {

    private final ObjectMapper objectMapper;

    public TeamEventParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Event parse(String payload) throws Exception {
        TeamEvent event = objectMapper.readValue(payload, TeamEvent.class);
        event.setType(TEAM);
        return event;
    }

    @Override
    public EventType getSupportedType() {
        return TEAM;
    }
}