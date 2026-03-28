package com.client.nflplayer.service.kafka;

public interface EventProducer {
    void publishPlayerRetrieval(String string);
}
