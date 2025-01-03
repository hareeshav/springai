package org.demo.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class AIChatBotService {
    private final ChatClient chatClient;

    public AIChatBotService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory())).build();
    }

    public String getResponse(String message) {
        return chatClient.prompt().user(message).call().content();
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your message: ");
        while (true) {
            String message = scanner.nextLine();
            if (message.equals("exit")) {
                System.out.println("Exiting chat...");
                break;
            }
            String response = getResponse(message);
            System.out.println("Bot: " + response);
        }
        scanner.close();
    }
}
