package com.mitchmele.restaurantrez.controller;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitchmele.restaurantrez.RestaurantService;
import com.mitchmele.restaurantrez.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RestaurantControllerTestIT {

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void restaurantNames_returnsAllNamesOfRestaurantsInSystem() throws Exception {
        Restaurant restaurant = Restaurant.builder().build();
        List<Restaurant> expected = List.of(restaurant);
        when(restaurantService.getRestaurants()).thenReturn(expected);

        mockMvc.perform(get("/api/v1/restaurants/random"))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(expected)));
    }

    @Test
    void getRestaurantsByBorough_returnsListOfRestaurantsByBorough() throws Exception {
        List<Restaurant> expected = singletonList(Restaurant.builder().name("Pattys").borough("Bronx").build());
        when(restaurantService.getAllRestaurantsByBorough(anyString())).thenReturn(expected);

        mockMvc
                .perform((get("/api/v1/restaurants?borough=Bronx")))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(expected)));

        verify(restaurantService).getAllRestaurantsByBorough("Bronx");
    }
}