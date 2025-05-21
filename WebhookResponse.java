package com.example.yourapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookResponse {

    private String webhook;

    @JsonProperty("accessToken")
    private String accessToken;

    // getters and setters
    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}