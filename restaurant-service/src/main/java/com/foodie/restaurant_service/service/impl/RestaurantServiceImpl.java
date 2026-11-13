package com.foodie.restaurant_service.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.foodie.restaurant_service.dto.RestaurantDto;
import com.foodie.restaurant_service.entities.Restaurant;
import com.foodie.restaurant_service.repository.RestaurantRepo;
import com.foodie.restaurant_service.service.RestaurantService;

@Service 
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepo restaurantRepo;
    private final ModelMapper modelMapper;

    @Autowired 
    public RestaurantServiceImpl(RestaurantRepo restaurantRepo, ModelMapper modelMapper) {
        this.restaurantRepo = restaurantRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public RestaurantDto create(RestaurantDto dto) {

        Restaurant restaurant = modelMapper.map(dto, Restaurant.class);

        restaurant.setId(UUID.randomUUID().toString());

        //save
        restaurant = restaurantRepo.save(restaurant);

        return modelMapper.map(restaurant, RestaurantDto.class);
    }

    @Override
    public RestaurantDto getById(String id) {

        return modelMapper.map(findOrThrow(id), RestaurantDto.class);
    }

    @Override
    public RestaurantDto getByName(String name) {
        Restaurant optionalRestaurant = restaurantRepo.findByName(name).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found with id: " + name));

        return  modelMapper.map(optionalRestaurant, RestaurantDto.class);
    }

    @Override
    public List<RestaurantDto> getAll() {

        return restaurantRepo.findAll().stream()
                .map(r -> modelMapper.map(r, RestaurantDto.class))
                .toList();
    }

    @Override
    public RestaurantDto update(String id, RestaurantDto dto) {

        Restaurant existing = findOrThrow(id);

        modelMapper.map(dto, existing); // copies dto values onto the existing entity

        existing.setId(id);        // make sure id never changes

        return modelMapper.map(restaurantRepo.save(existing), RestaurantDto.class);
    }

    @Override
    public RestaurantDto updateOpenStatus(String id, boolean open) {

        Restaurant existing = findOrThrow(id);

        existing.setOpen(open);

        return modelMapper.map(restaurantRepo.save(existing), RestaurantDto.class);
    }

    @Override
    public void delete(String id) {

        restaurantRepo.delete(findOrThrow(id));
    }

    private Restaurant findOrThrow(String id) {

        return restaurantRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found with id: " + id));
    }

    

}
