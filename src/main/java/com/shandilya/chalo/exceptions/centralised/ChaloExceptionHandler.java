package com.shandilya.chalo.exceptions.centralised;

import com.shandilya.chalo.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ChaloExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timeStamp(new Date())
                .message("Validation Failed")
                .details(ex.getBindingResult().toString())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex,
                                                            WebRequest webRequest) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timeStamp(new Date())
                .message(ex.getMessage())
                .details(webRequest.getDescription(false))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CabNotFoundException.class)
    public final ResponseEntity<Object> handleCabNotFoundException(CabNotFoundException ex,
                                                                   WebRequest webRequest) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timeStamp(new Date())
                .message(ex.getMessage())
                .details(webRequest.getDescription(false))
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CabAlreadyExistsException.class)
    public final ResponseEntity<Object> handleCabAlreadyExistsException(CabAlreadyExistsException ex,
                                                                        WebRequest webRequest) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timeStamp(new Date())
                .message(ex.getMessage())
                .details(webRequest.getDescription(false))
                .build(), HttpStatus.IM_USED);
    }

    @ExceptionHandler(NoCabsAvailableException.class)
    public final ResponseEntity<Object> handleNoCabsAvailableException(NoCabsAvailableException ex,
                                                                       WebRequest webRequest) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timeStamp(new Date())
                .message(ex.getMessage())
                .details(webRequest.getDescription(false))
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RiderAlreadyExistsException.class)
    public final ResponseEntity<Object> handleRiderAlreadyExistsException(RiderAlreadyExistsException ex,
                                                                          WebRequest webRequest) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timeStamp(new Date())
                .message(ex.getMessage())
                .details(webRequest.getDescription(false))
                .build(), HttpStatus.IM_USED);
    }

    @ExceptionHandler(RiderNotFoundException.class)
    public final ResponseEntity<Object> handleRiderNotFoundException(RiderNotFoundException ex,
                                                                     WebRequest webRequest) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timeStamp(new Date())
                .message(ex.getMessage())
                .details(webRequest.getDescription(false))
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TripNotFoundException.class)
    public final ResponseEntity<Object> handleTripNotFoundException(TripNotFoundException ex,
                                                                    WebRequest webRequest) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timeStamp(new Date())
                .message(ex.getMessage())
                .details(webRequest.getDescription(false))
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoCabsFoundInRange.class)
    public final ResponseEntity<Object> handleNoCabsFoundInRange(NoCabsFoundInRange ex,
                                                                 WebRequest webRequest) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timeStamp(new Date())
                .message(ex.getMessage())
                .details(webRequest.getDescription(false))
                .build(), HttpStatus.NOT_FOUND);
    }
}