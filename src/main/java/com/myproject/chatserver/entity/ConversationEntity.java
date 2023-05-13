package com.myproject.chatserver.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "conversation")
public class ConversationEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "last_update")
    private Date lastUpdate;
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<ParticipantEntity> participant;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<MessageEntity> messenger;

    public ConversationEntity(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.participant = new ArrayList<>();
        this.lastUpdate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdate = new Date();
    }
}
