package com.myproject.chatserver.service;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.chatserver.Model.MessageSendRequest;
import com.myproject.chatserver.entity.ConversationEntity;
import com.myproject.chatserver.entity.MessageEntity;
import com.myproject.chatserver.repository.ConversationRepository;
import com.myproject.chatserver.repository.MessageRepository;
import com.myproject.chatserver.security.UserContext;

@RestController
@RequestMapping("/message")
public class MessageService {
    @Autowired
    EntityManager entityManager;
    @Autowired
    ConversationRepository conversationRepo;
    @Autowired
    MessageRepository messageRepo;

    public void addMessageIntoConversation(MessageSendRequest request, String conversationId) {
        String id = UUID.randomUUID().toString().replace("-", "");
        String userSend = UserContext.getUsername();
        ConversationEntity conversation = conversationRepo.getById(conversationId);
        MessageEntity message = new MessageEntity(id, conversation, userSend, "text-plain",
                request.getContent(), new Date());
        messageRepo.insertMessage(message);
    }
}
