package ma.alten.backend.controller;

import ma.alten.backend.dto.auth.AuthenticationRequest;
import ma.alten.backend.dto.auth.AuthenticationResponse;
import ma.alten.backend.jwt.services.ApplicationUserDetailsService;
import ma.alten.backend.jwt.utils.JwtUtil;
import ma.alten.backend.user.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    private final JwtUtil jwtTokenUtil;
    private final ApplicationUserDetailsService userDetailsService;

    public AuthenticateController(JwtUtil jwtTokenUtil, ApplicationUserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }


    @PostMapping(value = "/token")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest req) throws Exception {
        UserEntity user;

        try {
            user = userDetailsService.authenticate(req.getEmail(), req.getPassword());
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        var userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        System.out.println(userDetails);
        var jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }
}
