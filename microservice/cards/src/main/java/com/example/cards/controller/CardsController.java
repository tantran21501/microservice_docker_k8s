package com.example.cards.controller;

import com.example.cards.config.CardsServiceConfig;
import com.example.cards.model.Cards;
import com.example.cards.model.Customer;
import com.example.cards.model.Properties;
import com.example.cards.service.CardsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardsController {
    private final CardsService cardsService;

    private final CardsServiceConfig cardsServiceConfig;
    public CardsController(CardsService cardsService, CardsServiceConfig cardsServiceConfig) {
        this.cardsService = cardsService;
        this.cardsServiceConfig = cardsServiceConfig;
    }
    @PostMapping("/myCards")
    public List<Cards> getCardsDetail(@RequestHeader("bank-correlation-id")String correlationId,@RequestBody Customer customer) {
        List<Cards> cards = cardsService.findByCustomerId(customer.getCustomerId());
        return cards;
    }
    @GetMapping("cards/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(
                cardsServiceConfig.getMsg(),
                cardsServiceConfig.getBuildVersion(),
                cardsServiceConfig.getMailDetails(),
                cardsServiceConfig.getActiveBranches()
        );
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
