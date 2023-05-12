package com.example.cards.service;

import com.example.cards.model.Cards;
import com.example.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardsServiceImpl  implements CardsService {
    @Autowired
    private final CardsRepository cardsRepository;

    public CardsServiceImpl(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    @Override
    public List<Cards> findByCustomerId(Long customerId) {
        return cardsRepository.findByCustomerId(customerId);
    }
}
