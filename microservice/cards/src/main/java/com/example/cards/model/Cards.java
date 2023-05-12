package com.example.cards.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="card_id")
    private Long cardId;
    private String cardNumber;
    @Column(name="customer_id")
    private Long customerId;
    @Column(name="card_type")
    private String cardType;
    @Column(name="total_limit")
    private int totalLimit;
    @Column(name="amount_used")
    private int amountUsed;
    @Column(name="available_amount")
    private int availableAmount;
    @Column(name="create_date")
    private String createDate;


}
