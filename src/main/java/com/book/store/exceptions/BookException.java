package com.book.store.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookException extends RuntimeException {


    private final HttpStatus status;
    private final String message;

    public BookException(HttpStatus status, String message) {
        super(status.getReasonPhrase());
        this.status = status;
        this.message = message;
    }


}