package anomalyDetector.detections;

import anomalyDetector.events.Event;
import anomalyDetector.events.RepositoryEvent;
import anomalyDetector.models.Alert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class RapidRepoLifecycleDetector implements AnomalyDetector<RepositoryEvent> {
    private final String DELETED_ACTION = "deleted";

    @Value("${repositoryMinLifetimeMinutes}")
    private long minLifetimeMinutes;

    @Override
    public boolean supports(Event event) {
        return event instanceof RepositoryEvent;
    }

    @Override
    public Optional<Alert> detect(RepositoryEvent event) {
        if (event.getAction().equals(DELETED_ACTION) && isRepoLifetimeLessThenMin(event)) {
            return Optional.of(new Alert("Repository created and deleted within " + minLifetimeMinutes + "  minutes: " + event.getRepository().getName(), event.getType()));
        }

        return Optional.empty();
    }

    private boolean isRepoLifetimeLessThenMin(RepositoryEvent event) {
        return Duration.between(event.getRepository().getCreatedAt(), event.getReceivedAt()).toMinutes() < minLifetimeMinutes;
    }
}
