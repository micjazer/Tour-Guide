package com.openclassrooms.tourguide.dto;

import gpsUtil.location.Location;
import lombok.Data;

@Data
public class NearbyAttractionDTO {

    public NearbyAttractionDTO(String attractionName, Location userLocation, double attractionLongitude,
            double attractionLatitude, double distance, int rewardPoints) {
        this.attractionName = attractionName;
        this.userLocation = userLocation;
        this.attractionLocation = new Location(attractionLatitude, attractionLongitude);
        this.distance = distance;
        this.rewardPoints = rewardPoints;
    }

    private String attractionName;

    private Location userLocation;

    private Location attractionLocation;

    private double distance;

    private int rewardPoints;

}
