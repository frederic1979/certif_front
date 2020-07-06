package co.simplon.bakerdelivery.controller;

import co.simplon.bakerdelivery.dto.RestaurantDto;
import co.simplon.bakerdelivery.exception.EntityNotFoundException;
import co.simplon.bakerdelivery.exception.RestaurantNotFoundException;

import co.simplon.bakerdelivery.mappers.RestaurantMapper;
import co.simplon.bakerdelivery.model.Restaurant;
import co.simplon.bakerdelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/bakerdelivery/restaurants")
@CrossOrigin("http://localhost:4200")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;
       /*spring do injection dependency
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }*/

    @Autowired
    RestaurantMapper restaurantMapper;




    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    public List<RestaurantDto> getRestaurants() {
        return restaurantMapper.toDto(restaurantService.getRestaurants());
    }


    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long restaurantId) {
        try {

            RestaurantDto restaurant = restaurantService.getRestaurantById(restaurantId);
            return ResponseEntity.ok(restaurant);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @PostMapping
    public ResponseEntity<?> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        try {
            return ResponseEntity.ok(restaurantService.createRestaurant(restaurantDto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@RequestBody RestaurantDto restaurantDto, @PathVariable Long restaurantId) {
        try {

            return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantDto, restaurantId));
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> deleteRestaurant(@PathVariable Long restaurantId) {

        if (restaurantService.deleteRestaurant(restaurantId)) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();

    }
}
