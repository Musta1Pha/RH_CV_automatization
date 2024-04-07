package com.example.rh_cv_automatisation.Common.web;

import com.example.rh_cv_automatisation.Common.services.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(path = "/sendNotification/{offerId}")
    public void sendNotification(@PathVariable Long offerId){
        notificationService.sendNotification(offerId);
    }
}