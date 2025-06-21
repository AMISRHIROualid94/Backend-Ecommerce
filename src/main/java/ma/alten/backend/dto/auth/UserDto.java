package ma.alten.backend.dto.auth;

public record UserDto(
        Long id,
        String username,
        String firstname,
        String email,
        String password
) {
}
