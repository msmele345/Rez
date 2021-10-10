package com.mitchmele.restaurantrez;

import static org.junit.jupiter.api.Assertions.*;

import com.mitchmele.restaurantrez.user.RezUser;
import com.mitchmele.restaurantrez.user.RezUserRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.event.ContextRefreshedEvent;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserLoaderTest {

    @Mock
    private RezUserRepository repository;

    @InjectMocks
    private UserLoader userLoader;

    @Test
    void onApplicationEvent() {
        ContextRefreshedEvent event = mock(ContextRefreshedEvent.class);

        ArgumentCaptor<RezUser> captor = ArgumentCaptor.forClass(RezUser.class);

        userLoader.onApplicationEvent(event);

        verify(repository, times(10)).save(captor.capture());
        captor.getAllValues().forEach(System.out::println);
    }
}