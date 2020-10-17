package com.shandilya.chalo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.IM_USED)
public class CabAlreadyExistsException extends RuntimeException {
    public CabAlreadyExistsException(String message) {
        super(message);
    }
}