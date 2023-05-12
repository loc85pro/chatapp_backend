package com.myproject.chatserver.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myproject.chatserver.entity.ParticipantEntity;

@Repository
public class ParticipantRepository {
    @Autowired
    private EntityManager entityManager;

    public List<ParticipantEntity> getByConversation(String id) {
        TypedQuery<ParticipantEntity> query = entityManager
                .createQuery("SELECT p FROM ParticipantEntity p WHERE p.id = :id", ParticipantEntity.class)
                .setParameter("id", id);
        return query.getResultList();
    }
}
