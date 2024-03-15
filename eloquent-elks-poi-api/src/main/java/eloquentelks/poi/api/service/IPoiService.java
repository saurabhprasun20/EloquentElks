package eloquentelks.poi.api.service;

import eloquentelks.poi.api.model.PoiGetDto;

import java.util.List;

/**
 * Service to retrieve Points of Interest from the database
 */
public interface IPoiService {
    /**
     * Loads all Points of Interest from the database and returns them as DTOs
     * @param longitude Geographical longitude of center
     * @param latitude Geographical latitude of center
     * @return A list of {@link eloquentelks.poi.api.model.PoiGetDto}, which is the structure that is exposed to the clients of the API
     */
    List<PoiGetDto> getAllPois(double longitude, double latitude);

    /**
     * Loads the Points of Interest of a specific attraction type.
     * @param attractionType Type of attractions to be returned
     * @return A list of {@link eloquentelks.poi.api.model.PoiGetDto}
     */
    List<PoiGetDto> getPoisByAttractionType(String attractionType);

    /**
     * Loads the famous Points of Interest including the distance to the specified position
     * @param longitude Geographical longitude of center
     * @param latitude Geographical latitude of center
     * @return A list of {@link eloquentelks.poi.api.model.PoiGetDto}
     */
    List<PoiGetDto> getFamousPoisWithDistance(double longitude, double latitude);
}
