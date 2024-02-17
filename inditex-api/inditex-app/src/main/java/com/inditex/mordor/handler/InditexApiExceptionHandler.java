package com.inditex.mordor.handler;

import com.inditex.mordor.common.exception.ErrorCode;
import com.inditex.mordor.common.exception.InditexApiException;
import com.inditex.mordor.common.response.InditexApiExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {"com.inditex.mordor"})
public class InditexApiExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(InditexApiExceptionHandler.class);
    @ExceptionHandler(InditexApiException.class)
    private ResponseEntity<InditexApiExceptionResponse> inditexApiException(InditexApiException exception, WebRequest request) {

        logger.error("--inditex-api --InditexApiExceptionHandler:inditexApiException --code: [{}] --message [{}] --status [{}]",
                exception.getCode(), exception.getMessage(), exception.getStatus());
        String message = "";
        if(exception.getCause() != null) {
            message = exception.getCause().getMessage();
        }
        InditexApiExceptionResponse response = new InditexApiExceptionResponse(exception.getCode(), exception.getMessage(), message);

        return ResponseEntity.status(exception.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<InditexApiExceptionResponse> allExceptions(Exception exception) {
        InditexApiExceptionResponse response = new InditexApiExceptionResponse(ErrorCode.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER", exception.getMessage());

        logger.error("--inditex-api --InditexApiExceptionHandler:AllException --code: [{}] --message [{}] --status [{}]",
                response.message(), response.message(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
