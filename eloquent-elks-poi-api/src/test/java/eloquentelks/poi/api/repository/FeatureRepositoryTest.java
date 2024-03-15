package eloquentelks.poi.api.repository;

import com.google.gson.JsonPrimitive;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link eloquentelks.poi.api.repository.FeatureRepository}
 */
@ExtendWith(MockitoExtension.class)
class FeatureRepositoryTest {

    /**
     * Stub of {@link org.springframework.data.mongodb.core.MongoTemplate}
     */
    @Mock
    private MongoTemplate mongoTemplate;

    /**
     * List of features as strings to be returned by {@link org.springframework.data.mongodb.core.MongoTemplate}
     */
    private static List<String> featureList;

    /**
     * Initializes two features that are returned by the MongoTemplate stub
     */
    @BeforeAll
    static void setUp(){
        String featureJson1 = "{\n" +
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
                "\t}";

        String featureJson2 = "{\n" +
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
                "\t}";

        featureList = Arrays.asList(featureJson1, featureJson2);
    }

    /**
     * Checks if {@link eloquentelks.poi.api.repository.FeatureRepository#getFeatures(Point, double)} considers the radius for returning the relevant features
     */
    @Test
    void testGetFeaturesRadius(){
        // arrange
        when(mongoTemplate.find(any(Query.class), any(Class.class), anyString())).thenReturn(featureList);
        IFeatureRepository featureRepository = new FeatureRepository(mongoTemplate);
        Feature f1 = Feature.fromJson(featureList.get(0));
        Point center = (Point)f1.geometry();

        // act
        List<Feature> features = featureRepository.getFeatures(center, 16000);

        // assert
        assertEquals(2, features.size());
    }

    /**
     * Checks if {@link eloquentelks.poi.api.repository.FeatureRepository#getFeatures(String)} returns a list of features by attractionType
     */
    @Test
    void testGetFeaturesAttractionType(){
        // arrange
        when(mongoTemplate.find(any(Query.class), any(Class.class), anyString())).thenReturn(featureList);
        IFeatureRepository featureRepository = new FeatureRepository(mongoTemplate);

        // act
        List<Feature> features = featureRepository.getFeatures("restaurant");

        // assert
        assertEquals(2, features.size());
    }

    /**
     * Checks if {@link eloquentelks.poi.api.repository.FeatureRepository#getDistanceOfFamousFeatures(Point)} includes the distance in its results
     */
    @Test
    void testGetDistanceOfFamousFeatures(){
        // arrange
        Feature famous = Feature.fromJson(featureList.get(1));
        famous.addProperty("distance", new JsonPrimitive(1200));
        AggregationResults<String> aggregationResults = mock(AggregationResults.class);
        when(aggregationResults.getMappedResults()).thenReturn(Collections.singletonList(famous.toJson()));
        when(mongoTemplate.aggregate(any(Aggregation.class), anyString(), any(Class.class))).thenReturn(aggregationResults);

        IFeatureRepository featureRepository = new FeatureRepository(mongoTemplate);
        Feature f1 = Feature.fromJson(featureList.get(0));
        Point referencePoint = (Point)f1.geometry();

        // act
        List<Feature> distanceOfFamousFeatures = featureRepository.getDistanceOfFamousFeatures(referencePoint);

        // assert
        assertEquals(1200, distanceOfFamousFeatures.get(0).getProperty("distance").getAsInt());
    }
}