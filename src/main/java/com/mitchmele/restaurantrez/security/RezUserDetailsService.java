package com.mitchmele.restaurantrez.security;

import com.mitchmele.restaurantrez.user.RezUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RezUserDetailsService implements UserDetailsService {

    private final RezUserRepository repository;

//  It is the responsibility of the spring framework to handle the password, the security framework will compare the password entered by the user and the password sent from the service –
    //take username and loads user. Spring security then compares the password to the form password

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .map(user -> {
                    return RezAuthorizedUser
                           .builder()
                           .rezUser(user)
                           .build();
                }).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
