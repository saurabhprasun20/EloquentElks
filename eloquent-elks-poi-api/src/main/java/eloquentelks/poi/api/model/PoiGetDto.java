package eloquentelks.poi.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a Point of Interest
 */
@Getter
@Setter
public class PoiGetDto {
    /**
     * Name of the Point of Interest (e.g., 'Empire State Building')
     */
    @JsonProperty
    private String name;

    /**
     * Category of the Point of Interest (e.g., 'restaurant')
     */
    @JsonProperty
    private String type;

    /**
     * Geographical longitude (e.g., -73.97242113140676)
     */
    @JsonProperty
    private double longitude;

    /**
     * Geographical latitude (e.g., 40.77374471268949)
     */
    @JsonProperty
    private double latitude;

    /**
     * Distance to this famous Point of Interest (in kilometers) (e.g., 0.57 = 570 meters)
     */
    @JsonProperty
    private double distance;
}
