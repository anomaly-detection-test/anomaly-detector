package anomalyDetector.models;

import anomalyDetector.events.EventType;

import java.time.OffsetDateTime;

public class Alert {
    private final String description;
    private final EventType eventType;
    private final OffsetDateTime detectedAt;

    public Alert(String description, EventType eventType) {
        this.description = description;
        this.eventType = eventType;
        this.detectedAt = OffsetDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public OffsetDateTime getDetectedAt() {
        return detectedAt;
    }

    @Override
    public String toString() {
        return "ALERT [" + eventType + "] at " + detectedAt + " - " + description;
    }
}
