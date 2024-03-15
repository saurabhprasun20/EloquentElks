package eloquentelks.poi.api.repository;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

/**
 * Provides access to MongoDb feature collection
 */
@Repository
public class FeatureRepository implements IFeatureRepository{

    /**
     * Key for the GeoJson distance query
     */
    private static final String QUERY_KEY = "geometry";

    /**
     * Data access component
     */
    private final MongoTemplate mongoTemplate;

    /**
     * Name of the feature collection on the database
     */
    private static final String FEATURE_COLLECTION = "feature";

    /**
     * Property path of the tourism field
     */
    private static final String PROPERTY_TOURISM = "properties.tourism";

    /**
     * Property path of the distance field
     */
    private static final String PROPERTY_DISTANCE = "properties.distance";

    /**
     * Attraction type famous
     */
    private static final String FAMOUS_ATTRACTION = "famous";

    /**
     * Maximum distance in meters to be considered for the famous POI distance calculation
     */
    private static final int MAX_DISTANCE_TO_FAMOUS = 25000;

    /**
     * Constructor
     * @param mongoTemplate Data access component
     */
    public FeatureRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * {@inheritDoc}
     * Note that the radius calculation is done by the database using a geospatial spherical index.
     */
    @Override
    public List<Feature> getFeatures(Point center, double radius){
        Criteria criteria = Criteria.where(QUERY_KEY).nearSphere(new GeoJsonPoint(center.longitude(), center.latitude())).maxDistance(radius);

        Query query = new Query(criteria);

        List<String> documents = mongoTemplate.find(query, String.class, FEATURE_COLLECTION);

        return convert(documents);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Feature> getFeatures(String attractionType){
        Criteria criteria = Criteria.where(PROPERTY_TOURISM).is(attractionType);

        Query query = new Query(criteria);

        List<String> document = mongoTemplate.find(query, String.class, FEATURE_COLLECTION);

        return convert(document);
    }

    /**
     * {@inheritDoc}
     * Built after this example: <a href="https://stackoverflow.com/questions/32795980/how-to-get-distance-mongodb-template-near-function">StackOverflow</a>
     * Note that the distance calculation is done by the database using a geospatial spherical index.
     */
    @Override
    public List<Feature> getDistanceOfFamousFeatures(Point point) {
        Criteria famousCriteria = Criteria.where(PROPERTY_TOURISM).is(FAMOUS_ATTRACTION);

        Query famousPoiQuery = new Query(famousCriteria);

        NearQuery query = NearQuery.near(point.longitude(), point.latitude())
                .query(famousPoiQuery)
                .maxDistance(MAX_DISTANCE_TO_FAMOUS)
                .spherical(true)
                .in(Metrics.KILOMETERS);

        Aggregation aggregation = newAggregation(Aggregation.geoNear(query, PROPERTY_DISTANCE));

        AggregationResults<String> aggregate = mongoTemplate.aggregate(aggregation, FEATURE_COLLECTION, String.class);

        return convert(aggregate.getMappedResults());
    }

    /**
     * Converts JSON documents to {@link com.mapbox.geojson.Feature}
     * @param documents List of JSON strings from the database
     * @return List of GeoJson Features
     */
    private List<Feature> convert(List<String> documents){
        List<Feature> features = new ArrayList<>();

        documents.forEach(document -> features.add(Feature.fromJson(document)));

        return features;
    }
}
