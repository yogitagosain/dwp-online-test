package com.dwp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String id) {
        super("User id not found : "+ id);
    }
}
