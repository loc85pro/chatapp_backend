package com.myproject.chatserver.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "message")
public class MessageEntity {
    @Id
    private String id;
    @ManyToOne
    @JsonIgnore
    private ConversationEntity conversation;

    private String username;
    private String type;
    private String content;
    private Date time;

    @PrePersist
    protected void onPersist() {
        time = new Date();
    }
}
