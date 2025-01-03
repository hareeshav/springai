package org.demo.springai.controller;

import org.demo.springai.model.Movie;
import org.demo.springai.service.MovieService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AIChatController {

    @Autowired
    private MovieService movieService;

    private final ChatClient chatClient;

    public AIChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }


    @GetMapping("/movie")
    public String getMovieInfo(@RequestParam(value="movie", defaultValue="Jurassic park") String movie) {
        return movieService.movieInfo(movie);
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value="message", defaultValue="Give me java hello world program ?") String message) {
        ChatResponse chatResponse = getMessage(message);
        return chatResponse.getResult().getOutput().getContent();
    }


    public ChatResponse getMessage(String message) {
        ChatResponse chatResponse = chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
        return chatResponse;
    }

    @GetMapping("/chatjson")
    public ChatResponse chatJson(@RequestParam(value="message", defaultValue="Please give me a simple joke in one line ") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
    }

    @GetMapping("/metadata")
    public ChatResponse metadata(@RequestParam(value="message", defaultValue="Please give me a simple joke in one line ") String message) {
        ChatResponse chatResponse = chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
        System.out.println("get model " + chatResponse.getMetadata().getModel());
        return chatResponse;
    }

    @GetMapping("/listcities")
    public List<String> getCities(@RequestParam(value="message", defaultValue="Top 5 cities in India ") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService()));
    }

    @GetMapping("/mapcities")
    public Map<String, String> getCitiesAndCapitals(@RequestParam(value="message", defaultValue="Top 5 cities in India and their capitals") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<Map<String, String>>() {});
    }

    @GetMapping("/director-movie-service")
    public List<Movie> getDirectorMoviesInPojo(@RequestParam(value="directorName", defaultValue = "Bharathan") String directorName) {
        String template = """
                "Generate a list of movies directed by {directorName}. If the director is unknown, return null. 
                Each movie should include a title and release year. {format}"
                """;
        List<Movie> movieList = chatClient.prompt()
                .user(userSpec -> userSpec.text(template)
                        .param("directorName", directorName)
                        .param("format", "json")
                )
                .call()
                .entity(new ParameterizedTypeReference<List<Movie>>() {
                });
        return movieList;
    }

}
