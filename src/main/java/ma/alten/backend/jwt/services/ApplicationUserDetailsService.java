package ma.alten.backend.jwt.services;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.constantes.Constante;
import ma.alten.backend.domain.UserDetailPrincipal;
import ma.alten.backend.dto.auth.AuthenticationResponse;
import ma.alten.backend.exception.AuthenticationException;
import ma.alten.backend.jwt.utils.JwtUtil;
import ma.alten.backend.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtils;

    @Override
    public UserDetailPrincipal loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserDetailPrincipal(userService.searchByEmail(email));
    }

    public AuthenticationResponse authenticate(String email, String password) throws NoSuchAlgorithmException, AuthenticationException {
        if (email.isEmpty() || password.isEmpty()) {
            throw new AuthenticationException(Constante.UNAUTHORIZED);
        }
        var userEntity = userService.searchByEmail(email);

        if (userEntity == null) {
            throw new AuthenticationException(Constante.UNAUTHORIZED);
        }

        var verified = verifyPassword(password, userEntity.getPassword());

        if (!verified) {
            throw new AuthenticationException(Constante.UNAUTHORIZED);
        }

        UserDetails userDetails = loadUserByUsername(userEntity.getEmail());
        String jwt = jwtUtils.generateToken(userDetails);

        return  AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    private Boolean verifyPassword(String password, String userPassword) throws AuthenticationException {
        if (password.isBlank()) {
            throw new AuthenticationException(Constante.PAYLOAD_EMPTY);
        }
        return passwordEncoder.matches(password, userPassword);
    }
}
