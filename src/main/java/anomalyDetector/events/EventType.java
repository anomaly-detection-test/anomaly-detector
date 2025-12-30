package anomalyDetector.events;

public enum EventType {
    PUSH,
    REPOSITORY,
    TEAM,
    UNKNOWN;

    public static EventType fromString(String type) {
        try {
            return EventType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return UNKNOWN;
        }
    }
}
