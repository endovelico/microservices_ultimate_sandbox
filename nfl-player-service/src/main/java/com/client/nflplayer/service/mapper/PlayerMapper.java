package com.client.nflplayer.service.mapper;

import com.client.nflplayer.service.dto.PlayerDTO;
import java.util.List;

import com.client.nflplayer.service.model.Player;
import org.mapstruct.Mapper;
import org.bson.types.ObjectId;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerDTO toDto(Player team);

    Player toEntity(PlayerDTO dto);

    // 🔥 List mapping
    List<PlayerDTO> toDtoList(List<Player> teams);

    List<Player> toEntityList(List<PlayerDTO> dtos);

    ObjectId map(Long value);
}
