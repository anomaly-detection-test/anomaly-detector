package anomalyDetector.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PushEvent extends Event {
    private String ref;
    //TODO: get the time of the push event
    @JsonProperty("pushed_at")
    private OffsetDateTime pushedAt;

    public PushEvent() {
    }
}
