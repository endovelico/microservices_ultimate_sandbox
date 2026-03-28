package com.client.nflteamservice.service;

import com.client.nflteamservice.dto.TeamDTO;
import com.client.nflteamservice.events.GetAllTeamsEvent;
import com.client.nflteamservice.model.Team;
import com.client.nflteamservice.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.containers.KafkaContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import org.testcontainers.utility.DockerImageName;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.apache.kafka.common.serialization.StringDeserializer;

import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

//@Testcontainers
//@SpringBootTest
public class TeamServiceEndToEndTest {
/*
    // ✅ MongoDB container
    @Container
    static MongoDBContainer mongo =
            new MongoDBContainer("mongo:6.0");

    // ✅ Kafka container
    @Container
    static KafkaContainer kafka =
            new KafkaContainer(
                    DockerImageName.parse("confluentinc/cp-kafka:7.5.0")
            );

    // ✅ Inject dynamic properties into Spring
    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri",
                mongo::getReplicaSetUrl);

        registry.add("spring.kafka.bootstrap-servers",
                kafka::getBootstrapServers);
    }

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository repository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Test
    void shouldFetchTeams_andPublishEvent_andSendKafkaMessage() {

        // Arrange → insert real data into MongoDB
        Team team1 = new Team("Team A", "City A", "Stadium A");
        Team team2 = new Team("Team B", "City B", "Stadium B");

        repository.saveAll(List.of(team1, team2));

        // Act
        List<TeamDTO> result = teamService.getAllTeams();

        // Assert DB → service mapping
        assertEquals(2, result.size());

        // ⚠️ Kafka verification (see below)
    }

    @Test
    void shouldSendKafkaMessage() throws Exception {

        repository.save(new Team("Team A", "City A", "Stadium A"));

        // Create consumer
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Consumer<String, String> consumer =
                new DefaultKafkaConsumerFactory<String, String>(props,
                        new StringDeserializer(),
                        new StringDeserializer()
                ).createConsumer();

        consumer.subscribe(Collections.singletonList("your-topic-name"));

        // Act
        teamService.getAllTeams();

        // Assert → read message
        ConsumerRecord<String, String> record =
                KafkaTestUtils.getSingleRecord(consumer, "your-topic-name");

        assertTrue(record.value().contains("Team A"));
    }

    @Component
    static class TestEventListener {
        boolean eventReceived = false;

        @EventListener
        public void handle(GetAllTeamsEvent event) {
            eventReceived = true;
        }
    }

    @Autowired
    private TestEventListener listener;

    @Test
    void shouldPublishEvent() {
        repository.save(new Team("Team A", "City A", "Stadium A"));

        teamService.getAllTeams();

        assertTrue(listener.eventReceived);
    }*/
}
