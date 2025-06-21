package ma.alten.backend.dto.auth;

import lombok.Builder;

@Builder
public record AuthenticationResponse(String token){
}
