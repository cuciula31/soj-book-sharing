package com.soj.booksharing.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends RuntimeException {

   public UserNotFoundException(Integer id) {
        super("Could not find employee " + id);
    }

}
