package com.inditex.zwift.config;

import exception.Error;
import exception.SecureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

/**
 * Interceptor to basic auth to call endpoints
 */
@Component
public class InditexBasicAuthInterceptor implements HandlerInterceptor {
    private String inditexUsernameBasicAuth;
    private String inditexPasswordBasicAuth;

    public InditexBasicAuthInterceptor(
            @Value("${inditex-price-ms.username.basic.auth}")
            String inditexUsernameBasicAuth,
            @Value("${inditex-price-ms.password.basic.auth}")
            String inditexPasswordBasicAuth) {
        this.inditexUsernameBasicAuth = inditexUsernameBasicAuth;
        this.inditexPasswordBasicAuth = inditexPasswordBasicAuth;
    }

    /**
     * prehandler to logic validate token
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return true if token is valid
     * @throws Exception if token is not valid
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authToken = request.getHeader("Authorization");
        if(authToken != null) {
            if(authToken.contains("Basic")) {
                if (!this.verifyBasicAuthentication(authToken)) {
                    throw new SecureException(Error.UNAUTHORIZED_APP.getCode(), Error.UNAUTHORIZED_APP.getName(), HttpStatus.UNAUTHORIZED);
                }
            } else {
                throw new SecureException(Error.INVALID_TOKEN.getCode(), Error.INVALID_TOKEN.getName(), HttpStatus.BAD_REQUEST);
            }
            return true;
        } else {
            throw new SecureException(Error.INVALID_HEADER.getCode(), Error.INVALID_HEADER.getName(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *  Method to validate token
     * @param basicToken token to validate
     * @return boolean if token is equals
     */
    private Boolean verifyBasicAuthentication(String basicToken) {
        String token = this.extractToken(basicToken);
        String credentials = inditexUsernameBasicAuth+":"+inditexPasswordBasicAuth;

        return token.equals(this.getEncodeBase64(credentials.getBytes()));
    }

    /**
     * Extract token to header
     * @param authToken token to validate
     * @return only token generate
     */
    private String extractToken(String authToken)  {
         try {
             return authToken.split(" ")[1];
        } catch (Exception ex) {
             return "";
        }
    }

    /**
     * Get token enconde base 64
     * @param byteArray
     * @return
     */
    private String getEncodeBase64(byte[] byteArray){
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
