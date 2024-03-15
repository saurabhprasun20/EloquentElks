package com.airbnb.eloquentelksbackend.repository;

import com.airbnb.eloquentelksbackend.entity.BoundingBox;
import com.airbnb.eloquentelksbackend.entity.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Repository to access the Property data from the database
 */
@Repository("propertyRepository")
public interface PropertyRepository extends MongoRepository<Property, Long> {

    /**
     * 
     * @param north Latitude of the bounding box in the Northern edge
     * @param east Longitude of the bounding box in the Eastern edge
     * @param south Latitude of the bounding box in the Northern edge
     * @param west Longitude of the bounding box in the Western edge
     * @param minPrice minPrice filter to fetch the properties above the minPrice
     * @param maxPrice maxPrice filter to fetch the properties below the maxPrice
     * @return List of Properties @see {@link com.airbnb.eloquentelksbackend.entity.Property}
     */
    @Query(value = "{ 'latitude': {'$lt': ?0, '$gt': ?2}, 'longitude': {'$lt': ?1, '$gt': ?3}, 'price': {'$gt': ?4, '$lt': ?5}, 'availability_365': {'$gt': 120}}")
    List<Property> findInBoundingBox(double north, double east, double south, double west, int minPrice, int maxPrice);


}
