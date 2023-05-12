package com.myproject.chatserver.service;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.myproject.chatserver.Model.CreateConversationRequest;
import com.myproject.chatserver.entity.ConversationEntity;
import com.myproject.chatserver.entity.ParticipantEntity;
import com.myproject.chatserver.repository.ParticipantRepository;
import com.myproject.chatserver.security.UserContext;

@Service
public class ConversationService {
    @Autowired
    ParticipantRepository repoParticipant;
    @Autowired
    EntityManager entityManager;

    @Transactional
    public void createNewConversation(CreateConversationRequest request) {
        Session session = entityManager.unwrap(Session.class);
        // -------------
        String username_1 = request.getUsername();
        String username_2 = "";
        username_2 = UserContext.getUsername();
        // ---------------
        UUID id = UUID.randomUUID();
        ConversationEntity conversationEntity = new ConversationEntity(id.toString(), "none", "inbox");
        ParticipantEntity participant_1 = new ParticipantEntity(id.toString(), username_1);
        ParticipantEntity participant_2 = new ParticipantEntity(id.toString(), username_2);
        participant_1.setConversation(conversationEntity);
        participant_2.setConversation(conversationEntity);
        System.out.println("this is id: " + id.toString());
        conversationEntity.getParticipant().add(participant_1);
        conversationEntity.getParticipant().add(participant_2);
        session.persist(conversationEntity);

        // repoConversation.save(conversationEntity);
    }

    public List<ConversationEntity> getAll() {
        // return repoConversation.findAll();
        return null;
    }
}
