package com.airbnb.eloquentelksbackend.repository;

import com.airbnb.eloquentelksbackend.entity.Property;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;


@SpringBootTest
@AutoConfigureMockMvc
public class PropertyRepositoryTest {

    @Mock
    PropertyRepository propertyRepository;

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
    public void testFinaAll(){
        Mockito.when(propertyRepository.findAll()).thenReturn(propertyList);
        List<Property> fetchedPropList = propertyRepository.findAll();
        assertEquals(2,fetchedPropList.size());
    }

    @Test
    public void testFindInBoundingBox(){
        Mockito.when(propertyRepository.findInBoundingBox(anyDouble(),anyDouble(),anyDouble(),anyDouble(),anyInt(),anyInt())).thenReturn(propertyList);
        List<Property> fetchedList = propertyRepository.findInBoundingBox(-23.0,14.0,123.0,98.8, 45,105);
        assertEquals(2,fetchedList.size());
    }



}
