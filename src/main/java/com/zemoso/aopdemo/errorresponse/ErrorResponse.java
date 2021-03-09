package com.zemoso.aopdemo.errorresponse;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private final HttpStatus error;
    private final int status;
    private final String message;
    private final String path;

    public ErrorResponse(HttpStatus error, String message, String path) {
        this.error = error;
        this.status = error.value();
        this.message = message;
        this.path = path;
    }
}
