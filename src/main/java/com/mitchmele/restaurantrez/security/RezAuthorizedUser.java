package com.mitchmele.restaurantrez.security;

import com.mitchmele.restaurantrez.user.RezUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Data
@Builder
public class RezAuthorizedUser implements UserDetails {

   private final RezUser rezUser;

    public RezAuthorizedUser(RezUser rezUser) {
        this.rezUser = rezUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (rezUser.isAdmin()) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return rezUser.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return rezUser.getPassword();
    }

    @Override
    public String getUsername() {
        return rezUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return rezUser.isActive();
    }
}
