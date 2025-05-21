package com.example.yourapp.service;

import com.example.yourapp.model.WebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    @Autowired
    private RestTemplate restTemplate;

    public WebhookResponse generateWebhook(String regNo) {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{"regNo":"" + regNo + ""}";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(url, entity, WebhookResponse.class);
        return response.getBody();
    }

    public ResponseEntity<String> callTestWebhook(String webhookUrl, String accessToken, String sqlQuery) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        String jsonBody = "{"finalQuery":"" + sqlQuery + ""}";

        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        return restTemplate.postForEntity(webhookUrl, entity, String.class);
    }
}