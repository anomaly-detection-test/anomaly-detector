package anomalyDetector.models;

import anomalyDetector.events.EventType;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class Alert {
    private final String description;
    private final EventType eventType;
    private final OffsetDateTime detectedAt;

    public Alert(String description, EventType eventType) {
        this.description = description;
        this.eventType = eventType;
        this.detectedAt = OffsetDateTime.now();
    }

    @Override
    public String toString() {
        return "ALERT [" + eventType + "] at " + detectedAt + " - " + description;
    }
}
