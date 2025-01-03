package org.demo.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class AppConfig {

    @Value("classpath:input.txt")
    private Resource resource;

    @Bean
    public ChatClient chatClientBuilderCustom(ChatClient.Builder builder) {
        return builder.defaultSystem(resource).build();
    }
}
