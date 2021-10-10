package com.mitchmele.restaurantrez.controller;

import com.mitchmele.restaurantrez.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    private static final String GREETING = "Welcome to The Rez";

    @GetMapping("/restaurant/names")
    public List<String> getAllRestaurantsNames() {
        return restaurantService.getRestaurantNames();
    }

    @GetMapping("/about")
    public String about() {
        return GREETING;
    }
}
