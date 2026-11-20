package com.foodie.food_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.food_service.dto.FoodCategoryDto;
import com.foodie.food_service.services.FoodCategoryService;

@RestController 
@RequestMapping ("/api/v1/categories")
public class FoodCategoryController {

    private final FoodCategoryService foodCategoryService;

    public FoodCategoryController(FoodCategoryService foodCategoryService) {
        this.foodCategoryService = foodCategoryService;
    }

    @PostMapping 
    public ResponseEntity<FoodCategoryDto> create(@RequestBody FoodCategoryDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(foodCategoryService.create(dto));
    }

    @GetMapping 
    public ResponseEntity<List<FoodCategoryDto>> getAll() {
        return ResponseEntity.ok(foodCategoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodCategoryDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(foodCategoryService.getById(id));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<FoodCategoryDto> update(@PathVariable String id, @RequestBody FoodCategoryDto dto) {
        return ResponseEntity.ok(foodCategoryService.update(id, dto));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        foodCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



// POST	/api/categories	Create category
// GET	/api/categories	Get all categories
// GET	/api/categories/{id}	Get one category
// PUT	/api/categories/{id}	Update category
// DELETE	/api/categories/{id}	Delete category (also deletes its items, because of CascadeType.ALL)
