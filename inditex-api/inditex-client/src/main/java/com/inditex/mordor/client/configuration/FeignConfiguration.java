package com.inditex.mordor.client.configuration;

import feign.Logger;
import feign.Response;
import feign.RetryableException;
import feign.Retryer;
import feign.form.FormEncoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class FeignConfiguration {
    ObjectFactory<HttpMessageConverters> messageConverters;

    public FeignConfiguration(ObjectFactory<HttpMessageConverters> messageConverters){
        this.messageConverters = messageConverters;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    @Bean
    @Primary
    Retryer retry() {
        return new Retryer.Default(SECONDS.toMillis(1), SECONDS.toMillis(2), 3);
    }

    @Bean
    FeignErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    @Bean
    FormEncoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }
}
