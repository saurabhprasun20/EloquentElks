package eloquentelks.recommender.api.helper;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static eloquentelks.recommender.api.Constants.GEOJSON_FEATURE_PROPERTY_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for @see{@link FeatureCollectionAccessor}
 */
class FeatureCollectionAccessorTests {

    /**
     * FeatureCollection containing the density data
     */
    private FeatureCollection featureCollection;

    /**
     * Object under test
     */
    private IFeatureCollectionAccessor accessor;

    /**
     * Test initialization
     */
    @BeforeEach
    void setUp(){
        // arrange
        featureCollection = FeatureCollectionFactory.create(Map.of(1, 42));
        accessor = new FeatureCollectionAccessor();
    }

    /**
     * Tests if the getPoiDensity method works properly
     */
    @Test
    void testGetPoiDensity(){
        // act
        double density = accessor.getDensity(featureCollection, 1);

        // assert
        assertEquals(42, density);
    }

    /**
     * Tests if the getPoidensity method throws an execption if null is passed for a Feature
     */
    @Test
    void testGetPoiDensity_nullFeature(){
        // act, assert
        assertThrows(IllegalArgumentException.class, () -> accessor.getDensity(null));
    }

    /**
     * Tests that the getPoiDensity method throws an exception if the
     * preconditions are not met
     */
    @Test
    void testGetPoiDensity_featureNotExisting(){
        // act, assert
        assertThrows(IllegalArgumentException.class, () -> accessor.getDensity(featureCollection, 0));
    }

    /**
     * Tests if the getFeatureById method works properly
     */
    @Test
    void testGetFeatureById(){
        // act
        Feature feature = accessor.getFeatureById(featureCollection, 1);

        // assert
        assertEquals(1, feature.properties().get(GEOJSON_FEATURE_PROPERTY_ID).getAsInt());
    }

    /**
     * Tests if the getFeatureById method throws an exception if the preconditions
     * are not met
     */
    @Test
    void testGetFeatureById_notFound(){
        // act
        Feature feature = accessor.getFeatureById(featureCollection, 0);

        // assert
        assertNull(feature);
    }

    /**
     * Tests if the copyFeatureIds method creates a deep copy of the original
     * collection
     */
    @Test
    void testCopyFeatureIds(){
        // act
        FeatureCollection copy = accessor.copyFeatureIds(featureCollection);

        // assert
        assertEquals(42, accessor.getDensity(featureCollection, 1));
        assertEquals(0, accessor.getDensity(copy, 1));
    }

    /**
     * Tests if the setDensity method works properly
     */
    @Test
    void testSetDensity(){
        // act
        accessor.setDensity(featureCollection, 1, 244);

        // assert
        assertEquals(244, accessor.getDensity(featureCollection, 1));
    }
}
