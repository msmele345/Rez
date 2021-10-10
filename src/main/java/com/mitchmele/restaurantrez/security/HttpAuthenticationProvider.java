package com.mitchmele.restaurantrez.security;

import com.mitchmele.restaurantrez.user.RezUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class HttpAuthenticationProvider implements AuthenticationProvider {

    private final RezUserRepository repository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
