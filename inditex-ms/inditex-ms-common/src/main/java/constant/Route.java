package constant;

/**
 * Class to managment endpoint internal (called in controllers)
 */
public class Route {
    /**
     * Route to healthcheck
     */
    public static final String HEALTH = "/health";
    /**
     * Route to version endpoints
     */
    public static final String V1 = "/v1";
    /**
     * Route to get prices
     */
    public static final String PRICES = V1+"/prices";

}
