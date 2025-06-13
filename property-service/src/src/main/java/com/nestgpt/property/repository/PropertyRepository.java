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

    /**
     * Initialize the in-memory Property Map and list
     */
    static {
        MOCK_PROPERTIES.add(new Property("p123"
                ,"Bentonville"
                ,200000
                ,"mockURL1"));
        MOCK_PROPERTIES.add(new Property("p124"
                ,"Hicksville"
                ,200000
                ,"mockURL2"));
        MOCK_PROPERTIES.add(new Property("p125"
                ,"Bentonville"
                ,200000
                ,"mockURL3"
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
