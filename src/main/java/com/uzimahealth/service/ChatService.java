package com.uzimahealth.service;

import com.uzimahealth.model.ChatMessage;
import com.uzimahealth.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public List<ChatMessage> findBySenderAndReceiver(String sender, String receiver) {
        return chatMessageRepository.findBySenderAndReceiver(sender, receiver);
    }

    public ChatMessage save(ChatMessage message) {
        return chatMessageRepository.save(message);
    }
}