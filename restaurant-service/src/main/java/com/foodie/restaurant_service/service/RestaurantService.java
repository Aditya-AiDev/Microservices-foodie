package com.foodie.restaurant_service.service;

import java.util.List;



import com.foodie.restaurant_service.dto.RestaurantDto;


public interface RestaurantService {

    RestaurantDto create(RestaurantDto dto);

    RestaurantDto getById(String id);
    
    
    RestaurantDto getByName(String name);


    List<RestaurantDto> getAll();

    RestaurantDto update(String id, RestaurantDto dto);

    RestaurantDto updateOpenStatus(String id, boolean open);

    void delete(String id);

}
