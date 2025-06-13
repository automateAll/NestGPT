package com.nestgpt.property.controller;

import com.nestgpt.property.model.Property;
import com.nestgpt.property.model.PropertyContext;
import com.nestgpt.property.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/properties")
public class PropertyController {

    protected PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService){
        this.propertyService = propertyService;
    }

    @GetMapping("")
    public List<Property> getPropertiesList(@RequestParam(required = false) String zip,
                                  @RequestParam(required = false) String city,
                                  @RequestParam(required = false) String state){
        return propertyService.getPropertiesList(zip);
    }

    @GetMapping("/{id}")
    public PropertyContext getPropertyContext(@PathVariable("id") String propertyId){
        return propertyService.getPropertyContext(propertyId);
    }
}
