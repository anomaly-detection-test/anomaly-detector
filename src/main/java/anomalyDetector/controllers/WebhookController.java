package anomalyDetector.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {
    @PostMapping()
    public ResponseEntity<Void> receive(
            @RequestHeader("X-GitHub-Event") String eventType,
            @RequestBody String payload
    ) {
        System.out.println("Received event: " + eventType);
        System.out.println(payload);
        return ResponseEntity.ok().build();
    }
}
