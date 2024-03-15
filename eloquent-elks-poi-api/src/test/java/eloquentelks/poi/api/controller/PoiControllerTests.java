package eloquentelks.poi.api.controller;

import eloquentelks.poi.api.model.PoiGetDto;
import eloquentelks.poi.api.service.IPoiService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link eloquentelks.poi.api.controller.PoiController}
 */
@ExtendWith(MockitoExtension.class)
class PoiControllerTests {

    /**
     * Stub of {@link eloquentelks.poi.api.service.IPoiService}
     */
    @Mock
    private IPoiService poiServiceMock;

    /**
     * List of {@link eloquentelks.poi.api.model.PoiGetDto} used by the {@link eloquentelks.poi.api.service.IPoiService} stub
     */
    private static List<PoiGetDto> poiList;

    /**
     * Initialization of the {@link eloquentelks.poi.api.service.IPoiService} stub
     */
    @BeforeAll
    static void setUp(){
        PoiGetDto poi1 = new PoiGetDto();
        poi1.setLatitude(42.1d);
        poi1.setLongitude(1.323d);

        PoiGetDto poi2 = new PoiGetDto();
        poi2.setLatitude(-25.49d);
        poi2.setLongitude(173.82d);

        poiList = new ArrayList<>();
        poiList.add(new PoiGetDto());
        poiList.add(new PoiGetDto());
    }

    /**
     * Tests the retrieval of Point of Interests via the HTTP GET endpoint
     */
    @Test
    void testGetAllPoi(){
        // arrange
        when(poiServiceMock.getAllPois(1.323d, 42.1d)).thenReturn(poiList);
        PoiController controller = new PoiController(poiServiceMock);

        // act
        List<PoiGetDto> result = controller.getAllPoi(1.323d, 42.1d);

        // assert
        assertEquals(2, result.size());
    }

    /**
     * Tests the retrieval of Point of Interests by attraction type via the HTTP GET endpoint
     */
    @Test
    void testGetPoiByAttractionType(){
        // arrange
        when(poiServiceMock.getPoisByAttractionType(any(String.class))).thenReturn(poiList);
        PoiController controller = new PoiController(poiServiceMock);

        // act
        List<PoiGetDto> result = controller.getPoiByAttractionType("restaurant");

        // assert
        assertEquals(2, result.size());
    }

    /**
     * Tests the retrieval of famous Point of Interests including their distances to a reference point
     * via the HTTP GET endpoint
     */
    @Test
    void testGetFamousPoiWithDistance(){
        // arrange
        when(poiServiceMock.getFamousPoisWithDistance(anyDouble(), anyDouble())).thenReturn(poiList);
        PoiController controller = new PoiController(poiServiceMock);

        // act
        List<PoiGetDto> result = controller.getFamousPoiWithDistance(-74.01, 40.72);

        // assert
        assertEquals(2, result.size());
    }
}
