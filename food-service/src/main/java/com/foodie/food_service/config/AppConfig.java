package com.foodie.food_service.config;

import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.foodie.food_service.dto.FoodCategoryDto;
import com.foodie.food_service.dto.FoodItemDto;
import com.foodie.food_service.entities.FoodCategory;
import com.foodie.food_service.entities.FoodItem;

@Configuration 
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(FoodCategoryDto.class, FoodCategory.class).addMappings(m -> m.skip(FoodCategory::setFoodItems));

        mapper.typeMap(FoodItem.class, FoodItemDto.class)
                .addMappings(m -> {
                                m.skip(FoodItemDto::setFoodCategoryId);
                                m.skip(FoodItemDto::setFoodCategoryDto);
        });

        // mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return mapper;
    
    }

    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();

    }

    @Bean 
    public RestClient restClient(){
        return RestClient.builder()
                        .baseUrl("http://localhost:9091")
                        .build();
    }

    @Bean 
    public WebClient webClient(){
        return WebClient.builder()
                        .baseUrl("http://localhost:9091")
                        .build();
    }



}
