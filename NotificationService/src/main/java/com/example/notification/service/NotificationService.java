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
        throw new UnsupportedOperationException("This method is not yet implemented");
    }

    // Helper method to create a notification message based on the Kafka message
    private String createNotificationMessage(String message) {
        throw new UnsupportedOperationException("This method is not yet implemented");
    }

    // Notify the frontend via WebSocket
    private void notifyFrontend(String notificationMessage) {
//        messagingTemplate.convertAndSend("/topic/notifications", notificationMessage);
    }
}
