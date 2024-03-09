package com.anwhiteko.vk.rest.auth.jwt.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthException extends AuthenticationException {
    private final HttpStatus httpStatus;

    public JwtAuthException(String msg) {
        super(msg);
        this.httpStatus = HttpStatus.I_AM_A_TEAPOT;
    }

    public JwtAuthException(String explanation, HttpStatus httpStatus) {
        super(explanation);
        this.httpStatus = httpStatus;
    }
}
