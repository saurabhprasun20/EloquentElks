package eloquentelks.recommender.api.helper;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;

/**
 * Extends the functionality of Mapbox FeatureCollection, as it is a final class that cannot be
 * changed. Provides access to various properties of Features inside a FeatureCollection. In this case,
 * Two fields are of particular interest: 'id' and 'poiCount'
 */
public interface IFeatureCollectionAccessor {

    /**
     * Reads the poiCount property from a Feature in a FeatureCollection
     * @param collection The FeatureCollection to be searched
     * @param id Id of the desired Feature
     * @return Density value (poiCount)
     */
    double getDensity(FeatureCollection collection, int id);

    /**
     * Reads the poiCount property from a Feature
     * @param feature The Feature containing a density property
     * @return Density value (poiCount)
     */
    double getDensity(Feature feature);

    /**
     * Sets the poiCount attribute of a feature with the specified id property value
     * @param collection Feature collection to be altered
     * @param id Identifier of the Feature (properties)
     * @param density PoiCount to be stored
     */
    void setDensity(FeatureCollection collection, int id, double density);

    /**
     * Returns the id property of a Feature
     * @param feature The Feature in which id one is interested
     * @return The value of the id property
     */
    int getId(Feature feature);

    /**
     * Finds a Feature with the specified id property from a FeatureCollection
     * @param collection The FeatureCollection to be searched
     * @param id Id of the desired Feature
     * @return Feature with the specified id
     */
    Feature getFeatureById(FeatureCollection collection, int id);

    /**
     * Copies the specified feature collection and resets the poiCount property
     * @param collection Original FeatureCollection
     * @return A copy of the original FeatureCollection with poiCount reset to 0
     */
    FeatureCollection copyFeatureIds(FeatureCollection collection);

    /**
     * Returns the maximum poiCount value of a FeatureCollection
     * @param collection The FeatureCollection to be investigated
     * @return Maximum poiCount value
     */
    double getMaxDensity(FeatureCollection collection);
}
