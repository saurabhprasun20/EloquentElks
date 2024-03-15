package com.airbnb.eloquentelksbackend.controller;

import com.airbnb.eloquentelksbackend.DTO.DTOMapper;
import com.airbnb.eloquentelksbackend.DTO.PropertyFetchDTO;
import com.airbnb.eloquentelksbackend.entity.BoundingBox;
import com.airbnb.eloquentelksbackend.entity.Property;
import com.airbnb.eloquentelksbackend.service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Rest controller for fetching property
 */

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    /**
     * Service to interact and fetch the properties from DAO.
     */
    private final PropertyService propertyService;

    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(PropertyController.class);

    /**
     *
     * @param propertyService autowired
     */
    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /** Returns all the properties within the bounding box of area
     * @param min Minimum price of an AirBnb
     * @param max Maximum price of an AirBnb
     * @param north Northern boundary to be considered
     * @param east Eastern boundary to be considered
     * @param south Southern boundary to be considered
     * @param west Western boundary to be considered
     * @return A list of all the airbnb properties {@link com.airbnb.eloquentelksbackend.entity.Property} list in the database for New York .
     */
    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/airbnb")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PropertyFetchDTO> getProperty(@RequestParam(defaultValue = "1") int min,
                                              @RequestParam(defaultValue = "999") int max,
                                              @RequestParam(defaultValue = "40.92842013954254") double north,
                                              @RequestParam(defaultValue = "-73.68572896669198") double east,
                                              @RequestParam(defaultValue = "40.47050851839848") double south,
                                              @RequestParam(defaultValue = "-74.29541147644714") double west){
        BoundingBox bbox = new BoundingBox(north, east, south, west);

        List<Property> propertiesList = propertyService.getAllProperties(bbox, min, max);

        return DTOMapper.INSTANCE.map(propertiesList.stream().limit(100).collect(Collectors.toList()));
    }
}
