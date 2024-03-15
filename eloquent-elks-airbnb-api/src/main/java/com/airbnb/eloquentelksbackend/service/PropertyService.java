package com.airbnb.eloquentelksbackend.service;

import com.airbnb.eloquentelksbackend.entity.BoundingBox;
import com.airbnb.eloquentelksbackend.entity.Property;
import com.airbnb.eloquentelksbackend.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    private final Logger log = LoggerFactory.getLogger(PropertyService.class);

    /**
     * repository to access the database
     */
    private final PropertyRepository propertyRepository;


    /**
     * Constructor to autowire the propertyRepository
     * @param propertyRepository
     */
    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    /**
     * Serivce to return the list of properties after fetching from the database.
     * @param boundingBox coordinates of the {@link com.airbnb.eloquentelksbackend.entity.BoundingBox}.
     * @param min minimum price filter for the list of properties.
     * @param max max price filter for the list of properties.
     * @return list of {@link com.airbnb.eloquentelksbackend.entity.Property}
     */
    public List<Property> getAllProperties(BoundingBox boundingBox, int min, int max){
        List<Property> allEntries = propertyRepository.findInBoundingBox(boundingBox.getNorth(), boundingBox.getEast(),
                boundingBox.getSouth(), boundingBox.getWest(), min, max);
        return allEntries;
    }
}
