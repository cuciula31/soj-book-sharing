package com.soj.booksharing.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Book not found")
public class BookNotFoundException  extends RuntimeException{
    public BookNotFoundException(Integer id) {
        super("Could not find book " + id);
    }
}
