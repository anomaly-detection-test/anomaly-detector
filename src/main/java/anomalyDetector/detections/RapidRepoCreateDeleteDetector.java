package anomalyDetector.detections;

import anomalyDetector.events.Event;
import anomalyDetector.events.RepositoryEvent;
import anomalyDetector.models.Alert;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class RapidRepoCreateDeleteDetector implements AnomalyDetector<RepositoryEvent> {
    @Override
    public boolean supports(Event event) {
        return event instanceof RepositoryEvent;
    }

    @Override
    public Optional<Alert> detect(RepositoryEvent event) {
        if (event.getAction().equals("deleted") && Duration.between(event.getRepository().getCreatedAt(), event.getReceivedAt()).toMinutes() < 10) {
            return Optional.of(
                    new Alert("Repository created and deleted within 10 minutes: " + event.getRepository().getName(), event.getType())
            );
        }
        return Optional.empty();
    }
}

