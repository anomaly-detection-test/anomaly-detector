package anomalyDetector.notification;

import anomalyDetector.models.Alert;

public interface Notifier {
    void notify(Alert alert);
}
