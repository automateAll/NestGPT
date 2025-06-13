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
    String id;
    String address;
    double price;
    String thumbnailUrl;
}
