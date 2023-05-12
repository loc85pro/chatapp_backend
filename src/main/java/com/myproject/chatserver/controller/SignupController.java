package com.myproject.chatserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.chatserver.Model.SignupRequest;
import com.myproject.chatserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.myproject.chatserver.security.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping(value = "/api/signup")
public class SignupController {
    @Autowired
    UserService service;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping(value = "")
    public String signupPost(@RequestBody SignupRequest request) {
        System.out.println("Dang signup");
        service.saveUser(request);
        // --------------------------
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(),
                                request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(request.getUsername());
        System.out.println(jwt);
        return jwt;
    }

}
