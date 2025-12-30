package anomalyDetector.parsers;

import anomalyDetector.events.Event;
import anomalyDetector.events.EventType;
import anomalyDetector.exceptions.EventParsingException;
import anomalyDetector.exceptions.UnsupportedEventException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static anomalyDetector.events.EventType.fromString;

@Slf4j
@Component
public class EventParser {
    private final Map<EventType, Parsable> parserMap;

    public EventParser(List<Parsable> parsers) {
        this.parserMap = parsers.stream().collect(Collectors.toMap(Parsable::getSupportedType, Function.identity()));
    }

    public Event parse(String eventType, String payload, Instant eventCreatedTime) throws UnsupportedEventException {
        Parsable parser = parserMap.get(fromString(eventType));
        if (parser == null) {
            throw new UnsupportedEventException(eventType);
        }
        try {
            return parser.parse(payload, eventCreatedTime);
        } catch (Exception e) {
            log.error("error parsing event type {}: {}", eventType, e.getMessage(), e);
            throw new EventParsingException(eventType, e);
        }
    }
}
