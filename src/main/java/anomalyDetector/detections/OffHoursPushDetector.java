package anomalyDetector.detections;

import anomalyDetector.events.Event;
import anomalyDetector.events.PushEvent;
import anomalyDetector.models.Alert;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Optional;

import static anomalyDetector.events.EventType.PUSH;

@Component
public class OffHoursPushDetector implements AnomalyDetector<PushEvent> {
    private LocalTime START;
    private LocalTime END;

    @Value("${offhours.start.hour}")
    private int startHour;

    @Value("${offhours.start.minute}")
    private int startMinute;

    @Value("${offhours.end.hour}")
    private int endHour;

    @Value("${offhours.end.minute}")
    private int endMinute;

    @PostConstruct
    public void init() {
        START = LocalTime.of(startHour, startMinute);
        END = LocalTime.of(endHour, endMinute);
    }

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
