package anomalyDetector.events;

import anomalyDetector.models.Repository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryEvent extends Event {
    @JsonProperty("repository")
    private Repository repository;

    public RepositoryEvent() {
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
