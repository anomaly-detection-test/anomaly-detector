package anomalyDetector.parsers;

import anomalyDetector.events.Event;
import anomalyDetector.events.EventType;

import java.time.Instant;

public interface Parsable {
    Event parse(String payload, Instant eventCreatedTime) throws Exception;

    EventType getSupportedType();
}
