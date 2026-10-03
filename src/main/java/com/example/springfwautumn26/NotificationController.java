package com.example.springfwautumn26;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final ObjectProvider<NotificationService> notificationService;

    public NotificationController(
            ObjectProvider<NotificationService> notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public String getStatus() {
        NotificationService service =
                notificationService.getIfAvailable();

        if (service == null) {
            return "Notifications are OFF";
        }

        return service.getStatus();
    }
}