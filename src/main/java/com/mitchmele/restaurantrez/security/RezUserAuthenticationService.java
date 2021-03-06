package com.mitchmele.restaurantrez.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RezUserAuthenticationService implements AuthenticationUserDetailsService<Authentication> {

    private final RezUserDetailsService rezUserDetailsService;

    @Override
    public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
        String username = (String) token.getPrincipal();
        return rezUserDetailsService.loadUserByUsername(username);
    }
}
