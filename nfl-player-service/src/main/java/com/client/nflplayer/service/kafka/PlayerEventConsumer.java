package com.client.nflplayer.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PlayerEventConsumer {

    @KafkaListener(topics = {"player-events", "team-events"}, groupId = "nfl-player-group-nps")
    public void consume(String message) {
        System.out.println("Received  Event in Microservice Player: " + message);
    }
}