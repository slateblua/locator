package com.slateblua.locator;

/**
 * Represents an object with geographic coordinates (latitude and longitude).
 */
public interface Trackable {

    /**
     * Gets the latitude of the object in decimal degrees.
     *
     * @return the latitude
     */
    double getLatitude ();

    /**
     * Gets the longitude of the object in decimal degrees.
     *
     * @return the longitude
     */
    double getLongitude ();
}
