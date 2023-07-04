package com.myproject.chatserver.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.Payload;

@Controller
public class SocketControler {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{id}")
    public void handleMessage(@Payload Message data, @DestinationVariable String id) throws Exception {
        System.out.println(data);

        simpMessagingTemplate.convertAndSend("/conversation/" + id, data);
    }
    @MessageMapping("/chat")
    @SendTo("/conversation/test")
    public String handleMessages(@Payload String data) throws Exception {
        return data;
    }
}
