package com.myproject.chatserver.service;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.myproject.chatserver.Model.CreateConversationRequest;
import com.myproject.chatserver.entity.ConversationEntity;
import com.myproject.chatserver.entity.MessageEntity;
import com.myproject.chatserver.entity.ParticipantEntity;
import com.myproject.chatserver.repository.ConversationRepository;
import com.myproject.chatserver.repository.MessageRepository;
import com.myproject.chatserver.repository.ParticipantRepository;
import com.myproject.chatserver.security.UserContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ConversationService {
    @Autowired
    ParticipantRepository repoParticipant;
    @Autowired
    ConversationRepository conversationRepo;
    @Autowired
    MessageRepository messageRepo;
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
        String id = UUID.randomUUID().toString().replace("-", "");

        ConversationEntity conversationEntity = new ConversationEntity(id, "none", "inbox");
        ParticipantEntity participant_1 = new ParticipantEntity(id, username_1);
        ParticipantEntity participant_2 = new ParticipantEntity(id, username_2);
        participant_1.setConversation(conversationEntity);
        participant_2.setConversation(conversationEntity);
        System.out.println("this is id: " + id.toString());
        conversationEntity.getParticipant().add(participant_1);
        conversationEntity.getParticipant().add(participant_2);
        session.persist(conversationEntity);

    }

    public List<Object> getAll(String username) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery(
                "SELECT con.id FROM ParticipantEntity par INNER JOIN par.conversation con WHERE par.username=:username")
                .setParameter("username", username);
        return query.getResultList();
    }

    public void setConversationName(String id, String name) {
        conversationRepo.setName(id, name);
    }

    public List<MessageEntity> getAllMessage(String conversationId) {
        return messageRepo.getAllMessageInConversation(conversationId);
    }

}
