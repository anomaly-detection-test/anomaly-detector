package anomalyDetector.detections;

import anomalyDetector.events.Event;
import anomalyDetector.events.PushEvent;
import anomalyDetector.models.Alert;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Optional;

import static anomalyDetector.events.EventType.PUSH;

@Component
public class OffHoursPushDetector implements AnomalyDetector<PushEvent> {
    private static final LocalTime START = LocalTime.of(14, 0);
    private static final LocalTime END = LocalTime.of(16, 0);

    @Override
    public boolean supports(Event event) {
        return event instanceof PushEvent;
    }

    @Override
    public Optional<Alert> detect(PushEvent event) {
        LocalTime pushTime = event.getRepository().getPushedAt()
                .atZoneSameInstant(ZoneId.systemDefault())
                .toLocalTime();

        if (!pushTime.isBefore(START) && !pushTime.isAfter(END)) {
            return Optional.of(new Alert("Push between 14:00–16:00", PUSH));
        }


        return Optional.empty();
    }
}
