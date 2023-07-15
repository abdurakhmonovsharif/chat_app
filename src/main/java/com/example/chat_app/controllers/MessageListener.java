package com.example.chat_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessageToTopic(String topic, Object message) {
        messagingTemplate.convertAndSend(topic, message);
    }
}
