package com.inditex.mordor.client.configuration;

import com.google.gson.Gson;
import com.inditex.mordor.common.exception.Error;
import com.inditex.mordor.common.exception.InditexApiException;
import com.inditex.mordor.common.response.ExternalErrorResponse;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public RuntimeException decode(String methodKey, Response response) {

        String body;
        try {
            body = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            try {
                if (response.status() == HttpStatus.SERVICE_UNAVAILABLE.value()) {
                    return new RetryableException(response.status(), methodKey, response.request().httpMethod(), null, response.request());
                }
                ExternalErrorResponse error = new Gson().fromJson(body.toString(), ExternalErrorResponse.class);
                return new InditexApiException(error.code(), mapHttStatus(response.status()), error.message());
            } catch (Exception e) {
                return new InditexApiException(Error.INTERNAL_SERVER_ERROR.getCode(), HttpStatus.INTERNAL_SERVER_ERROR, new Throwable(body));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpStatus mapHttStatus(Integer statusExternal) {
        return HttpStatus.valueOf(statusExternal);
    }
}