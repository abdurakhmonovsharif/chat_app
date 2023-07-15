package com.example.chat_app.controllers;

import com.example.chat_app.Entity.User;
import com.example.chat_app.projections.AuthProjection;
import com.example.chat_app.projections.UserProjection;
import com.example.chat_app.servises.UserServiseImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiseImp userServiseImp;

    @GetMapping("/chat_users/{userId}")
    List<UserProjection> getChatUsers(@PathVariable UUID userId) {
        return userServiseImp.getChatUsers(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        return userServiseImp.UserLogin(user);
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userServiseImp.save(user);
    }
}
