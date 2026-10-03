package com.example.springfwautumn26;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(
        prefix = "app.notification",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = false
)
public class NotificationService {

    public String getStatus() {
        return "Notifications are ON";
    }
}