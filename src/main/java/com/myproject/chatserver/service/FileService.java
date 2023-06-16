package com.myproject.chatserver.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    public boolean saveFile(MultipartFile file, String filename, String path) {
        File myFile = new File(path);
        if (!myFile.exists())
            myFile.mkdirs();
        myFile = new File(path + '/' + filename);
        try {
            FileOutputStream out = new FileOutputStream(myFile);
            out.write(file.getBytes());
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public File loadAvatar(String username) {
        File file = new File("/src/main/resources/static/data/user/" + username + "/avatar.jpg");
        if (!file.exists())
            file = new File("/src/main/resources/static/general/default_avatar.png");
        return file;
    }
}
