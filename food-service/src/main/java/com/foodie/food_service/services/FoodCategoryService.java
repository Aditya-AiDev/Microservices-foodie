package com.foodie.food_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foodie.food_service.dto.FoodCategoryDto;


public interface FoodCategoryService {

    FoodCategoryDto create(FoodCategoryDto dto);

    FoodCategoryDto getById(String id);
    
    List<FoodCategoryDto> getAll();

    FoodCategoryDto update(String id, FoodCategoryDto dto);
    
    void delete(String id);

}
