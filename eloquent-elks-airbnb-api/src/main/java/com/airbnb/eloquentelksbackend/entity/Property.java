package com.airbnb.eloquentelksbackend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Date;

/**
 * Contract for the Property
 */

@Getter
@Setter
@ToString
@Document(collection = "airbnbs")
public class Property{

    /**
     * Unique Id of the property
     */
    private String id;
    /**
     * Name of the property
     */
    private String name;
    /**
     * Unique id of the host
     */
    private int host_id;
    /**
     * Name of the host
     */
    private String host_name;
    /**
     * Neighbourhood group of the property
     */
    private String neighbourhood_group;
    /**
     * Neighbourhood of the property
     */
    private String neighbourhood;
    /**
     * Longitude of the property
     */
    private double longitude;
    /**
     * Latitude of the property
     */
    private double latitude;
    /**
     * Type of the room in the property
     */
    private String room_type;
    /**
     * Price of the property per day
     */
    private double price;
    /**
     * Minimum nights required to book the property
     */
    private int minimum_nights;
    /**
     * Total number of the reviews for this property
     */
    private int noOfReviews;
    /**
     * Last date when the property was reviewed
     */
    private Date lastReview;
    /**
     * No of reviews per month
     */
    private double number_of_reviews;
    /**
     * Calculated Host listing count for the property
     */
    private double calculated_host_listings_count;
    /**
     * Price of the property per day
     */
    @Field("availability_365")
    private int availability;

}
