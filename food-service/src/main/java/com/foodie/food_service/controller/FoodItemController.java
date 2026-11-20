package com.foodie.food_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.food_service.dto.FoodItemDto;
import com.foodie.food_service.entities.FoodType;
import com.foodie.food_service.services.FoodItemService;

@RestController 
@RequestMapping ("/api/v1/food-items")
public class FoodItemController {

    private final FoodItemService foodItemsService;

    @Autowired 
    public FoodItemController(FoodItemService foodItemsService) {
        this.foodItemsService = foodItemsService;
    }

    @PostMapping 
    public ResponseEntity<FoodItemDto> create(@RequestBody FoodItemDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(foodItemsService.create(dto));
    }

    @GetMapping 
    public ResponseEntity<List<FoodItemDto>> getAll() {
        return ResponseEntity.ok(foodItemsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItemDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(foodItemsService.getById(id));
    }

    @GetMapping("/restaurant/{restaurantId}")
    // get food-Item By restaurant Id
    public ResponseEntity<List<FoodItemDto>> getByRestaurant(@PathVariable String restaurantId) {
        return ResponseEntity.ok(foodItemsService.getByRestaurantId(restaurantId));
    }

    @GetMapping("/category/{categoryId}")
    // get food-Item By Category Id
    public ResponseEntity<List<FoodItemDto>> getByCategory(@PathVariable String categoryId) {
        return ResponseEntity.ok(foodItemsService.getByCategoryId(categoryId));
    }

    @GetMapping("/type/{foodType}")
    // get food-Item By food Type --VEG,NONVEG
    public ResponseEntity<List<FoodItemDto>> getByType(@PathVariable FoodType foodType) {
        return ResponseEntity.ok(foodItemsService.getByFoodType(foodType));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<FoodItemDto> update(@PathVariable String id, @RequestBody FoodItemDto dto) {
        return ResponseEntity.ok(foodItemsService.update(id, dto));
    }

    @PatchMapping ("/{id}/stock")
    public ResponseEntity<FoodItemDto> updateStock(@PathVariable String id, @RequestParam boolean outOfStock) {
        return ResponseEntity.ok(foodItemsService.updateStock(id, outOfStock));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        foodItemsService.delete(id);
        return ResponseEntity.noContent().build();
    }





}



// POST	/api/food-items	Create food item
// GET	/api/food-items	Get all
// GET	/api/food-items/{id}	Get one
// GET	/api/food-items/restaurant/{restaurantId}	Items of a restaurant
// GET	/api/food-items/category/{categoryId}	Items of a category
// GET	/api/food-items/type/VEG	Filter by VEG / NONVEG
// PUT	/api/food-items/{id}	Update
// PATCH	/api/food-items/{id}/stock?outOfStock=false	Stock toggle
// DELETE	/api/food-items/{id}	Delete
