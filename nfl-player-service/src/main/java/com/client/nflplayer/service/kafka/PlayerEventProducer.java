package com.client.nflplayer.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlayerEventProducer implements EventProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public PlayerEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPlayerRetrieval(String playerJson) { kafkaTemplate.send("player-events", playerJson);}
}