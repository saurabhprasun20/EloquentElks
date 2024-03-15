package eloquentelks.recommender.api.helper;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static eloquentelks.recommender.api.Constants.GEOJSON_FEATURE_PROPERTY_ID;
import static eloquentelks.recommender.api.Constants.GEOJSON_FEATURE_PROPERTY_POICOUNT;

/**
 * Provides convenient access to GeoJSON FeatureCollections and their Features and Properties as well.
 */
@Service
public class FeatureCollectionAccessor implements IFeatureCollectionAccessor {

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDensity(FeatureCollection collection, int id){
        var feature = getFeatureById(collection, id);

        if(feature == null){
            throw new IllegalArgumentException("Feature with id " + id + " does not exist in the FeatureCollection");
        }

        return getDensity(feature);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDensity(Feature feature) {
        if(feature == null){
            throw new IllegalArgumentException("Feature must not be null");
        }

        JsonObject properties = feature.properties();

        return properties.get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsDouble();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Feature getFeatureById(FeatureCollection collection, int id){
        Optional<Feature> feature = collection.features().stream().filter(f -> f.properties().get("id").getAsInt() == id).findFirst();

        if(!feature.isPresent()){
            return null;
        }

        return feature.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FeatureCollection copyFeatureIds(FeatureCollection featureCollection){
        var collection = FeatureCollection.fromJson(featureCollection.toJson());

        collection.features().forEach(f -> f.properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT));

        collection.features().forEach(f -> f.properties().add(GEOJSON_FEATURE_PROPERTY_POICOUNT, new JsonPrimitive(0)));

        return collection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMaxDensity(FeatureCollection collection) {
        double maxDensity = Double.MIN_VALUE;

        for (Feature f : collection.features()) {
            double density = f.properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsDouble();
            maxDensity = Math.max(maxDensity, density);
        }

        return maxDensity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDensity(FeatureCollection collection, int id, double density){
        var feature = getFeatureById(collection, id);

        feature.properties().remove(GEOJSON_FEATURE_PROPERTY_POICOUNT);

        feature.properties().add(GEOJSON_FEATURE_PROPERTY_POICOUNT, new JsonPrimitive(density));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId(Feature feature) {
        return feature.properties().get(GEOJSON_FEATURE_PROPERTY_ID).getAsInt();
    }
}
