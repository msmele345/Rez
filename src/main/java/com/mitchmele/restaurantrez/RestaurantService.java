package com.mitchmele.restaurantrez;

import com.mitchmele.restaurantrez.model.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {

    public List<String> getRestaurantNames() {
        return Collections.emptyList();
    }
}
