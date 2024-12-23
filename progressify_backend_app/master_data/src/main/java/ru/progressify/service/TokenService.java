package ru.progressify.service;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    public UUID getCurrentUserId() {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication != null && authentication.getToken() != null) {
            String subject = authentication.getToken().getSubject();
            return UUID.fromString(subject);
        }

        throw new AuthenticationServiceException("User not authenticated or token not present");
    }
}
