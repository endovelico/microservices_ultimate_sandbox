package com.client.nflteamservice.mapper;

import com.client.nflteamservice.dto.TeamDTO;
import com.client.nflteamservice.model.Team;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDTO toDto(Team team);

    Team toEntity(TeamDTO dto);

    // 🔥 List mapping
    List<TeamDTO> toDtoList(List<Team> teams);

    List<Team> toEntityList(List<TeamDTO> dtos);

}
