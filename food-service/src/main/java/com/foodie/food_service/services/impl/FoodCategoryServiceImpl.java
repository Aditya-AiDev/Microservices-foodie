package com.foodie.food_service.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.Locale.Category;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.foodie.food_service.dto.FoodCategoryDto;
import com.foodie.food_service.entities.FoodCategory;
import com.foodie.food_service.repository.FoodCategoryRepo;
import com.foodie.food_service.services.FoodCategoryService;

@Service 
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    public FoodCategoryServiceImpl(FoodCategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public FoodCategoryDto create(FoodCategoryDto dto) {
        FoodCategory category = modelMapper.map(dto, FoodCategory.class);
        category.setId(UUID.randomUUID().toString());
        // return modelMapper.map(categoryRepo.save(category), FoodCategoryDto.class);
        return toDto(categoryRepo.save(category)); 
    }

    @Override
    public FoodCategoryDto getById(String id) {
        // return modelMapper.map(findOrThrow(id), FoodCategoryDto.class);
        return toDto(findOrThrow(id)); 
    }

    @Override
    public List<FoodCategoryDto> getAll() {
        return categoryRepo.findAll().stream()
                // .map(c -> modelMapper.map(c, FoodCategoryDto.class))
                .map(this::toDto)  
                .toList();
    }

    @Override
    public FoodCategoryDto update(String id, FoodCategoryDto dto) {
        FoodCategory existing = findOrThrow(id);
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        // return modelMapper.map(categoryRepo.save(existing), FoodCategoryDto.class);
        return toDto(categoryRepo.save(existing));
    }

    @Override
    public void delete(String id) {
        categoryRepo.delete(findOrThrow(id));
    }


    // helper//
    private FoodCategory findOrThrow(String id) {
        return categoryRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + id));


    }

    // helper// convert entity(FoodCategory)-- >> to DTO(FoodCategoryDto)
    private FoodCategoryDto toDto(FoodCategory category) {
        FoodCategoryDto dto = modelMapper.map(category, FoodCategoryDto.class);

        dto.getFoodItems().forEach(i -> i.setFoodCategoryId(category.getId()));
        
        return dto;
    }

}
