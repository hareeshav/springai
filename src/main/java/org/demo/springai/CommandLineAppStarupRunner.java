package org.demo.springai;

import org.demo.springai.service.AIChatBotService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStarupRunner implements CommandLineRunner {

    private final AIChatBotService chatService;

    public CommandLineAppStarupRunner(AIChatBotService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void run(String... args) throws Exception {
       chatService.startChat();
    }
}
