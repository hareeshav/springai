package org.demo.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Value("classpath:prompts/movieinfo.st")
    private Resource prompts;

    private final ChatClient chatClient;

    public MovieService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String movieInfo(String movieName){
        return chatClient.prompt()
                .user(userSpec -> userSpec.text(prompts)
                        .param("moviename", movieName)
                )
                .call()
                .chatResponse().getResult().getOutput().getContent();
    }

}
