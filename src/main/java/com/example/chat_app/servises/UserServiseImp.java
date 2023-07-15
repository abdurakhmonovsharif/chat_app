package com.example.chat_app.servises;

import com.example.chat_app.Entity.User;
import com.example.chat_app.projections.AuthProjection;
import com.example.chat_app.projections.UserProjection;
import com.example.chat_app.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface UserServiseImp  {

    List<UserProjection> getChatUsers(UUID userId);

    ResponseEntity<?> UserLogin(User user);

    ResponseEntity<?> save(User user);
}
