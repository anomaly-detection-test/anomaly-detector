package anomalyDetector.parsers;

import anomalyDetector.events.Event;
import anomalyDetector.events.EventType;
import anomalyDetector.exceptions.EventParsingException;
import anomalyDetector.exceptions.UnsupportedEventException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EventParser {
    private final Map<EventType, Parsable> parserMap;

    public EventParser(List<Parsable> parsers) {
        this.parserMap = parsers.stream().collect(Collectors.toMap(Parsable::getSupportedType, Function.identity()));
    }

    public Event parse(String eventType, String payload) throws UnsupportedEventException {
        Parsable parser = parserMap.get(EventType.fromString(eventType));
        if (parser == null) {
            throw new UnsupportedEventException(eventType);
        }
        try {
            return parser.parse(payload);
        } catch (Exception e) {
            throw new EventParsingException(eventType, e);
        }
    }
}
