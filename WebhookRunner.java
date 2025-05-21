package com.example.yourapp.runner;

import com.example.yourapp.model.WebhookResponse;
import com.example.yourapp.service.WebhookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class WebhookRunner implements CommandLineRunner {

    private final WebhookService webhookService;

    public WebhookRunner(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @Override
    public void run(String... args) throws Exception {
        String registrationNumber = "1292240030";

        // Step 1: Generate webhook and access token
        WebhookResponse webhookResponse = webhookService.generateWebhook(registrationNumber);
        System.out.println("Webhook URL: " + webhookResponse.getWebhook());
        System.out.println("Access Token: " + webhookResponse.getAccessToken());

        // Step 2: Prepare your SQL query
        String sql = "SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME, "
                + "COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT "
                + "FROM EMPLOYEE e1 "
                + "JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID "
                + "LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB "
                + "GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME "
                + "ORDER BY e1.EMP_ID DESC;";

        // Step 3: Call testWebhook with SQL and token
        ResponseEntity<String> response = webhookService.callTestWebhook(
                webhookResponse.getWebhook(),
                webhookResponse.getAccessToken(),
                sql
        );

        System.out.println("Test Webhook Response Status: " + response.getStatusCode());
        System.out.println("Test Webhook Response Body: " + response.getBody());
    }
}