package com.myproject.chatserver.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CreateConversationRequest {
    private String username;
    private String id;
}
