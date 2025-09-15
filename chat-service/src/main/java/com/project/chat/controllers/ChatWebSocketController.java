package com.project.chat.controllers;

import com.project.chat.models.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatWebSocketController {

    @MessageMapping("/sendMessage")       // client gửi tới /app/sendMessage
    @SendTo("/topic/public")             // broadcast ra /topic/public
    public ChatMessage sendMessage(ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        return message;
    }
}
