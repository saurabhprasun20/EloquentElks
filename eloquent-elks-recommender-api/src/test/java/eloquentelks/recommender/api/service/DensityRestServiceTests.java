package eloquentelks.recommender.api.service;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import eloquentelks.recommender.api.helper.FeatureCollectionAccessor;
import eloquentelks.recommender.api.helper.FeatureCollectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the @see{@link eloquentelks.recommender.api.service.DensityRestService} class
 */
class DensityRestServiceTests {

    /**
     * Access to FeatureCollections
     */
    private FeatureCollectionAccessor accessor;

    /**
     * Mock of the RestTemplate
     */
    private RestTemplate mockRestTemplate;

    /**
     * Sets up the common elements of the unit tests
     */
    @BeforeEach
    void setUp(){
        accessor = new FeatureCollectionAccessor();
        mockRestTemplate = mock(RestTemplate.class);
    }

    /**
     * Validates that getDensity works as intended
     */
    @Test
    void testGetDensity(){
        // arrange
        when(mockRestTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(FeatureCollectionFactory.create(Map.of(1, 42)).toJson());
        DensityRestService service = new DensityRestService(mockRestTemplate);
        String attractionType = "museum";

        // act
        FeatureCollection density = service.getDensity(attractionType);

        // assert
        assertEquals(42, accessor.getDensity(density.features().get(0)));
    }

    /**
     * Validates that getDensity can handle null results from the REST service
     */
    @Test
    void testGetDensityNull(){
        // arrange
        when(mockRestTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(null);
        DensityRestService service = new DensityRestService(mockRestTemplate);
        String attractionType = "museum";

        // act
        FeatureCollection density = service.getDensity(attractionType);

        // assert
        assertNull(density);
    }

    /**
     * Validates that getDensities works as intended
     */
    @Test
    void testGetDensities(){
        // arrange
        when(mockRestTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(FeatureCollectionFactory.create(Map.of(1, 42)).toJson());
        DensityRestService service = new DensityRestService(mockRestTemplate);
        List<String> attractionTypes = List.of("museum", "statue");

        // act
        List<FeatureCollection> densities = service.getDensities(attractionTypes);

        // assert
        assertEquals(42, accessor.getDensity(densities.get(0).features().get(0)));
        assertEquals(42, accessor.getDensity(densities.get(1).features().get(0)));
    }
}
