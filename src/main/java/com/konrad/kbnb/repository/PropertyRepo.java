package com.konrad.kbnb.repository;


import com.konrad.kbnb.entity.Property;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepo extends Repository<Property, Long> {
    Property findById(Long id);
    Property save(Property property);
    List<Property> findAll();
    Property getFakeProperty(String propertyName);
    List<Property> getPropertyWithMinimumStars(String propertyName);

    @Query("SELECT p FROM Property p WHERE p.name LIKE CONCAT('%', :name, '%') ORDER BY POSITION(:name IN p.name)")
    List<Property> findByNameContaining(@Param("name") String name);

    // 2nd task
    boolean existsById(Long id);
    void deleteById(Long id);

    //3rd task 
    Page<Property> findAll(Pageable pageable);

    //4th
    @Query("SELECT p FROM Property p WHERE p.name LIKE CONCAT('%', :name, '%') AND p.stars >= :stars ORDER BY POSITION(:name IN p.name)")
    List<Property> findByNameContainingAndStarsGreaterThanEqual(@Param("name") String name, @Param("stars") int stars);
}
