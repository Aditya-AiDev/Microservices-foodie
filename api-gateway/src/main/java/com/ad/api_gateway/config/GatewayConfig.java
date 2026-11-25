package com.ad.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class GatewayConfig {

    @Bean 
    public RouteLocator routes(RouteLocatorBuilder builder){

        return builder.routes().route(
                                        "food-service", //id
                                        route -> route.path("/foods/**") //predicate scope
                                                        .filters( f -> f.rewritePath("/foods/?(?<remaining>.*)", "/${remaining}"))
                                                        .uri("lb://food-service")
                                    )
                                .route(
                                    
                                        "restaurant-service", //id
                                        route -> route.path("/restaurants/**") //predicate scope
                                                        // .filters( f -> f.rewritePath("/restaurants/?(?<remaining>.*)", "/${remaining}").addRequestHeader("ExtraValue", "SAMOSA"))
                                                        .filters( f -> f.rewritePath("/restaurants/?(?<remaining>.*)", "/${remaining}"))
                                                        .uri("lb://restaurant-service")

                                )
                                .build();

    } 

}
