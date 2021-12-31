package com.mitchmele.restaurantrez.security;

import com.mitchmele.restaurantrez.user.RezUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class RezAuthorizedUserTest {

    private RezUser rezUser;
    private RezAuthorizedUser rezAuthorizedUser;

    @BeforeEach
    void setUp() {
        rezUser = RezUser.builder().username("ted").password("bundy123").roles(asList("ROLE_USER")).build();
        rezAuthorizedUser = RezAuthorizedUser.builder().rezUser(rezUser).build();
    }

    @Test
    void RezAuthorizedUser_hasCredentialsMatchingPassword() {
        assertThat(rezAuthorizedUser.getPassword()).isEqualTo("bundy123");
    }

    @Test
    void RezAuthorizedUser_hasUSER_Authority() {
        List<GrantedAuthority> expected = singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        assertThat(rezAuthorizedUser.getAuthorities()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void RezAuthorizedUser_hasADMIN() {
        rezUser.setAdmin(true);
        assertThat(rezAuthorizedUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))).isTrue();
    }
}