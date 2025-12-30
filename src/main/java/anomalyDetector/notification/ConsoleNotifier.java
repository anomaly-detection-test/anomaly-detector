package anomalyDetector.notification;

import anomalyDetector.models.Alert;
import org.springframework.stereotype.Component;

@Component
public class ConsoleNotifier implements Notifier {
    @Override
    public void notify(Alert alert) {
        System.out.println(alert);
    }
}

