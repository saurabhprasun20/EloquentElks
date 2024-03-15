package com.airbnb.eloquentelksbackend.DTO;


import com.airbnb.eloquentelksbackend.entity.Property;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Maps the contract to DTO object {@link com.airbnb.eloquentelksbackend.DTO.PropertyFetchDTO}.
 */

@Mapper
public interface DTOMapper {

    /**
     * DTOMapper Instance for the mapper
     */
    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    /**
     * Maps Property to PropertyFetchDTO
     * @param property contract for airbnb {@link com.airbnb.eloquentelksbackend.entity.Property}
     * @return PropertyFetchDTO after mapping as per the source and target {@link com.airbnb.eloquentelksbackend.DTO.DTOMapper}
     */
    @Mapping(source = "name", target="name")
    @Mapping(source = "longitude", target="longitude")
    @Mapping(source = "latitude", target="latitude")
    @Mapping(source = "room_type", target="roomType")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "host_name", target = "hostName")
    PropertyFetchDTO convertPropertyToPropertyFetchDTO(Property property);

    /**
     * Maps list of Properties to List of PropertyFetchDTO
     * @param properties list of {@link com.airbnb.eloquentelksbackend.entity.Property} to convert to list of {@link com.airbnb.eloquentelksbackend.DTO.PropertyFetchDTO}
     * @return list of {@link com.airbnb.eloquentelksbackend.DTO.PropertyFetchDTO}
     */
    List<PropertyFetchDTO> map(List<Property> properties);
}
