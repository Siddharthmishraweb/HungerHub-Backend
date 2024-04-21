package com.hungerhub.service;

import com.hungerhub.dto.RestaurantDto;
import com.hungerhub.model.Address;
import com.hungerhub.model.Restaurant;
import com.hungerhub.model.User;
import com.hungerhub.repository.RestaurantRepository;
import com.hungerhub.repository.UserRepository;
import com.hungerhub.request.CreateRestaurantRequest;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        System.out.println("*********************************88"+ updatedRestaurant.getAddress());

        if (updatedRestaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if (updatedRestaurant.getDescription() != null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if (updatedRestaurant.getName() != null) {
            restaurant.setName(updatedRestaurant.getName());
        }
        // if (updatedRestaurant.getAddress() != null) {
        //     Address updatedAddress = updatedRestaurant.getAddress();
        //     Address persistedAddress = addressRepository.findByStreetAddressAndCityAndStateAndPincodeAndCountry(
        //             updatedAddress.getStreetAddress(),
        //             updatedAddress.getCity(),
        //             updatedAddress.getState(),
        //             updatedAddress.getPincode(),
        //             updatedAddress.getCountry());
        //     if (persistedAddress == null) {
        //         // Address doesn't exist in the database, so save it
        //         persistedAddress = addressRepository.save(updatedAddress);
        //     }
        //     // Set the address for the restaurant
        //     restaurant.setAddress(persistedAddress);
        // }

        if (updatedRestaurant.getAddress() != null) {
            Address updatedAddress = updatedRestaurant.getAddress();
            Address persistedAddress = null;
            if (updatedAddress.getId() != null) {
                // If address id is provided, check if the address already exists in the database
                persistedAddress = addressRepository.findById(updatedAddress.getId()).orElse(null);
            }
            if (persistedAddress == null) {
                // If address doesn't exist in the database, save it
                persistedAddress = addressRepository.save(updatedAddress);
            }
            // Set the address for the restaurant
            restaurant.setAddress(persistedAddress);
        }
        if (updatedRestaurant.getContactInformation() != null) {
            restaurant.setContactInformation(updatedRestaurant.getContactInformation());
        }
        if (updatedRestaurant.getOpeningHours() != null) {
            restaurant.setOpeningHours(updatedRestaurant.getOpeningHours());
        }

        if (updatedRestaurant.getImages() != null) {
            restaurant.setImages(updatedRestaurant.getImages());
        }
        if (restaurant.getRegistrationDate() != null) {
            restaurant.setRegistrationDate(LocalDateTime.now());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("Restaurant not found with id "+id);
        }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if(restaurant == null){
            throw new Exception("Restaurant not found with owner id"+userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);
        boolean isFavorited = false;
        List<RestaurantDto> favorites = user.getFavorites();
        for(RestaurantDto favorite : favorites){
            if(favorite.getId().equals(restaurantId)){
                isFavorited = true;
                break;
            }
        }
        if(isFavorited){
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        }else{
            favorites.add(dto);
        }

//        if(user.getFavorites().contains(dto)){
//            user.getFavorites().remove(dto);
//        }else user.getFavorites().add(dto);
        userRepository.save(user);
        return dto;
    }

    @Transactional
    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        System.out.println("------------restaurant-----------"+ restaurant);
        System.out.println("------------  restaurant.isOpen() -----------"+ restaurant.isOpen());
        restaurant.setOpen(!restaurant.isOpen());
        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        System.out.println("+++++++++++++++++++++++++ updatedRestaurant ++++++++++++++++++++++++++++++"+ updatedRestaurant);
        
        return updatedRestaurant;
    }












}
