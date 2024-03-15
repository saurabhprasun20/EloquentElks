package eloquentelks.poi.api.service;

import com.google.gson.JsonPrimitive;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import eloquentelks.poi.api.model.PoiGetDto;
import eloquentelks.poi.api.repository.FeatureRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link eloquentelks.poi.api.service.PoiService}
 */
@ExtendWith(MockitoExtension.class)
class PoiServiceTests {

    /**
     * Stub of a {@link eloquentelks.poi.api.repository.FeatureRepository}
     */
    @Mock
    private FeatureRepository poiRepository;

    /**
     * Features list that is used by the FeatureRepository stub
     * */
    private static List<Feature> features;

    /**
     * Sets up two features that are returned by the FeatureRepository stub
     */
    @BeforeAll
    static void setUp(){
        Feature f1 = Feature.fromJson("{\n" +
                "\t  \"type\": \"Feature\",\n" +
                "\t  \"properties\": {\n" +
                "\t\t\"@id\": \"node/8533468096\",\n" +
                "\t\t\"opening_hours\": \"Mo-Sa 11:00-19:00; Su 12:00-17:00\",\n" +
                "\t\t\"tourism\": \"gallery\"\n" +
                "\t  },\n" +
                "\t  \"geometry\": {\n" +
                "\t\t\"type\": \"Point\",\n" +
                "\t\t\"coordinates\": [\n" +
                "\t\t  -74.0447579,\n" +
                "\t\t  40.7868407\n" +
                "\t\t]\n" +
                "\t  },\n" +
                "\t  \"id\": \"node/8533468096\"\n" +
                "\t}");

        Feature f2 = Feature.fromJson("{\n" +
                "\t  \"type\": \"Feature\",\n" +
                "\t  \"properties\": {\n" +
                "\t\t\"@id\": \"node/8541462092\",\n" +
                "\t\t\"artwork_type\": \"mural\",\n" +
                "\t\t\"name\": \"Yusuf Hawkins\",\n" +
                "\t\t\"tourism\": \"artwork\"\n" +
                "\t  },\n" +
                "\t  \"geometry\": {\n" +
                "\t\t\"type\": \"Point\",\n" +
                "\t\t\"coordinates\": [\n" +
                "\t\t  -73.9475876,\n" +
                "\t\t  40.6804506\n" +
                "\t\t]\n" +
                "\t  },\n" +
                "\t  \"id\": \"node/8541462092\"\n" +
                "\t}");

        features = new ArrayList<>();
        features.add(f1);
        features.add(f2);
    }

    /**
     * Tests if the PoiService is able to return all PoIs from the FeatureRepository
     */
    @Test
    void testGetAllPois(){
        // arrange
        when(poiRepository.getFeatures(Point.fromLngLat(-73.9475876, 40.6804506), 500)).thenReturn(features);
        IPoiService service = new PoiService(poiRepository);

        // act
        List<PoiGetDto> pois = service.getAllPois( -73.9475876, 40.6804506);

        // assert
        assertEquals(2, pois.size());
    }

    /**
     * Checks if the POIService is able to load filtered from the FeatureRepository.
     */
    @Test
    void testGetPoisByAttractionType(){
        // arrange
        when(poiRepository.getFeatures(any(String.class))).thenReturn(features);
        IPoiService service = new PoiService(poiRepository);

        // act
        List<PoiGetDto> restaurant = service.getPoisByAttractionType("restaurant");

        // assert
        assertEquals(2, restaurant.size());
    }

    /**
     * Checks if the PoiService can handle radius results from the FeatureRepository.
     */
    @Test
    void testGetFamousPoisWithDistance(){
        // arrange
        Feature f1 = Feature.fromGeometry(Point.fromLngLat(-73.8, 40.74));
        Feature f2 = Feature.fromGeometry(Point.fromLngLat(-74.01, 40.61));
        f1.addProperty("distance", new JsonPrimitive(1500.75));
        f2.addProperty("distance", new JsonPrimitive(125.98));
        when(poiRepository.getDistanceOfFamousFeatures(any(Point.class))).thenReturn(List.of(f1, f2));

        IPoiService service = new PoiService(poiRepository);

        // act
        List<PoiGetDto> pois = service.getFamousPoisWithDistance(-73.96839771059717, 40.778716795476605);

        // assert
        assertTrue(pois.stream().allMatch(poi -> poi.getDistance() > 0));
    }
}
