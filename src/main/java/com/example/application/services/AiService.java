package com.example.application.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import reactor.core.publisher.Flux;

@Service
@BrowserCallable
@AnonymousAllowed
public class AiService {
    
    private final ChatClient chatClient;

    public AiService(ChatClient.Builder builder) {
        chatClient = builder.build();   
    }

    public Flux<String> getResponse(String prompt) {
        return chatClient.prompt()
        .user(prompt)
        .stream()
        .content();
    }
}
