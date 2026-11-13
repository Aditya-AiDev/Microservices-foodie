package com.foodie.restaurant_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodie.restaurant_service.entities.Restaurant;

@Repository 
public interface RestaurantRepo  extends JpaRepository<Restaurant,String>{

    Optional<Restaurant> findByName(String name);


}
