package anomalyDetector.exceptions;

public class EventParsingException extends RuntimeException {
    public EventParsingException(String parserName, Exception e) {
        super("Failed to parse event with parser: " + parserName, e);
    }
}
