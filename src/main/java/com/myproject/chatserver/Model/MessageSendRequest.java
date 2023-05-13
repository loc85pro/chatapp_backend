package com.myproject.chatserver.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessageSendRequest {
    private String content;
    private String type;
}
