package com.example.demo.service;

import com.example.demo.dto.RestaurantDto;
import com.example.demo.models.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDto>findAllResto();
  Restaurant saveRestaurant(Restaurant restaurant);

    RestaurantDto findRestaurantById( long restaurantId);

    void updateRestaurant(RestaurantDto restaurant);

    void delete(long restaurantId);
}
