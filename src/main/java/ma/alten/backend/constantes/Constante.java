package ma.alten.backend.constantes;

public class Constante {

    public static final String[] SWAGGER_PATHS = {"/swagger-ui/**", "/v3/api-docs/**"};
    public static final String[] AUTH_PATH = {"/users/**", "/token"};

    //LocalDateTime format
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static final String ERRORPREFIX = "ALTEN- ";

    //Auth
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String TOKEN_EXPIRED = "TOKEN EXPIRED";
    public static final String TOKEN_INVALID = "TOKEN INVALID";
    public static final String INVALID_SIGNATURE = "Invalid signature";
    public static final String EXPIRED = "expired";

    public static final String INVALID_CREDENTIAL = "INVALID CREDENTIAL";
    public static final String UNAUTHORIZED = "UNAUTHORIZED USER";
    public static final String PAYLOAD_EMPTY = "Password cannot be empty or whitespace only string.";


}
