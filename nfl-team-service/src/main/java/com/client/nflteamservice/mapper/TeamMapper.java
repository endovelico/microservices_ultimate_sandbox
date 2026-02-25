package com.client.nflteamservice.mapper;

import com.client.nflteamservice.dto.TeamDTO;
import com.client.nflteamservice.model.Team;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDTO toDto(Team team);

    Team toEntity(TeamDTO dto);

}
