package com.example.spring_security_04web_verifycode.security.exception;


import org.springframework.security.core.AuthenticationException;

public class KaptchaNotMatchException extends AuthenticationException {



    public KaptchaNotMatchException(String msg,Throwable cause) {
        super(msg,cause);
    }

    public KaptchaNotMatchException(String msg) {
        super(msg);
    }
}
