package com.myproject.chatserver.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "messenger")
public class MessengerEntity {
    @Id
    private String messenger_Id;
    private String conversation_Id;
    private String username;
    private String type;
    private String content;
    private Date time;
}
