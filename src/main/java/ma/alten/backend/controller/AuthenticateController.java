package ma.alten.backend.controller;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.dto.auth.AuthenticationRequest;
import ma.alten.backend.dto.auth.AuthenticationResponse;
import ma.alten.backend.jwt.services.ApplicationUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticateController {

    private final ApplicationUserDetailsService userDetailsService;

    @PostMapping(value = "/token")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest req) throws Exception {
        return ResponseEntity.ok(userDetailsService.authenticate(req.email(), req.password()));
    }
}
