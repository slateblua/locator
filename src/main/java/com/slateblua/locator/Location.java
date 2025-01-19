package com.slateblua.locator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a geographic location with latitude and longitude coordinates.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Location implements Trackable {

    /**
     * The latitude of the location in decimal degrees.
     */
    private double latitude;

    /**
     * The longitude of the location in decimal degrees.
     */
    private double longitude;
}