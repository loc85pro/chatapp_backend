package com.myproject.chatserver.Model;

import java.util.List;

import com.myproject.chatserver.entity.ConversationEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListConversationResponse {
    private List<ConversationEntity> list;
}
