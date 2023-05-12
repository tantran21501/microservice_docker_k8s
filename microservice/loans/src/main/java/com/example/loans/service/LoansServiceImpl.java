package com.example.loans.service;

import com.example.loans.model.Loans;
import com.example.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoansServiceImpl implements  LoansService{
    @Autowired
    private final LoansRepository loansRepository;

    public LoansServiceImpl(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }


    @Override
    public List<Loans> findByCustomerIdOrderByStartDateDesc(Long customerId) {
        return loansRepository.findByCustomerIdOrderByStartDateDesc(customerId);
    }
}
