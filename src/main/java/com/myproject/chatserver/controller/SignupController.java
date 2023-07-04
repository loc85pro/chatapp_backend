package com.myproject.chatserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.chatserver.Model.SignupRequest;
import com.myproject.chatserver.Model.TokenModel;
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
    public TokenModel signupPost(@RequestBody SignupRequest request) {
        System.out.println("Dang signup");
        service.saveUser(request);
        // --------------------------
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(),
                                request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtProvider.generateToken(request.getUsername(), 300000L); // 1 minute for expiration
        String refreshToken = jwtProvider.generateToken(request.getUsername(), 900000L); // 5 minutes for expiration
        return new TokenModel(accessToken, refreshToken);
    }

}
