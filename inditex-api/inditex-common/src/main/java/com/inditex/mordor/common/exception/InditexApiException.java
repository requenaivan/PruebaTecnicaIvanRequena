package com.inditex.mordor.common.exception;

import org.springframework.http.HttpStatus;

public class InditexApiException extends RuntimeException {
    private String code;
    private HttpStatus status;

    public InditexApiException(String code, HttpStatus status, Throwable cause, String message) {
        super(message, cause);
        this.code = code;
        this.status = status;
    }

    public InditexApiException(String code, HttpStatus status, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public InditexApiException(String code, HttpStatus status, Throwable cause) {
        super(cause);
        this.code = code;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
