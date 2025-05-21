# Spring Boot Webhook Solution

This project is a Spring Boot application designed to automatically submit a SQL query solution to a specified webhook URL using a POST request with JWT authorization. It runs the submission on application startup without any external triggers.

---

## Features

- Sends a POST request to the webhook URL on startup.
- Includes an Authorization Bearer JWT token header.
- Sends JSON body containing the final SQL query.
- Prints the server response in the console.
- No controller or endpoint is required to trigger the flow.
- Uses `RestTemplate` (or `WebClient`) for HTTP calls.

---

## Project Structure


