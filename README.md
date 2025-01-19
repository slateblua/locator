# Locator App

The **Locator App** is a Java-based application that simulates and tracks a person's location changes over time. It leverages **Apache Kafka** for real-time processing, stores geolocation data (longitude and latitude), and generates periodic reports, such as the total distance traveled using the **Haversine formula**.

## Features

- Simulates location changes of a person.
- Tracks and stores location coordinates (latitude and longitude).
- Publishes location changes using Kafka producer and processes them with Kafka consumer.
- Calculates total distance traveled (in kilometers) using the Haversine formula.
- Generates periodic reports based on tracked location data.

---

## Requirements

Make sure the following are installed and running:

- **Java 17** (for application runtime)
- **Apache Kafka** (for message streaming and processing)
- **Zookeeper** (to manage Kafka configurations and operations)

---

## Installation & Setup

1. **Clone the Repository**  
   Clone the Locator App repository to your local system:

   ```bash
   git clone <repository_url>
   cd locator
   ```

## Usage

1. **Simulating Location Changes:**  
   The app simulates a person's location changes over time and produces Kafka messages with the updated latitude and longitude.

2. **Kafka Consumer:**  
   A Kafka consumer listens for location updates, stores the geolocation data, and processes it.

3. **Periodic Reports:**  
   The app periodically calculates the **total distance traveled** using the **Haversine formula** and generates reports.

---

## Key Concepts

### Haversine Formula
The app uses the Haversine formula to calculate the great-circle distance between two points on the Earth's surface given their latitude and longitude:

```java
public double haversine(double lat1, double lon1, double lat2, double lon2) {
    final int R = 6371; // Radius of Earth in km
    double latDistance = Math.toRadians(lat2 - lat1);
    double lonDistance = Math.toRadians(lon2 - lon1);
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return R * c; // Distance in km
}
```

This calculation is used to determine the distance between two geolocation points.

---
