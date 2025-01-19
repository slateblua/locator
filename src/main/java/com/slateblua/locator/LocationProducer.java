package com.slateblua.locator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Service for producing and sending location updates to a Kafka topic.
 * It simulates movement by generating random changes to the latitude and longitude
 * of the current location and sends the updated location to the Kafka topic at regular intervals.
 */
@Service
@RequiredArgsConstructor
public class LocationProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topic.locations}")
    private String topic;

    private final Random random = new Random();

    private double currentLat = 40.191966;
    private double currentLon = 44.509819;

    /**
     * Simulates movement by randomly modifying the current latitude and longitude by small increments.
     * The updated location is serialized into JSON format and sent to the configured Kafka topic.
     * This method runs at a fixed interval of 5 seconds.
     */
    @Scheduled(fixedRate = 5_000L) // Simulate movement every 5 seconds
    public void simulateMovement () {
        try {
            // Simulate movement by adding small random changes
            currentLat += random.nextDouble() * 0.001;
            currentLon += random.nextDouble() * 0.001;

            final Location location = new Location(
                    currentLat,
                    currentLon
            );

            final String locationJson = objectMapper.writeValueAsString(location);
            kafkaTemplate.send(topic, locationJson);
        } catch (JsonProcessingException e) {
            System.err.println("Error converting to Location to Json");
        }
    }
}
