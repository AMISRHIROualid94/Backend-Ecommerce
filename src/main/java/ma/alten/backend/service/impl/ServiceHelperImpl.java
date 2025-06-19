package ma.alten.backend.service.impl;

import ma.alten.backend.service.ServiceHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ServiceHelperImpl implements ServiceHelper {

    @Override
    public void adminAccess(Authentication authentication) {
        if (!"admin@admin.com".equals(authentication.getName())) {
            throw new AccessDeniedException("You are not allowed to create new product.");
        }
        return;
    }
}
