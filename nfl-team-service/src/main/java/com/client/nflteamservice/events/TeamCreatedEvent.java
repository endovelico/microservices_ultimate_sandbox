package com.client.nflteamservice.events;

import com.client.nflteamservice.dto.TeamDTO;

public class TeamCreatedEvent {

    private final TeamDTO createdEventTeam;

    public TeamCreatedEvent(TeamDTO createdEventTeam) {
        this.createdEventTeam=createdEventTeam;
    }

    public TeamDTO getCreatedEventTeam(){
        return createdEventTeam;
    }
}
