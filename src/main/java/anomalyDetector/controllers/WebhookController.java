package anomalyDetector.controllers;

import anomalyDetector.events.Event;
import anomalyDetector.parsers.EventParser;
import anomalyDetector.services.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {
    //TODO:  add webhook secret validation
    private static final String EVENT_TYPE_HEADER = "X-GitHub-Event";

    private final EventParser eventParser;
    private final EventService eventService;

    public WebhookController(EventParser eventParser, EventService eventService) {
        this.eventParser = eventParser;
        this.eventService = eventService;
    }

    @PostMapping
    public void handleEvent(
            @RequestHeader(EVENT_TYPE_HEADER) String eventType,
            @RequestBody String payload
    ) {
        try {
            Event event = eventParser.parse(eventType, payload);
            eventService.handleEvent(event);
        } catch (Exception e) {
            //TODO: log the error and throw appropriate HTTP response
        }
    }
}