package com.foodie.food_service.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter 
@Setter 
public class RestaurantDto {

    private String id;
    private String name;
    private String address;
    private String phone;
    private List<String> pictures = new ArrayList<>();
    private boolean open;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String aboutRestaurant;

}
