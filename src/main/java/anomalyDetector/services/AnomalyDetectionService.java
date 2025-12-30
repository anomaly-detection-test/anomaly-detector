package anomalyDetector.services;

import anomalyDetector.detections.AnomalyDetector;
import anomalyDetector.events.Event;
import anomalyDetector.models.Alert;
import anomalyDetector.notification.Notifier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class AnomalyDetectionService {
    private final List<AnomalyDetector<?>> detectors;
    private final List<Notifier> notifiers;

    public void handleEvent(Event event) {
        detectors.stream()
                .filter(d -> d.supports(event))
                .forEach(detector ->
                        ((AnomalyDetector<Event>) detector)
                                .detect(event)
                                .ifPresent(this::notifyAll)
                );
    }

    private void notifyAll(Alert alert) {
        notifiers.forEach(n -> n.notify(alert));
    }
}
