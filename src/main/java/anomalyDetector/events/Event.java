package anomalyDetector.events;

public abstract class Event {
    private EventType type;
    private String action;

    public Event() {
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}