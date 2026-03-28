package com.client.nflteamservice.kafka;

public interface EventProducer {
    void publishTeamsRetrieval(String string);
}
