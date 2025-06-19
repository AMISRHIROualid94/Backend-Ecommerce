package ma.alten.backend.service;

import org.springframework.security.core.Authentication;

public interface ServiceHelper {
    void adminAccess(Authentication authentication);
}
