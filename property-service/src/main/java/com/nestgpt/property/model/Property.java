package com.nestgpt.property.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Property {
    private String id;
    private String address;
    private double price;
    private String thumbnailUrl;
    private double latitude;
    private double longitude;
}
