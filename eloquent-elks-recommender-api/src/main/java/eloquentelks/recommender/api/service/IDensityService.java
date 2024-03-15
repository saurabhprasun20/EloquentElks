package eloquentelks.recommender.api.service;

import com.mapbox.geojson.FeatureCollection;

import java.util.List;

/**
 * Provides access to the density calculation of specific attraction types by accessing the density API.
 * The densities are calculated for grid cells (hexagons) laid over NYC. For each of these cells, the number
 * of POIs are counted that lay within the cell. This is what is returned by the POI Density API.
 * Classes implementing this interface are responsible for retrieving the densities, normalizing them, and finally
 * aggregating multiple cell layers into one aggregated density layer.
 */
public interface IDensityService {

    /**
     * Retrieves the result of the density calculation
     *
     * @param attractionTypes List of attraction types according to the GeoJSON feature properties.
     * @return A GeoJson FeatureCollection with density property
     */
    List<FeatureCollection> getDensities(List<String> attractionTypes);

    /**
     * Normalize all densities such that they can be aggregated afterwards. This enables the combination of
     * densities for POI categories with any amount of POIs.
     * @param featureCollections Contains a FeatureCollection per attraction type
     * @return A list of GeoJSON FeatureCollections with normalized densities
     */
    List<FeatureCollection> normalizeDensities(List<FeatureCollection> featureCollections);

    /**
     * Normalize all densities of a single FeatureCollection.
     * Density normalization formula from <a href="https://stats.stackexchange.com/questions/70801/how-to-normalize-data-to-0-1-range">https://stats.stackexchange.com/questions/70801/how-to-normalize-data-to-0-1-range</a>
     * @param featureCollection A single FeatureCollection with densities
     * @return A list of GeoJSON FeatureCollections with normalized densities
     */
    FeatureCollection normalizeDensity(FeatureCollection featureCollection);

    /**
     * Combines the poi counts of the specified FeatureCollections into a single aggregated FeatureCollection
     * @param featureCollections Contains a FeatureCollection per attraction type
     * @return A single aggregated GeoJSON FeatureCollection with combined densities
     */
    FeatureCollection aggregateDensities(List<FeatureCollection> featureCollections);
}
