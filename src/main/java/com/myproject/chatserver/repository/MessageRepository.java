package com.myproject.chatserver.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myproject.chatserver.entity.MessageEntity;

@Repository
public class MessageRepository {
    @Autowired
    EntityManager entityManager;

    @Transactional
    public void insertMessage(MessageEntity message) {
        entityManager.persist(message);
    }

    public List<MessageEntity> getAllMessageInConversation(String id) {
        return entityManager.createQuery("FROM MessageEntity m WHERE m.conversation.id ==:id").getResultList();

    }
}
