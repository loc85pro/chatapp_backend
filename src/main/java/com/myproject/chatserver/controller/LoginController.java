package com.myproject.chatserver.controller;

import org.springframework.web.bind.annotation.RestController;

import com.myproject.chatserver.Model.LoginRequest;
import com.myproject.chatserver.security.JwtProvider;
import com.myproject.chatserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {
    @Autowired
    UserService service;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping(value = "")
    public String postLogin(@RequestBody LoginRequest requestBody) {
        System.out.println("co 1 thang dang muon log in" + requestBody.getUsername() + " " + requestBody.getPassword());
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(requestBody.getUsername(),
                                requestBody.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(requestBody.getUsername());
        System.out.println(jwt);
        return jwt;
    }
}
