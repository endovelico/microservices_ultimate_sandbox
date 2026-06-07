package com.client.nflteamservice.external;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalTeamClient {

    private final WebClient webClient;

    public ExternalTeamClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://dummyjson.com").build();
    }

    public String fetchExternalData() {
        return webClient.get()
                .uri("/users/1")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
