package com.client.nflplayer.service.events;

import com.client.nflplayer.service.dto.PlayerDTO;

import java.util.ArrayList;

public class RetrievePlayerEvent {

    private final ArrayList<PlayerDTO> playerList = null;

    public RetrievePlayerEvent() {
    }

    public ArrayList<PlayerDTO> getTeamsList() {
        return playerList;
    }
}
