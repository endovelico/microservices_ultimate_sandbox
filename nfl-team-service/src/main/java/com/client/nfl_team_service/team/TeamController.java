package com.client.nfl_team_service.team;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}