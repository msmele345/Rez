package com.mitchmele.restaurantrez.controller;

import com.mitchmele.restaurantrez.RestaurantService;
import com.mitchmele.restaurantrez.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    private static final String GREETING = "Welcome to The Rez";

    @CrossOrigin
    @GetMapping("/restaurants/random")
    public List<Restaurant> getAllRestaurantsNames() {
        return restaurantService.getRestaurants();
    }

    @CrossOrigin
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurantsByBorough(@RequestParam String borough) {
        return restaurantService.getAllRestaurantsByBorough(borough);
    }

    @GetMapping("/about")
    public String about() {
        return GREETING;
    }
}
