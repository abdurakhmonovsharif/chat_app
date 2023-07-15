package com.example.chat_app.dto;

import com.example.chat_app.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDTO {
    private String content;
    private User user;

}

