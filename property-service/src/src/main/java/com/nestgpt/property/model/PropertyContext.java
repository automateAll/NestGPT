package com.nestgpt.property.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PropertyContext {
    String id;
    String address;
    double price;
    String yearBuilt;
    double schoolRatingNearby;
    double hospitalDistanceMiles;
    String neighborhoodTrend;
    String investmentScore;
    String description;
}
