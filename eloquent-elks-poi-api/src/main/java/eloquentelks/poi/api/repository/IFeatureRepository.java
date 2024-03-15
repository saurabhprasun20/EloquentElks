package eloquentelks.poi.api.repository;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;

import java.util.List;

/**
 * Repository for GeoJson features
 */
public interface IFeatureRepository {

    /**
     * Loads GeoJson features from the database within the radius around the specified center.
     * @param center Center point of the circle
     * @param radius Radius of the circle
     * @return A list of features containing the POIs in the specified radius of the center point.
     */
    List<Feature> getFeatures(Point center, double radius);

    /**
     * Loads GeoJson features from the database with a specific attraction type.
     * @param attractionType Specifies the type of attraction to be filtered.
     * @return A list of features containing the POIs of the specified attractionType.
     */
    List<Feature> getFeatures(String attractionType);

    /**
     * Calculates the distance of the specified point to New York's most famous POIs
     * @param point Point of reference for measuring the distance to the POIs
     * @return  A list of features with the respective distances to the specified point
     */
    List<Feature> getDistanceOfFamousFeatures(Point point);
}
