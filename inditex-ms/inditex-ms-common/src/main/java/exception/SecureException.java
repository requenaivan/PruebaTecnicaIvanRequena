package exception;

import org.springframework.http.HttpStatus;

/**
 * Exception to security into ms
 */
public class SecureException extends RuntimeException {
    /**
     * Code to exception
     */
    private String code;
    /**
     * Http status to exception
     */
    private HttpStatus status;

    public SecureException(String code, HttpStatus status, Throwable cause, String message) {
        super(message, cause);
        this.code = code;
        this.status = status;
    }

    public SecureException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public SecureException(String code, HttpStatus status, Throwable cause) {
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
