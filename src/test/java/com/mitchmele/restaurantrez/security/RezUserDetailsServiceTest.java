package com.mitchmele.restaurantrez.security;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.mitchmele.restaurantrez.user.RezUser;
import com.mitchmele.restaurantrez.user.RezUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RezUserDetailsServiceTest {

    @Mock
    private RezUserRepository repository;

    @InjectMocks
    private RezUserDetailsService userDetailsService;

    @Test
    void loadUserByUsername() {
        RezUser user = RezUser.builder()
                .username("username")
                .password("pass1")
                .roles(singletonList("ROLE_USER"))
                .active(true)
                .build();

        RezAuthorizedUser expected = new RezAuthorizedUser(user);

        Optional<RezUser> rezUser = Optional.of(user);
        when(repository.findByUsername(anyString())).thenReturn(rezUser);

        UserDetails actual = userDetailsService.loadUserByUsername("username");

        assertThat(actual).isEqualTo(expected);
        verify(repository).findByUsername("username");
        //return a user with a pass
        //return a RezAuthorizedUser after matching the password
    }

    @Test
    void loadUserByUsername_repositoryReturnsEmptyOptional() {
        when(repository.findByUsername(anyString()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> userDetailsService.loadUserByUsername("username"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("username not found");
    }
}