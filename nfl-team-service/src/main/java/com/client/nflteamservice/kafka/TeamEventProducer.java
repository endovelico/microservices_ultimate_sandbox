package com.client.nflteamservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
public class TeamEventProducer implements EventProducer {

    private static final Logger logger = LoggerFactory.getLogger(TeamEventProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public TeamEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishTeamCreated(String teamJson) {
        logger.info("Calling publishTeamCreated in nfl-team-service", kv("produced", teamJson));
        kafkaTemplate.send("team-events", teamJson);
    }

    public void publishTeamsRetrieval(String teamJson) {
        logger.info("Calling publishTeamsRetrieval in nfl-team-service", kv("produced", teamJson));
        kafkaTemplate.send("team-events", teamJson);
    }
}