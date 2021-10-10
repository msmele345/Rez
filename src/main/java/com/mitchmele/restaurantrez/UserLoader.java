package com.mitchmele.restaurantrez;

import com.mitchmele.restaurantrez.user.RezUser;
import com.mitchmele.restaurantrez.user.RezUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RezUserRepository repository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for(int i = 0; i < 10; i++) {
            String username = makeUsername();
            RezUser user = RezUser.builder()
                    .username(username)
                    .password(makePassword(i))
                    .roles(getRoles(i))
                    .build();
            repository.save(user);
        }
    }

    private static String makePassword(int i) {
        int idNumber = i * 2;
        String postFix = String.valueOf(idNumber);
        return "password0" + postFix;
    }

    private static List<String> getRoles(int i) {
        return i % 2 == 0
                ? Collections.singletonList("ROLE_USER")
                : Collections.singletonList("ROLE_OWNER");
    }

    private static String makeUsername() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        return "rezUser" + letters.charAt(random.nextInt(letters.length()));
    }
}
