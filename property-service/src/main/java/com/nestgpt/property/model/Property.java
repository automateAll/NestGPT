package com.nestgpt.property.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Property {
    private int id;
    private String address;
    private String city;
    private String state;
    private String zip;
    private double latitude;
    private double longitude;
    private double price;
    private int bedrooms;
    private int bathrooms;
    private int area;
    @JsonProperty("year_built")
    private int yearBuilt;
    @JsonProperty("property_type")
    private String propertyType;
    private String description;
    private List<String> images;
    @JsonProperty("listing_status")
    private String listingStatus;
    private Agent agent;

    // Getters and Setters

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Agent {
        private String name;
        private String phone;
        private String agency;

        // Getters and Setters
    }
}
