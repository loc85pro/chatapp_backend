package com.myproject.chatserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.myproject.chatserver.security.UserContext;
import com.myproject.chatserver.service.DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping("/initialize")
    public Object getDataInit() {
        System.out.println(dataService.init(UserContext.getUsername()));
        return dataService.init(UserContext.getUsername());

    }

}
