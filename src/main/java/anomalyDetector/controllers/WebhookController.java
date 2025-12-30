package anomalyDetector.controllers;

import anomalyDetector.events.Event;
import anomalyDetector.parsers.EventParser;
import anomalyDetector.services.AnomalyDetectionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class WebhookController {
    //TODO:  add webhook secret validation
    private static final String EVENT_TYPE_HEADER = "X-GitHub-Event";

    private final EventParser eventParser;
    private final AnomalyDetectionService anomalyDetectionService;

    public WebhookController(EventParser eventParser, AnomalyDetectionService anomalyDetectionService) {
        this.eventParser = eventParser;
        this.anomalyDetectionService = anomalyDetectionService;
    }

    @PostMapping
    public void handleEvent(
            @RequestHeader(EVENT_TYPE_HEADER) String eventType,
            @RequestBody String payload
    ) {
        try {
            Instant eventCreatedTime = Instant.now();
            Event event = eventParser.parse(eventType, payload, eventCreatedTime);
            anomalyDetectionService.handleEvent(event);
        } catch (Exception e) {
            //TODO: log the error and throw appropriate HTTP response
        }
    }
}