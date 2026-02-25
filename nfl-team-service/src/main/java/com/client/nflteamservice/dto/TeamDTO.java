package com.client.nflteamservice.dto;

public record TeamDTO(
        Long id,
        String name,
        String city,
        String stadium
) {}
