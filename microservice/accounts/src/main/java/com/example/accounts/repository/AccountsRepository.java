package com.example.accounts.repository;

import com.example.accounts.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository  extends JpaRepository<Accounts, Long> {
    Accounts findByCustomerId(Long customerId);
}
