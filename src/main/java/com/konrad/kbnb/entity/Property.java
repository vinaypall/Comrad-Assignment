package com.konrad.kbnb.entity;

import com.konrad.kbnb.Model.PropertyRequestBody;
import jakarta.persistence.*;

@Entity
public class Property {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private double rate;
    @Column
    private String city;
    @Column
    String houseType;
    @Column
    String imageAltText;
    @Column
    String idSlug;
    @Column
    long hostId;
    @Column
    String country;
    @Column
    String imageSrc;
    @Column
    int stars;
    @Column
    String territory;

    public Property() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getImageAltText() {
        return imageAltText;
    }

    public void setImageAltText(String imageAltText) {
        this.imageAltText = imageAltText;
    }

    public String getIdSlug() {
        return idSlug;
    }

    public void setIdSlug(String idSlug) {
        this.idSlug = idSlug;
    }

    public long getHostId() {
        return hostId;
    }

    public void setHostId(long hostId) {
        this.hostId = hostId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public static Property propertyFromDto(PropertyRequestBody propertyRequestBody){
        Property property = new Property();
        property.setName(propertyRequestBody.getName());
        property.setRate(propertyRequestBody.getRate());
        property.setCity(propertyRequestBody.getCity());
        property.setHouseType(propertyRequestBody.getHouseType());
        property.setImageAltText(propertyRequestBody.getImageAltText());
        property.setIdSlug(propertyRequestBody.getIdSlug());
        property.setCountry(propertyRequestBody.getCountry());
        property.setImageSrc(propertyRequestBody.getImageSrc());
        property.setStars(propertyRequestBody.getStars());
        property.setTerritory(propertyRequestBody.getTerritory());
        property.setHostId(propertyRequestBody.getHostId());
        return property;
    }
}
