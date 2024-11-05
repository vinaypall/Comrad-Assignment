package com.konrad.kbnb.service;

import com.konrad.kbnb.Model.LookUpMatch;
import com.konrad.kbnb.Model.LookUpTreeNode;
import com.konrad.kbnb.Model.PropertyRequestBody;
import com.konrad.kbnb.entity.Property;
import com.konrad.kbnb.exception.RestrictedPropertyException;
import com.konrad.kbnb.repository.PropertyRepo;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private final PropertyRepo propertyRepo;

    public PropertyService(PropertyRepo propertyRepo) {
        this.propertyRepo = propertyRepo;
    }

    public List<Property> getPropertyByName(String name) {
        List<Property> properties = propertyRepo.findByNameStartingWith(name);
        return properties;
    }

    public boolean deleteProperty(Long id) {
        if (propertyRepo.existsById(id)) { 
            propertyRepo.deleteById(id);
            return true;
        }
        return false;
    }
     
    public List<Property> getPropertiesPage(int pageNum, int pageSize) {
        Page<Property> propertyPage = propertyRepo.findAll(PageRequest.of(pageNum, pageSize));
        return propertyPage.getContent(); // Return the list of properties on the current page
    }
    public List<Property> getPropertiesWithNameAndMinimumStars(String name, int stars) {
        // Retrieve properties that match the name and have at least the specified number of stars
        return propertyRepo.findByNameContainingAndStarsGreaterThanEqual(name, stars);
    }
    public Property getProperty(long id){
        Property property = propertyRepo.findById(id);
        return property;
    }

    public Property addProperty(PropertyRequestBody propertyBody){
        Property property = Property.propertyFromDto(propertyBody);
        return propertyRepo.save(property);
    }

    public Property getFakeProperty(String name){
        Property property = propertyRepo.getFakeProperty(name);
        if(property.getName().equals("restricted"))throw new RestrictedPropertyException("restricted");
        if(property.getName().equals("name"))property.setName("Put in a real name");
        return property;
    }

    public List<Property> getPropertiesWithNameAndMinimumAmountOfStars(String name){
        return propertyRepo.getPropertyWithMinimumStars(name);
    }

    public List<Long> getSuperHost(){
        return List.of();
    }

    public LookUpTreeNode getLookUpTree(){
        return new LookUpTreeNode();
    }

}
