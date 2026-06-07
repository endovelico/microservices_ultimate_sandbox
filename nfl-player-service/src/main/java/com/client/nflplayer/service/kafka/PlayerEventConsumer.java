package com.client.nflplayer.service.kafka;

import com.client.nflplayer.service.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
public class PlayerEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PlayerEventConsumer.class);

    @KafkaListener(topics = {"player-events", "team-events"}, groupId = "nfl-player-group-nps")
    public void consume(String message) {

        logger.info("Calling Consume in PlayerEventConsumer", kv("consumed-message", message));
    }
}