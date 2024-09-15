package com.example.zomatorestaurants;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Component
public class RestaurantCSVLoader implements CommandLineRunner {

    @Value("${data.load.on.startup}")
    private boolean loadDataOnStartup;

    @Override
    public void run(String... args) throws Exception {

        if (!loadDataOnStartup) {
            System.out.println("Data loading on startup is disabled.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/zomato";
        String user = "postgres";
        String password = "postgres";

        // CSV file location
        File csvFile = new File("src/main/resources/data/zomato.csv");

        // SQL for inserting data
        String sql = "INSERT INTO restaurants (restaurant_id, name, country_code, city, address, locality, locality_verbose, " +
                "longitude, latitude, cuisines, average_cost_for_two, currency, has_table_booking, has_online_delivery, is_delivering, " +
                "switch_to_order_menu, price_range, aggregate_rating, rating_color, rating_text, votes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (restaurant_id) DO NOTHING";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             Reader reader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            conn.setAutoCommit(false);  // Ensure auto-commit is off

            // Iterate over each record in the CSV
            for (CSVRecord record : csvParser) {
                // Extract required fields from the CSV record
                String restaurantId = record.get("Restaurant ID");
                String name = record.get("Restaurant Name");
                int countryCode = Integer.parseInt(record.get("Country Code"));
                String city = record.get("City");
                String address = record.get("Address");
                String locality = record.get("Locality");
                String localityVerbose = record.get("Locality Verbose");
                double longitude = Double.parseDouble(record.get("Longitude"));
                double latitude = Double.parseDouble(record.get("Latitude"));
                String cuisines = record.get("Cuisines");
                int averageCostForTwo = Integer.parseInt(record.get("Average Cost for two"));
                String currency = record.get("Currency");
                boolean hasTableBooking = record.get("Has Table booking").equalsIgnoreCase("yes");
                boolean hasOnlineDelivery = record.get("Has Online delivery").equalsIgnoreCase("yes");
                boolean isDelivering = record.get("Is delivering now").equalsIgnoreCase("yes");
                boolean switchToOrderMenu = record.get("Switch to order menu").equalsIgnoreCase("yes");
                int priceRange = Integer.parseInt(record.get("Price range"));
                double aggregateRating = Double.parseDouble(record.get("Aggregate rating"));
                String ratingColor = record.get("Rating color");
                String ratingText = record.get("Rating text");
                int votes = Integer.parseInt(record.get("Votes"));

                // Truncate currency to fit within 10 characters
                currency = currency.length() > 10 ? currency.substring(0, 10) : currency;

                // Set parameters and add to batch
                pstmt.setString(1, restaurantId);
                pstmt.setString(2, name);
                pstmt.setInt(3, countryCode);
                pstmt.setString(4, city);
                pstmt.setString(5, address);
                pstmt.setString(6, locality);
                pstmt.setString(7, localityVerbose);
                pstmt.setDouble(8, longitude);
                pstmt.setDouble(9, latitude);
                pstmt.setString(10, cuisines);
                pstmt.setInt(11, averageCostForTwo);
                pstmt.setString(12, currency);
                pstmt.setBoolean(13, hasTableBooking);
                pstmt.setBoolean(14, hasOnlineDelivery);
                pstmt.setBoolean(15, isDelivering);
                pstmt.setBoolean(16, switchToOrderMenu);
                pstmt.setInt(17, priceRange);
                pstmt.setDouble(18, aggregateRating);
                pstmt.setString(19, ratingColor);
                pstmt.setString(20, ratingText);
                pstmt.setInt(21, votes);

                pstmt.addBatch(); // Add to batch
            }

            int[] results = pstmt.executeBatch();
            conn.commit();  // Commit the transaction

            System.out.println("Batch executed with result counts: " + java.util.Arrays.toString(results));

        } catch (Exception e) {
            e.printStackTrace();  // Print any errors
        }
    }
}
