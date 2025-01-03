package org.demo.springai.controller;

import org.demo.springai.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/chat-default")
    public String chat() {
        return chatService.getMessage("","","");
    }
}
