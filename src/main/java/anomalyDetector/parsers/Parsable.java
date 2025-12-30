package anomalyDetector.parsers;

import anomalyDetector.events.Event;
import anomalyDetector.events.EventType;

public interface Parsable {
    Event parse(String payload) throws Exception;

    EventType getSupportedType();
}
