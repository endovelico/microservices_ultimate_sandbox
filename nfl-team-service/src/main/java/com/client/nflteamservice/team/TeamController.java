package com.client.nflteamservice.team;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @GetMapping
    public Flux<Team> getTeams() {
        return Flux.just(
                new Team("KC", "Kansas City Chiefs", "AFC West"),
                new Team("SF", "San Francisco 49ers", "NFC West"),
                new Team("DAL", "Dallas Cowboys", "NFC East")
        );
    }

    @PostMapping("/new")
    public ResponseEntity<String> createTeam(@RequestBody Team team) {
        // Save team to database (if you have one)
        //teamService.save(team);

        // Publish Kafka event
        //String teamJson = new ObjectMapper().writeValueAsString(team);
        //teamEventProducer.publishTeamCreated(teamJson);

        return ResponseEntity.ok("Team created and event published");
    }
}