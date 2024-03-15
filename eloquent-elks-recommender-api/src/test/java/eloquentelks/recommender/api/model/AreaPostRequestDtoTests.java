package eloquentelks.recommender.api.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the model @see{@link eloquentelks.recommender.api.model.AreaPostRequestDto}
 */
class AreaPostRequestDtoTests {

    /**
     * Tests it attractionType is handled correctly
     */
    @Test
    void testAttractionType(){
        // arrange
        AreaPostRequestDto requestDto = new AreaPostRequestDto();

        // act
        requestDto.setAttractionTypes(List.of("museum"));

        // assert
        assertEquals("museum", requestDto.getAttractionTypes().get(0));
    }
}
