package com.myproject.chatserver.exception;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.myproject.chatserver.Model.ErrorResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorResponse handleDataIntegrityViolationException(Exception ex) {
        return new ErrorResponse("Username is exist", "");
    }

    // -------------------------------------

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(Exception ex) {
        System.out.println("bad credentials");
        return ResponseEntity.status(404).body(new ErrorResponse("Bad Credentials",
                ""));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFound(Exception ex) {
        System.out.println("User not found");
        return ResponseEntity.status(401).body(new ErrorResponse("Username not found", ""));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleGeneral(Exception ex) {
        System.out.println(ex);
        System.out.println("Unknown error occurred - catch by ControllerAdvice");
        return new ErrorResponse("Bad request", "");
    }
}
