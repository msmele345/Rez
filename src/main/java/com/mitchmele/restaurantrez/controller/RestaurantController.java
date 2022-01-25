package com.mitchmele.restaurantrez.controller;

import com.mitchmele.restaurantrez.RestaurantService;
import com.mitchmele.restaurantrez.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    private static final String GREETING = "Welcome to The Rez";

    @GetMapping("/restaurants/random")
    public List<Restaurant> getAllRestaurantsNames() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurantsByBorough(@RequestParam String borough) {
        return restaurantService.getAllRestaurantsByBorough(borough);
    }

    @GetMapping("/trending")
    public List<Restaurant> getTrendingRestaurants() {
        return restaurantService.getTrending();
    }

    @GetMapping("/about")
    public String about() {
        return GREETING;
    }
}
