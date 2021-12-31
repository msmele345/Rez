package com.mitchmele.restaurantrez;

import com.mitchmele.restaurantrez.model.Restaurant;
import com.mitchmele.restaurantrez.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repository;

    public List<Restaurant> getRestaurants() {
        return repository.findAll()
                .parallelStream()
                .limit(5)
                .map(this::buildRestaurantWithId)
                .collect(Collectors.toList());
    } //add a sort feature that sorts on the high grades for a trending feature

    public List<Restaurant> getAllRestaurantsByBorough(String borough) {
        return repository.findAllByBorough(borough)
                .stream()
                .limit(5)
                .map(this::buildRestaurantWithId)
                .collect(Collectors.toList());
    }

    private Restaurant buildRestaurantWithId(Restaurant rez) {
        return Restaurant.builder()
                .restaurantId(UUID.randomUUID().toString())
                .name(rez.getName())
                .cuisine(rez.getCuisine())
                .borough(rez.getBorough())
                .build();
    }

}
