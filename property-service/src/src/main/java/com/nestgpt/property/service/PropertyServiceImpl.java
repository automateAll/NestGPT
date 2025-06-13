package com.nestgpt.property.service;

import com.nestgpt.property.model.Property;
import com.nestgpt.property.model.PropertyContext;
import com.nestgpt.property.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("propertyService")
public class PropertyServiceImpl implements PropertyService {
    protected PropertyRepository propertyRepository;

    @Autowired
    protected PropertyServiceImpl(PropertyRepository propertyRepository){
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getPropertiesList(String query){
        return propertyRepository.getPropertiesList(query);
    }

    public PropertyContext getPropertyContext(String propertyId){
        return propertyRepository.getPropertyContext(propertyId);
    }
}
