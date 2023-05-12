package com.example.accounts.service;

import com.example.accounts.model.Accounts;
import com.example.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    private final AccountsRepository accountsRepository;

    public AccountsServiceImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public Accounts findByCustomerId(Long customerId) {
        return accountsRepository.findByCustomerId(customerId);
    }
}
