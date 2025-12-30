package anomalyDetector.detections;

import anomalyDetector.events.Event;
import anomalyDetector.events.PushEvent;
import anomalyDetector.models.Alert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Optional;

import static anomalyDetector.events.EventType.PUSH;

@Component
public class OffHoursPushDetector implements AnomalyDetector<PushEvent> {
    @Value("${offHoursPush.startHour}")
    private int startHour;

    @Value("${offHoursPush.startMinute}")
    private int startMinute;

    @Value("${offHoursPush.endHour}")
    private int endHour;

    @Value("${offHoursPush.endMinute}")
    private int endMinute;

    LocalTime START = LocalTime.of(startHour, startMinute);
    LocalTime END = LocalTime.of(endHour, endMinute);

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
            return Optional.of(new Alert("Push in off hours", PUSH));
        }

        return Optional.empty();
    }
}
