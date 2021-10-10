package com.mitchmele.restaurantrez.security;

import com.mitchmele.restaurantrez.user.RezUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class RezUserAuthenticationServiceTest {

    @Mock
    private RezUserDetailsService rezUserDetailsService;

    @InjectMocks
    private RezUserAuthenticationService rezUserAuthenticationService;

    @Test
    void loadUserDetails_takesAuthenticationUserName_passesToUserDetailsService() {

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn("username");

        RezUser rezUser = RezUser.builder()
                .username("username")
                .password("pass")
                .active(true)
                .roles(Collections.singletonList("ADMIN")).build();

        RezAuthorizedUser user = new RezAuthorizedUser(rezUser);
        when(rezUserDetailsService.loadUserByUsername(anyString()))
                .thenReturn(user);

        rezUserAuthenticationService.loadUserDetails(authentication);

        verify(rezUserDetailsService).loadUserByUsername("username");
    }
}