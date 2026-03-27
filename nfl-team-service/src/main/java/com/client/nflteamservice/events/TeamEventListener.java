package com.client.nflteamservice.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TeamEventListener {

    @EventListener
    public void handleGetAllTeamsEvent(GetAllTeamsEvent event) {
        System.out.println("We are in TeamEventListener!");
    }
}