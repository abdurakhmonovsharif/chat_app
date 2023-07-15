package com.example.chat_app.repo;

import com.example.chat_app.Entity.User;
import com.example.chat_app.projections.AuthProjection;
import com.example.chat_app.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "SELECT users.id,\n" +
            "       users.username,\n" +
            "       (select content\n" +
            "        from message\n" +
            "        where timestamp IS NOT NULL and (receiver_id =?1 and sender_id = users.id)\n" +
            "           or (receiver_id = users.id and sender_id = ?1)\n" +
            "        order by timestamp desc\n" +
            "        limit 1)                                                AS last_message,\n" +
            "       SUM(CASE WHEN m.seen = false THEN 1 ELSE 0 END)          AS m_count,\n" +
            "       MAX(COALESCE(m.timestamp, '1976-06-20 23:22:30.000000')) AS last_time\n" +
            "FROM users\n" +
            "         LEFT JOIN\n" +
            "     message m ON users.id = m.sender_id AND m.receiver_id = ?1\n" +
            "GROUP BY users.id, users.username\n" +
            "ORDER BY last_time DESC;", nativeQuery = true)
    List<UserProjection> findUsers(UUID userId);

    @Query(value = "select username,id from users where username=?1 and password=?2", nativeQuery = true)
    AuthProjection loginUser(String username, String password);
}
