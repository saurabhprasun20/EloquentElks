package eloquentelks.poi.api.service;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import eloquentelks.poi.api.mapping.PoiMapper;
import eloquentelks.poi.api.model.PoiGetDto;
import eloquentelks.poi.api.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class PoiService implements IPoiService {

    /**
     * Search radius to look for features
     */
    private static final double SEARCH_RADIUS = 500.0d;

    /**
     * Database accessor
     */
    private final FeatureRepository poiRepository;


    /**
     * Constructor
     * @param poiRepository MongoDb repository to access the database
     */
    @Autowired
    public PoiService(FeatureRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PoiGetDto> getAllPois(double longitude, double latitude) {
        List<Feature> features = poiRepository.getFeatures(Point.fromLngLat(longitude, latitude), SEARCH_RADIUS);

        return PoiMapper.mapToDto(features);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PoiGetDto> getPoisByAttractionType(String attractionType){
        List<Feature> features = poiRepository.getFeatures(attractionType);

        return PoiMapper.mapToDto(features);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PoiGetDto> getFamousPoisWithDistance(double longitude, double latitude) {
        List<Feature> features = poiRepository.getDistanceOfFamousFeatures(Point.fromLngLat(longitude, latitude));

        return PoiMapper.mapToDto(features);
    }
}
