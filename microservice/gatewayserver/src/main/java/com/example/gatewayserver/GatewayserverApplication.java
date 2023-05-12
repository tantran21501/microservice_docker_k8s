package com.example.gatewayserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
//@EnableDiscoveryClient
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Autowired
    private TokenRelayGatewayFilterFactory filterFactory;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/tantran2/accounts/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .rewritePath("/tantran2/accounts/(?<segment>.*)", "/${segment}")
                                .removeRequestHeader("Cookie"))
                        .uri("lb://ACCOUNTS"))
                .route(p -> p
                        .path("/tantran2/loans/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .rewritePath("/tantran2/loans/(?<segment>.*)", "/${segment}")
                                .removeRequestHeader("Cookie"))
                        .uri("lb://LOANS"))
                .route(p -> p
                        .path("/tantran2/cards/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .rewritePath("/tantran2/cards/(?<segment>.*)", "/${segment}")
                                .removeRequestHeader("Cookie"))
                        .uri("lb://CARDS")).build();
//				.route(p -> p
//						.path("/tantran2/accounts/**")
//						.filters(f -> f.rewritePath("/tantran2/accounts/(?<segment>.*)","/${segment}")
//								.addResponseHeader("X-Response-Time",new Date().toString()))
//						.uri("lb://ACCOUNTS"))
//				.route(p -> p
//						.path("/tantran2/loans/**")
//						.filters(f -> f.rewritePath("/tantran2/loans/(?<segment>.*)","/${segment}")
//								.addResponseHeader("X-Response-Time",new Date().toString()))
//						.uri("lb://LOANS"))
//				.route(p -> p
//						.path("/tantran2/cards/**")
//						.filters(f -> f.rewritePath("/tantran2/cards/(?<segment>.*)","/${segment}")
//								.addResponseHeader("X-Response-Time",new Date().toString()))
//						.uri("lb://CARDS")).build();
    }

}
