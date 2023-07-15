package com.example.chat_app.servises;

import com.example.chat_app.Entity.Message;
import com.example.chat_app.repo.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MessageService implements MessageServiseImp {
    private final MessageRepository messageRepository;


    @Override
    public UUID postMessage(Message message) {

        return messageRepository.postMessage(message.getId(), message.getContent(), message.isSeen(), message.getTimestamp(), message.getReceiver().getId(), message.getSender().getId());
    }

    @Override
    public List<Message> getMessages(UUID current, UUID user) {
        messageRepository.updateSeen(current, user);
        return messageRepository.getMessages(current, user);
    }
    @Override
    public List<Message> getMessagesWithOutUpdate(UUID current, UUID user) {

        return messageRepository.getMessages(current, user);
    }


}
