package com.mateeooss.projeto.usando.openAI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateeooss.projeto.usando.openAI.models.MessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatService {
    private final String URL = "https://api.openai.com/v1/chat/completions";

    private final String AUTHORIZATION;

    public ChatService(
        @Value("${openAI.authorization}")
        String authorization
    ) {
        AUTHORIZATION = authorization;
    }

    public String sendMessage(MessageRequest messageRequest) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization",AUTHORIZATION);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        String requestBody =  new ObjectMapper().writeValueAsString(messageRequest);
        RequestEntity<String> requestEntity = RequestEntity
                .post(URL)
                .headers(headers)
                .body(requestBody);

        String json = restTemplate.exchange(requestEntity, String.class).getBody();
        return json;
    }
}
