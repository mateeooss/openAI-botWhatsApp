package com.mateeooss.projeto.usando.openAI.models;

import lombok.Data;

import java.util.List;

@Data
public class MessageRequest {
    private String model;
    private List<Message> messages;
}
