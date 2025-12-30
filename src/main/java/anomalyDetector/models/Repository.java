package anomalyDetector.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {
    private Long id;
    private String name;
    @JsonProperty("created_at")
    private Instant createdAt;
    @JsonProperty("pushed_at")
    private OffsetDateTime pushedAt;

    public Repository() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getPushedAt() {
        return pushedAt;
    }

    public void setPushedAt(OffsetDateTime pushedAt) {
        this.pushedAt = pushedAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }
}
