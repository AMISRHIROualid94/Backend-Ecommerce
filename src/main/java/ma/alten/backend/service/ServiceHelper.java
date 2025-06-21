package ma.alten.backend.service;

import ma.alten.backend.domain.Product;
import ma.alten.backend.domain.UserDetailPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface ServiceHelper {
    void adminAccess(Authentication authentication);
    Product findProductById(Long id);
    String extractUsername(String token);
    Boolean validateToken(String token, UserDetails userDetails);
    String generateToken(UserDetails userDetails);
    UserDetailPrincipal loadUserByUsername(String email);
}
