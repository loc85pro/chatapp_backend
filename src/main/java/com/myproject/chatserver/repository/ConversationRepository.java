package com.myproject.chatserver.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConversationRepository {
    @Autowired
    EntityManager entityManager;

    public void insertConversation() {

    }
}
