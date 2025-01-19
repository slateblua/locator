package com.slateblua.locator;

import java.util.List;

public class MathFormulas {
    private static final int EARTH_RADIUS = 6371;

    public static double haversineDistance (double lat1, double lon1,
                                            double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }


    /**
     * Calculates the total distance between consecutive locations using the Haversine formula.
     *
     * @param locations The list of locations to calculate the distance for.
     * @return The total distance traveled in kilometers.
     */
    public static double calculateTotalDistance (List<Trackable> locations) {
        double totalDistance = 0;
        for (int i = 1; i < locations.size(); i++) {
            final Trackable prev = locations.get(i - 1);
            final Trackable curr = locations.get(i);
            totalDistance += MathFormulas.haversineDistance(
                    prev.getLatitude(), prev.getLongitude(),
                    curr.getLatitude(), curr.getLongitude()
            );
        }
        return totalDistance;
    }
}
