package anomalyDetector.services;

import anomalyDetector.detections.AnomalyDetector;
import anomalyDetector.events.Event;
import anomalyDetector.notification.Notifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomalyDetectionService {
    private final List<AnomalyDetector<?>> detectors;
    private final Notifier notifier;

    public AnomalyDetectionService(List<AnomalyDetector<?>> detectors, Notifier notifier) {
        this.detectors = detectors;
        this.notifier = notifier;
    }

    public void dispatch(Event event) {
        detectors.stream()
                .filter(d -> d.supports(event))
                .forEach(detector ->
                        ((AnomalyDetector<Event>) detector)
                                .detect(event)
                                .ifPresent(notifier::notify)
                );
    }

    public void handleEvent(Event event) {
        dispatch(event);
    }
}
