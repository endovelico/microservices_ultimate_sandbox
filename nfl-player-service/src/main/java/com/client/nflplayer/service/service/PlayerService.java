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

        logger.info("Executing PlayerService.getllPlayer...");
        logger.debug("Publishing event RetrievePlayerEvent.");
        eventPublisher.publishEvent(new RetrievePlayerEvent());

        logger.debug("Finding all Teams in MongoDB Repository");
        List<Player> allPlayerEntities = repository.findAll();

        logger.debug("sending to Kafka the GetTeams Notification");
        playerEventProducer.publishPlayerRetrieval(allPlayerEntities.toString());

        logger.debug("Accessed Team Database, all players retrieved are "+allPlayerEntities.toString());
        return playerMapper.toDtoList(allPlayerEntities);
    }

    public PlayerDTO convertToDto(Player player) {
        return playerMapper.toDto(player);
    }

    public Player convertToEntity(PlayerDTO playerDTO) {
        return playerMapper.toEntity(playerDTO);
    }
}