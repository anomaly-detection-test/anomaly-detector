package anomalyDetector.detections;

import anomalyDetector.events.Event;
import anomalyDetector.events.TeamEvent;
import anomalyDetector.models.Alert;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static anomalyDetector.events.EventType.TEAM;

@Component
public class InvalidTeamNameDetector implements AnomalyDetector<TeamEvent> {

    @Override
    public boolean supports(Event event) {
        return event instanceof TeamEvent;
    }

    @Override
    public Optional<Alert> detect(TeamEvent event) {
        if (event.getTeam().getName().toLowerCase().startsWith("hacker")) {
            return Optional.of(
                    new Alert("Team created with suspicious prefix: " + event.getTeam().getName(), TEAM)
            );
        }

        return Optional.empty();
    }
}
