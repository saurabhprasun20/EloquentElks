package com.airbnb.eloquentelksbackend.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@ToString
public class PropertyFetchDTO {
    /**
     * Name of the property (e.g., property 1)
     */
    @JsonProperty
    private String name;
    /**
     * Longitude of the property (e.g., -73.97242113140676)
     */
    @JsonProperty
    private double longitude;
    /**
     * Latitude of the property (e.g., 40.77374471268949)
     */
    @JsonProperty
    private double latitude;
    /**
     * * Type of the room in the property (e.g., Private room)
     */
    @JsonProperty
    private String roomType;
    /**
     * Price of the property per day (e.g., 110 USD)
     */
    @JsonProperty
    private double price;
    /**
     * Name of the host of the property (e.g., Jack)
     */
    @JsonProperty
    private String hostName;
}
