package com.anwhiteko.vk.rest.auth.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;


public class JwtAuthException extends AuthenticationException {
    private HttpStatus httpStatus;

    public JwtAuthException(String explanation, HttpStatus httpStatus) {
        super(explanation);
        this.httpStatus = httpStatus;
    }
}
