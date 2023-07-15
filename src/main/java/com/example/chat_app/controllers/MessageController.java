package com.example.chat_app.controllers;

import com.example.chat_app.Entity.Message;
import com.example.chat_app.servises.MessageServiseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {
    private final MessageServiseImp messageService;
    private final MessageListener messageListener;

    @PostMapping
    public void postMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now());
        message.setId(UUID.randomUUID());
        messageService.postMessage(message);
        List<Message> messages1 = messageService.getMessagesWithOutUpdate(message.getReceiver().getId(), message.getSender().getId());
        messageListener.sendMessageToTopic("/topics/chat", messages1); // Notify listeners
        messageListener.sendMessageToTopic("/topics/seen", message); // Notify listeners
    }

    @GetMapping("/{current}/{user}")
    public List<Message> getMessages(@PathVariable UUID current, @PathVariable UUID user) {
        System.out.println(current);
        System.out.println(user);
        return messageService.getMessages(current, user);
    }
}
