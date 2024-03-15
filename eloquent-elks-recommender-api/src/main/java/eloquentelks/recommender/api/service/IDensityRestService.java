package eloquentelks.recommender.api.service;

import com.mapbox.geojson.FeatureCollection;

import java.util.List;

/**
 * Service to access the POI density REST API. Classes implementing this interface are responsible for
 * retrieving POI Density data from the POI Density API, which is a REST API.
 */
public interface IDensityRestService {

    /**
     * Requests a density analysis from the POI density REST API and returns it as a FeatureCollection
     * @param attractionType attractionType to be considered for the analysis
     * @return A FeatureCollection containing the density values of the grid
     */
    FeatureCollection getDensity(String attractionType);

    /**
     * Requests a density analysis from the POI density REST API and returns it as a list of FeatureCollections
     * @param attractionTypes List of attractionTypes to be considered for the analysis
     * @return A List of FeatureCollections containing the density values of the grids
     */
    List<FeatureCollection> getDensities(List<String> attractionTypes);
}
