package com.slateblua.locator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationConsumer {
    private static final int DATA_POINTS_THRESHOLD = 10;

    private final ObjectMapper objectMapper;
    private final List<Location> userLocations = new ArrayList<>();

    @KafkaListener(topics = "${kafka.topic.locations}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume (String message) {
        try {
            final Location location = objectMapper.readValue(message, Location.class);
            userLocations.add(location);

            // Generate report when we have enough data points
            if (userLocations.size() % DATA_POINTS_THRESHOLD == 0) {
                generateReport(userLocations);
            }
        } catch (JsonProcessingException e) {
            System.err.println("Error reading to Json: " + e.getMessage());
        }
    }

    private void generateReport (final List<Location> locations) {
        double totalDistance = calculateTotalDistance(locations);
        System.out.println("Report generated: Total locations processed: " + locations.size() +
                ", Total traveled distance: " + totalDistance + " km");
    }

    private double calculateTotalDistance (List<Location> locations) {
        double totalDistance = 0;
        for (int i = 1; i < locations.size(); i++) {
            final Location prev = locations.get(i - 1);
            final Location curr = locations.get(i);
            totalDistance += MathFormulas.haversineDistance(
                    prev.getLatitude(), prev.getLongitude(),
                    curr.getLatitude(), curr.getLongitude()
            );
        }
        return totalDistance;
    }
}