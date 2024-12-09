package org.acme.domain.models.user;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class Token {
    private UUID uuid;
    private String username;
    private String token;

    public Token(){}

    public Token(UUID uuid, String username, String token){
        this.uuid = uuid;
        this.username = username;
        this.token = token;
    }

    public static Token init(UUID uuid, String username, String token){
        return new Token(uuid, username, token);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
