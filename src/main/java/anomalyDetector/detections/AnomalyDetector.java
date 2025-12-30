package anomalyDetector.detections;

import anomalyDetector.events.Event;
import anomalyDetector.models.Alert;

import java.util.Optional;

public interface AnomalyDetector<T extends Event> {
    boolean supports(Event eventClass);

    Optional<Alert> detect(T event);
}
