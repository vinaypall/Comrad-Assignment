package com.konrad.kbnb.fixtures;

import com.konrad.kbnb.entity.Property;

public class PropertyFixture {

    public static Property createPropertyWithId(long id) {
        Property property = new Property();
        property.setId(id);
        return property;
    }
}
