package org.demo.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    ChatClient chatClient;

    public String getMessage(String subject, String tone, String message) {
        return //chatClient.prompt(new Prompt("Give me a joke"))
        chatClient.prompt()
                .system(sp-> sp.param("subject", subject).param("tone", tone))
                .user(message)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getContent();
    }
}
