package com.client.nflteamservice.service;

import com.client.nflteamservice.dto.TeamDTO;
import com.client.nflteamservice.events.GetAllTeamsEvent;
import com.client.nflteamservice.events.TeamCreatedEvent;
import com.client.nflteamservice.mapper.TeamMapper;
import com.client.nflteamservice.model.Team;
import com.client.nflteamservice.repository.TeamRepository;
import com.client.nflteamservice.team.TeamController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    private final TeamRepository repository;
    private final ApplicationEventPublisher eventPublisher;
    private final TeamMapper teamMapper;

    public TeamService(TeamRepository repository, ApplicationEventPublisher eventPublisher, TeamMapper teamMapper) {
        this.repository = repository;
        this.eventPublisher=eventPublisher;
        this.teamMapper = teamMapper;
    }

    public List<TeamDTO> getAllTeams() {

        logger.info("Executing TeamService.getllTeams...");
        logger.debug("Publishing event GetAllTeamsEvent.");

        eventPublisher.publishEvent(new GetAllTeamsEvent());

        logger.debug("Finding all Teams in MongoDB Repository");
        List<Team> allTeamEntities = repository.findAll();

        logger.debug("Accessed Team Database, all teams retrieved are "+allTeamEntities.toString());

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