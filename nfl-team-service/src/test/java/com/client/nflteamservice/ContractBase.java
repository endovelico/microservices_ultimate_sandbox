package com.client.nflteamservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class ContractBase {

    @Autowired
    private TeamService teamService;

    public void triggerGetAllTeams() {
        teamService.getAllTeams();
    }
}