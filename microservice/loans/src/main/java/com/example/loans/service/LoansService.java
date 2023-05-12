package com.example.loans.service;

import com.example.loans.model.Loans;

import java.util.List;

public interface LoansService {
    List<Loans> findByCustomerIdOrderByStartDateDesc(Long customerId);
}
