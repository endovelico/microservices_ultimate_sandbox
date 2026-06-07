package com.client.nflplayer.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
public class PlayerEventProducer implements EventProducer {

    private static final Logger logger = LoggerFactory.getLogger(PlayerEventProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public PlayerEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPlayerRetrieval(String playerJson) {
        logger.info("Calling publishPlayerRetrieval in nfl-player-service", kv("produced", playerJson));
        kafkaTemplate.send("player-events", playerJson);
    }
}