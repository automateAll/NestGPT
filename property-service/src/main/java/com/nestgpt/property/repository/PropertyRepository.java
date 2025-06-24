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


    public List<Property> getPropertiesList(String location){
        try {
            InputStream inputStream = getClass().getResourceAsStream("/mock-properties-enhanced.json");
            List<Property> allProperties = objectMapper.readValue(inputStream, new TypeReference<List<Property>>() {});

            final String searchString = location.toLowerCase().trim();
            return propertyLookUp(searchString, allProperties);
//            return allProperties.stream()
//                    .filter(p -> p.getAddress().toLowerCase().contains(searchTerm))
//                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private  List<Property> propertyLookUp(String searchString, List<Property> allProperties){
        return allProperties.stream().filter(l -> (
                            l.getAddress() != null && l.getAddress().toLowerCase().contains(searchString))
                        || (l.getCity() != null && l.getCity().toLowerCase().contains(searchString))
                        || String.valueOf(l.getZip()).contains(searchString))
                .collect(Collectors.toList());
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
