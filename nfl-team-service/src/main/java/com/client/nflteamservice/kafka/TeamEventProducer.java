package com.client.nflteamservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TeamEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TeamEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishTeamCreated(String teamJson) {
        kafkaTemplate.send("team-events", teamJson);
    }
}