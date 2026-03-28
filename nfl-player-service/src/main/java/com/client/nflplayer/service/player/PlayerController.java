package com.client.nflplayer.service.player;


import com.client.nflplayer.service.dto.PlayerDTO;
import com.client.nflplayer.service.kafka.PlayerEventProducer;
import com.client.nflplayer.service.service.PlayerService;
import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    PlayerEventProducer playerEventProducer;

    @Autowired
    PlayerService playerService;

    @GetMapping
    public Flux<PlayerDTO> getTeams() {
        logger.info("Retrieving all Players...");

        List<PlayerDTO> allPlayers = playerService.getAllPlayers();

        logger.debug("Retrieved players: " + allPlayers.toString());

        return Flux.fromIterable(allPlayers);
    }
}