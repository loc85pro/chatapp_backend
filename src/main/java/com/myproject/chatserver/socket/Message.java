package com.myproject.chatserver.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Message {
    private String user;
    private String content;
    private String type;
    private String conversationId;
}
