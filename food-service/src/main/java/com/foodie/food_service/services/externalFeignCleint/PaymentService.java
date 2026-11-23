package com.foodie.food_service.services.externalFeignCleint;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient (value = "payment-service" , url = "")
public interface PaymentService {

    //rest api
    
}
