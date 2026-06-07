package com.client.nflteamservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
public class TeamEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TeamEventConsumer.class);

    @KafkaListener(topics = "team-events", groupId = "nfl-team-group-nts")
    public void consume(String message) {
        logger.info("Calling Consume in TeamEventConsumer", kv("consumed-message", message));
    }
}