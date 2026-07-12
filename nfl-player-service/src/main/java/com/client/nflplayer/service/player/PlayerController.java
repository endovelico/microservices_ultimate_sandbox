package com.client.nflplayer.service.player;


import com.client.nflplayer.service.dto.PlayerDTO;
import com.client.nflplayer.service.external.ExternalPlayerClient;
import com.client.nflplayer.service.kafka.PlayerEventProducer;
import com.client.nflplayer.service.service.PlayerService;
import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    PlayerEventProducer playerEventProducer;

    @Autowired
    PlayerService playerService;

    @Autowired
    ExternalPlayerClient externalPlayerClient;


    @GetMapping
    public Flux<PlayerDTO> getPlayers() {

        logger.info("Retrieving all Players...");

        Mono<List<PlayerDTO>> playersMono =
            Mono.fromCallable(playerService::getAllPlayers)
                    .subscribeOn(Schedulers.boundedElastic());

        Mono<String> externalMono = externalPlayerClient.fetchExternalData();

        return Mono.zip(playersMono, externalMono)
                .flatMapMany(tuple -> {

                    logger.info("External call result: {}", tuple.getT2());
                    logger.debug("Retrieved {} players", tuple.getT1().size());

                    return Flux.fromIterable(tuple.getT1());
                });
    }

    public Mono<List<PlayerDTO>> getAllPlayersReactive() {
        return Mono.fromCallable(() -> playerService.getAllPlayers())
                .subscribeOn(Schedulers.boundedElastic());
    }
}