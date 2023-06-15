package com.myproject.chatserver.Model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenModel {
    public String accessToken;
    public String refreshToken;
}
