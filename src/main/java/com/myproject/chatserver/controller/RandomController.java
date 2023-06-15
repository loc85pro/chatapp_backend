package com.myproject.chatserver.controller;

import org.springframework.web.bind.annotation.RestController;

import com.myproject.chatserver.Model.ValidationResponse;
import com.myproject.chatserver.entity.UserEntity;
import com.myproject.chatserver.repository.UserRepository;
import com.myproject.chatserver.security.UserContext;
import com.myproject.chatserver.service.ConversationService;

import java.util.List;

import javax.persistence.ValidationMode;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value = "/other")
public class RandomController {
    @Autowired
    UserRepository repo;
    @Autowired
    ConversationService service;

    @GetMapping(value = "hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping(value = "admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdmin() {
        return "admin";
    }

    @GetMapping(value = "/check_authentication")
    public ResponseEntity<ValidationResponse> checkAuthenticationByToken() {
        return ResponseEntity.status(200).body(new ValidationResponse("Successfuly", ""));
    }

    @GetMapping(value = "getalluser")
    public List<UserEntity> getAllUser() {
        System.out.println("Hello");
        return repo.getAll();
    }

    @GetMapping(value = "getallconversation")
    public Object getAllConversation() {
        return service.getAll(UserContext.getUsername());
    }

}
