package com.inditex.mordor.client.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("!test")
public class FeignAuthConfiguration {

    private String username;

    private String password;

    public FeignAuthConfiguration(@Value("${inditex-price-ms.username.basic.auth}")
                                  String username,
                                  @Value("${inditex-price-ms.password.basic.auth}")
                                  String password) {
        this.username = username;
        this.password = password;
    }

    @Bean
    private BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(username, password);
    }

    @Bean
    private FeignErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
}