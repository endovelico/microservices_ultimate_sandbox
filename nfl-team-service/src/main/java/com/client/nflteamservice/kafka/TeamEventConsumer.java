package com.client.nflteamservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TeamEventConsumer {

    @KafkaListener(topics = "team-events", groupId = "nfl-team-group")
    public void consume(String message) {
        System.out.println("Received Team Event: " + message);
    }
}