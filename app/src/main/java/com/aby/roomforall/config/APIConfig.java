package com.aby.roomforall.config;



public class APIConfig {

    private final String client_id;
    private final String client_secret;
    private final String host;

    public APIConfig(String client_id, String client_secret, String host) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.host = host;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getHost() {
        return host;
    }
}