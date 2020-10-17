package com.shandilya.chalo.exceptions.centralised;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ExceptionResponse {
    private Date timeStamp;
    private String message;
    private String details;
}