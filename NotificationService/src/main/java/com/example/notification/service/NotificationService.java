package com.example.notification.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Kafka listener that reads messages from the 'expenses-topic' topic
    @KafkaListener(topics = "expenses-topic", groupId = "my-group-id")
    public void consumeBillSplitEvent(String message) {
        // Process the incoming message
        String notificationMessage = createNotificationMessage(message);

        // Send notification to the frontend
        notifyFrontend(notificationMessage);
    }

    // Helper method to create a notification message based on the Kafka message
    private String createNotificationMessage(String message) {
        return "Your friend has been notified to pay the bill. Event details: " + message;
    }

    // Notify the frontend via WebSocket
    private void notifyFrontend(String notificationMessage) {
        messagingTemplate.convertAndSend("/topic/notifications", notificationMessage);
    }
}
