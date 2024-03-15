package eloquentelks.recommender.api.controller;

import com.mapbox.geojson.FeatureCollection;
import eloquentelks.recommender.api.model.AreaPostRequestDto;
import eloquentelks.recommender.api.service.IDensityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Rest controller which serves area recommendations
 */
@RestController
@RequestMapping("/api/v1/recommendation")
public class AreaController {

    private IDensityService densityService;

    /**
     * Constructor
     * @param densityService Service which retrieves the POI density
     */
    @Autowired
    public AreaController(IDensityService densityService){
        this.densityService = densityService;
    }

    /**
     * Recommends an area for a specific type of tourist attraction
     * @param requestDto Specifies the type of tourist attractions the user is interested in
     * @return An GeoJSON FeatureCollection containing the density values as properties
     */
    @CrossOrigin(origins="http://localhost:3000")
    @PostMapping("area")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String postArea(@RequestBody AreaPostRequestDto requestDto){

        // step 1: load raw densities
        List<FeatureCollection> rawDensities = densityService.getDensities(requestDto.getAttractionTypes());

        // step 2: normalize the raw densities
        List<FeatureCollection> normalizedDensities = densityService.normalizeDensities(rawDensities);

        // step 3: combine normalized densities into a single FeatureCollection
        FeatureCollection aggregatedDensities = densityService.aggregateDensities(normalizedDensities);

        // step 4: normalize the aggregated FeatureCollection
        FeatureCollection finalDensities = densityService.normalizeDensity(aggregatedDensities);

        return finalDensities.toJson();
    }
}
