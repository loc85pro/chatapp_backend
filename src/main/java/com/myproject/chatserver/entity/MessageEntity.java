package com.myproject.chatserver.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private ConversationEntity conversation;
    @ManyToOne
    private UserEntity user;
    private String type;
    private String content;
    private Date time;
}
