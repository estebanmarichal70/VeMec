package com.vemec.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vemec.api.models.reporte.Reporte;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private static ObjectMapper mapper;
    private static SimpMessagingTemplate messageTemplate;

    public WebSocketController(ObjectMapper mapper, SimpMessagingTemplate messageTemplate) {
        WebSocketController.mapper = mapper;
        WebSocketController.messageTemplate = messageTemplate;
    }

    public static void enviarAlertaReporte(Reporte reporte) throws JsonProcessingException {
        WebSocketController.messageTemplate.convertAndSend("/alertas/reportes",
                WebSocketController.mapper.writeValueAsString(reporte));
    }
}
