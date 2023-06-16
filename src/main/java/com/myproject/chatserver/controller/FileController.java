package com.myproject.chatserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.chatserver.Model.MessageSendRequest;
import com.myproject.chatserver.security.UserContext;
import com.myproject.chatserver.service.FileService;
import com.myproject.chatserver.service.UserService;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    @PostMapping("/uploadFile")
    public ResponseEntity<MessageSendRequest> uploadFile(@RequestParam("file") MultipartFile file) {
        String path = "src/main/resources/static/data/user/file/" + UserContext.getUsername();
        if (fileService.saveFile(file, file.getOriginalFilename(), path))
            return ResponseEntity.status(200).body(new MessageSendRequest("Successfuly", ""));
        else
            return ResponseEntity.status(404).body(new MessageSendRequest("Fail", ""));
    }

    @PostMapping("/uploadAvatar")
    public ResponseEntity<MessageSendRequest> uploadAvatar(@RequestParam("file") MultipartFile file) {
        String path = "src/main/resources/static/data/user/" + UserContext.getUsername();
        if (fileService.saveFile(file, "avatar.jpg", path)) {
            return ResponseEntity.status(200).body(new MessageSendRequest("Successfuly", ""));
        } else
            return ResponseEntity.status(404).body(new MessageSendRequest("Fail", ""));
    }
}
