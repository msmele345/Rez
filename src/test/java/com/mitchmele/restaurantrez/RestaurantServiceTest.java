package com.mitchmele.restaurantrez;

import com.mitchmele.restaurantrez.model.Restaurant;
import com.mitchmele.restaurantrez.restaurant.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private RestaurantService service;

    @Test
    void getAllRestaurantsByBorough_pageable() {

        Restaurant restaurant = Restaurant.builder().name("1").borough("Manhattan").build();
        Restaurant restaurant2 = Restaurant.builder().name("2").borough("Queens").build();
        Restaurant restaurant3 = Restaurant.builder().name("3").borough("Manhattan").build();
        Restaurant restaurant4 = Restaurant.builder().name("4").borough("Bronx").build();

        List<Restaurant> expected = List.of(restaurant, restaurant3);
        when(repository.findAllByBorough(anyString())).thenReturn(expected);

        List<Restaurant> actual = service.getAllRestaurantsByBorough("Manhattan");
        assertThat(actual).isEqualTo(expected);

        verify(repository).findAllByBorough("Manhattan");
    }

    @Test
    void getRestaurant() {

        Restaurant restaurant = Restaurant.builder().name("1").name("Freds").borough("Manhattan").build();
        Restaurant restaurant2 = Restaurant.builder().name("2").name("Bad Beefs").borough("Queens").build();
        Restaurant restaurant3 = Restaurant.builder().name("3").name("Tommys").borough("Manhattan").build();
        Restaurant restaurant4 = Restaurant.builder().name("4").name("Armands").borough("Bronx").build();

        when(repository.findAll()).thenReturn(List.of(restaurant, restaurant2, restaurant3, restaurant4));

        List<Restaurant> actual = service.getRestaurants();

        List<Restaurant> expected = List.of(restaurant, restaurant2, restaurant3, restaurant4);
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("restaurantId")
                .isEqualTo(expected);
    }
}