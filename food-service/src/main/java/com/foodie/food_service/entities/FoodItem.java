package com.foodie.food_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity 
@Table (name = "food_item")
public class FoodItem {

    @Id 
    private String id;

    private String title;

    private String description;

    private int quantity;

    private boolean outOfStock =true;
    
    @Enumerated (EnumType.STRING)
    private FoodType foodType= FoodType.VEG;
    
    @ManyToOne 
    private FoodCategory foodCategory;


    // store the restaurant information inside food item
    @Column (nullable = false)
    private String restaurantId;




}
