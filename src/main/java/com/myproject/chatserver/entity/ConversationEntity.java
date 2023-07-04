package com.myproject.chatserver.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
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
    @Column(name = "seen")
    private boolean seen;
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ParticipantEntity> participant;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MessageEntity> message;

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
