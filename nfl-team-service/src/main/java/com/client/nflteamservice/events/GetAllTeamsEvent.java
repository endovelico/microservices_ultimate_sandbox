package com.client.nflteamservice.events;

import com.client.nflteamservice.dto.TeamDTO;

import java.util.ArrayList;

public class GetAllTeamsEvent {

    private final ArrayList<TeamDTO> teamsList;

    public GetAllTeamsEvent(ArrayList<TeamDTO> teamsList) {
        this.teamsList=teamsList;
    }

    public ArrayList<TeamDTO> getTeamsList() {
        return teamsList;
    }
}
