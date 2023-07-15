package com.example.chat_app.repo;

import com.example.chat_app.Entity.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Transactional
    @Query(value = "insert into  message (id,content, seen, timestamp, receiver_id, sender_id) values (?1,?2,?3,?4,?5,?6) RETURNING id", nativeQuery = true)
    UUID postMessage(UUID id, String content, Boolean seen, LocalDateTime timestamp, UUID receiver_id, UUID sender_id);

    @Query(value = "select * from message where (receiver_id=?1 and sender_id=?2) or (receiver_id=?2 and sender_id=?1)", nativeQuery = true)
    List<Message> getMessages(UUID current, UUID user);

    @Modifying
    @Transactional
    @Query(value = "update message set seen=true where receiver_id=?1 and sender_id=?2 and seen=false", nativeQuery = true)
    void updateSeen(UUID current, UUID user);
}
