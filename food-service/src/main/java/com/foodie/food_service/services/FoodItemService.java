package com.foodie.food_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foodie.food_service.dto.FoodItemDto;
import com.foodie.food_service.entities.FoodType;


public interface FoodItemService {

    FoodItemDto create(FoodItemDto dto);
    //
    FoodItemDto getById(String id);
    //
    List<FoodItemDto> getAll();

    List<FoodItemDto> getByRestaurantId(String restaurantId);

    List<FoodItemDto> getByCategoryId(String categoryId);
    
    List<FoodItemDto> getByFoodType(FoodType foodType);
    
    FoodItemDto update(String id, FoodItemDto dto);
    
    FoodItemDto updateStock(String id, boolean outOfStock);
    
    void delete(String id);

}
