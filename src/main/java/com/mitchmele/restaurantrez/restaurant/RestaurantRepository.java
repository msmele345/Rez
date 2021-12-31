package com.mitchmele.restaurantrez.restaurant;

import com.mitchmele.restaurantrez.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    List<Restaurant> findAllByBorough(String borough);
}
