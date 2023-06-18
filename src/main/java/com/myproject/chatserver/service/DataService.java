package com.myproject.chatserver.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.stereotype.Service;
import com.myproject.chatserver.entity.ParticipantEntity;
import com.myproject.chatserver.entity.ConversationEntity;

@Service
public class DataService {
    @PersistenceContext
    private EntityManager entityManager;

    // SELECT par FROM ParticipantEntity par JOIN ConversationEntity con ON
    // par.conversation.id=con.id WHERE par.conversation.id in(select
    // par_1.conversation.id from ParticipantEntity par_1 where
    // par_1.username=:username)
    public Object init(String username) {
        return entityManager.createQuery(
                "FROM ConversationEntity con JOIN con.participant par WHERE par.username=:username ORDER BY con.lastUpdate")
                .setParameter("username", username).getResultList();
    }
}
