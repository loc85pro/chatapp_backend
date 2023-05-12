package com.myproject.chatserver.controller;

import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.MalformedJwtException;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/")
public class RandomController {
    @GetMapping(value = "hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping(value = "admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdmin() {
        return "admin";
    }

    @PostMapping(value = "/")
    public String test() {
        return "this is test";
    }

}
