package com.example.zomatorestaurants.service;

import com.example.zomatorestaurants.model.Restaurant;
import com.example.zomatorestaurants.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Method to save a single restaurant
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    // Method to save multiple restaurants
    public List<Restaurant> saveAllRestaurants(List<Restaurant> restaurants) {
        return restaurantRepository.saveAll(restaurants);
    }

    // Method to get all restaurants
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Add more methods as needed
}
