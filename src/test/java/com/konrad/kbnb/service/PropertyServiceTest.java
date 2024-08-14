package com.konrad.kbnb.service;

import com.konrad.kbnb.entity.Property;
import com.konrad.kbnb.fixtures.PropertyFixture;
import com.konrad.kbnb.repository.PropertyRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @Mock
    PropertyRepo propertyRepo;
    @InjectMocks
    PropertyService propertyService;

    @Test
    void getPropertyByIdTest() {
        Property mockProperty = PropertyFixture.createPropertyWithId(10L);
        when(propertyRepo.findById(10L)).thenReturn(mockProperty);

        Property returnedProperty = propertyService.getProperty(10L);
        assertEquals(mockProperty.getId(), returnedProperty.getId());
    }
}
