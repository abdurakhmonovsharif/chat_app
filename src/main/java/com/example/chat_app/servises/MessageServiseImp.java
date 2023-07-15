package com.example.chat_app.servises;

import com.example.chat_app.Entity.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface MessageServiseImp {
    UUID postMessage(Message message);

    List<Message> getMessages(UUID receiver, UUID sender);

    List<Message> getMessagesWithOutUpdate(UUID current, UUID user);
}
