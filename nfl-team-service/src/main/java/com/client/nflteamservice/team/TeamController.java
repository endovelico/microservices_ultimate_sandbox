package com.client.nflteamservice.team;

import com.client.nflteamservice.kafka.TeamEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamEventProducer teamEventProducer;

    @GetMapping
    public Flux<Team> getTeams() {
        return Flux.just(
                new Team("KC", "Kansas City Chiefs", "AFC West"),
                new Team("SF", "San Francisco 49ers", "NFC West"),
                new Team("DAL", "Dallas Cowboys", "NFC East")
        );
    }

    @PostMapping("/new")
    public ResponseEntity<String> createTeam(@RequestBody Team team) throws JsonProcessingException {
        // Save team to database (if you have one)
        //teamService.save(team);

        try {
        // Publish Kafka event
        String teamJson = new ObjectMapper().writeValueAsString(team);
        teamEventProducer.publishTeamCreated(teamJson);
        } catch(Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }

        return ResponseEntity.ok("Team created and event published v2");
    }
}