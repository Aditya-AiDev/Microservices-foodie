package com.foodie.food_service.services.externalRestClient;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.foodie.food_service.dto.RestaurantDto;


@Service 
public class RestaurantServiceRestClient {

    private RestClient restClient;

    @Autowired 
    public RestaurantServiceRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    ///get single 
    /// 

    public RestaurantDto getById(String id){

        RestaurantDto restaurantDto = restClient.get()
                                                .uri("/api/v1/restaurants/{id}", id)
                                                .retrieve()
                                                .body(RestaurantDto.class);
                                                

        return  restaurantDto;


    }

    //get all
    public List<RestaurantDto> getAll(){

        // return restClient.get()
        //                 .uri("/api/v1/restaurants")
        //                 .retrieve()
        //                 .body(new ParameterizedTypeReference <List<RestaurantDto>>(){});

        RestaurantDto[] restaurantDtoArray =restClient.get()
                        .uri("/api/v1/restaurants")
                        .retrieve()
                        // .body(new ParameterizedTypeReference <List<RestaurantDto>>(){});
                        .body(RestaurantDto[].class);

        return restaurantDtoArray != null ? Arrays.asList(restaurantDtoArray) : List.of();
    }


    /// post 
    /// 
    // restClient.post().uri("/api/v1/restaurants").body(restaurantDto).retrieve().body(RestaurantDto.class);

    public RestaurantDto create(RestaurantDto restaurantDto){

       return restClient.post()
                        .uri("/api/v1/restaurants")
                        .body(restaurantDto)
                        .retrieve()
                        .body(RestaurantDto.class);


    }

}
