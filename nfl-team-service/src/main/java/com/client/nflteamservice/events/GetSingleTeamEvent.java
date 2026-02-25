package com.client.nflteamservice.events;

import com.client.nflteamservice.dto.TeamDTO;

public class GetSingleTeamEvent {

    private final TeamDTO singleEventTeam;

    public GetSingleTeamEvent(TeamDTO singleEventTeam) {
        this.singleEventTeam=singleEventTeam;
    }

    public TeamDTO getSingleEventTeam(){
        return singleEventTeam;
    }
}
