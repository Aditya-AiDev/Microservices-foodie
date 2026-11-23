package com.foodie.food_service.services.externalWebClient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.foodie.food_service.dto.RestaurantDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service 
public class RestaurantsServiceWebClient {

    private final WebClient webClient;

    @Autowired 
    public RestaurantsServiceWebClient(WebClient webClient) {
        this.webClient = webClient;
    }


    //get single
    public RestaurantDto getById(String id){

        RestaurantDto restaurantDto = webClient.get()
                                                .uri("/api/v1/restaurants/{id}", id)
                                                .retrieve()
                                                .bodyToMono(RestaurantDto.class)
                                                .block();

        return  restaurantDto;


    }

    //get all
    public List<RestaurantDto> getAll(){

        return webClient.get()
                        .uri("/api/v1/restaurants")
                        .retrieve()
                        .bodyToFlux(RestaurantDto.class)
                        .collectList()
                        .block();
    }

    //create
    public RestaurantDto create(RestaurantDto restaurantDto){

        // return webClient.post()
        //                 .uri("/api/v1/restaurants")
        //                 .bodyValue(restaurantDto)
        //                 .retrieve()
        //                 .bodyToMono(RestaurantDto.class)
        //                 .block();

        return webClient.post()
                        .uri("/api/v1/restaurants")
                        .bodyValue(restaurantDto)
                        .header("Authorization", "Bearer ctydfdfhfffdhgfggdddg")
                        .retrieve()
                        .bodyToMono(RestaurantDto.class)
                        .block();
    }

    //////////////////////////////////////////////////////////////////////////////
    /// 
    /// Non blocking ---Asynchronous for web-flux reactive project////////////////////////////////////////////
    /// 
    
    ///get single
    public Mono<RestaurantDto> getByIdNon(String id){

        return webClient.get()
                        .uri("/api/v1/restaurants/{id}", id)
                        .retrieve()
                        .bodyToMono(RestaurantDto.class);
        
    }

    ///get all
    public Flux<RestaurantDto> getAllNon(){

        return webClient.get()
                        .uri("/api/v1/restaurants")
                        .retrieve()
                        .bodyToFlux(RestaurantDto.class);
        
    }




    

}
