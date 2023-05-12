package com.example.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseTraceFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseTraceFilter.class);
    @Autowired
    FilterUtility filterUtility;
//    @Bean
//    public GlobalFilter postGlobalFilter(){
//        return ((exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
//            HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
//            String correlationId = filterUtility.getCorrelationId(requestHeaders);
//            LOGGER.debug("Updated the correlation id to the outbond headers. {}", correlationId);
//            exchange.getRequest().getHeaders().add(FilterUtility.CORRELATION_ID,correlationId);
//        })));
//    }
    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                String correlationId = filterUtility.getCorrelationId(requestHeaders);
                LOGGER.info("Updated the correlation id to the outbound headers. {}", correlationId);
                exchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, correlationId);
            }));
        };
    }
}
