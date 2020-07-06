package co.simplon.bakerdelivery.service;

import co.simplon.bakerdelivery.controller.RestaurantController;
import co.simplon.bakerdelivery.dto.RestaurantDto;
import co.simplon.bakerdelivery.exception.EntityNotFoundException;
import co.simplon.bakerdelivery.exception.RestaurantNotFoundException;
import co.simplon.bakerdelivery.mappers.RestaurantMapper;
import co.simplon.bakerdelivery.model.Restaurant;
import co.simplon.bakerdelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
     RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantMapper restaurantMapper;

    //Tu peux utiliser l'annotation de Spring @Autowired quand tu veux qu'une variable de classe (ici ton service) soit instanciée dans le constructeur de ta classe. C'est utile quand tu n'as pas besoin d'autres variables et ça évite d'alourdir le code avec des constructeurs.
    // tu n'as pas besoin de le rendre visible depuis l'exterieur de ta classe, pense à utiliser 'private'.
    /*private RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }*/

    @Override //on surcharge la methode qui est dans le service
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public RestaurantDto getRestaurantById(Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
       if (restaurant.isPresent()){ /*si le restau est present, on lui envoi un get ci dessous*/
           Restaurant restaurantFound=restaurant.get();
        return restaurantMapper.toDto(restaurantFound);}
       else throw new EntityNotFoundException("The restaurant with ID: " + restaurantId + " cannot be found in DB", "restaurant");

    }


    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) { //à quoi sert le ResponseEntity ici ?
        Restaurant restaurant = restaurantMapper.toEntity(restaurantDto);
        restaurant = restaurantRepository.save(restaurant);//car le save du repo ne s'applique qu'à des Restaurant
        //ou Restaurant restaurant = restaurantRepository.save(mapper.toEntity(dto))
        return restaurantMapper.toDto(restaurant);
    }


    @Override
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto, Long restaurantId) throws RestaurantNotFoundException {
        if (restaurantRepository.existsById(restaurantId)) {

            Restaurant restaurant=restaurantMapper.toEntity(restaurantDto);
            restaurant.setId(restaurantId);
            restaurant = restaurantRepository.save(restaurant);
            return restaurantMapper.toDto(restaurant);
        } else
            throw new RestaurantNotFoundException();

    }


    @Override
    public Boolean deleteRestaurant(Long restaurantId) {
        if (restaurantRepository.existsById(restaurantId)) {
            restaurantRepository.deleteById(restaurantId);
            return true;
        } else {
            return false;
        }

    }


}