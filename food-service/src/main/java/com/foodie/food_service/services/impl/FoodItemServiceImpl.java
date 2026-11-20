package com.foodie.food_service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.foodie.food_service.dto.FoodCategoryDto;
import com.foodie.food_service.dto.FoodItemDto;
import com.foodie.food_service.dto.RestaurantDto;
import com.foodie.food_service.entities.FoodCategory;
import com.foodie.food_service.entities.FoodItem;
import com.foodie.food_service.entities.FoodType;
import com.foodie.food_service.repository.FoodCategoryRepo;
import com.foodie.food_service.repository.FoodItemRepo;
import com.foodie.food_service.services.FoodItemService;

@Service 
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodCategoryRepo categoryRepo;
    private final ModelMapper modelMapper;
    private final FoodItemRepo itemRepo;

    private final RestTemplate restTemplate;
    

    

    public FoodItemServiceImpl(FoodCategoryRepo categoryRepo, ModelMapper modelMapper, FoodItemRepo itemRepo,
        RestTemplate restTemplate) {
                this.categoryRepo = categoryRepo;
                this.modelMapper = modelMapper;
                this.itemRepo = itemRepo;
                this.restTemplate = restTemplate;
    }

    @Override
    public FoodItemDto create(FoodItemDto dto) {
        FoodItem item = modelMapper.map(dto, FoodItem.class);
        item.setId(UUID.randomUUID().toString());
        //
        item.setFoodCategory(findCategory(dto.getFoodCategoryId()));
        //
        return toDto(itemRepo.save(item));
    }

    @Override
    public FoodItemDto getById(String id) {
        FoodItem foodItem=findOrThrow(id);
        //call Restaurant-Service by it's URL ---for get single element
        String restaurantServiceUrl = "http://localhost:9091/api/v1/restaurants/" + foodItem.getRestaurantId();

        /////////////////////////////////////////////////////////////////
        // /////////////////////////call by restTemplate/////////////////
        //get// ---getForObject
        RestaurantDto restaurantDto=restTemplate.getForObject(restaurantServiceUrl, RestaurantDto.class);
        //getAll//---getForEntity
        //post//
        //put//
        /////////
        FoodItemDto foodItemDto= toDto(foodItem);
        foodItemDto.setRestaurantDto(restaurantDto);

        return foodItemDto;

    }

    @Override
    public List<FoodItemDto> getAll() {
        
        List<FoodItem> allFoodItem= itemRepo.findAll();

        //call Restaurant-Service by it's URL ---for getAll
        String restaurantServiceUrl = "http://localhost:9091/api/v1/restaurants" ;
        /////////////////////////////////////////////////////////////////
        // /////////////////////////call by restTemplate/////////////////
        //getAll// ---getForObject
        RestaurantDto[] restaurantDtoArray=restTemplate.getForObject(restaurantServiceUrl, RestaurantDto[].class);
        List<RestaurantDto> restaurantDtoList=Arrays.asList(restaurantDtoArray);
        /////
        /// 
        
        // Map restaurants by ID for quick O(1) matching
        Map<String, RestaurantDto> restaurantMap = restaurantDtoList.stream()
                                                                    .collect(Collectors.toMap(RestaurantDto :: getId, r -> r));
        
        // Convert food items to DTOs and attach their respective restaurants
        List<FoodItemDto> allFoodItemDtos = allFoodItem.stream().map(foodItem -> {
                                                                            FoodItemDto foodItemDto = toDto(foodItem);
                                                                            foodItemDto.setRestaurantDto(restaurantMap.get(foodItem.getRestaurantId()));
                                                                            return foodItemDto;
                                                                        }
        ).toList();

        return allFoodItemDtos;
    }

    @Override
    public List<FoodItemDto> getByRestaurantId(String restaurantId) {
        return itemRepo.findByRestaurantId(restaurantId).stream().map(this::toDto).toList();
    }

    @Override
    public List<FoodItemDto> getByCategoryId(String categoryId) {
        return itemRepo.findByFoodCategoryId(categoryId).stream().map(this::toDto).toList();
    }

    @Override
    public List<FoodItemDto> getByFoodType(FoodType foodType) {
        return itemRepo.findByFoodType(foodType).stream().map(this::toDto).toList();
    }

    @Override
    public FoodItemDto update(String id, FoodItemDto dto) {
        FoodItem existing = findOrThrow(id);
        // modelMapper.map(dto, existing);   // category is skipped by the modelMapper config
        // existing.setId(id);          // id never changes
        // existing.setFoodCategory(findCategory(dto.getFoodCategoryId()));
        // return toDto(itemRepo.save(existing));
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setQuantity(dto.getQuantity());
        existing.setOutOfStock(dto.isOutOfStock());
        existing.setFoodType(dto.getFoodType());
        existing.setRestaurantId(dto.getRestaurantId());
        existing.setFoodCategory(findCategory(dto.getFoodCategoryId()));

        return toDto(itemRepo.save(existing));
    }

    @Override
    public FoodItemDto updateStock(String id, boolean outOfStock) {
        FoodItem existing = findOrThrow(id);
        existing.setOutOfStock(outOfStock);
        return toDto(itemRepo.save(existing));
    }

    @Override
    public void delete(String id) {
        itemRepo.delete(findOrThrow(id));
    }

    // ---------- helpers ----------

    // private FoodItemDto toDto(FoodItem item) {
    //     FoodItemDto dto = modelMapper.map(item, FoodItemDto.class);
    //     if (item.getFoodCategory() != null) {
    //         dto.setFoodCategoryId(item.getFoodCategory().getId());
    //     }
    //     return dto;
    // }

    private FoodItemDto toDto(FoodItem item) {
        
        FoodItemDto dto = modelMapper.map(item, FoodItemDto.class);
        if (item.getFoodCategory() != null) {
            FoodCategory c = item.getFoodCategory();
            dto.setFoodCategoryId(c.getId());

            FoodCategoryDto categoryDto = new FoodCategoryDto();
            categoryDto.setId(c.getId());
            categoryDto.setName(c.getName());
            categoryDto.setDescription(c.getDescription());
            dto.setFoodCategoryDto(categoryDto);   // foodItems stays null
        }
        return dto;
    }

    private FoodCategory findCategory(String categoryId) {
        if (categoryId == null) return null;
        return categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + categoryId));
    }

    private FoodItem findOrThrow(String id) {
        return itemRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Food item not found with id: " + id));
    }

}
