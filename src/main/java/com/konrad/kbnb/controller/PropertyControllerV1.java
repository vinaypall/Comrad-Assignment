package com.konrad.kbnb.controller;

import com.konrad.kbnb.Model.GenericResponse;
import com.konrad.kbnb.Model.LookUpTreeNode;
import com.konrad.kbnb.Model.PropertyRequestBody;
import com.konrad.kbnb.entity.Property;
import com.konrad.kbnb.service.PropertyService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property")
public class PropertyControllerV1 {

    private final PropertyService propertyService;

    public PropertyControllerV1(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Property>> getPropertyById(@PathVariable("id") long id) {
        return ResponseEntity.ok(new GenericResponse<>(null, propertyService.getProperty(id)));
    }

    @GetMapping("/fake")
    public ResponseEntity<GenericResponse<Property>> getFakePropertyById(@RequestParam("name") String name) {
        return ResponseEntity.ok(new GenericResponse<>(null, propertyService.getFakeProperty(name)));
       
    }

    @GetMapping("/minstars")
    public ResponseEntity<GenericResponse<List<Property>>> getPropertiesWithMinimumStars(
        @RequestParam("name") String name,
        @RequestParam("stars") int stars) {
        List<Property> properties = propertyService.getPropertiesWithNameAndMinimumStars(name, stars);
        return ResponseEntity.ok(new GenericResponse<>(null, properties));
    }


    @PostMapping
    public ResponseEntity<GenericResponse<Property>> addProperty(@RequestBody PropertyRequestBody propertyBody) {
        return ResponseEntity.ok(new GenericResponse<>(null, propertyService.addProperty(propertyBody)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable("id") long id) {
        boolean isDeleted = propertyService.deleteProperty(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body("Property successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Property not found");
        }
    }

    @GetMapping("/properties")
    public ResponseEntity<GenericResponse<List<Property>>> getPropertyByName(@RequestParam("name") String name) {
    List<Property> properties = propertyService.getPropertyByName(name);
    return ResponseEntity.ok(new GenericResponse<>(null, properties));
}

@GetMapping("/page/{pageNum}")
public ResponseEntity<GenericResponse<List<Property>>> getPropertyPage(@PathVariable int pageNum,
                                                                       @RequestParam(defaultValue = "10") int pageSize) {
    List<Property> properties = propertyService.getPropertiesPage(pageNum, pageSize);
    return ResponseEntity.ok(new GenericResponse<>(null, properties));
}

    @GetMapping("/superhost")
    public ResponseEntity<GenericResponse<List<Long>>> getSuperHosts(){
        //TODO: get super hosts from properties
        return ResponseEntity.ok(new GenericResponse<>(null, propertyService.getSuperHost()));
    }

    @GetMapping("/lookuptree")
    public ResponseEntity<GenericResponse<LookUpTreeNode>> getPropertyLookupTree(){
        //TODO: get Property look up tree
        return ResponseEntity.ok(new GenericResponse<>(null, propertyService.getLookUpTree()));
    }
}
