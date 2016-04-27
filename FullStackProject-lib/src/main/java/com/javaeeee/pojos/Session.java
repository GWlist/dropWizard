package com.javaeeee.pojos;

public class Session {

    private final String username;
    private final String token;
    private final Long lastAccess;

    public Session(String username, String token, Long lastAccess) {
        this.username = username;
        this.token = token;
        this.lastAccess = lastAccess;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public Long getLastAccess() {
        return lastAccess;
    }

}
