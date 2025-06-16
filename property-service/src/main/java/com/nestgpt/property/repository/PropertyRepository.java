package com.nestgpt.property.repository;

import com.nestgpt.property.model.Property;
import com.nestgpt.property.model.PropertyContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository("propertyRepository")
public class PropertyRepository {

    private static final List<Property> MOCK_PROPERTIES = new ArrayList<>();
    private static final Map<String,PropertyContext> MOCK_PROPERTY_CONTEXT_MAP = new HashMap<>();

    static {
        MOCK_PROPERTIES.add(new Property("p123"
                ,"Bentonville"
                ,200000
                ,"mockURL1",36.3729, -94.2088));
        MOCK_PROPERTIES.add(new Property("p124"
                ,"Hicksville"
                ,200000
                ,"mockURL2", 40.7684, -73.5251));
        MOCK_PROPERTIES.add(new Property("p125"
                ,"Bentonville"
                ,200000
                ,"mockURL3", 40.7128, -74.0060
                ));

        //Initialize the PropertyContext Map
        MOCK_PROPERTY_CONTEXT_MAP.put("p123", new PropertyContext("p123","Bentonville"
                ,200000,"1998", 4,5, "","",""));
    }

    public List<Property> getPropertiesList(String zip){
        return MOCK_PROPERTIES.stream()
                .filter(p -> zip == null || p.getAddress().toLowerCase().contains(zip.toLowerCase()))
                .collect(Collectors.toList());
    }

    public PropertyContext getPropertyContext(String propertyId){
        return MOCK_PROPERTY_CONTEXT_MAP.get(propertyId);
    }
}
