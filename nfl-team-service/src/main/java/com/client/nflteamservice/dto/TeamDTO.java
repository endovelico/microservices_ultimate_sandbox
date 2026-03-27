package com.client.nflteamservice.dto;

import org.bson.types.ObjectId;

public record TeamDTO(
        ObjectId id,
        String name,
        String city,
        String stadium
) {}
