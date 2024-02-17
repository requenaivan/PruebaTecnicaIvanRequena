package com.inditex.zwift.config;

import constant.Route;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/** Basic Authentication Interceptor Configuration Class */
@Configuration
public class BasicAuthInterceptorConfiguration implements WebMvcConfigurer {

    @Lazy
    private InditexBasicAuthInterceptor inditexBasicAuthInterceptor;

    public BasicAuthInterceptorConfiguration(InditexBasicAuthInterceptor inditexBasicAuthInterceptor) {
        this.inditexBasicAuthInterceptor = inditexBasicAuthInterceptor;
    }

    /**
     * Inteceptor to validate auth
     * @param registry interceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(inditexBasicAuthInterceptor)
                .excludePathPatterns(
                        Route.HEALTH,
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/error",
                        "/configuration/security"
                );
    }
}
