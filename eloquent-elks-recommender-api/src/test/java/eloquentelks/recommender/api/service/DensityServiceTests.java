package eloquentelks.recommender.api.service;

import com.mapbox.geojson.FeatureCollection;
import eloquentelks.recommender.api.helper.FeatureCollectionAccessor;
import eloquentelks.recommender.api.helper.FeatureCollectionFactory;
import eloquentelks.recommender.api.helper.IFeatureCollectionAccessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static eloquentelks.recommender.api.Constants.GEOJSON_FEATURE_PROPERTY_POICOUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for @see {@link eloquentelks.recommender.api.service.DensityService}
 */
class DensityServiceTests {

    /**
     * Object under test
     */
    private IDensityService densityService;

    /**
     * Helper to access the FeatureCollections
     */
    private IFeatureCollectionAccessor accessor;

    /**
     * Test initialization
     */
    @BeforeEach
    void setUp(){
        // arrange
        IDensityRestService densityRestService = mock(IDensityRestService.class);
        Map<Integer, Integer> densities1 = Map.of(1, 42, 2, 98, 3, 182);
        Map<Integer, Integer> densities2 = Map.of(1, 7, 2, 1, 3, 12);


        when(densityRestService.getDensities(any(List.class))).thenReturn( List.of(
                FeatureCollectionFactory.create(densities1),
                FeatureCollectionFactory.create(densities2)
        ));

        densityService = new DensityService(new FeatureCollectionAccessor(), densityRestService);
        accessor = new FeatureCollectionAccessor();
    }

    /**
     * Tests if the density property is contained in the FeatureCollection
     */
    @Test
    void testGetDensity()
    {
        // act
        List<FeatureCollection> densities = densityService.getDensities(List.of("museum"));
        double density = accessor.getDensity(densities.get(0), 1);
        // assert
        assertEquals(42, density);
    }

    /**
     * Tests if the density normalization works properly
     */
    @Test
    void testNormalizeDensities(){
        // arrange
        FeatureCollection featureCollection1 = FeatureCollectionFactory.create(Map.of(1, 42, 2, 98, 3, 182));
        FeatureCollection featureCollection2 = FeatureCollectionFactory.create(Map.of(1, 7, 2, 1, 3, 12));

        // act
        List<FeatureCollection> resultingCollections = densityService.normalizeDensities(List.of(featureCollection1, featureCollection2));

        // assert
        assertEquals(0.23076923076923078, accessor.getFeatureById(resultingCollections.get(0), 1).properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsDouble());
        assertEquals(0.5384615384615384, accessor.getFeatureById(resultingCollections.get(0), 2).properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsDouble());
        assertEquals(1, accessor.getFeatureById(resultingCollections.get(0), 3).properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsDouble());
        assertEquals(0.58333333333333333, accessor.getFeatureById(resultingCollections.get(1), 1).properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsDouble());
        assertEquals(0.08333333333333333, accessor.getFeatureById(resultingCollections.get(1), 2).properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsDouble());
        assertEquals(1, accessor.getFeatureById(resultingCollections.get(1), 3).properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsDouble());
    }

    @Test
    void testNormalizeDensity(){
        // arrange
        FeatureCollection featureCollection = FeatureCollectionFactory.create(Map.of(1, 42, 2, 98, 3, 182));

        // act
        FeatureCollection normalizedCollection = densityService.normalizeDensity(featureCollection);

        // assert
        assertEquals(0.23076923076923078, accessor.getFeatureById(normalizedCollection, 1).properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsDouble());
        assertEquals(0.5384615384615384, accessor.getFeatureById(normalizedCollection, 2).properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsDouble());
        assertEquals(1, accessor.getFeatureById(normalizedCollection, 3).properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsDouble());

    }

    /**
     * Tests if the density aggregation works properly
     */
    @Test
    void testAggregateDensities(){
        // arrange
        FeatureCollection featureCollection = FeatureCollectionFactory.create(Map.of(1, 42));

        // act
        FeatureCollection resultingCollection = densityService.aggregateDensities(List.of(featureCollection, featureCollection));

        // assert
        assertEquals(84, resultingCollection.features().get(0).properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsInt());
    }

    /**
     * Tests if the density aggregation throws an exception if an empty feature collection list is passed
     */
    @Test
    void testAggregateDensities_emptyList(){
        // act, assert
        assertThrows(IllegalArgumentException.class, () -> densityService.aggregateDensities(List.of()));
    }
}
