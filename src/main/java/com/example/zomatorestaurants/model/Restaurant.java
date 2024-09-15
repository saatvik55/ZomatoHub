package com.example.zomatorestaurants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "restaurant_id", nullable = false)
    private String restaurantId;

    @Column(name = "name")
    private String name;

    @Column(name = "country_code")
    private Integer countryCode;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "locality")
    private String locality;

    @Column(name = "locality_verbose")
    private String localityVerbose;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "cuisines")
    private String cuisines;

    @Column(name = "average_cost_for_two")
    private Integer averageCostForTwo;

    @Column(name = "currency", length = 10)
    private String currency;

    @Column(name = "has_table_booking")
    private Boolean hasTableBooking;

    @Column(name = "has_online_delivery")
    private Boolean hasOnlineDelivery;

    @Column(name = "is_delivering")
    private Boolean isDelivering;

    @Column(name = "switch_to_order_menu")
    private Boolean switchToOrderMenu;

    @Column(name = "price_range")
    private Integer priceRange;

    @Column(name = "aggregate_rating")
    private Double aggregateRating;

    @Column(name = "rating_color", length = 50)
    private String ratingColor;

    @Column(name = "rating_text", length = 50)
    private String ratingText;

    @Column(name = "votes")
    private Integer votes;

    // Getters and Setters
}
