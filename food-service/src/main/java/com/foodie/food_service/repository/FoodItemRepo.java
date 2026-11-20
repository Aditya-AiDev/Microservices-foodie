package com.foodie.food_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodie.food_service.entities.FoodItem;
import com.foodie.food_service.entities.FoodType;

@Repository 
public interface FoodItemRepo extends JpaRepository<FoodItem,String> {

    List<FoodItem> findByRestaurantId(String restaurantId);
    List<FoodItem> findByFoodCategoryId(String categoryId);
    List<FoodItem> findByFoodType(FoodType foodType);

}
