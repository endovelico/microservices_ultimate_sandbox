package com.client.nflteamservice.service;

import com.client.nflteamservice.dto.TeamDTO;
import com.client.nflteamservice.events.TeamCreatedEvent;
import com.client.nflteamservice.mapper.TeamMapper;
import com.client.nflteamservice.model.Team;
import com.client.nflteamservice.repository.TeamRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository repository;
    private final ApplicationEventPublisher eventPublisher;
    private final TeamMapper teamMapper;

    public TeamService(TeamRepository repository, ApplicationEventPublisher eventPublisher, TeamMapper teamMapper) {
        this.repository = repository;
        this.eventPublisher=eventPublisher;
        this.teamMapper = teamMapper;
    }

    private Team saveTeam(Team team) {
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

    public Team createTeam(Team team) {
        Team savedTeam = saveTeam(team);
        //eventPublisher.publishEvent(new TeamCreatedEvent(savedTeam));
        return savedTeam;
    }

    public TeamDTO convertToDto(Team team) {
        return teamMapper.toDto(team);
    }
}