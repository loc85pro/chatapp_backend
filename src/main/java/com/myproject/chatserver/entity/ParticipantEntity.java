package com.myproject.chatserver.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "participant")
public class ParticipantEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private ConversationEntity conversation;

    public ParticipantEntity(String conversationId, String username) {
        this.username = username;
        String id = UUID.randomUUID().toString().replace("-", "");
        this.id = id;
        this.nickname = this.username;
    }
}
