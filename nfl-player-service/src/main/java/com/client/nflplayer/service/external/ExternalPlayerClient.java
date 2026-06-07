package com.client.nflplayer.service.external;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExternalPlayerClient {

    private final WebClient webClient;

    public ExternalPlayerClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://dummyjson.com").build();
    }

    public Mono<String> fetchExternalData() {
        return webClient.get()
                .uri("/users/1")
                .retrieve()
                .bodyToMono(String.class);
    }
}
