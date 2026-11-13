package com.foodie.restaurant_service.controller;

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

import com.foodie.restaurant_service.dto.RestaurantDto;
import com.foodie.restaurant_service.service.RestaurantService;


@RestController 
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired 
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> create(@RequestBody RestaurantDto dto) {

        // RestaurantDto createRestaurant = restaurantService.create(dto);
        // return new ResponseEntity<RestaurantDto>(createRestaurant,HttpStatus.CREATED);

        return ResponseEntity.status(HttpStatus.CREATED)
                            .body(restaurantService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAll() {

        return ResponseEntity.ok(restaurantService.getAll());
    }

    //Get restaurant by id
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getById(@PathVariable String id) {

        return ResponseEntity.ok(restaurantService.getById(id));
    }

    //Get restaurant by name
    @GetMapping("/name/{name}")
    public ResponseEntity<RestaurantDto> getByName(@PathVariable String name) {

        // RestaurantDto dto = restaurantService.getByName(name);
        // return new ResponseEntity<RestaurantDto>(dto, HttpStatus.OK);

        return ResponseEntity.ok(restaurantService.getByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> update(@PathVariable String id, @RequestBody RestaurantDto dto) {

        return ResponseEntity.ok(restaurantService.update(id, dto));
    }

    @PatchMapping("/{id}/open")
    public ResponseEntity<RestaurantDto> updateOpenStatus(@PathVariable String id, @RequestParam boolean open) {

        return ResponseEntity.ok(restaurantService.updateOpenStatus(id, open));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {

        restaurantService.delete(id);
        
        return ResponseEntity.noContent().build();
    }

}
