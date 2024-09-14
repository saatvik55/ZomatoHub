package com.example.zomatorestaurants;

import com.example.zomatorestaurants.model.Restaurant;
import com.example.zomatorestaurants.repository.RestaurantRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonDataLoader implements CommandLineRunner {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void run(String... args) throws IOException {
        // Load JSON files from the classpath
        File jsonFile1 = new ClassPathResource("data/file1.json").getFile();
        File jsonFile2 = new ClassPathResource("data/file2.json").getFile();
        File jsonFile3 = new ClassPathResource("data/file3.json").getFile();
        File jsonFile4 = new ClassPathResource("data/file4.json").getFile();
        File jsonFile5 = new ClassPathResource("data/file5.json").getFile();

        ObjectMapper objectMapper = new ObjectMapper();

        // Read each JSON file into a list of Restaurant objects
        List<Restaurant> restaurants1 = objectMapper.readValue(jsonFile1, new TypeReference<List<Restaurant>>(){});
        List<Restaurant> restaurants2 = objectMapper.readValue(jsonFile2, new TypeReference<List<Restaurant>>(){});
        List<Restaurant> restaurants3 = objectMapper.readValue(jsonFile3, new TypeReference<List<Restaurant>>(){});
        List<Restaurant> restaurants4 = objectMapper.readValue(jsonFile4, new TypeReference<List<Restaurant>>(){});
        List<Restaurant> restaurants5 = objectMapper.readValue(jsonFile5, new TypeReference<List<Restaurant>>(){});

        // Combine all lists and save to the database
        restaurantRepository.saveAll(restaurants1);
        restaurantRepository.saveAll(restaurants2);
        restaurantRepository.saveAll(restaurants3);
        restaurantRepository.saveAll(restaurants4);
        restaurantRepository.saveAll(restaurants5);

        System.out.println("Data loaded successfully from JSON files!");
    }
}
