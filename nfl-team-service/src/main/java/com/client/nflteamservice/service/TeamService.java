package com.client.nflteamservice.service;

import com.client.nflteamservice.dto.TeamDTO;
import com.client.nflteamservice.events.GetAllTeamsEvent;
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

    public List<TeamDTO> getAllTeams() {
        eventPublisher.publishEvent(new GetAllTeamsEvent());

        List<Team> allTeamEntities = repository.findAll();
        return teamMapper.toDtoList(allTeamEntities);
    }

    public Team getTeamByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public TeamDTO convertToDto(Team team) {
        return teamMapper.toDto(team);
    }

    public Team convertToEntity(TeamDTO teamDTO) {
        return teamMapper.toEntity(teamDTO);
    }
}