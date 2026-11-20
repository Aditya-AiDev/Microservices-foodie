package com.foodie.food_service.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
@Getter 
@Setter 
public class FoodCategoryDto {

    private String id;
    private String name;
    private String description;

    private List<FoodItemDto> foodItems;
}
