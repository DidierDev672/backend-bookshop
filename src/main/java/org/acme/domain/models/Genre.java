package org.acme.domain.models;

import java.util.UUID;

public class Genre {
    private UUID uuid;
    private String name;

    public Genre(){}

    public Genre(UUID uuid, String name){
        this.uuid = uuid;
        this.name = name;
    }

    public Genre(UUID uuid, String name, Object o) {
    }

    public static Genre createGenre(String name){
        return new Genre(UUID.randomUUID(), name);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

