package com.example.demo.service.impl;

import com.example.demo.dto.RestaurantDto;
import com.example.demo.models.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class RestaurantImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;

    public RestaurantImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public List<RestaurantDto> findAllResto(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map((restaurant)-> mapToRestaurantDto(restaurant)).collect(Collectors.toList());
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public RestaurantDto findRestaurantById(long restaurantId) {
        Restaurant restaurant =restaurantRepository.findById(restaurantId).get();
        return mapToRestaurantDto(restaurant);
    }

    @Override
    public void updateRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant=mapToRestaurant(restaurantDto);
        restaurantRepository.save(restaurant);
    }

    private Restaurant mapToRestaurant(RestaurantDto restaurant) {
        Restaurant restaurantDto=Restaurant.builder()
                .id(restaurant.getId())
                .title(restaurant.getTitle())
                .content(restaurant.getContent())
                .photoUrl(restaurant.getPhotoUrl())
        .build();
        return restaurantDto;
    }

    private RestaurantDto mapToRestaurantDto(Restaurant restaurant){
        RestaurantDto restaurantDto =RestaurantDto.builder()
                .id(restaurant.getId())
                .title(restaurant.getTitle())
                .content(restaurant.getContent())
                .photoUrl(restaurant.getPhotoUrl())
                .createdOn(restaurant.getCreatedOn())
                .updatedOn(restaurant.getUpdatedOn())
                .build();
        return restaurantDto;
    }

}
