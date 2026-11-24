package com.foodie.food_service.services.externalFeignCleint;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.foodie.food_service.config.AppConstants;
import com.foodie.food_service.dto.RestaurantDto;

// @FeignClient (name = "restaurant-service", url = "http://localhost:9091")
@FeignClient (name = AppConstants.RESTAURANT_SERVICE_NAME , url = AppConstants.RESTAURANT_SERVICE_URL)
public interface RestaurantService {

    ////Restaurant-Service ---for getting single element
    @GetMapping ("/api/v1/restaurants/{id}")
    RestaurantDto getById(@PathVariable("id") String restaurantId );
    //

    //get All
    @GetMapping ("/api/v1/restaurants")
    List<RestaurantDto> getAll();

    //create
    @PostMapping ("/api/v1/restaurants")
    RestaurantDto create(@RequestBody RestaurantDto restaurantDto);

    //delete
    @DeleteMapping ("/api/v1/restaurants/{id}")
    void delete(@PathVariable ("id") String restaurantId);





}
