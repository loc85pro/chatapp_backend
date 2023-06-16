package com.myproject.chatserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/data")
public class DataController {
    @GetMapping("/initialize")
    public EntityResponse<Object> getDataInit() {
        return null;
    }

}
