package com.mitchmele.restaurantrez;

import com.mitchmele.restaurantrez.user.RezUser;
import com.mitchmele.restaurantrez.user.RezUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
//@Service
@RequiredArgsConstructor
public class UserLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RezUserRepository repository;
    private static final Random rand = new Random();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for(int i = 0; i < 10; i++) {
            String username = makeUsername();
            RezUser user = RezUser.builder()
                    .username(username)
                    .password(makePassword())
                    .roles(getRoles(i))
                    .build();
            repository.save(user);
        }
    }

    private static String makePassword() {
        List<String> specialChars = Arrays.asList("&", "*", "$", "#","@","^");
        String postFix = String.valueOf(rand.nextInt(100));
        return "password" + postFix + specialChars.get(rand.nextInt(specialChars.size()));
    }

    private static List<String> getRoles(int i) {
        return i % 2 == 0
                ? Collections.singletonList("ROLE_USER")
                : Collections.singletonList("ROLE_OWNER");
    }

    private static String makeUsername() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        return "rezUser" + letters.charAt(rand.nextInt(letters.length()));
    }
}
