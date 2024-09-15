package com.example.zomatorestaurants.controller;

import com.example.zomatorestaurants.model.Restaurant;
import com.example.zomatorestaurants.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable Integer id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
        System.out.println("Fetched Restaurant: " + restaurant);  // Debug log
        return restaurant;
    }

}
