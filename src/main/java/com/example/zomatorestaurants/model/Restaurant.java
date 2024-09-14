package com.example.zomatorestaurants.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "restaurants")
@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore any unknown JSON fields to prevent errors
public class Restaurant {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", unique = true)
    private String restaurantId;

    @Column
    private String name;

    @Column(name = "country_code")
    private Integer countryCode;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    private String locality;

    @Column(name = "locality_verbose")
    private String localityVerbose;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @Column
    private String cuisines;

    @Column(name = "average_cost_for_two")
    private Integer averageCostForTwo;

    @Column
    private String currency;

    @Column(name = "has_table_booking")
    private Boolean hasTableBooking;

    @Column(name = "has_online_delivery")
    private Boolean hasOnlineDelivery;

    @Column(name = "is_delivering")
    private Boolean isDelivering;

    @Column(name = "price_range")
    private Integer priceRange;

    @Column(name = "aggregate_rating")
    private Double aggregateRating;

    @Column(name = "rating_color")
    private String ratingColor;

    @Column(name = "rating_text")
    private String ratingText;

    @Column
    private Integer votes;

    @Column(name = "featured_image")
    private String featuredImage;

    @Column
    private String thumb;

}
