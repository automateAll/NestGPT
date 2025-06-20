package com.nestgpt.property.service;

import com.nestgpt.property.model.Property;
import com.nestgpt.property.model.PropertyContext;

import java.util.List;

public interface PropertyService {
    public List<Property> getPropertiesList(String location);
    public Property getPropertyContext(String id);
}
