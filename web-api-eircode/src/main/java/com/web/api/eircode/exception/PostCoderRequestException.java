package com.web.api.eircode.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Flavio on 23/05/2017.
 */
public class PostCoderRequestException extends Exception {
    private HttpStatus httpStatus;

    public PostCoderRequestException(final String message) {
        super(message);
    }

    public PostCoderRequestException(final HttpStatus httpStatus, final String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
