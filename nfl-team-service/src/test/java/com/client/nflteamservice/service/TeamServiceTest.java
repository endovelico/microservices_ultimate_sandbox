package com.client.nflteamservice.service;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import com.client.nflteamservice.repository.TeamRepository;
import com.client.nflteamservice.mapper.TeamMapper;
import com.client.nflteamservice.kafka.TeamEventProducer;
import org.springframework.context.ApplicationEventPublisher;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import com.client.nflteamservice.model.Team;
import com.client.nflteamservice.dto.TeamDTO;
import org.bson.types.ObjectId;
import static org.mockito.Mockito.times;
import org.springframework.kafka.core.KafkaTemplate;


import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository repository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private TeamMapper teamMapper;

    private TeamEventProducer teamEventProducer;

    @InjectMocks
    private TeamService teamService;

    @BeforeEach
    void setUp() { // 1TBS or Strouspot

        // 👇 HERE is where you put it
        teamEventProducer = new TeamEventProducer(mock(KafkaTemplate.class));
    }


    @Test
    void testGetAllTeams() {
        // Arrange: mock repository
        Team team1 = new Team("Team A", "City A", "Stadium A");
        Team team2 = new Team("Team B", "City B", "Staadium B");
        List<Team> teams = Arrays.asList(team1, team2);

        when(repository.findAll()).thenReturn(teams);

        // mock mapper
        TeamDTO dto1 = new TeamDTO(new ObjectId(),"Team A", "City A", "Stadium A");
        TeamDTO dto2 = new TeamDTO(new ObjectId(), "Team B", "City B", "Stadium B");
        when(teamMapper.toDtoList(teams)).thenReturn(Arrays.asList(dto1, dto2));

        // Act
        List<TeamDTO> result = teamService.getAllTeams();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Team A", result.get(0).name());
        assertEquals("Team B", result.get(1).name());

        // Verify events published
        verify(eventPublisher, times(1)).publishEvent(any());
        verify(teamEventProducer, times(1)).publishTeamsRetrieval(teams.toString());
    }

    @Test
    void testConvertToDtoAndEntity() {
        Team team = new Team("Team A", "Stadium A", "City A");
        TeamDTO dto = new TeamDTO(new ObjectId(), "Team A", "Stadium A", "City A");

        when(teamMapper.toDto(team)).thenReturn(dto);
        when(teamMapper.toEntity(dto)).thenReturn(team);

        assertEquals(dto, teamService.convertToDto(team));
        assertEquals(team, teamService.convertToEntity(dto));
    }
}
