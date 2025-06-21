package ma.alten.backend.service.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import ma.alten.backend.constantes.ExceptionConst;
import ma.alten.backend.domain.Product;
import ma.alten.backend.domain.UserDetailPrincipal;
import ma.alten.backend.exception.NotFoundException;
import ma.alten.backend.jwt.services.ApplicationUserDetailsService;
import ma.alten.backend.jwt.utils.JwtUtil;
import ma.alten.backend.repo.ProductRepo;
import ma.alten.backend.service.ServiceHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceHelperImpl implements ServiceHelper {

    private final ProductRepo productRepository;
    private final JwtUtil jwtTokenUtil;
    private final ApplicationUserDetailsService userDetailsService;

    @Override
    public void adminAccess(Authentication authentication) {
        if (!"admin@admin.com".equals(authentication.getName())) {
            throw new AccessDeniedException(ExceptionConst.NOT_ALLOWED);
        }
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(ExceptionConst.PRODUCT_NOT_FOUND,id)));
    }

    @Override
    public String extractUsername(String token) {
        return jwtTokenUtil.extractClaim(token, Claims::getSubject);
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        return jwtTokenUtil.validateToken(token,userDetails);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public UserDetailPrincipal loadUserByUsername(String email) {
        return userDetailsService.loadUserByUsername(email);
    }
}
