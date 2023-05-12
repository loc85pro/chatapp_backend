package com.myproject.chatserver.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "participant_conversation")
public class ParticipantEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "conversationId")
    private ConversationEntity conversation;

    public ParticipantEntity(String conversationId, String username) {
        // this.conversationId = conversationId;
        this.username = username;
        UUID id = UUID.randomUUID();
        this.id = id.toString();
    }
}
