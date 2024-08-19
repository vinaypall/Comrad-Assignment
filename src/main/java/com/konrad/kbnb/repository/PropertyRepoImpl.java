package com.konrad.kbnb.repository;

import com.konrad.kbnb.entity.Property;
import com.konrad.kbnb.exception.RestrictedPropertyException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PropertyRepoImpl{
    @PersistenceContext
    private EntityManager entityManager;

    public Property getFakeProperty(String propertyName){
        Property property = new Property();
        property.setName(propertyName);
        return property;
    }

    public List<Property> getPropertyWithMinimumStars(String propertyName){
        return entityManager.createQuery("select p from Property p where p.stars < 5", Property.class).getResultList();
    }

}
