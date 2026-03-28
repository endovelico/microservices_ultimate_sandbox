package com.client.nflplayer.service.dto;

import org.bson.types.ObjectId;

public record PlayerDTO (
    ObjectId id,
    String name,
    String college,
    String team,
    String position
) {}

