package com.example.accounts.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {
    private Accounts accounts;
    private List<Cards> cards;
    private List<Loans> loans;
}
