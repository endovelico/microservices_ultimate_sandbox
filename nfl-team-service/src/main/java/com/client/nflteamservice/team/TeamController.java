package com.client.nflteamservice.team;

import com.client.nflteamservice.dto.TeamDTO;
import com.client.nflteamservice.kafka.TeamEventProducer;
import com.client.nflteamservice.service.TeamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    TeamEventProducer teamEventProducer;

    @Autowired
    TeamService teamService;

    @GetMapping
    public Flux<TeamDTO> getTeams() {
        logger.info("Retrieving all Teams...");

        List<TeamDTO> allTeams = teamService.getAllTeams();

        logger.debug("Retrieved teams: " + allTeams.toString());

        return Flux.fromIterable(allTeams);
    }
}