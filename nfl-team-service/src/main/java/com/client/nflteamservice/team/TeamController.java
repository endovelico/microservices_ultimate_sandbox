package com.client.nflteamservice.team;

import com.client.nflteamservice.dto.TeamDTO;
import com.client.nflteamservice.external.ExternalTeamClient;
import com.client.nflteamservice.kafka.TeamEventProducer;
import com.client.nflteamservice.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ExternalTeamClient externalTeamClient;

    @GetMapping
    public Flux<TeamDTO> getTeams() {

        // Here we get Teams
        logger.info("Retrieving all Teams...");
        List<TeamDTO> allTeams = teamService.getAllTeams();
        logger.debug("Retrieved teams: " + allTeams.toString());

        // In teams lets try to then call something external, but blocked by getAllTeams()
        // call external service (if needed)
        logger.info("Now we are going to call sync externalCall");
        String externalCall = externalTeamClient.fetchExternalData();
        logger.info("Called externalCall with result"+externalCall);

        return Flux.fromIterable(allTeams);
    }
}