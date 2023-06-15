package com.myproject.chatserver.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ValidationResponse {
    private String value;
    private String message;
}
