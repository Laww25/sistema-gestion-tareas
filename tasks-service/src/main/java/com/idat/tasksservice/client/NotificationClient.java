package com.idat.tasksservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notifications-service", url = "http://localhost:8083")
public interface NotificationClient {

    @PostMapping("/notifications")
    void sendNotification(@RequestBody NotificationRequest request);

    record NotificationRequest(String message) {}
}