package anomalyDetector.events;

import anomalyDetector.models.Repository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushEvent extends Event {
    @JsonProperty("ref")
    private String ref;

    @JsonProperty("repository")
    private Repository repository;
}