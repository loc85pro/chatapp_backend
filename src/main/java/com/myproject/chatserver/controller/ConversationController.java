package com.myproject.chatserver.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.myproject.chatserver.Model.CreateConversationRequest;
import com.myproject.chatserver.entity.MessageEntity;
import com.myproject.chatserver.security.UserContext;
import com.myproject.chatserver.service.ConversationService;
import com.myproject.chatserver.service.MessageService;

@RestController
@RequestMapping(value = "/conversation")
public class ConversationController {
    @Autowired
    ConversationService conversationService;
    @Autowired
    MessageService messageService;

    @PostMapping("/create")
    public ResponseEntity<String> createNewConversation(@RequestBody CreateConversationRequest request) {
        conversationService.createNewConversation(request);
        return null;
        // return ResponseEntity.status(200).(JSONPObject());
    }

    @GetMapping("/getall")
    public ResponseEntity<Object> getAllConversation() {
        String user = UserContext.getUsername();
        List<Object> rs = conversationService.getAll(user);
        return ResponseEntity.status(200).body(rs);
    }

    @Transactional
    @PutMapping("/{conversationId}/set_conversation_name")
    public ResponseEntity<String> setConversationName(@PathVariable String conversationId,
            @RequestBody String newName) {
        conversationService.setConversationName(conversationId, newName);
        return ResponseEntity.status(200).body("Successfully");
    }

    @GetMapping(value = "/{conversationId}/getallmessage")
    public ResponseEntity<List<MessageEntity>> getAllMessage(@PathVariable String conversationId) {
        List<MessageEntity> allMessage = conversationService.getAllMessage(conversationId);
        return ResponseEntity.status(200).body(allMessage);
    }

}
