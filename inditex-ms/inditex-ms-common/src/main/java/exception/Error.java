package exception;

/**
 * Class to managment errors to inditex-ms
 */
public enum Error {
    /**
     * Errors to inditex-ms
     */
    UNAUTHORIZED_APP("inditex.unauthorized_app", "Unauthorized Petition"),
    INVALID_TOKEN("inditex.invalid.token", "Basic token is required"),
    INVALID_HEADER("inditex.invalid.header", "Authorization header is required");
    private String code;
    private String name;

    Error(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
