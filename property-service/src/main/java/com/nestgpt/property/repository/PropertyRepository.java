package com.nestgpt.property.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nestgpt.property.model.Property;
import com.nestgpt.property.model.PropertyContext;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("propertyRepository")
public class PropertyRepository {

    private static final List<Property> MOCK_PROPERTIES = new ArrayList<>();
    private static final Map<String,PropertyContext> MOCK_PROPERTY_CONTEXT_MAP = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

//    static {
//        MOCK_PROPERTIES.add(new Property("p123"
//                ,"Bentonville"
//                ,200000
//                ,"mockURL1",36.3729, -94.2088));
//        MOCK_PROPERTIES.add(new Property("p124"
//                ,"Hicksville"
//                ,200000
//                ,"mockURL2", 40.7684, -73.5251));
//        MOCK_PROPERTIES.add(new Property("p125"
//                ,"Bentonville"
//                ,200000
//                ,"mockURL3", 40.7128, -74.0060
//                ));
//
//        //Initialize the PropertyContext Map
//        MOCK_PROPERTY_CONTEXT_MAP.put("p123", new PropertyContext("p123","Bentonville"
//                ,200000,"1998", 4,5, "","",""));
//    }


    public List<Property> getPropertiesList(String location){
        try {
            InputStream inputStream = getClass().getResourceAsStream("/mock-properties-enhanced.json");
            List<Property> allProperties = objectMapper.readValue(inputStream, new TypeReference<List<Property>>() {});

            final String searchTerm = location.toLowerCase().trim();
            return allProperties.stream()
                    .filter(p -> p.getAddress().toLowerCase().contains(searchTerm))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Property getPropertyContext(String propertyId){
        try {
            InputStream inputStream = getClass().getResourceAsStream("/mock-properties-enhanced.json");
            List<Property> allProperties = objectMapper.readValue(inputStream, new TypeReference<List<Property>>() {});

            final int id = Integer.parseInt(propertyId);
            return allProperties.stream()
                    .filter(p -> p.getId()==id)
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
