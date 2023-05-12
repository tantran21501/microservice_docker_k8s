package com.example.accounts.service;

import com.example.accounts.model.Accounts;
import org.springframework.stereotype.Service;

public interface AccountsService {
    Accounts findByCustomerId(Long customerId);
}
