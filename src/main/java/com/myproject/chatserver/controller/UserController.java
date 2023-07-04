package com.myproject.chatserver.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;

import com.myproject.chatserver.entity.UserEntity;
import com.myproject.chatserver.security.UserContext;
import com.myproject.chatserver.service.FileService;
import com.myproject.chatserver.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    @GetMapping("/get_all_username")
    public List<String> getMethodName() {
        return userService.getAllUsername();
    }

    @GetMapping("/get_own_data")
    public UserEntity getOwnData() {
        String username = UserContext.getUsername();
        return userService.getUserByUsername(username);
    }

    @GetMapping(value = "/get_avatar{username}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getAvatar(@RequestParam String username) {
        UserEntity user = userService.getUserByUsername(username);
        String path = "src/main/resources/static/data/user/" + user.getUsername() + "/avatar.jpg";
        File myFile = new File(path);
        if (!myFile.exists())
            path = "src/main/resources/static/general/default_avatar.png";
        try {
            FileInputStream in = new FileInputStream(path);
            return ResponseEntity.status(200).body(in.readAllBytes());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(404).body(null);
        }
    }
}
