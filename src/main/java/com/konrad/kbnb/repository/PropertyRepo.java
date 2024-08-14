package com.konrad.kbnb.repository;


import com.konrad.kbnb.entity.Property;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PropertyRepo extends Repository<Property, Long> {
    Property findById(Long id);
    Property save(Property property);
    List<Property> findAll();
    Property getFakeProperty(String propertyName);
    List<Property> getPropertyWithMinimumStars(String propertyName);
}
