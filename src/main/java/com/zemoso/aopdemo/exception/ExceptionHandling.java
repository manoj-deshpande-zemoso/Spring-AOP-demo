package com.zemoso.aopdemo.exception;

import com.zemoso.aopdemo.errorresponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value = {EmployeeNotFoundException.class})
    public ResponseEntity<Object> handleInvalidParams(Exception ex, WebRequest webRequest) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String uri = ((ServletWebRequest) webRequest).getRequest()
                .getRequestURI();
        ErrorResponse error = new ErrorResponse(status, ex.getMessage(), uri);
        return new ResponseEntity<>(error, status);
    }
}
