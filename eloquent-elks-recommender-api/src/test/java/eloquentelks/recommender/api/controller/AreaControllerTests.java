package eloquentelks.recommender.api.controller;

import com.mapbox.geojson.FeatureCollection;
import eloquentelks.recommender.api.helper.FeatureCollectionAccessor;
import eloquentelks.recommender.api.helper.FeatureCollectionFactory;
import eloquentelks.recommender.api.helper.IFeatureCollectionAccessor;
import eloquentelks.recommender.api.model.AreaPostRequestDto;
import eloquentelks.recommender.api.service.DensityService;
import eloquentelks.recommender.api.service.IDensityRestService;
import eloquentelks.recommender.api.service.IDensityService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Unit tests for @see{@link eloquentelks.recommender.api.controller.AreaController}
 */
class AreaControllerTests {

    /**
     * Tests if the @see{@link eloquentelks.recommender.api.controller.AreaController} returns an area recommendation
     */
    @Test
    void testPostArea(){
        // arrange
        IDensityRestService densityRestService = mock(IDensityRestService.class);
        Map<Integer, Integer> densities1 = Map.of(1, 42, 2, 98, 3, 182);
        Map<Integer, Integer> densities2 = Map.of(1, 7, 2, 1, 3, 12);

        when(densityRestService.getDensities(any(List.class))).thenReturn( List.of(
                FeatureCollectionFactory.create(densities1),
                FeatureCollectionFactory.create(densities2)
        ));

        IFeatureCollectionAccessor accessor = new FeatureCollectionAccessor();
        IDensityService densityService = new DensityService(new FeatureCollectionAccessor(), densityRestService);
        AreaController controller = new AreaController(densityService);
        AreaPostRequestDto requestDto = new AreaPostRequestDto();
        requestDto.setAttractionTypes(List.of("restaurant"));

        // act
        FeatureCollection featureCollection = FeatureCollection.fromJson(controller.postArea(requestDto));

        // assert
        assertEquals(0.40705128205128205, accessor.getDensity(featureCollection, 1));
        assertEquals(0.3108974358974359, accessor.getDensity(featureCollection, 2));
        assertEquals(1, accessor.getDensity(featureCollection, 3));
    }
}
