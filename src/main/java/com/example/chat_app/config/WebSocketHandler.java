package com.example.chat_app.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebSocketHandler implements org.springframework.web.socket.WebSocketHandler {
    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // Handle incoming messages from clients
        // You can access the message content using message.getPayload().toString()
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sessions.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Handle transport errors, if needed
    }

    public void broadcastMessage(String content) {
        TextMessage message = new TextMessage(content);
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                // Handle exception
            }
        }
    }
}
