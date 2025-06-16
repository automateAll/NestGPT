package com.nestgpt.property.service;

import com.nestgpt.property.model.Property;
import com.nestgpt.property.model.PropertyContext;

import java.util.List;

public interface PropertyService {
    public List<Property> getPropertiesList(String query);
    public PropertyContext getPropertyContext(String id);
}
