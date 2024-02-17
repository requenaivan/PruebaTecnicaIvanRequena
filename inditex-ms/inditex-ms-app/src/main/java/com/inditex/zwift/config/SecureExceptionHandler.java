package com.inditex.zwift.config;

import com.google.gson.Gson;
import exception.SecureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import response.ExternalErrorResponse;

@ControllerAdvice
public class SecureExceptionHandler{

    /**
     * Handler to exceptions in this case secureException to response DTO
     * @param secureException secure exception generate by logic
     * @return ReponseEntity mapped
     */
    @ExceptionHandler(SecureException.class)
    private ResponseEntity handleSecurityException(SecureException secureException) {
        return new
                ResponseEntity(
                        new ExternalErrorResponse(
                                secureException.getCode(),
                                secureException.getMessage(),
                                HttpStatus.UNAUTHORIZED.getReasonPhrase()),
                new HttpHeaders(), secureException.getStatus());
    }

}
