package ma.alten.backend.dto.auth;

import lombok.Builder;

import java.util.List;

@Builder
public record UserTokenDto(
        String userName,
        String firstName,
        String email,
        List<String> roles) {

}
