package eloquentelks.recommender.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a user's request for an area recommendation
 */
@Getter
@Setter
public class AreaPostRequestDto {

    /**
     * Defines the types of attractions to be considered for the recommendation of an area.
     * Example: ["museum", "statue", "famous"]
     */
    @JsonProperty
    private List<String> attractionTypes;
}
