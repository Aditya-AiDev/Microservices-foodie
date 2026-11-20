package com.foodie.food_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foodie.food_service.entities.FoodType;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
@Getter 
@Setter 
public class FoodItemDto {

    private String id;
    private String title;
    private String description;
    private int quantity;
    private boolean outOfStock;

    private FoodType foodType;

    //
    private String foodCategoryId;

    private FoodCategoryDto foodCategoryDto;
    //
    
    //
    private String restaurantId;

    private RestaurantDto restaurantDto;
    //
}
