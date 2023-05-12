package com.myproject.chatserver.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import io.jsonwebtoken.MalformedJwtException;

// @ControllerAdvice
// public class GlobalExceptionHandler {

// @ExceptionHandler(Exception.class)
// public ResponseEntity<String> handleGeneral(Exception ex) {
// System.out.println("Unknown error occurred");
// return
// ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went
// wrong hehe");
// }
// }
