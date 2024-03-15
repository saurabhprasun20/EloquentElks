package com.airbnb.eloquentelksbackend.service;

import com.airbnb.eloquentelksbackend.entity.BoundingBox;
import com.airbnb.eloquentelksbackend.entity.Property;
import com.airbnb.eloquentelksbackend.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for Property service @use{@link com.airbnb.eloquentelksbackend.service.PropertyService}
 */
@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    /**
     * Mocked the service
     */
    @InjectMocks
    private PropertyService propertyService;

    /**
     * Stub of a @see{@link com.airbnb.eloquentelksbackend.repository.PropertyRepository}
     */
    @Mock
    private PropertyRepository propertyRepository;

    /**
     * List of properties that is returned by the method of repository
     */
    private static List<Property> propertyList;

    /**
     * Initialized the list that will be returned by the repository method.
     */
    @BeforeAll
    public static void setup()
    {
        Property property1 = new Property();
        property1.setId("1");
        property1.setPrice(200);
        property1.setName("Name");
        property1.setHost_name("HostName");

        Property property2 = new Property();
        property2.setId("2");
        property2.setName("Name 2");
        property2.setHost_name("Host Name 2");
        property2.setPrice(120);

        propertyList = new ArrayList<>();
        propertyList.add(property1);
        propertyList.add(property2);
    }

    @Test
    public void testPropertyService(){
        BoundingBox bbox = new BoundingBox(40.92842013954254, -73.68572896669198, 40.47050851839848, -74.29541147644714);
        Mockito.when(propertyRepository.findInBoundingBox(bbox.getNorth(), bbox.getEast(),
                bbox.getSouth(), bbox.getWest(), 0,999)).thenReturn(propertyList);

        List<Property> propertyListReturned = propertyService.getAllProperties(bbox,0,999);
        assertEquals(propertyListReturned.size(),propertyList.size());
    }

}
