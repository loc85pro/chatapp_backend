package com.myproject.chatserver.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.chatserver.Model.ValidationResponse;
import com.myproject.chatserver.service.UserService;

@Controller
@RequestMapping(value = "/validation")
public class ValidationController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/user_existing")
    public ResponseEntity<ValidationResponse> getMethodName(@RequestParam(name = "username") String username) {
        System.out.println(username);
        if (userService.getUserByUsername(username) == null)
            return ResponseEntity.status(200).body(new ValidationResponse(username, "Accepted"));
        return ResponseEntity.status(200).body(new ValidationResponse(username, "Conflict"));
    }

}
