package com.client.nflteamservice.service;

import com.client.nflteamservice.model.Team;
import com.client.nflteamservice.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public Team saveTeam(Team team) {
        return repository.save(team);
    }

    public List<Team> getAllTeams() {
        return repository.findAll();
    }

    public Team getTeamByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public void deleteTeam(String id) {
        repository.deleteById(id);
    }
}