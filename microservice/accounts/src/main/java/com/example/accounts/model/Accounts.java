package com.example.accounts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter@Setter@ToString
public class Accounts {
    @Id
    @Column(name="account_number")
    private Long accountNumber;
    @Column(name="customer_id")
    private int customerId;
    @Column(name="account_type")
    private String accountType;
    @Column(name="branch_address")
    private String branchAddress;
    @Column(name="create_date")
    private String createDate;

}
