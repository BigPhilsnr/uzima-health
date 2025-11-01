package com.uzimahealth.controller;

import com.uzimahealth.model.ChatMessage;
import com.uzimahealth.service.ChatService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{sender}/{receiver}")
    public List<ChatMessage> getChat(@PathVariable String sender, @PathVariable String receiver) {
        return chatService.findBySenderAndReceiver(sender, receiver);
    }

    @PostMapping
    public ChatMessage sendMessage(@RequestBody ChatMessage message) {
        return chatService.save(message);
    }
}