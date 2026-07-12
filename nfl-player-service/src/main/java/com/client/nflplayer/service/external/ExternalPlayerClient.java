package com.client.nflplayer.service.external;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;

import io.github.resilience4j.reactor.bulkhead.operator.BulkheadOperator;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.retry.RetryOperator;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ExternalPlayerClient {

    private final WebClient webClient;
    private final CircuitBreaker circuitBreaker;
    private final Retry retry;
    private final Bulkhead bulkhead;

    public ExternalPlayerClient(
            WebClient.Builder builder,
            CircuitBreaker circuitBreaker,
            Retry retry,
            Bulkhead bulkhead) {

        this.webClient = builder
                .baseUrl("https://dummyjson.com")
                .build();

        this.circuitBreaker = circuitBreaker;
        this.retry = retry;
        this.bulkhead = bulkhead;
    }

    public Mono<String> fetchExternalData() {
        return webClient.get()
                .uri("/users/1")
                .retrieve()
                .bodyToMono(String.class)
                .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .transformDeferred(RetryOperator.of(retry))
                .transformDeferred(BulkheadOperator.of(bulkhead))
                .onErrorResume(this::fallback);
    }

    private Mono<String> fallback(Throwable ex) {
        return Mono.just("External service unavailable");
    }
}