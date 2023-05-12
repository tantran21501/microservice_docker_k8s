package com.example.cards.service;

import com.example.cards.model.Cards;

import java.util.List;

public interface CardsService {
    List<Cards> findByCustomerId(Long customerId);
}
