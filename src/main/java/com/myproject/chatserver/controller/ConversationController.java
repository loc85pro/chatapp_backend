package com.myproject.chatserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.chatserver.Model.CreateConversationRequest;
import com.myproject.chatserver.Model.ListConversationResponse;
import com.myproject.chatserver.service.ConversationService;

@RestController
@RequestMapping(value = "/conversation")
public class ConversationController {
    @Autowired
    ConversationService service;

    @PostMapping("/create")
    public ResponseEntity<String> createNewConversation(@RequestBody CreateConversationRequest request) {
        service.createNewConversation(request);
        return ResponseEntity.status(200).body("Successfully");
    }

    @GetMapping("/getall")
    public ResponseEntity<ListConversationResponse> getAllConversation() {
        ListConversationResponse rs = new ListConversationResponse(service.getAll());
        return ResponseEntity.status(200).body(rs);
    }

}
