package com.foodie.restaurant_service.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
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
