package com.example.chat_app.servises;

import com.example.chat_app.Entity.User;
import com.example.chat_app.projections.AuthProjection;
import com.example.chat_app.projections.UserProjection;
import com.example.chat_app.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiseImp {
    private final UserRepository userRepository;


    @Override
    public List<UserProjection> getChatUsers(UUID userId) {
        return userRepository.findUsers(userId);
    }

    @Override
    public ResponseEntity<?> UserLogin(User user) {
        var userdata = userRepository.loginUser(user.getUsername(), user.getPassword());
        if (userdata != null) {
            return ResponseEntity.ok().body(userdata);
        } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(403)).build();
        }
    }

    @Override
    public ResponseEntity<?> save(User user) {
        return ResponseEntity.ok().body(userRepository.save(user));
    }

}
