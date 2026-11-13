package com.foodie.restaurant_service.entities;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity 
@Table(name = "restaurant") 
public class Restaurant {

    @Id 
    private String id;

    private String name;

    private String address;

    private String phone;

    @ElementCollection 
    private List<String> pictures = new ArrayList<>();

    private boolean open = false;

    private LocalTime openTime;
    
    private LocalTime closeTime;

    private String aboutRestaurant;





}
