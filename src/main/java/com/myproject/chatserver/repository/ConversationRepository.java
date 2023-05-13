package com.myproject.chatserver.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myproject.chatserver.entity.ConversationEntity;

@Repository
public class ConversationRepository {
    @Autowired
    EntityManager entityManager;

    public ConversationEntity getById(String id) {
        return (ConversationEntity) entityManager.createQuery("FROM ConversationEntity con WHERE con.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    public void setName(String id, String name) {
        ConversationEntity conversation = (ConversationEntity) entityManager
                .createQuery("FROM ConversationEntity con WHERE con.id=:id").setParameter("id", id).getSingleResult();
        conversation.setName(name);
        entityManager.merge(conversation);
    }
}
