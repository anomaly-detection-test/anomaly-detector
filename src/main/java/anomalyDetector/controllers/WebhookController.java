package anomalyDetector.controllers;

import anomalyDetector.events.Event;
import anomalyDetector.exceptions.EventParsingException;
import anomalyDetector.exceptions.UnsupportedEventException;
import anomalyDetector.parsers.EventParser;
import anomalyDetector.services.AnomalyDetectionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/webhook")
public class WebhookController {
    //TODO:  add webhook secret validation
    private static final String EVENT_TYPE_HEADER = "X-GitHub-Event";

    private final EventParser eventParser;
    private final AnomalyDetectionService anomalyDetectionService;

    @PostMapping
    public ResponseEntity<String> handleEvent(
            @RequestHeader(EVENT_TYPE_HEADER) String eventType,
            @RequestBody String payload
    ) {
        try {
            Instant eventCreatedTime = Instant.now();
            Event event = eventParser.parse(eventType, payload, eventCreatedTime);
            anomalyDetectionService.handleEvent(event);

            return ResponseEntity.ok("OK");
        } catch (UnsupportedEventException e) {
            log.warn("Unsupported event type: {}", eventType);
            return ResponseEntity.status(BAD_REQUEST).body("Unsupported event type: " + eventType);

        } catch (EventParsingException e) {
            log.warn("Error parsing event {}: {}", eventType, e.getMessage());
            return ResponseEntity.status(BAD_REQUEST).body("Error parsing event: " + e.getMessage());

        } catch (Exception e) {
            log.error("Unexpected error handling webhook event {}: {}", eventType, e.getMessage(), e);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}