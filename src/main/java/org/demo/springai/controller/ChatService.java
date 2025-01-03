package org.demo.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    ChatClient chatClient;
    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getMessage() {
        return chatClient.prompt(new Prompt("Give me a joke"))
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getContent();
    }
}
