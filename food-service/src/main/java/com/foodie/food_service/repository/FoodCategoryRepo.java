package com.foodie.food_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodie.food_service.entities.FoodCategory;

@Repository 
public interface FoodCategoryRepo extends JpaRepository<FoodCategory,String> {

}
