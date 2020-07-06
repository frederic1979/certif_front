package co.simplon.bakerdelivery.dto;


/**
 * Specific DTO to return JWT as a JSON object through REST response.
 */
public class JsonWebToken {

    private final String token;

    public JsonWebToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
