package com.myproject.chatserver.controller;

import org.springframework.web.bind.annotation.RestController;

import com.myproject.chatserver.Model.LoginRequest;
import com.myproject.chatserver.Model.TokenModel;
import com.myproject.chatserver.security.JwtProvider;
import com.myproject.chatserver.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public TokenModel postLogin(@RequestBody LoginRequest requestBody, HttpServletResponse response) {
        System.out.println("co 1 thang dang muon log in" + requestBody.getUsername() + " " + requestBody.getPassword());

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(requestBody.getUsername(),
                                requestBody.getPassword()));

        if (authentication != null)
            SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtProvider.generateToken(requestBody.getUsername(), 60000L); // 1 minute for expiration
        String refreshToken = jwtProvider.generateToken(requestBody.getUsername(), 3600000L); // 5 minutes for
        // expiration
        Cookie cookie = new Cookie("token", accessToken);
        response.addCookie(cookie);
        return new TokenModel(accessToken, refreshToken);
    }
}
