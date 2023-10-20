package com.mateeooss.projeto.usando.openAI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mateeooss.projeto.usando.openAI.models.MessageRequest;
import com.mateeooss.projeto.usando.openAI.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatbot")
@AllArgsConstructor
public class openAIController {
    private final ChatService chatService;


    @PostMapping("/send-message")
    public String sendMessage(@RequestBody MessageRequest messageRequest) throws JsonProcessingException {
        return this.chatService.sendMessage(messageRequest);
    }
}
