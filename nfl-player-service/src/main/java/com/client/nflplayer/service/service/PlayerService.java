package com.client.nflplayer.service.service;

import com.client.nflplayer.service.dto.PlayerDTO;
import com.client.nflplayer.service.events.RetrievePlayerEvent;
import com.client.nflplayer.service.mapper.PlayerMapper;
import com.client.nflplayer.service.model.Player;
import com.client.nflplayer.service.repository.PlayerRepository;
import com.client.nflplayer.service.kafka.EventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import static net.logstash.logback.argument.StructuredArguments.kv;

import java.util.List;

@Service
public class PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);

    private PlayerRepository repository;
    private ApplicationEventPublisher eventPublisher;
    private final PlayerMapper playerMapper;
    private EventProducer playerEventProducer;

    public PlayerService(PlayerRepository repository, ApplicationEventPublisher eventPublisher, PlayerMapper playerMapper, EventProducer playerEventProducer) {
        this.repository = repository;
        this.eventPublisher=eventPublisher;
        this.playerMapper = playerMapper;
        this.playerEventProducer = playerEventProducer;
    }

    public List<PlayerDTO> getAllPlayers() {

    logger.info("Executing PlayerService.getAllPlayers",
            kv("player-ids", "all"),
            kv("team-ids", "all"));

    eventPublisher.publishEvent(new RetrievePlayerEvent());

    List<Player> players = repository.findAll();

    playerEventProducer.publishPlayerRetrieval(
            players.toString());

    return playerMapper.toDtoList(players);
}

    public PlayerDTO convertToDto(Player player) {
        return playerMapper.toDto(player);
    }

    public Player convertToEntity(PlayerDTO playerDTO) {
        return playerMapper.toEntity(playerDTO);
    }
}