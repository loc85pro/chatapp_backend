package com.myproject.chatserver.controller;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.chatserver.Model.TokenModel;
import com.myproject.chatserver.security.JwtProvider;

@Controller
@RequestMapping(value = "/token")
public class TokenController {
    @Autowired
    JwtProvider jwtProvider;

    @GetMapping(value = "/refresh")
    public ResponseEntity<TokenModel> refreshToken(@RequestHeader("Token") String refreshToken) {
        if (refreshToken != null && refreshToken.startsWith("Bearer "))
            refreshToken.replace("Bearer ", "");
        if (!this.jwtProvider.validateToken(refreshToken))
            return ResponseEntity.status(401).body(null);
        else {
            String username = this.jwtProvider.getUsernameFromToken(refreshToken);
            String token = this.jwtProvider.generateToken(username, 6000);
            return ResponseEntity.status(200).body(new TokenModel(token, refreshToken));
        }
    }

}
