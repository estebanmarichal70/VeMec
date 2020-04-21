package com.vemec.api.controllers;

import com.vemec.api.models.reporte.Reporte;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@Controller
public class WebSocketController {

    private static ObjectMapper mapper;
    private static SimpMessagingTemplate messageTemplate;

    public WebSocketController(ObjectMapper mapper, SimpMessagingTemplate messageTemplate) {
        WebSocketController.mapper = mapper;
        WebSocketController.messageTemplate = messageTemplate;
    }

    public static void sendWebSocketUpdate(Map<String, Object> data) throws JsonProcessingException {
        messageTemplate.convertAndSend("/alertas/reportes",
                mapper.writeValueAsString(data));
    }
}
