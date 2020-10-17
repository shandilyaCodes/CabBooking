package com.shandilya.chalo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class RiderAlreadyInTripException extends RuntimeException {
    public RiderAlreadyInTripException(String message) {
        super(message);
    }
}