package com.example.zomatorestaurants.controller;

import com.example.zomatorestaurants.model.Restaurant;
import com.example.zomatorestaurants.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // Endpoint to save a single restaurant
    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.saveRestaurant(restaurant);
    }

    // Endpoint to save multiple restaurants
    @PostMapping("/bulk")
    public List<Restaurant> addRestaurants(@RequestBody List<Restaurant> restaurants) {
        return restaurantService.saveAllRestaurants(restaurants);
    }

    // Endpoint to get all restaurants
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    // Add more endpoints as needed
}
