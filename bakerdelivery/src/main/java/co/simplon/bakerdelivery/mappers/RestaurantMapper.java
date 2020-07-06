package co.simplon.bakerdelivery.mappers;

import co.simplon.bakerdelivery.dto.RestaurantDto;
import co.simplon.bakerdelivery.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;


@Mapper(componentModel = "spring")
public interface RestaurantMapper {


    @Mapping(source = "id", target = "id")
    RestaurantDto toDto(Restaurant restaurant);


    List<RestaurantDto> toDto(List<Restaurant> restaurants);

    // transf DTO ---> EntityManager
    Restaurant toEntity(RestaurantDto restaurantDto);
}
