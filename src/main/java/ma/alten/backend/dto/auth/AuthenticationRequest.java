package ma.alten.backend.dto.auth;

public record AuthenticationRequest(
        String email,
        String password
) {
}
