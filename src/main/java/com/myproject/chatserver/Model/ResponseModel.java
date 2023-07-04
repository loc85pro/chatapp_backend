package com.myproject.chatserver.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseModel {
    private String message;
    private Integer status;
}
