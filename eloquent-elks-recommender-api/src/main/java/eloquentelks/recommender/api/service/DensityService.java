package eloquentelks.recommender.api.service;

import com.google.gson.JsonPrimitive;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import eloquentelks.recommender.api.helper.FeatureCollectionAccessor;
import eloquentelks.recommender.api.helper.IFeatureCollectionAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for retrieving, normalizing and aggregating the densities of POIs in NYC. The retrieval
 * is delegated to {@link IDensityRestService}, while normalization and aggregation are performed in this class.
 */
@Service
public class DensityService implements IDensityService {

    /**
     * Helper to access FeatureCollections in a convenient manner
     */
    private final IFeatureCollectionAccessor featureCollectionAccessor;

    /**
     * Accesses the POI density REST service
     */
    private final IDensityRestService densityRestService;

    /**
     * The minimum density is always 0 in our case
     */
    private static final int MIN_DENSITY = 0;

    /**
     * Constructor
     * @param helper             Provides access to FeatureCollections
     * @param densityRestService Provides access to the POI Density API
     */
    @Autowired
    public DensityService(FeatureCollectionAccessor helper, IDensityRestService densityRestService) {
        this.featureCollectionAccessor = helper;
        this.densityRestService = densityRestService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FeatureCollection> getDensities(List<String> attractionTypes) {
        return densityRestService.getDensities(attractionTypes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FeatureCollection> normalizeDensities(List<FeatureCollection> featureCollections) {
        for (FeatureCollection collection : featureCollections) {
            normalizeDensity(collection);
        }

        return featureCollections;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FeatureCollection normalizeDensity(FeatureCollection featureCollection) {
        double maxDensity = featureCollectionAccessor.getMaxDensity(featureCollection);

        normalize(featureCollection, MIN_DENSITY, maxDensity);

        return featureCollection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FeatureCollection aggregateDensities(List<FeatureCollection> featureCollections) {
        // check preconditions
        if (featureCollections.isEmpty()) {
            throw new IllegalArgumentException("featureCollections must not be an empty list");
        }

        var combinedFeatures = FeatureCollection.fromFeatures(new ArrayList<>());

        List<Integer> ids = new ArrayList<>();
        featureCollections.forEach(collection -> {
            collection.features().forEach(feature -> {
                aggregateDensity(combinedFeatures, ids, collection, feature);
            });
        });

        return combinedFeatures;
    }

    /**
     * Combines the densities of already aggregated densities and new density values into one FeatureCollection
     * @param combinedFeatures Target FeatureCollection where the aggregated densities will reside
     * @param ids List of ids that are already considered in the aggregation, used to decide if new Features must be created prior to aggregation
     * @param collection Current collection to be included in the aggregation
     * @param feature Feature to be aggregated
     */
    private void aggregateDensity(FeatureCollection combinedFeatures, List<Integer> ids, FeatureCollection collection, Feature feature) {
        int featureId = featureCollectionAccessor.getId(feature);

        if (!ids.contains(featureId)) {
            var emptyFeature = Feature.fromGeometry(feature.geometry());
            emptyFeature.properties().add("id", new JsonPrimitive(featureId));
            emptyFeature.properties().add("poiCount", new JsonPrimitive(feature.properties().get("poiCount").getAsDouble()));
            combinedFeatures.features().add(emptyFeature);
            ids.add(featureId);
        } else {
            double existingDensity = featureCollectionAccessor.getDensity(combinedFeatures, featureId);
            double currentDensity = featureCollectionAccessor.getDensity(collection, featureId);
            featureCollectionAccessor.setDensity(combinedFeatures, featureId, existingDensity + currentDensity);
        }
    }

    /**
     * Normalizes the poiCounts inside a collection to values between 0 and 1
     * Density normalization formula from <a href="https://stats.stackexchange.com/questions/70801/how-to-normalize-data-to-0-1-range">https://stats.stackexchange.com/questions/70801/how-to-normalize-data-to-0-1-range</a>
     *
     * @param collection FeatureCollection to be processed
     * @param minDensity Minimum poiCount value
     * @param maxDensity Maximum poiCount value
     */
    private void normalize(FeatureCollection collection, double minDensity, double maxDensity) {
        for (Feature feature : collection.features()) {
            int id = featureCollectionAccessor.getId(feature);

            double poiCount = featureCollectionAccessor.getDensity(feature);

            double density = (poiCount - minDensity) / (maxDensity - minDensity);

            featureCollectionAccessor.setDensity(collection, id, density);
        }
    }

}
