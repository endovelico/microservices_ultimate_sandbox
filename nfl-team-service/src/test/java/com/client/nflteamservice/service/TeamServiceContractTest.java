package com.client.nflteamservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class TeamServiceContractTest {

    @Autowired
    private TeamService teamService;

    public void triggerGetAllTeams() {
        teamService.getAllTeams();
    }
}