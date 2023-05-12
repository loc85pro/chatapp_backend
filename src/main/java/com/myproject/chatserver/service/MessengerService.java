package com.myproject.chatserver.service;

import java.sql.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.chatserver.entity.MessengerEntity;
import com.myproject.chatserver.security.UserContext;

@RestController
@RequestMapping("/messenger")
public class MessengerService {

    @PostMapping(value = "/{conversationID}/text")
    public ResponseEntity<String> addNewMessenger(@RequestBody String messengerContent,
            @PathVariable("conversationID") String conversationId) {
        UUID id = UUID.randomUUID();
        String userSend = UserContext.getUsername();
        MessengerEntity messenger = new MessengerEntity(id.toString(), conversationId, userSend, "text-plain",
                messengerContent, new Date(new java.util.Date().getTime()));
        // repo.save(messenger);
        return ResponseEntity.status(200).body("Successfully");
    }
}
