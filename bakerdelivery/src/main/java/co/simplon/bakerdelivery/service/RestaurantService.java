package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.dto.RestaurantDto;
import co.simplon.bakerdelivery.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RestaurantService {

    List<Restaurant> getRestaurants();

    RestaurantDto getRestaurantById(Long restaurantId);

    RestaurantDto createRestaurant(RestaurantDto restaurant);

    RestaurantDto updateRestaurant(RestaurantDto restaurant, Long restaurantId);

    Boolean deleteRestaurant(Long restaurantId);

}
