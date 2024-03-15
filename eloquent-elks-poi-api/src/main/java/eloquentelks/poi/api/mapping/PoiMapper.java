package eloquentelks.poi.api.mapping;

import com.google.gson.JsonElement;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import eloquentelks.poi.api.model.PoiGetDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps Points of Interest from Features (GeoJson) to {@link eloquentelks.poi.api.model.PoiGetDto}
 */
public class PoiMapper {

    /**
     * Private constructor to hide the implicit constructor
     */
    private PoiMapper(){}

    /**
     * Property name of the distance field
     */
    private static final String PROPERTY_DISTANCE = "distance";

    /**
     * Property name of the tourism field
     */
    private static final String PROPERTY_TOURISM = "tourism";

    /**
     * Property name of the name field
     */
    private static final String PROPERTY_NAME = "name";

    /**
     * Placeholder value for JSON Null representation in DTO
     */
    private static final String NOT_AVAILABLE = "N/A";

    /**
     * Maps GeoJson features to {@link eloquentelks.poi.api.model.PoiGetDto}
     *
     * @param features A list of GeoJson features
     * @return A list of {@link eloquentelks.poi.api.model.PoiGetDto}
     */
    public static List<PoiGetDto> mapToDto(List<Feature> features){
        List<PoiGetDto> dtos = new ArrayList<>();

        for (Feature feature: features) {
            PoiGetDto dto = fromFeature(feature);

            dtos.add(dto);
        }

        return dtos;
    }

    /**
     * Takes a GeoJson feature and converts it to a {@link eloquentelks.poi.api.model.PoiGetDto}
     *
     * @param feature GeoJson feature
     * @return
     */
    private static PoiGetDto fromFeature(Feature feature){
        PoiGetDto dto = new PoiGetDto();
        Point p = (Point)feature.geometry();
        dto.setLatitude(p.latitude());
        dto.setLongitude(p.longitude());

        if(feature.hasProperty(PROPERTY_DISTANCE)){
            dto.setDistance(feature.getProperty(PROPERTY_DISTANCE).getAsDouble());
        }

        dto.setName(getProperty(feature, PROPERTY_NAME));
        dto.setType(getProperty(feature, PROPERTY_TOURISM));

        return dto;
    }

    /**
     * Retrieves the GeoJson feature property with the specified name
     *
     * @param feature A GeoJson feature
     * @param name The name of the desired feature property
     * @return String containing the value of the accessed property
     */
    private static String getProperty(Feature feature, String name){
        JsonElement element = feature.properties().get(name);

        if(element == null){
            return NOT_AVAILABLE;
        }

        if(element.isJsonNull()) {
            return NOT_AVAILABLE;
        }

        return element.getAsString();
    }
}
