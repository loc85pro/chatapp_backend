package com.myproject.chatserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.chatserver.Model.MessageSendRequest;
import com.myproject.chatserver.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping(value = "/{conversationId}")
    public ResponseEntity<String> addMessage(@RequestBody MessageSendRequest request,
            @PathVariable String conversationId) {
        messageService.addMessageIntoConversation(request, conversationId);
        return ResponseEntity.status(200).body("Successfully");
    }

}
